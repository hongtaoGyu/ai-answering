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
 * 字典
 * @TableName select_option
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="select_option")
@Data
public class SelectOption extends BaseEntity {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID, value = "select_optionId")
    private Long selectOptionId;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 描述名称
     */
    @TableField(value = "remark_name")
    private String remarkName;

    /**
     * 描述
     */
    @TableField(value = "remark")
    private String remark;
}