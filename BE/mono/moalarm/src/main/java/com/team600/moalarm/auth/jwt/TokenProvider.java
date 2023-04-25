package com.team600.moalarm.auth.jwt;

import com.team600.moalarm.auth.vo.JwtDecryptResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITY_KEY = "roles";
    private final Key accessKey;
    @Value("${jwt.expire-day}")
    private final int ACCESS_TOKEN_VALID_DAY;

    public TokenProvider(@Value(value = "${jwt.secret}") String secret,
            @Value("${jwt.expire-day}") int accessTokenValidDay) {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        ACCESS_TOKEN_VALID_DAY = accessTokenValidDay;
    }

    public String createToken(String subject) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenValidTime = now.plusDays(ACCESS_TOKEN_VALID_DAY);

        return Jwts.builder()
                .setSubject(subject)
                .claim(AUTHORITY_KEY, List.of("USER"))
                .setExpiration(Date.from(tokenValidTime.toInstant(ZoneOffset.UTC)))
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public JwtDecryptResult decryptJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<SimpleGrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITY_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new JwtDecryptResult(claims.getSubject(), authorities);
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
