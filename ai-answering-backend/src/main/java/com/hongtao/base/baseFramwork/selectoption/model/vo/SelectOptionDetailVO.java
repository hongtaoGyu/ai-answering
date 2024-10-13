package com.hongtao.base.baseFramwork.selectoption.model.vo;

import com.hongtao.base.baseFramwork.core.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SelectOptionDetailVO extends BaseVO {
    private Long selectOptionDetailId;

    /**
     * 名称
     */
    private String label;

    /**
     * 值
     */
    private String value;

    private Long selectOptionId;
}
