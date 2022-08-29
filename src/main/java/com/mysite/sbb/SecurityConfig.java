package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

// @Configuration : 스프링의 환경설정 파일임을 의미
// @EnableWebSecurity : 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만듬
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // SecurityFilterChain : 스프링 시큐리티의 세부 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/**").permitAll() // 모든 인증되지 않은 요청을 허락한다는 의미
                .and().csrf().ignoringAntMatchers("/h2-console/**") // 스프링 시큐리티가 CSRF 처리시 /h2-console/로 시작하는 URL은 CSRF 검증을 하지 않는다는 설정이다.
                .and().headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)) // X-Frame-Options 헤더의 값으로 sameorigin을 설정하면 frame에 포함된 페이지가 페이지를 제공하는 사이트와 동일한 경우에는 계속 사용할 수 있다.
        ;

        return http.build();
    }
}
