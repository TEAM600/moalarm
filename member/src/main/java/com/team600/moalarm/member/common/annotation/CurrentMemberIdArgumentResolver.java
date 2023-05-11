package com.team600.moalarm.member.common.annotation;

import com.team600.moalarm.member.common.exception.impl.IllegalMemberIdException;
import com.team600.moalarm.member.common.utils.HttpRequestUtils;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class CurrentMemberIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(CurrentMemberId.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        String memberId = HttpRequestUtils.resolveMemberIdHeader(
                (HttpServletRequest) webRequest.getNativeRequest());
        //TODO
        try {
            return Long.valueOf(memberId);
        } catch (NumberFormatException e) {
            throw new IllegalMemberIdException(memberId);
        }
    }
}
