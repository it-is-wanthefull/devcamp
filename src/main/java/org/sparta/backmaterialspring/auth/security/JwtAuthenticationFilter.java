package org.sparta.backmaterialspring.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.sparta.backmaterialspring.auth.dto.LoginRequestDto;
import org.sparta.backmaterialspring.auth.entity.AccessLog;
import org.sparta.backmaterialspring.auth.entity.TokenType;
import org.sparta.backmaterialspring.auth.entity.User;
import org.sparta.backmaterialspring.auth.jwt.JwtProvider;
import org.sparta.backmaterialspring.auth.repository.AccessLogRepository;
import org.sparta.backmaterialspring.common.util.HttpRequestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtProvider jwtProvider;
    private final AccessLogRepository accessLogRepository;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, AccessLogRepository accessLogRepository) {
        this.jwtProvider = jwtProvider;
        this.accessLogRepository = accessLogRepository;
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Login 성공
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        User user = ((UserDetailsImpl) authResult.getPrincipal()).getUser();
        String email = user.getEmail();
        String accessToken = jwtProvider.createToken(jwtProvider.createTokenPayload(email, TokenType.ACCESS));
        String refreshToken = jwtProvider.createToken(jwtProvider.createTokenPayload(email, TokenType.REFRESH));

        response.addHeader(JwtProvider.ACCESS_TOKEN_HEADER, accessToken);
        response.addHeader(JwtProvider.REFRESH_TOKEN_HEADER, refreshToken);

        AccessLog accessLog = new AccessLog(
                HttpRequestUtils.getUserAgent(request),
                request.getRequestURI(),
                HttpRequestUtils.getRemoteAddr(request),
                user
        );
        accessLogRepository.save(accessLog);
    }

    /**
     * Login 실패
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
}
