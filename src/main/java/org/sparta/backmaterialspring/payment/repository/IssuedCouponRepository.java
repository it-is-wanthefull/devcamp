package org.sparta.backmaterialspring.payment.repository;

import org.sparta.backmaterialspring.payment.entity.IssuedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Long> {
}
