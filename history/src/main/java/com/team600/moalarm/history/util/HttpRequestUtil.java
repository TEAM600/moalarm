package com.team600.moalarm.history.util;

import com.team600.moalarm.history.exception.MissingMemberIdHeaderException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpRequestUtil {

    private static final String MEMBER_ID_HEADER = "Member-Id";

    public static String resolveMemberIdHeader() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return Optional.ofNullable(request.getHeader(MEMBER_ID_HEADER))
                .orElseThrow(MissingMemberIdHeaderException::new);
    }

    public static Long getMemberId() {
        String memberId = resolveMemberIdHeader();
        try {
            return Long.valueOf(memberId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

}
