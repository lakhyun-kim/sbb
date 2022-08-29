package com.mysite.sbb;

import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest : 스프링부트 테스트 클래스임을 의미
@SpringBootTest
class SbbApplicationTests {

    // @Autowired : 스프링의 DI 기능으로 questionRepository 객체를 스프링이 자동으로 생성해 준다.
    // DI : 스프링이 객체를 대신 생성하여 주입한다.
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;

    // @Transactional : 메서드가 종료될 때까지 DB 세션이 유지된다.
    //@Transactional // 주의사항 : 해당 어노테이션을 붙이면 DB 세션은 유지되나 rollback 됨
    @Test
    void testJpa() {
        /**
         * 1. 데이터 저장하기
         */
        /*
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);   // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);   // 두번째 질문 저장
        */
        
        /**
         * 2. 데이터 조회하기
         * findAll() : 모든 데이터 조회
         * findById() : id로 데이터 조회
         * findBySubject() : 해당 속성의 값으로 데이터 조회
         * findBySubjectAndContent() : 두 개의 속성을 And 조건으로 데이터 조회
         * findBySubjectLike() : 속성의 문자열이 포함되어있는 데이터 조회
         *
         * 이것들 말고 상당히 많은 조합들을 사용할 수 있다.
         * 쿼리 생성 규칙에 대한 공식문서 참고
         * - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
         */
        /*
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
        */
        /*
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()) { // NULL 확인
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
        */
        /*
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
        */
        /*
        Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
        */
        /*
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
        */
        /**
         * 3. 데이터 수정하기
         *
         * Optional은 null 처리를 유연하게 처리하기 위해 사용하는 클래스로 아래와 같이 isPresent로 null이 아닌지를 확인한 후에 get으로 실제 Question 객체 값을 얻어야 한다.
         */
        /*
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
        */
        /**
         * 4. 데이터 삭제하기
         */
        /*
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
        */
        /**
         * 5. 답변 데이터 생성 후 저장하기
         */
        /*
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);   // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
        */
        /**
         * 6. 답변 조회하기
         */
        /*
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
        */
        /**
         * 7. 답변에 연결된 질문 찾기 vs 질문에 달린 답변 찾기
         */
        /*
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
        */
        /**
         * 3-02 페이징 : 대량 테스트 데이터 만들기
         */
        /*
        for (int i = 0; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다.:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content, null);
        }
        */
    }

}
