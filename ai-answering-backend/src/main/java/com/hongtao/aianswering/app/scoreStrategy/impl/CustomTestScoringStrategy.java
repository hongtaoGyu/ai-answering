package com.hongtao.aianswering.app.scoreStrategy.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hongtao.aianswering.answer.model.entity.UserAnswer;
import com.hongtao.aianswering.app.model.dto.QuestionContentDTO;
import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.model.entity.Question;
import com.hongtao.aianswering.app.model.entity.ScoringResult;
import com.hongtao.aianswering.app.scoreStrategy.ScoringStrategyConfig;
import com.hongtao.aianswering.app.service.AppService;
import com.hongtao.aianswering.app.service.QuestionService;
import com.hongtao.aianswering.app.service.ScoringResultService;
import com.hongtao.aianswering.app.scoreStrategy.ScoringStrategy;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义测评类应用评分策略
 */
@ScoringStrategyConfig(appType = 1, scoringStrategy = 0)
public class CustomTestScoringStrategy implements ScoringStrategy {

    @Resource
    private QuestionService questionService;

    @Resource
    private AppService appService;

    @Resource
    private ScoringResultService scoringResultService;

    @Override
    public UserAnswer doScore(List<String> choices, App app) {
        Long appId = app.getAppId();
        // 1.根据应用id查询题目和题目结果信息。
        Question question = questionService.getOne(Wrappers.lambdaQuery(Question.class).eq(Question::getAppId, appId));

        // 根据appid获取应用答案结果列表。
        List<ScoringResult> scoringResultList = scoringResultService.list(Wrappers.lambdaQuery(ScoringResult.class).eq(ScoringResult::getAppId, appId));

        // 2.统计用户每个选择对应的属性个数。
        // 初始化一个Map，用于存储每个选项的计数。
        Map<String, Integer> optionCount = new HashMap<>();

        List<QuestionContentDTO> questionContent = JSONUtil.toList(question.getQuestionContent(), QuestionContentDTO.class);

        // 遍历题目列表
        for (QuestionContentDTO questionContentDTO : questionContent) {
            // 遍历答案列表
            for (String answer : choices) {
                // 遍历题目中的选项
                for (QuestionContentDTO.Option option : questionContentDTO.getOptions()) {
                    // 如果答案和选项的key匹配
                    if (option.getKey().equals(answer)) {
                        // 获取选项的result属性
                        String result = option.getResult();

                        // 如果result属性不在optionCount中，初始化为0
                        if (!optionCount.containsKey(result)) {
                            optionCount.put(result, 0);
                        }

                        // 如果在optionCount中存在此result属性，在optionCount中增加计数
                        optionCount.put(result, optionCount.get(result) + 1);
                    }

                }
            }
        }

        // 3.遍历每种评分结果，计算那个结果的得分更高。
        // 初始化最高分数和最高分数对相应的评分结果
        int maxScore = 0;
        // 默认先取第一个
        ScoringResult maxScoringResult = scoringResultList.get(0);

        // 遍历评分结果列表
        for (ScoringResult scoringResult : scoringResultList) {
            List<String> resultProp = JSONUtil.toList(scoringResult.getResultProp(), String.class);
            // 计算当前评分结果的分数
            int score = resultProp.stream().mapToInt(prop -> optionCount.getOrDefault(prop, 0)).sum();

            // 如果分数高于当前最高分数，更新最高分数和最高分数对应的评分结果
            if (score > maxScore) {
                maxScore = score;
                maxScoringResult = scoringResult;
            }
        }

        // 4.构造返回值，填充答案对象的属性。
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUserAnswerId(appId);
        userAnswer.setAppId(app.getAppId());
        userAnswer.setAppType(app.getAppType());
        userAnswer.setScoringStrategy(app.getScoringStrategy());
        userAnswer.setChoices(JSONUtil.toJsonStr(choices));
        userAnswer.setResultId(maxScoringResult.getScoringResultId());
        userAnswer.setResultName(maxScoringResult.getResultName());
        userAnswer.setResultDesc(maxScoringResult.getResultDesc());
        userAnswer.setResultPicture(maxScoringResult.getResultPicture());

        return userAnswer;
    }
}
