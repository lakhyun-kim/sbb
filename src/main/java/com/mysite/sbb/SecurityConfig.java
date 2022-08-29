package com.mysite.sbb;

import com.mysite.sbb.user.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration : 스프링의 환경설정 파일임을 의미
// @EnableWebSecurity : 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만듬
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true) : 로그인 여부를 판별하기 위해 사용했던 @PreAuthorize 애너테이션을 사용하기 위해 반드시 필요하다.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserSecurityService userSecurityService;

    // SecurityFilterChain : 스프링 시큐리티의 세부 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/**").permitAll() // 모든 인증되지 않은 요청을 허락한다는 의미
                .and().csrf().ignoringAntMatchers("/h2-console/**") // 스프링 시큐리티가 CSRF 처리시 /h2-console/로 시작하는 URL은 CSRF 검증을 하지 않는다는 설정이다.
                .and().headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)) // X-Frame-Options 헤더의 값으로 sameorigin을 설정하면 frame에 포함된 페이지가 페이지를 제공하는 사이트와 동일한 경우에는 계속 사용할 수 있다.
                .and().formLogin().loginPage("/user/login").defaultSuccessUrl("/")  // 스프링 시큐리티의 로그인 설정을 담당하는 부분으로 로그인 페이지의 URL은 /user/login이고 로그인 성공시에 이동하는 디폴트 페이지는 루트 URL(/)임을 의미한다.
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/").invalidateHttpSession(true)   // 로그아웃 URL을 /user/logout으로 설정하고 로그아웃이 성공하면 루트(/) 페이지로 이동하도록 했다. 그리고 로그아웃시 생성된 사용자 세션도 삭제하도록 처리했다.
        ;

        return http.build();
    }

    /**
     * BCryptPasswordEncoder는 BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 암호화한다.
     * BCryptPasswordEncoder 객체를 직접 new로 생성하는 방식보다는 PasswordEncoder 빈(bean)으로 등록해서 사용하는 것이 좋다. 왜냐하면 암호화 방식을 변경하면 BCryptPasswordEncoder를 사용한 모든 프로그램을 일일이 찾아서 수정해야 하기 때문이다.
     * PasswordEncoder는 BCryptPasswordEncoder의 인터페이스이다.
     * PasswordEncoder 빈(bean)을 만드는 가장 쉬운 방법은 @Configuration이 적용된 SecurityConfig에 @Bean 메서드를 생성하는 것이다. 다음과 같이 SecurityConfig를 수정하자.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 빈 생성시 스프링의 내부 동작으로 인해 위에서 작성한 UserSecurityService와 PasswordEncoder가 자동으로 설정된다.
    @Bean
    public AuthenticationManager authcAnthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
