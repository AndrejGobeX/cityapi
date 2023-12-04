package com.cities.citytempapi.utils;
        
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class Interceptor implements HandlerInterceptor {

    private TokenWrapper tokenWrapper;

    public Interceptor(TokenWrapper tokenWrapper) {
        this.tokenWrapper = tokenWrapper;
    }

    public boolean preHandle(HttpServletRequest request,
                     HttpServletResponse response, Object handler) throws Exception {
        final String authorizationHeaderValue = request.getHeader("Authorization");
        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer")) {
            String token = authorizationHeaderValue.substring(7);
            tokenWrapper.setToken(token);
        }

        return true;
    }
}
