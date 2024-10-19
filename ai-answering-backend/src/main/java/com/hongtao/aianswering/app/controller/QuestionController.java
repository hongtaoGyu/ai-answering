package com.hongtao.aianswering.app.controller;

import cn.hutool.json.JSONUtil;
import com.hongtao.aianswering.app.model.dto.AiGenerateQuestionRequest;
import com.hongtao.aianswering.app.model.dto.QuestionContentDTO;
import com.hongtao.aianswering.app.model.entity.Question;
import com.hongtao.aianswering.app.model.vo.QuestionVO;
import com.hongtao.aianswering.app.service.QuestionService;
import com.hongtao.base.baseFramwork.core.BaseController;
import com.hongtao.base.common.BaseResponse;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.common.ResultUtils;
import com.hongtao.base.exception.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController extends BaseController<Question, QuestionVO, QuestionService> {

    @Resource
    private QuestionService questionService;

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
}
