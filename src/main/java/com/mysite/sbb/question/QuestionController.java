package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/question")
// @RequiredArgsConstructor : final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할
@RequiredArgsConstructor
@Controller
public class QuestionController {

    // questionService 객체는 생성자 방식으로 DI 규칙에 의해 주입된다.
    private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    // @RequestMapping(value = "") : URL 매핑시 value 매개변수는 생략할수 있다.
    // @PathVariable : 변하는 id 값을 얻을 때 사용
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

}
