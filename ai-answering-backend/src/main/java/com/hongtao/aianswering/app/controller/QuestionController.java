package com.hongtao.aianswering.app.controller;

import com.hongtao.aianswering.app.model.entity.Question;
import com.hongtao.aianswering.app.model.vo.QuestionVO;
import com.hongtao.aianswering.app.service.QuestionService;
import com.hongtao.base.baseFramwork.core.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController extends BaseController<Question, QuestionVO, QuestionService> {
}
