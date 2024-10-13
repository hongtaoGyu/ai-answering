package com.hongtao.base.baseFramwork;

import com.hongtao.base.baseFramwork.user.model.entity.User;

public class TestMain {
    public static void main(String[] args) {
        User user = new User();
        user.setUserId(123456L);
        System.out.println(user.getUserId());
    }
}
