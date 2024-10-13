package com.hongtao.aianswering.answer.plugin;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONUtil;
import com.hongtao.aianswering.answer.model.entity.UserAnswer;
import com.hongtao.aianswering.answer.service.UserAnswerService;
import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.scoreStrategy.ScoringStrategyExecutor;
import com.hongtao.aianswering.app.service.AppService;
import com.hongtao.base.baseFramwork.core.DataPlugin;
import com.hongtao.base.baseFramwork.core.constants.PluginConstant;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOptionDetail;
import com.hongtao.base.baseFramwork.selectoption.service.SelectOptionService;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.constant.CommonConstant;
import com.hongtao.base.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class UserAnswerPlugin implements DataPlugin<UserAnswer> {

    @Resource
    private UserAnswerService userAnswerService;

    @Resource
    private ScoringStrategyExecutor scoringStrategyExecutor;

    @Resource
    private AppService appService;

    @Resource
    private SelectOptionService selectOptionService;

    private List<SelectOptionDetail> selectOptionDataList;

    @Override

    public void execute(String executeMode, UserAnswer userAnswer) {
        Long appId = userAnswer.getAppId();
        App app = appService.getById(appId);
        switch (executeMode) {
            case PluginConstant.PRE_CREATE:
                // 数据校验
//                userAnswerService.validUserAnswer(userAnswer, true);
                if (app.getReviewStatus() != 1) {
                    throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "应用未通过审核，无法答题");
                }
                break;
            case PluginConstant.CREATED:
                // 调用评分模块
                log.info("CREATED userAnswer:{}", userAnswer);


                if (app != null) {
                    try {
                        UserAnswer newUserAnswer = scoringStrategyExecutor.doScore(JSONUtil.toList(userAnswer.getChoices(), String.class), app);
                        newUserAnswer.setUserAnswerId(userAnswer.getUserAnswerId());
                        userAnswer = newUserAnswer;
                        userAnswerService.updateById(userAnswer);
                    } catch (Exception e) {
                        throw new BusinessException(ErrorCode.OPERATION_ERROR, "评分错误");
                    }
                }

                break;
            default:
                break;
        }
    }
}
