package com.team600.moalarm.gateway.common.config.provider;

import com.team600.moalarm.gateway.common.config.vo.JwtDecryptResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITY_KEY = "roles";
    private final Key accessKey;
    private final int ACCESS_TOKEN_VALID_DAY;

    public TokenProvider(@Value("${jwt.secret}") String secret,
            @Value("${jwt.expire-day}") int accessTokenValidDay) {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.ACCESS_TOKEN_VALID_DAY = accessTokenValidDay;
    }

    public JwtDecryptResult parseJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        @SuppressWarnings("unchecked")
        List<String> authClaim = claims.get(AUTHORITY_KEY, List.class);
        return new JwtDecryptResult(claims.getSubject(), authClaim);
    }

    public boolean validateAccessToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(accessKey).build()
                    .parseClaimsJws(token);
            return claimsJws.getBody()
                    .getExpiration()
                    .after(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        } catch (Exception e) {
            return false;
        }
    }

}
