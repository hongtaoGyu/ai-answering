package com.hongtao.aianswering.app.model.entity;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hongtao.base.baseFramwork.core.BaseEntity;
import com.hongtao.base.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评分结果
 * @TableName scoring_result
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="scoring_result")
@Data
public class ScoringResult extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "scoring_resultId", type = IdType.ASSIGN_ID)
    private Long scoringResultId;

    /**
     * 结果名称，如物流师
     */
    @TableField(value = "result_name")
    private String resultName;

    /**
     * 结果描述
     */
    @TableField(value = "result_desc")
    private String resultDesc;

    /**
     * 结果图片
     */
    @TableField(value = "result_picture")
    private String resultPicture;

    /**
     * 结果属性集合 JSON，如 [I,S,T,J]
     */
    @TableField(value = "result_prop")
    private String resultProp;

    @TableField(exist = false)
    private List<String> resultPropList;

    /**
     * 结果得分范围，如 80，表示 80及以上的分数命中此结果
     */
    @TableField(value = "result_score_range")
    private Integer resultScoreRange;

    /**
     * 应用 id
     */
    @TableField(value = "appId")
    private Long appId;

    public String getResultProp() {
        if (StringUtils.isEmpty(resultProp)) {
            return JSONUtil.toJsonStr(resultPropList);
        }
        return resultProp;
    }

    public void setResultPropList(List<String> resultPropList) {
        this.resultPropList = resultPropList;
        if (StringUtils.isEmpty(resultProp)) {
            this.resultProp = JSONUtil.toJsonStr(resultPropList);
        }
    }

    public void setResultProp(String resultProp) {
        this.resultProp = resultProp;
        if (resultPropList == null || resultProp.isEmpty()) {
            this.resultPropList = JSONUtil.parse(resultProp).toBean(List.class);
        }
    }
}