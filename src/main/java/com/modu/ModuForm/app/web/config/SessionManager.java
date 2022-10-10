package com.modu.ModuForm.app.web.config;

import com.modu.ModuForm.app.domain.user.Access;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class SessionManager {
    private final String SESSION_CONST;
    public SessionManager() {
        SESSION_CONST = "USER";
    }

    public String createSession(HttpServletRequest request, Access access) {
        HttpSession session = request.getSession();

        Long userPk = access.getUser().getId();
        String nickname = access.getUser().getNickName();

        session.setAttribute(SESSION_CONST, userPk);

        session.setAttribute("userNickName", nickname);
        session.setAttribute("userName", access.getUser().getName());
        session.setAttribute("userPk", access.getUser().getId());
        return nickname;
    }

    public Long getUserPk(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (Long) session.getAttribute(SESSION_CONST);
    }
}
