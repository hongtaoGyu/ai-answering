package com.hongtao.aianswering.app.controller;

import cn.hutool.json.JSONUtil;
import com.hongtao.aianswering.app.model.dto.AiGenerateQuestionRequest;
import com.hongtao.aianswering.app.model.dto.QuestionContentDTO;
import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.model.entity.Question;
import com.hongtao.aianswering.app.model.vo.QuestionVO;
import com.hongtao.aianswering.app.service.AppService;
import com.hongtao.aianswering.app.service.QuestionService;
import com.hongtao.base.baseFramwork.core.BaseController;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOptionDetail;
import com.hongtao.base.baseFramwork.selectoption.service.SelectOptionService;
import com.hongtao.base.common.BaseResponse;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.common.ResultUtils;
import com.hongtao.base.exception.ThrowUtils;
import com.hongtao.base.utils.StringUtils;
import com.hongtao.constant.AiConstant;
import com.hongtao.manager.AiManager;
import com.zhipu.oapi.service.v4.model.ModelData;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController extends BaseController<Question, QuestionVO, QuestionService> {

    @Resource
    private QuestionService questionService;

    @Resource
    private AppService appService;

    @Resource
    private AiManager aiManager;

    @Resource
    private SelectOptionService selectOptionService;

    private List<SelectOptionDetail> appTypeList;

    /**
     * AI 自动生成题目
     *
     * @param aiGenerateQuestionRequest
     * @return
     */
    @PostMapping("/ai_generate")
    public BaseResponse<List<QuestionContentDTO>> aiGenerateQuestion(@RequestBody AiGenerateQuestionRequest aiGenerateQuestionRequest) {
        ThrowUtils.throwIf(aiGenerateQuestionRequest == null, ErrorCode.PARAMS_ERROR);
        String jsonStr = questionService.aiGenerateQuestion(
                aiGenerateQuestionRequest.getAppId(),
                aiGenerateQuestionRequest.getQuestionNumber(),
                aiGenerateQuestionRequest.getOptionNumber()
        );
        log.info(jsonStr);
        jsonStr = jsonStr.replace("\\n", "");
        jsonStr = jsonStr.replace("\\", "");
        log.info(jsonStr);
        List<QuestionContentDTO> questionContentDTOList = JSONUtil.toList(jsonStr, QuestionContentDTO.class);
        return ResultUtils.success(questionContentDTOList);
    }

    /**
     * AI 自动生成题目（流式）
     *
     * @param aiGenerateQuestionRequest
     * @return
     */
    @GetMapping("/ai_generate/sse")
    public SseEmitter aiGenerateQuestionSSE(AiGenerateQuestionRequest aiGenerateQuestionRequest, HttpServletResponse response) {
        ThrowUtils.throwIf(aiGenerateQuestionRequest == null, ErrorCode.PARAMS_ERROR);
        App app = appService.getById(aiGenerateQuestionRequest.getAppId());
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);

        // 设置响应头，防止浏览器缓存
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("X-Accel-Buffering", "no");
        response.setHeader("Pragma", "no-cache");
        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
        response.setDateHeader("Expires", 0);
        response.setHeader("Content-Encoding", "identity");

        // 封装 Prompt，构造生成题目的用户消息
        String userMessage = getGenerateQuestionUserMessage(app, aiGenerateQuestionRequest.getQuestionNumber(), aiGenerateQuestionRequest.getOptionNumber());
        // 建立 SSE 连接对象，0 表示永不超时
        SseEmitter sseEmitter = new SseEmitter(0L);
        // AI 生成，使用 SSE 流式返回
        Flowable<ModelData> modelDataFlowable = aiManager.doStreamRequest(AiConstant.GENERATE_QUESTION_SYSTEM_MESSAGE, userMessage, null);
        // 括号计数器，除了默认值外，当回归为 0 时，意味着左括号等于右括号，可以截取
        AtomicInteger counter = new AtomicInteger(0);
        // 拼接完整题目
        StringBuffer stringBuffer = new StringBuffer();
        modelDataFlowable
                .observeOn(Schedulers.io()) // 指定线程池
                .map(modalData -> modalData.getChoices().get(0).getDelta().getContent()) // 取出返回的内容
                .map(message -> message.replace("\\s", "")) // 去除空格
                .filter(StringUtils::isNotEmpty) // 去除空串
                .flatMap(message -> {
                    List<Character> characterList = new ArrayList<>();
                    for (char c : message.toCharArray()) {
                        characterList.add(c);
                    }
                    return Flowable.fromIterable(characterList);
                }) // 分流，将字符串的流分解成单个字符的多个流
                .doOnNext(c -> {
                    // 首先应判断是否是“{”
                    // 如果是“{”，计数器 + 1
                    if (c == '{') {
                        counter.incrementAndGet();
                    }
                    // 此时应该判断计数器是否大于 0，若大于 0 则需要拼接
                    // 此处判断是为了过滤掉不在两个大括号范围之内的字符
                    if (counter.get() > 0) {
                        stringBuffer.append(c);
                    }
                    // 如果是“}”，计数器 - 1
                    if (c == '}') {
                        counter.decrementAndGet();
                        if (counter.get() == 0) {
                            // 可以拼接题目，并且通过 SSE 返回给前端
                            sseEmitter.send(JSONUtil.toJsonStr(stringBuffer.toString()));
                            // 清空 stringBuffer，以便于下次拼接
                            stringBuffer.setLength(0);
                            Thread.sleep(200);
                        }
                    }
                })
                .doOnError(err -> {
                    log.error("AI 生成题目 SSE 异常：", err);
                })
                .doOnComplete(sseEmitter::complete)
                .subscribe();

        return sseEmitter;
    }

    /**
     * 生成题目的用户消息
     *
     * @param app
     * @param questionNumber
     * @param optionNumber
     * @return
     */
    private String getGenerateQuestionUserMessage(App app, int questionNumber, int optionNumber) {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append(app.getAppName()).append("\n");
        userMessage.append(app.getAppDesc()).append("\n");

        // 获取应用类型选项集
        if (appTypeList == null || appTypeList.isEmpty()) {
            appTypeList = selectOptionService.getSelectOptionDataByName("select_app_type");
        }
        userMessage.append(selectOptionService.getSelectOptionDataByValue(app.getAppType().toString(), appTypeList)).append("类").append("\n");
        userMessage.append(questionNumber).append("\n");
        userMessage.append(optionNumber);
        return userMessage.toString();
    }
}
