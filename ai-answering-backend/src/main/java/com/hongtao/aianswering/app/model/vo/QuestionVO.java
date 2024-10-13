package com.hongtao.aianswering.app.model.vo;

import cn.hutool.json.JSONUtil;
import com.hongtao.aianswering.app.model.dto.QuestionContentDTO;
import com.hongtao.base.baseFramwork.core.BaseVO;
import com.hongtao.base.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionVO extends BaseVO {
    /**
     * id
     */
    private Long questionId;

    /**
     * 题目内容（前端接收）
     */
    private QuestionContentDTO questionContentDTO;

    /**
     * 题目内容（json格式）
     */
    private String questionContent;

    /**
     * 应用 id
     */
    private Long appId;

    public String getQuestionContent() {
        if (StringUtils.isEmpty(questionContent)) {
            return JSONUtil.toJsonStr(questionContentDTO);
        }
        return questionContent;
    }

    public void setQuestionContentDTO(QuestionContentDTO questionContentDTO) {
        this.questionContentDTO = questionContentDTO;
        if (StringUtils.isEmpty(questionContent)) {
            this.questionContent = JSONUtil.toJsonStr(questionContentDTO);
        }
    }
}
