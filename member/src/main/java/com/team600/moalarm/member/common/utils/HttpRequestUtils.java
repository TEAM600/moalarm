package com.team600.moalarm.member.common.utils;

import com.team600.moalarm.member.common.exception.impl.MissingMemberIdHeaderException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpRequestUtils {

    private static final String MEMBER_ID_HEADER = "Member-Id";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TYPE = "MoalarmKey ";

    public static String resolveMemberIdHeader(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(MEMBER_ID_HEADER))
                .orElseThrow(MissingMemberIdHeaderException::new);
    }

    public static String resolveAuthorizationHeader(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String key = null;
        if (authorization != null && authorization.startsWith(AUTHORIZATION_TYPE)) {
            key = authorization.replaceFirst("^" + AUTHORIZATION_TYPE, "");
        }
        return key;
    }
}
