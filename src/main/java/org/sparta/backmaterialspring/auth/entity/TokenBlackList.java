package org.sparta.backmaterialspring.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.sparta.backmaterialspring.common.entity.BaseEntity;

import java.util.Date;

@Entity
@Getter
public class TokenBlackList extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String token;

    @Column
    private String jti;

    @Column
    @Enumerated(value = EnumType.STRING)
    private TokenType tokenType;

    @Column
    private Date expiresAt;

    public TokenBlackList() {}

    public TokenBlackList(String token, String jti, TokenType tokenType, Date expiresAt) {
        this.token = token;
        this.jti = jti;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
    }
}
