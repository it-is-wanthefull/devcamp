package org.sparta.backmaterialspring.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.sparta.backmaterialspring.auth.dto.CreateUserDto;
import org.sparta.backmaterialspring.auth.dto.SignupResponseDto;
import org.sparta.backmaterialspring.auth.entity.TokenType;
import org.sparta.backmaterialspring.auth.jwt.JwtProvider;
import org.sparta.backmaterialspring.auth.service.AuthService;
import org.sparta.backmaterialspring.auth.service.TokenBlackListService;
import org.sparta.backmaterialspring.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtProvider jwtProvider;
    private final AuthService authService;
    private final UserService userService;
    private final TokenBlackListService tokenBlackListService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody CreateUserDto createUserDto) {
        SignupResponseDto responseDto = userService.signup(createUserDto);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        tokenBlackListService.addToBlacklist(
                jwtProvider.getJwtFromHeader(request, TokenType.ACCESS),
                jwtProvider.getJwtFromHeader(request, TokenType.REFRESH)
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Token Refresh")
    @GetMapping("/refresh")
    public ResponseEntity<String> refresh(HttpServletRequest request) {
        String accessToken = authService.refreshAccessToken(jwtProvider.getJwtFromHeader(request, TokenType.REFRESH));
        return ResponseEntity.ok(accessToken);
    }

    @Operation(summary = "Token Blacklist (Batch) : Batch 서버에서 주기적으로 Expired 된 토큰 제거")
    @DeleteMapping("/blacklist")
    public ResponseEntity<Void> resetBlacklist() {
        tokenBlackListService.removeExpiredTokens();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
