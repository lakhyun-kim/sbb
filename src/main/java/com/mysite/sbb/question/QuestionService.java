package com.mysite.sbb.question;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
// @Service : 스프링부트의 서비스가 된다.
@Service
public class QuestionService {

    // questionRepository 객체는 생성자 방식으로 DI 규칙에 의해 주입된다.
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
}
