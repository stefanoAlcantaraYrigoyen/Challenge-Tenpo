package com.challange.tenpo.config;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import static com.challange.tenpo.config.Consts.ENTRY_POINT;
import static com.challange.tenpo.config.Consts.MESSAGE_UNAUTHORIZED_REQUEST;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(MESSAGE_UNAUTHORIZED_REQUEST);
    }

    @Override
    public void afterPropertiesSet(){
        setRealmName(ENTRY_POINT);
        super.afterPropertiesSet();
    }

}
