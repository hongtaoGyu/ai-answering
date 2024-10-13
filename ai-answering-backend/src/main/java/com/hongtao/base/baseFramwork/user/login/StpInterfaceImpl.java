package com.hongtao.base.baseFramwork.user.login;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.hongtao.base.baseFramwork.user.model.entity.User;
import com.hongtao.base.baseFramwork.user.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.hongtao.base.constant.UserConstant.USER_LOGIN_STATE;

@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserService userService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        User currentUser = null;
        Object sessionObj = StpUtil.getSession().get(USER_LOGIN_STATE);
        if (sessionObj != null) {
            currentUser = (User) sessionObj;
        }
        ArrayList<String> list = new ArrayList<>();
        if (currentUser != null) {
            list.add(currentUser.getUserRole());
        }
        return list;
    }
}
