package com.hongtao.aianswering.app.scoreStrategy;

import com.hongtao.aianswering.answer.model.entity.UserAnswer;
import com.hongtao.aianswering.app.model.entity.App;

import java.util.List;

/**
 * 评分策略
 */
public interface ScoringStrategy {
    /**
     * 评分
     *
     * @param choices 选项
     * @param app 应用
     * @return
     */
    UserAnswer doScore(List<String> choices, App app);
}
