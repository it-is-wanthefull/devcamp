package org.sparta.backmaterialspring.payment.repository;

import org.sparta.backmaterialspring.payment.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
