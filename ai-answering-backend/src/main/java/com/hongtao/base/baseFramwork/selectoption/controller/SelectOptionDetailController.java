package com.hongtao.base.baseFramwork.selectoption.controller;

import com.hongtao.base.baseFramwork.core.BaseController;
import com.hongtao.base.baseFramwork.selectoption.model.entity.SelectOptionDetail;
import com.hongtao.base.baseFramwork.selectoption.model.vo.SelectOptionDetailVO;
import com.hongtao.base.baseFramwork.selectoption.service.SelectOptionDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/selectOptionDetail")
public class SelectOptionDetailController extends BaseController<SelectOptionDetail, SelectOptionDetailVO, SelectOptionDetailService> {
}
