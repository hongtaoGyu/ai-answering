package com.hongtao.base.baseFramwork.core;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hongtao.base.utils.ReflectUtils;
import com.hongtao.base.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Long id;

    @TableField(exist = false)
    private String tableName;

    /**
     * 创建用户 id
     */
    @TableField(value = "createUserId")
    private Long createUserId;

    /**
     * 创建用户名
     */
    @TableField(value = "createUserName")
    private String createUserName;
    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新用户 id
     */
    @TableField(value = "updateUserId")
    private Long updateUserId;

    /**
     * 更新用户名
     */
    @TableField(value = "updateUserName")
    private String updateUserName;
    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 状态码
     */
    @TableField(value = "stateCode")
    private String stateCode;

    public Long getId() {
        Class<? extends BaseEntity> aClass = this.getClass();
        String tableName = getTableName();
        Object fieldValue = ReflectUtils.getFieldValue(this, StringUtils.toCamelCase(tableName) + "Id");
        if (fieldValue != null) {
            id = (Long) fieldValue;
        }

        return id;
    }

    public String getTableName() {
        Class<? extends BaseEntity> aClass = this.getClass();
        String value = "";
        for (Annotation item : aClass.getAnnotations()) {
            if (item.annotationType().equals(TableName.class)) {
                TableName tableName = (TableName) item;
                value = tableName.value();
                break;
            }
        }

        return value;
    }
}
