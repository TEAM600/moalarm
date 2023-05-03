package com.team600.moalarm.common.config.filter;

import javax.servlet.http.HttpServletRequest;

public class AuthFilterUtils {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static String resolveAuthHeader(HttpServletRequest request, String authorizationType) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String key = null;
        if (authorization != null && authorization.startsWith(authorizationType)) {
            key = authorization.replaceFirst("^" + authorizationType, "");
        }
        return key;
    }
}
