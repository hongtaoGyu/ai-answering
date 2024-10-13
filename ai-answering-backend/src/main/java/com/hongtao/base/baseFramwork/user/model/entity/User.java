package com.hongtao.base.baseFramwork.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户
 */
@TableName(value = "user")
@Data
public class User implements Serializable {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 开放平台id
     */
    private String unionId;

    /**
     * 公众号openId
     */
    private String mpOpenId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

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