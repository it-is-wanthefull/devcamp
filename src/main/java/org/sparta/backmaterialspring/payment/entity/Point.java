package org.sparta.backmaterialspring.payment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.sparta.backmaterialspring.auth.entity.User;
import org.sparta.backmaterialspring.common.entity.BaseEntity;

import java.util.List;

@Entity
@Getter
public class Point extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private int availableAmount;

    @OneToMany(mappedBy = "point")
    private List<PointLog> logs;

    public void use(int amountToUse) {
        this.availableAmount -= amountToUse;
    }

}