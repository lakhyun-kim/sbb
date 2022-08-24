package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

// @RequiredArgsConstructor : final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할
@RequiredArgsConstructor
@Controller
public class QuestionController {

    // questionService 객체는 생성자 방식으로 DI 규칙에 의해 주입된다.
    private final QuestionService questionService;

    @RequestMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
