package com.team600.moalarm.member.common.utils;

import javax.servlet.http.HttpServletRequest;

public class AuthUtils {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TYPE = "MoalarmKey ";

    public static String resolveAuthorizationHeader(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String key = null;
        if (authorization != null && authorization.startsWith(AUTHORIZATION_TYPE)) {
            key = authorization.replaceFirst("^" + AUTHORIZATION_TYPE, "");
        }
        return key;
    }
}
