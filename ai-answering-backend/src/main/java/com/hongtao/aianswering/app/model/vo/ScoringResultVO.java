package com.hongtao.aianswering.app.model.vo;

import cn.hutool.json.JSONUtil;
import com.hongtao.base.baseFramwork.core.BaseVO;
import com.hongtao.base.utils.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScoringResultVO extends BaseVO {
    /**
     * id
     */
    private Long scoringResultId;

    /**
     * 结果名称，如物流师
     */
    private String resultName;

    /**
     * 结果描述
     */
    private String resultDesc;

    /**
     * 结果图片
     */
    private String resultPicture;

    /**
     * 结果属性集合（前端接收）
     */
    private List<String> resultPropList;

    /**
     * 结果属性集合 JSON，如 [I,S,T,J]
     */
    private String resultProp;

    /**
     * 结果得分范围，如 80，表示 80及以上的分数命中此结果
     */
    private Integer resultScoreRange;

    /**
     * 应用 id
     */
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
}
