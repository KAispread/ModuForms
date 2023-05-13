package com.modu.ModuForm.app.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.modu.ModuForm.app.web.config.jwt.JwtCookie.ENCRYPT;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI() + "?" + request.getQueryString();
        Cookie[] cookies = request.getCookies();

        if (!isCookieContainJwt(cookies)) {
            log.warn("UNAUTHORIZED [{}]", requestURI);
            response.sendRedirect("/users/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }

    private boolean isCookieContainJwt(Cookie[] cookies) {
        if (cookies == null) {
            return false;
        }
        return ENCRYPT.isCookiesContainToken(cookies);
    }
}
