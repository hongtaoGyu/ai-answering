package com.hongtao.aianswering.app.scoreStrategy.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hongtao.aianswering.answer.model.dto.QuestionAnswerDTO;
import com.hongtao.aianswering.answer.model.entity.UserAnswer;
import com.hongtao.aianswering.app.model.dto.QuestionContentDTO;
import com.hongtao.aianswering.app.model.entity.App;
import com.hongtao.aianswering.app.model.entity.Question;
import com.hongtao.aianswering.app.model.vo.QuestionVO;
import com.hongtao.aianswering.app.scoreStrategy.ScoringStrategy;
import com.hongtao.aianswering.app.scoreStrategy.ScoringStrategyConfig;
import com.hongtao.aianswering.app.service.QuestionService;
import com.hongtao.constant.AiConstant;
import com.hongtao.manager.AiManager;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * AI 测评类应用评分策略
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://www.code-nav.cn">编程导航学习圈</a>
 */
@ScoringStrategyConfig(appType = 1, scoringStrategy = 1)
public class AiTestScoringStrategy implements ScoringStrategy {

    @Resource
    private QuestionService questionService;

    @Resource
    private AiManager aiManager;

    @Override
    public UserAnswer doScore(List<String> choices, App app) {
        Long appId = app.getAppId();
        // 1. 根据 id 查询到题目
        Question question = questionService.getOne(
                Wrappers.lambdaQuery(Question.class).eq(Question::getAppId, appId)
        );
        QuestionVO questionVO = new QuestionVO();
        BeanUtil.copyProperties(question, questionVO);
        List<QuestionContentDTO> questionContent = questionVO.getQuestionContentDTOList();

        // 2. 调用 AI 获取结果
        // 封装 Prompt
        String userMessage = getAiTestScoringUserMessage(app, questionContent, choices);
        // AI 生成
        String result = aiManager.doSyncStableRequest(AiConstant.AI_TEST_SCORING_SYSTEM_MESSAGE, userMessage);
        // 截取需要的 JSON 信息
        int start = result.indexOf("{");
        int end = result.lastIndexOf("}");
        String json = result.substring(start, end + 1);

        // 3. 构造返回值，填充答案对象的属性
        UserAnswer userAnswer = JSONUtil.toBean(json, UserAnswer.class);
        userAnswer.setAppId(appId);
        userAnswer.setAppType(app.getAppType());
        userAnswer.setScoringStrategy(app.getScoringStrategy());
        userAnswer.setChoices(JSONUtil.toJsonStr(choices));
        return userAnswer;
    }

    /**
     * AI 评分用户消息封装
     *
     * @param app
     * @param questionContentDTOList
     * @param choices
     * @return
     */
    private String getAiTestScoringUserMessage(App app, List<QuestionContentDTO> questionContentDTOList, List<String> choices) {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append(app.getAppName()).append("\n");
        userMessage.append(app.getAppDesc()).append("\n");
        List<QuestionAnswerDTO> questionAnswerDTOList = new ArrayList<>();
        for (int i = 0; i < questionContentDTOList.size(); i++) {
            QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO();
            questionAnswerDTO.setTitle(questionContentDTOList.get(i).getTitle());
            questionAnswerDTO.setUserAnswer(choices.get(i));
            questionAnswerDTOList.add(questionAnswerDTO);
        }
        userMessage.append(JSONUtil.toJsonStr(questionAnswerDTOList));
        return userMessage.toString();
    }

}
