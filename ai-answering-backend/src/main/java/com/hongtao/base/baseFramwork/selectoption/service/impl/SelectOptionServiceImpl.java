package com.hongtao.base.baseFramwork.selectoption.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hongtao.base.baseFramwork.core.BaseServiceImpl;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOption;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOptionDetail;
import com.hongtao.base.baseFramwork.selectoption.service.SelectOptionDetailService;
import com.hongtao.base.baseFramwork.selectoption.service.SelectOptionService;
import com.hongtao.base.baseFramwork.selectoption.dao.SelectOptionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 * @description 针对表【select_option(字典)】的数据库操作Service实现
 * @createDate 2024-07-02 23:01:54
 */
@Service
public class SelectOptionServiceImpl extends BaseServiceImpl<SelectOptionMapper, SelectOption> implements SelectOptionService {

    @Resource
    private SelectOptionDetailService selectOptionDetailService;

    @Override
    public List<SelectOptionDetail> getSelectOptionDataByName(String name) {
        // 查询选项集主表
        // 构造查询条件，name
        QueryWrapper<SelectOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        SelectOption selectOption = getOne(queryWrapper);
        // 根据选项集主表主键id，去选项集明细表查询数据
        // 构造查询条件，select_optionId
        Long selectOptionId = selectOption.getSelectOptionId();
        QueryWrapper<SelectOptionDetail> detailQueryWrapper = new QueryWrapper<>();
        detailQueryWrapper.eq("select_optionId", selectOptionId);
        // 返回选项集明细列表
        return selectOptionDetailService.list(detailQueryWrapper);
    }

    public boolean checkSelectOptionDataByValue(String value, List<SelectOptionDetail> selectOptionDetailList) {
        Optional<SelectOptionDetail> first = selectOptionDetailList.stream().filter(item -> item.getValue().equals(value)).findFirst();
        return first.isPresent();
    }

    @Override
    public SelectOptionDetail getSelectOptionDataByValue(String value, List<SelectOptionDetail> selectOptionDetailList) {
        Optional<SelectOptionDetail> first = selectOptionDetailList.stream().filter(item -> item.getValue().equals(value)).findFirst();
        return first.orElse(null);
    }
}




