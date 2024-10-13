package com.hongtao.base.baseFramwork.core;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建用户 id
     */
    private Long createUserId;

    /**
     * 创建用户名
     */
    private String createUserName;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新用户 id
     */
    private Long updateUserId;

    /**
     * 更新用户名
     */
    private String updateUserName;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态码
     */
    private String stateCode;

}
