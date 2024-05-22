package org.sparta.backmaterialspring.payment.service;

import org.sparta.backmaterialspring.payment.entity.IssuedCoupon;

public interface IssuedCouponService {
    /**
     * 발급된 쿠폰을 사용처리한다
     * @param issuedCoupon coupon entity
     */
    void useCoupon(IssuedCoupon issuedCoupon) throws Exception;
}
