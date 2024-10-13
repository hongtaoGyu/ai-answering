package com.hongtao.aianswering.answer.service;

import com.hongtao.base.baseFramwork.core.BaseService;
import com.hongtao.aianswering.answer.model.entity.UserAnswer;

/**
 * @author Administrator
 * @description 针对表【user_answer(用户答题记录)】的数据库操作Service
 * @createDate 2024-07-01 22:42:37
 */
public interface UserAnswerService extends BaseService<UserAnswer> {
    void validUserAnswer(UserAnswer userAnswer, boolean add);

}
