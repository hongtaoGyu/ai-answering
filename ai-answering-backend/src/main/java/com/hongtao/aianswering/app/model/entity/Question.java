package com.hongtao.aianswering.app.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.hongtao.base.baseFramwork.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 题目
 * @TableName question
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="question")
@Data
public class Question extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "questionId", type = IdType.ASSIGN_ID)
    private Long questionId;

    /**
     * 题目内容（json格式）
     */
    @TableField(value = "question_content")
    private String questionContent;

    /**
     * 应用 id
     */
    @TableField(value = "appId")
    private Long appId;

}