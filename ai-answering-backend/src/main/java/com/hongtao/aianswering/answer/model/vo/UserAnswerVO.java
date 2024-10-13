package com.hongtao.aianswering.answer.model.vo;

import cn.hutool.json.JSONUtil;
import com.hongtao.base.baseFramwork.core.BaseVO;
import com.hongtao.base.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserAnswerVO extends BaseVO {
    /**
     *
     */
    private Long userAnswerId;

    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 应用类型（0-得分类，1-角色测评类）
     */
    private Integer appType;

    /**
     * 评分策略（0-自定义，1-AI）
     */
    private Integer scoringStrategy;

    /**
     * 用户答案（前端接收）
     */
    private List<String> choicesList;

    /**
     * 用户答案（JSON 数组）
     */
    private String choices;

    /**
     * 评分结果 id
     */
    private Long resultId;

    /**
     * 结果名称，如物流师
     */
    private String resultName;

    /**
     * 结果描述
     */
    private String resultDesc;

    /**
     * 结果图标
     */
    private String resultPicture;

    /**
     * 得分
     */
    private Integer resultScore;

    public String getChoices() {
        if (StringUtils.isEmpty(choices)) {
            return JSONUtil.toJsonStr(choicesList);
        }
        return choices;
    }

    public void setChoicesList(List<String> choicesList) {
        this.choicesList = choicesList;
        if (StringUtils.isEmpty(choices)) {
            this.choices = JSONUtil.toJsonStr(choicesList);
        }
    }
}
