package com.modu.ModuForm.app.web.config.auth;

import com.modu.ModuForm.app.domain.user.acess.Access;
import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.config.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SessionManager {
    private final HttpSession session;
    private final Integer INTERVAL_TIME = 30 * 60;

    public String createSession(Access access) {
        String nickname = access.getUsers().getNickName();

        session.setAttribute("user" , new SessionUser(access.getUsers()));
        session.setMaxInactiveInterval(INTERVAL_TIME);
        return nickname;
    }

    public void createSession(User user) {
        session.setAttribute("user" , new SessionUser(user));
        session.setMaxInactiveInterval(INTERVAL_TIME);
    }
}
