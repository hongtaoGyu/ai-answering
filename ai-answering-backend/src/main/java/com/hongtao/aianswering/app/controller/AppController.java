package com.hongtao.aianswering.app.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongtao.aianswering.app.model.dto.AppQueryRequest;
import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.model.vo.AppReviewVO;
import com.hongtao.aianswering.app.model.vo.AppUserVO;
import com.hongtao.aianswering.app.model.vo.AppVO;
import com.hongtao.aianswering.app.service.AppService;
import com.hongtao.base.annotation.AuthCheck;
import com.hongtao.base.baseFramwork.core.BaseController;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOptionDetail;
import com.hongtao.base.baseFramwork.selectoption.service.SelectOptionService;
import com.hongtao.base.baseFramwork.user.model.entity.User;
import com.hongtao.base.baseFramwork.user.service.UserService;
import com.hongtao.base.common.BaseResponse;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.common.ResultUtils;
import com.hongtao.base.constant.CommonConstant;
import com.hongtao.base.constant.UserConstant;
import com.hongtao.base.exception.BusinessException;
import com.hongtao.base.exception.ThrowUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class AppController extends BaseController<App, AppVO, AppService> {
    @Resource
    private SelectOptionService selectOptionService;

    @Resource
    private AppService appService;

    @Resource
    private UserService userService;

    private List<SelectOptionDetail> selectOptionDataList;

    @PostMapping("/review")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @SaCheckLogin
    public BaseResponse<Boolean> doAppReview(@RequestBody AppReviewVO appReviewVO, HttpServletRequest request) {
        ThrowUtils.throwIf(appReviewVO == null, ErrorCode.PARAMS_ERROR);
        Long id = appReviewVO.getAppId();
        if (selectOptionDataList == null || selectOptionDataList.isEmpty()) {
            selectOptionDataList = selectOptionService.getSelectOptionDataByName(CommonConstant.REVIEW_OPTION_NAME);
        }
        Optional<SelectOptionDetail> first = selectOptionDataList.stream().filter(item -> item.getValue().equals(appReviewVO.getReviewStatus().toString())).findFirst();
        ThrowUtils.throwIf(id == null || !first.isPresent() , ErrorCode.PARAMS_ERROR);
        // 判断应用是否存在
        App oldApp = appService.getById(id);
        ThrowUtils.throwIf(oldApp == null, ErrorCode.NOT_FOUND_ERROR);
        // 勿重复审核
        if (oldApp.getReviewStatus().equals(appReviewVO.getReviewStatus())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请勿重复提交审核");
        }

        // 更新审核状态
        User loginUser = userService.getLoginUser();
        oldApp.setReviewStatus(appReviewVO.getReviewStatus());
        oldApp.setReviewerId(loginUser.getUserId());
        oldApp.setReviewTime(new Date());
        oldApp.setReviewMessage(appReviewVO.getReviewMessage());
        appService.updateData(oldApp);
        return ResultUtils.success(true);
    }

    /**
     * 分页获取应用列表（封装类）
     *
     * @param appQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<AppUserVO>> listAppVOByPage(@RequestBody AppQueryRequest appQueryRequest,
                                                         HttpServletRequest request) {
        long current = appQueryRequest.getPageIndex();
        long size = appQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        // 只能看到已过审的应用
        appQueryRequest.setReviewStatus(1);
        // 查询数据库
        Page<App> appPage = appService.page(new Page<>(current, size),
                appService.getQueryWrapper(appQueryRequest));
        // 获取封装类
        return ResultUtils.success(appService.getAppVOPage(appPage, request));
    }
}
