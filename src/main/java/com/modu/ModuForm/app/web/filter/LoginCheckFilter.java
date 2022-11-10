package com.modu.ModuForm.app.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {
                    "/users/login*", "/users/register*",
                    "/app/users", "/app/users/login*", "/app/users/logout*",
                    "/css/*", "/js/*", "/fonts/*"
            };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (isLoginCheckPath(requestURI)) {
                log.info("AUTHORIZE CHECK [{}]", requestURI);
                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute("user") == null) {
                    log.warn("UNAUTHORIZED [{}]", requestURI);
                    httpResponse.sendRedirect("/users/login?redirectURL=" + requestURI);
                    return;
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("AUTHORIZE ERROR : {}", e.getMessage());
            throw e;
        }
    }

    /* 화이트 리스트의 경우 인증 체크 X
    * */
    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
