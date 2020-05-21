package com.fiveletters.brush.config;

import com.fiveletters.brush.domain.admin.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("adminId", authentication.getName()); // 세션에 adminId = id값 넣음

        if(authentication.getAuthorities().toString().equals("["+Role.ADMIN.getValue()+"]")){
            session.setAttribute("authority", Role.ADMIN.getValue2()); // 세션에 authority = ADMIN 넣음
        }
        else if(authentication.getAuthorities().toString().equals("["+Role.MEMBER.getValue()+"]")){
            session.setAttribute("authority", Role.MEMBER.getValue2()); // 세션에 authority = MEMBER 넣음
        }

        response.sendRedirect("/auctionResults"); // 해당 url로 이동
    }
}
