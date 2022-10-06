package com.modu.ModuForm.app.web.config;

import com.modu.ModuForm.app.domain.user.Access;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.UUID;

@Component
public class SessionManager {
    private final String SESSION_CONST;

    public SessionManager() {
        SESSION_CONST = "USER";
    }

    public String createSession(HttpSession session, Access access, HttpServletResponse response) {
        String sessionId = UUID.randomUUID().toString();
        Long userPk = access.getUser().getId();
        String nickname = access.getUser().getNickName();

        session.setAttribute(sessionId, userPk);
        Cookie accessCookie = new Cookie(SESSION_CONST, sessionId);
        accessCookie.setMaxAge(-1);

        response.addCookie(accessCookie);
        return nickname;
    }

    public Long getSession(HttpServletRequest request, HttpSession session) {
        Cookie cookie = findCookie(request);
        if (cookie == null) {
            return null;
        }
        return (Long) session.getAttribute(cookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(SESSION_CONST))
                .findFirst()
                .orElse(null);
    }

}
