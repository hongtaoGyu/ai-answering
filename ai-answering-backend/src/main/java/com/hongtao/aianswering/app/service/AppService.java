package com.hongtao.aianswering.app.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtao.aianswering.app.model.dto.AppQueryRequest;
import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.model.vo.AppUserVO;
import com.hongtao.base.baseFramwork.core.BaseService;

import javax.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【app(应用)】的数据库操作Service
* @createDate 2024-07-01 22:42:37
*/
public interface AppService extends BaseService<App> {
    public Page<AppUserVO> getAppVOPage(Page<App> appPage, HttpServletRequest request);

    public QueryWrapper<App> getQueryWrapper(AppQueryRequest appQueryRequest);
}
