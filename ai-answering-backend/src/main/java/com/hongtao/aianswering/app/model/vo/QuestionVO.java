package com.hongtao.aianswering.app.model.vo;

import cn.hutool.json.JSONUtil;
import com.hongtao.aianswering.app.model.dto.QuestionContentDTO;
import com.hongtao.base.baseFramwork.core.BaseVO;
import com.hongtao.base.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
    private List<QuestionContentDTO> questionContentDTOList;

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
            return JSONUtil.toJsonStr(questionContentDTOList);
        }
        return questionContent;
    }

    public void setQuestionContentDTOList(List<QuestionContentDTO> questionContentDTOList) {
        this.questionContentDTOList = questionContentDTOList;
        if (StringUtils.isEmpty(questionContent)) {
            this.questionContent = JSONUtil.toJsonStr(questionContentDTOList);
        }
    }

    public List<QuestionContentDTO> getQuestionContentDTOList() {
        if (questionContentDTOList == null || questionContentDTOList.isEmpty()) {
            return JSONUtil.toList(questionContent, QuestionContentDTO.class);
        }
        return questionContentDTOList;
    }
}
