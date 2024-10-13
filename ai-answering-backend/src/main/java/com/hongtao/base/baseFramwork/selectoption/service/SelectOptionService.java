package com.hongtao.base.baseFramwork.selectoption.service;

import com.hongtao.base.baseFramwork.core.BaseService;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOption;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOptionDetail;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【select_option(字典)】的数据库操作Service
 * @createDate 2024-07-02 23:01:54
 */
public interface SelectOptionService extends BaseService<SelectOption> {
    /**
     * 根据选项集名称获取字典数据列表
     *
     * @param name 选项集名称
     * @return 选项集数据列表
     */
    List<SelectOptionDetail> getSelectOptionDataByName(String name);

    /**
     * 根据value检查选项集数据是否存在
     *
     * @param value                  选项集value
     * @param selectOptionDetailList 选项集数据列表
     * @return Boolean
     */
    boolean checkSelectOptionDataByValue(String value, List<SelectOptionDetail> selectOptionDetailList);

    /**
     * 根据value获取选项集数据
     *
     * @param value                  选项集value
     * @param selectOptionDetailList 选项集数据列表
     * @return 选项集数据
     */
    SelectOptionDetail getSelectOptionDataByValue(String value, List<SelectOptionDetail> selectOptionDetailList);
}
