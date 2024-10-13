package com.hongtao.base.baseFramwork.selectoption.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.hongtao.base.baseFramwork.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典明细
 *
 * @TableName select_option_detail
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "select_option_detail")
@Data
public class SelectOptionDetail extends BaseEntity {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_ID, value = "select_option_detailId")
    private Long selectOptionDetailId;

    /**
     * 名称
     */
    @TableField(value = "label")
    private String label;

    /**
     * 值
     */
    @TableField(value = "value")
    private String value;

    @TableField(value = "select_optionId")
    private Long selectOptionId;
}