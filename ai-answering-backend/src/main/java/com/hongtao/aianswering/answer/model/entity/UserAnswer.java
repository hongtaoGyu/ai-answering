package com.hongtao.aianswering.answer.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.hongtao.base.baseFramwork.core.BaseEntity;
import lombok.*;

/**
 * 用户答题记录
 *
 * @TableName user_answer
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_answer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer extends BaseEntity {
    /**
     *
     */
    @TableId(value = "user_answerId", type = IdType.ASSIGN_ID)
    private Long userAnswerId;

    /**
     * 应用 id
     */
    @TableField(value = "appId")
    private Long appId;

    /**
     * 应用类型（0-得分类，1-角色测评类）
     */
    @TableField(value = "app_type")
    private Integer appType;

    /**
     * 评分策略（0-自定义，1-AI）
     */
    @TableField(value = "scoring_strategy")
    private Integer scoringStrategy;

    /**
     * 用户答案（JSON 数组）
     */
    @TableField(value = "choices")
    private String choices;

    /**
     * 评分结果 id
     */
    @TableField(value = "resultId")
    private Long resultId;

    /**
     * 结果名称，如物流师
     */
    @TableField(value = "resultName")
    private String resultName;

    /**
     * 结果描述
     */
    @TableField(value = "result_desc")
    private String resultDesc;

    /**
     * 结果图标
     */
    @TableField(value = "result_picture")
    private String resultPicture;

    /**
     * 得分
     */
    @TableField(value = "result_score")
    private Integer resultScore;
}