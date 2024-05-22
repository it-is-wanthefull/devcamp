package org.sparta.backmaterialspring.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sparta.backmaterialspring.common.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
public class AccessLog extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512)
    private String ua;

    @Column
    private String endpoint;

    @Column
    private String ip;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public AccessLog() {}

    public AccessLog(String ua, String endpoint, String ip, User user) {
        this.ua = ua;
        this.endpoint = endpoint;
        this.ip = ip;
        this.user = user;
    }
}
