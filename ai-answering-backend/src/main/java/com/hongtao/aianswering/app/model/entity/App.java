package com.hongtao.aianswering.app.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.hongtao.base.baseFramwork.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用
 * @TableName app
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="app")
@Data
public class App extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "appId", type = IdType.ASSIGN_ID)
    private Long appId;

    /**
     * 应用名
     */
    @TableField(value = "app_name")
    private String appName;

    /**
     * 应用描述
     */
    @TableField(value = "app_desc")
    private String appDesc;

    /**
     * 应用图标
     */
    @TableField(value = "app_icon")
    private String appIcon;

    /**
     * 应用类型（0-得分类，1-测评类）
     */
    @TableField(value = "app_type")
    private Integer appType;

    /**
     * 评分策略（0-自定义，1-AI）
     */
    @TableField(value = "scoring_strategy")
    private Integer scoringStrategy;

    /**
     * 审核状态：0-待审核, 1-通过, 2-拒绝
     */
    @TableField(value = "review_status")
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    @TableField(value = "review_message")
    private String reviewMessage;

    /**
     * 审核人 id
     */
    @TableField(value = "reviewerId")
    private Long reviewerId;

    /**
     * 审核时间
     */
    @TableField(value = "review_time")
    private Date reviewTime;
}