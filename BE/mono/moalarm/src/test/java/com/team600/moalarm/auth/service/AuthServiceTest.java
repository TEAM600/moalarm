package com.team600.moalarm.auth.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.team600.moalarm.auth.dto.request.SignInRequest;
import com.team600.moalarm.auth.dto.response.SignInResponse;
import com.team600.moalarm.auth.exception.SignInFailedException;
import com.team600.moalarm.member.entity.Member;
import com.team600.moalarm.member.repository.MemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    MemberRepository memberRepository;
    @Mock
    EncryptJWTManager encryptJWTManager;
    PasswordEncoder passwordEncoder;

    AuthService authService;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.authService = new AuthService(memberRepository, encryptJWTManager, passwordEncoder);
    }

    @Test
    @DisplayName("signIn")
    void signIn() {
        String rawPassword = "1234";
        Member member = getTestMember("test", passwordEncoder.encode(rawPassword));

        SignInRequest signInRequest = getSignInRequest(member.getEmail(), rawPassword);

        when(memberRepository.findByEmail(member.getEmail()))
                .thenReturn(Optional.of(member));
        when(encryptJWTManager.createToken(any()))
                .thenReturn("token");

        SignInResponse signInResponse = authService.signIn(signInRequest);

        assertFalse(signInResponse.getToken().isEmpty()); // 오류없이 생성된다면 성공한걸로 간주
    }

    @Test
    @DisplayName("password가 다르면 signin에 실패한다")
    void signInFailWhenPasswordDifferent() {
        Member member = Member.builder()
                .email("test")
                .password("12345")
                .build();

        SignInRequest signInRequest = getSignInRequest(member.getEmail(), "1234");

        when(memberRepository.findByEmail(any()))
                .thenReturn(Optional.of(member));

        assertThrows(SignInFailedException.class,
                () -> authService.signIn(signInRequest));
    }

    @Test
    @DisplayName("email이 존재하지 않으면 signin에 실패한다")
    void signInFailWhenMemberNotFound() {
        SignInRequest signInRequest = getSignInRequest("test", "1234");

        when(memberRepository.findByEmail(any())).thenReturn(Optional.empty());

        assertThrows(SignInFailedException.class,
                () -> authService.signIn(signInRequest));
    }

    private static SignInRequest getSignInRequest(String email, String password) {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setEmail(email);
        signInRequest.setPassword(password);
        return signInRequest;
    }

    private Member getTestMember(String email, String encodedPassword) {
        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .build();

    }
}