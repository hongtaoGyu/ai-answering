package com.hongtao.aianswering.answer.controller;

import cn.hutool.core.bean.BeanUtil;
import com.hongtao.aianswering.answer.model.vo.UserAnswerVO;
import com.hongtao.aianswering.answer.model.vo.UserAnswerWithUserVO;
import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.model.vo.AppUserVO;
import com.hongtao.base.baseFramwork.core.BaseController;
import com.hongtao.aianswering.answer.model.entity.UserAnswer;
import com.hongtao.aianswering.answer.service.UserAnswerService;
import com.hongtao.base.baseFramwork.user.model.entity.User;
import com.hongtao.base.baseFramwork.user.service.UserService;
import com.hongtao.base.common.BaseResponse;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.common.ResultUtils;
import com.hongtao.base.exception.ThrowUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userAnswer")
public class UserAnswerController extends BaseController<UserAnswer, UserAnswerVO, UserAnswerService> {
    @Resource
    private UserAnswerService userAnswerService;

    @Resource
    private UserService userService;

    @GetMapping("/getUserAnswerWithUser")
    public BaseResponse<UserAnswerWithUserVO> getUserAnswerWithUser(@RequestParam Long id) {
        ThrowUtils.throwIf(id == null, ErrorCode.PARAMS_ERROR);
        UserAnswerWithUserVO userAnswerWithUserVO = new UserAnswerWithUserVO();
        UserAnswer userAnswer = userAnswerService.getData(id);
        ThrowUtils.throwIf(userAnswer == null, ErrorCode.NOT_FOUND_ERROR);
        BeanUtil.copyProperties(userAnswer, userAnswerWithUserVO);
        User user = userService.getById(userAnswer.getCreateUserId());
        if (user != null) {
            userAnswerWithUserVO.setUser(userService.getUserVO(user));
        }

        return ResultUtils.success(userAnswerWithUserVO);
    }

}
