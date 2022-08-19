package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// 스프링부트의 컨트롤러가 된다.
@Controller
public class MainController {

    // 요청된 URL과의 매핑
    @RequestMapping("/sbb")
    // URL 요청에 대한 응답으로 문자열을 리턴
    @ResponseBody
    public String index() {
        return "안녕하세요 sbb에 오신것을 환영합니다.";
    }
}
