package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    // @Valid : QuestionForm의 @NotEmpty, @Size 등으로 설정한 검증 기능이 동작한다.
    // BindingResult : @Valid 애너테이션으로 인해 검증이 수행된 결과를 의미하는 객체이다.
    // BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치해야 한다.
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";   // 질문 저장 후 질문목록으로 이동
    }

}
