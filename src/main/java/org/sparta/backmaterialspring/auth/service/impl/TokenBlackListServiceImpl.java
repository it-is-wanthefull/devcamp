package org.sparta.backmaterialspring.auth.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.sparta.backmaterialspring.auth.entity.TokenBlackList;
import org.sparta.backmaterialspring.auth.entity.TokenType;
import org.sparta.backmaterialspring.auth.jwt.JwtProvider;
import org.sparta.backmaterialspring.auth.repository.TokenBlackListRepository;
import org.sparta.backmaterialspring.auth.service.TokenBlackListService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenBlackListServiceImpl implements TokenBlackListService {

    private final JwtProvider jwtProvider;
    private final TokenBlackListRepository tokenBlackListRepository;

    @Override
    public void addToBlacklist(String accessToken, String refreshToken) {
        Claims accessClaims = jwtProvider.getUserInfoFromToken(accessToken);
        Claims refreshClaims = jwtProvider.getUserInfoFromToken(refreshToken);

        tokenBlackListRepository.save(new TokenBlackList(
                accessToken,
                accessClaims.getId(),
                TokenType.ACCESS,
                accessClaims.getExpiration()
        ));

        tokenBlackListRepository.save(new TokenBlackList(
                refreshToken,
                refreshClaims.getId(),
                TokenType.REFRESH,
                refreshClaims.getExpiration()
        ));
    }

    @Override
    public boolean isTokenBlacklisted(String jti) {
        Optional<TokenBlackList> tokenByJti = tokenBlackListRepository.findByJti(jti);
        return tokenByJti.isPresent();
    }

    @Override
    public void removeExpiredTokens() {
        List<TokenBlackList> expiredList = tokenBlackListRepository.findAllByExpiresAtLessThan(new Date());
        tokenBlackListRepository.deleteAllInBatch(expiredList);
    }
}
