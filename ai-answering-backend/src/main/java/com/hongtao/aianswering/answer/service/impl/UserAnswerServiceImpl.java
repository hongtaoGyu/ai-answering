package com.hongtao.aianswering.answer.service.impl;

import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.service.AppService;
import com.hongtao.base.baseFramwork.core.BaseServiceImpl;
import com.hongtao.aianswering.answer.model.entity.UserAnswer;
import com.hongtao.aianswering.answer.service.UserAnswerService;
import com.hongtao.aianswering.answer.dao.UserAnswerMapper;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.exception.ThrowUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @description 针对表【user_answer(用户答题记录)】的数据库操作Service实现
 * @createDate 2024-07-01 22:42:37
 */
@Service
public class UserAnswerServiceImpl extends BaseServiceImpl<UserAnswerMapper, UserAnswer> implements UserAnswerService {
    @Resource
    private AppService appService;

    /**
     * 校验数据
     *
     * @param userAnswer
     * @param add        对创建的数据进行校验
     */
    @Override
    public void validUserAnswer(UserAnswer userAnswer, boolean add) {
        ThrowUtils.throwIf(userAnswer == null, ErrorCode.PARAMS_ERROR);
        // 从对象中取值
        Long appId = userAnswer.getAppId();
        Long id = userAnswer.getId();
        // 创建数据时，参数不能为空
        if (add) {
            // 补充校验规则
            ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR, "appId 非法");
            ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR, "id 非法");
        }
        // 修改数据时，有参数则校验
        // 补充校验规则
        if (appId != null) {
            App app = appService.getById(appId);
            ThrowUtils.throwIf(app == null, ErrorCode.PARAMS_ERROR, "应用不存在");
        }
    }

}




