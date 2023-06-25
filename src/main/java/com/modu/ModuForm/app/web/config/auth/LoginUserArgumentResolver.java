package com.modu.ModuForm.app.web.config.auth;

import com.modu.ModuForm.app.web.config.dto.JwtUser;
import com.modu.ModuForm.app.web.security.authentication.jwt.JwtExtractor;
import com.modu.ModuForm.app.web.security.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.modu.ModuForm.app.web.security.authentication.jwt.JwtType.ACCESS;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtExtractor jwtHandler;
    private final JwtProperties jwtProperties;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = JwtUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        JwtUser jwtUser = jwtHandler.getJwtUser(Objects.requireNonNull(request)
                .getHeader(jwtProperties.getHeader(ACCESS))
        );

        Objects.requireNonNull(mavContainer).getModel().addAttribute("user", jwtUser);
        return jwtUser;
    }
}
