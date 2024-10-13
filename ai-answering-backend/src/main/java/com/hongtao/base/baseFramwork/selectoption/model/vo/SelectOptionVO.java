package com.hongtao.base.baseFramwork.selectoption.model.vo;

import com.hongtao.base.baseFramwork.core.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SelectOptionVO extends BaseVO {
    private Long selectOptionId;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述名称
     */
    private String remarkName;

    /**
     * 描述
     */
    private String remark;
}
