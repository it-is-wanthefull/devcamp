package org.sparta.backmaterialspring.auth.repository;

import org.sparta.backmaterialspring.auth.entity.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {
    Optional<TokenBlackList> findByJti(String jti);
    List<TokenBlackList> findAllByExpiresAtLessThan(Date now);
}
