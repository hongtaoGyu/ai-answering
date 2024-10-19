package com.hongtao.aianswering.app.service.impl;

import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.service.AppService;
import com.hongtao.base.baseFramwork.core.BaseServiceImpl;
import com.hongtao.aianswering.app.model.entity.Question;
import com.hongtao.aianswering.app.service.QuestionService;
import com.hongtao.aianswering.app.dao.QuestionMapper;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOptionDetail;
import com.hongtao.base.baseFramwork.selectoption.service.SelectOptionService;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.exception.ThrowUtils;
import com.hongtao.constant.AiConstant;
import com.hongtao.manager.AiManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
* @author Administrator
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2024-07-01 22:42:37
*/
@Service
public class QuestionServiceImpl extends BaseServiceImpl<QuestionMapper, Question> implements QuestionService{

    @Resource
    private AppService appService;

    @Resource
    private AiManager aiManager;

    @Resource
    private SelectOptionService selectOptionService;

    private List<SelectOptionDetail> appTypeList;

    @Override
    public String aiGenerateQuestion(Long appId, int questionNumber, int optionNumber) {
        App app = appService.getById(appId);
        ThrowUtils.throwIf(app == null, ErrorCode.NOT_FOUND_ERROR);
        // 封装 Prompt，构造生成题目的用户消息
        String userMessage = getGenerateQuestionUserMessage(app, questionNumber, optionNumber);
        // AI 生成
        String result = aiManager.doSyncRequest(AiConstant.GENERATE_QUESTION_SYSTEM_MESSAGE, userMessage, null);
        // 从AI接口返回的内容中截取需要的 JSON 信息
        int start = result.indexOf("[");
        int end = result.lastIndexOf("]");
        return result.substring(start, end + 1);
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




