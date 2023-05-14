package com.modu.ModuForm.app.web.config.OAuth;

import com.modu.ModuForm.app.domain.user.User;
import com.modu.ModuForm.app.web.security.authentication.jwt.CustomJwtProvider;
import com.modu.ModuForm.app.web.security.dto.AccessUserDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class JwtSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RequestCache requestCache;
    private final RedirectStrategy redirectStrategy;
    private final CustomJwtProvider jwtProvider;

    public JwtSuccessHandler(CustomJwtProvider jwtProvider) {
        super();
        this.requestCache = new HttpSessionRequestCache();
        this.redirectStrategy = new DefaultRedirectStrategy();
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        clearSession(request);
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        // Jwt 삽입
        AccessUserDetail userDetail = (AccessUserDetail) authentication.getPrincipal();
        User user = userDetail.getUser();
        jwtProvider.createAccessRefreshToken(response, user);

        // 기본 URI
        String uri = "/";

        // 이전 요청 값이 있다면 redirect
        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();
        }

        redirectStrategy.sendRedirect(request, response, uri);
    }

    // 로그인 실패 후 성공 시 남아있는 에러 세션 제거
    protected void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
