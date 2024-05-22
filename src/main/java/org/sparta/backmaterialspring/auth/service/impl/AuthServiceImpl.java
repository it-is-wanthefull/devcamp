package org.sparta.backmaterialspring.auth.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.sparta.backmaterialspring.auth.entity.TokenType;
import org.sparta.backmaterialspring.auth.entity.User;
import org.sparta.backmaterialspring.auth.jwt.JwtProvider;
import org.sparta.backmaterialspring.auth.repository.UserRepository;
import org.sparta.backmaterialspring.auth.service.AuthService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public String refreshAccessToken(String refreshToken) {
        // refreshToken 유효성 검사
        if (!jwtProvider.validateToken(refreshToken)) {
           throw new RuntimeException("Invalid Token");
        }
        // Jwt Claims
        Claims info = jwtProvider.getUserInfoFromToken(refreshToken);

        // User 조회
        User user = userRepository.findByEmail(info.getSubject()).orElseThrow(
                () -> new RuntimeException("Not Found User By : " + info.getSubject())
        );

        return jwtProvider.createToken(jwtProvider.createTokenPayload(user.getEmail(), TokenType.ACCESS));
    }
}
