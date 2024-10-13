package com.hongtao.aianswering.app.controller;

import com.hongtao.aianswering.app.model.entity.ScoringResult;
import com.hongtao.aianswering.app.model.vo.ScoringResultVO;
import com.hongtao.aianswering.app.service.ScoringResultService;
import com.hongtao.base.baseFramwork.core.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scoringResult")
public class ScoringResultController extends BaseController<ScoringResult, ScoringResultVO, ScoringResultService> {
}
