package com.hongtao.aianswering.app.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * AI 生成题目请求
 *
 */
@Data
public class AiGenerateQuestionRequest implements Serializable {

    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 题目数
     */
    private int questionNumber = 10;

    /**
     * 选项数
     */
    private int optionNumber = 2;

    private static final long serialVersionUID = 1L;
}
