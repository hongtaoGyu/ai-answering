package com.hongtao.aianswering.app.scoreStrategy;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Component
public @interface ScoringStrategyConfig {

    /**
     * 应用类型
     *
     * @return
     */
    int appType();

    /**
     * 评分策略
     *
     * @return
     */
    int scoringStrategy();
}
