package org.sparta.backmaterialspring.payment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.sparta.backmaterialspring.auth.entity.User;
import org.sparta.backmaterialspring.common.entity.BaseEntity;

import java.util.Date;

@Entity
@Getter
public class IssuedCoupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToOne
    private Order usedOrder;

    @Column(columnDefinition = "boolean default false")
    private boolean isValid;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date validUntil;

    @Column(columnDefinition = "boolean default false")
    private boolean isUsed;

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date usedAt;

    public void use() {
        this.isUsed = true;
        this.isValid = false;
        this.usedAt = new Date();
    }

}