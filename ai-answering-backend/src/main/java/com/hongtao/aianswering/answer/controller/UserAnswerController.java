package com.hongtao.aianswering.answer.controller;

import com.hongtao.aianswering.answer.model.vo.UserAnswerVO;
import com.hongtao.base.baseFramwork.core.BaseController;
import com.hongtao.aianswering.answer.model.entity.UserAnswer;
import com.hongtao.aianswering.answer.service.UserAnswerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAnswer")
public class UserAnswerController extends BaseController<UserAnswer, UserAnswerVO, UserAnswerService> {

}
