package com.hongtao.aianswering.app.service;

import com.hongtao.base.baseFramwork.core.BaseService;
import com.hongtao.aianswering.app.model.entity.Question;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
* @author Administrator
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-07-01 22:42:37
*/
public interface QuestionService extends BaseService<Question> {
    /**
     * AI 生成题目
     *
     * @param appId
     * @param questionNumber
     * @param optionNumber
     * @return
     */
    public String aiGenerateQuestion(Long appId, int questionNumber, int optionNumber);
}
