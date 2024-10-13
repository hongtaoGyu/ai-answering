package com.hongtao.base.baseFramwork.selectoption.controller;

import com.hongtao.base.baseFramwork.core.BaseController;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOption;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOptionDetail;
import com.hongtao.base.baseFramwork.selectoption.model.vo.SelectOptionVO;
import com.hongtao.base.baseFramwork.selectoption.service.SelectOptionService;
import com.hongtao.base.common.BaseResponse;
import com.hongtao.base.common.ErrorCode;
import com.hongtao.base.common.ResultUtils;
import com.hongtao.base.exception.BusinessException;
import com.hongtao.base.exception.ThrowUtils;
import com.hongtao.base.utils.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/selectOption")
public class SelectOptionController extends BaseController<SelectOption, SelectOptionVO, SelectOptionService> {
    @Resource
    private SelectOptionService selectOptionService;

    @GetMapping("/getSelectOptionDataByName")
    public BaseResponse<List<SelectOptionDetail>> getSelectOptionDataByName(@RequestParam String name) {
        ThrowUtils.throwIf(StringUtils.isEmpty(name), new BusinessException(ErrorCode.PARAMS_ERROR, "id为空"));
        List<SelectOptionDetail> selectOptionDetailList = selectOptionService.getSelectOptionDataByName(name);
        return ResultUtils.success(selectOptionDetailList);
    }
}
