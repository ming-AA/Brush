package com.fiveletters.brush.config;

import com.fiveletters.brush.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AdminService adminService;

    // 보안 필요한 URL 경로 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().antMatchers("/**").permitAll(); // 경로 지정, 모든 사용자 접근 가능
//        http.formLogin().loginPage("/adminLogin").permitAll(); // 커스텀 로그인 페이지 생성, 모든 사용자 접근 가능
//        http.exceptionHandling().accessDeniedPage("/accessDenied"); // 권한 없는 페이지 접근 시
//        http.logout().invalidateHttpSession(true); // 세션 무효화
        //http.logout().logoutUrl("/logout").invalidateHttpSession(ture);

        http
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/adminLogin", "/img/**").permitAll() // 해당 url 모두 허용
                .anyRequest().authenticated() // 외 모든 요청은 인증된 사용자만 접근 가능
                .and()
            .formLogin()
                .loginPage("/adminLogin") // 커스텀 로그인 페이지 지정
                .successHandler(new AdminLoginSuccessHandler())
//                .defaultSuccessUrl("/auctionResults").permitAll() // 로그인 성공 시 이동 페이지 지정
                .and()
             .logout()
                .logoutSuccessUrl("/adminLogin")
                .invalidateHttpSession(true);

    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/templates/**");
    }

}
