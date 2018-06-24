package com.assia.configure.login;

import com.assia.service.UserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomFailureLoginHandler implements AuthenticationFailureHandler {
    private final UserService userService;

    public CustomFailureLoginHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.getWriter().append("Authentication failure");
        httpServletResponse.setStatus(401);

    }
}
