package org.sparta.backmaterialspring.payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.sparta.backmaterialspring.payment.entity.IssuedCoupon;
import org.sparta.backmaterialspring.payment.repository.IssuedCouponRepository;
import org.sparta.backmaterialspring.payment.service.IssuedCouponService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssuedCouponServiceImpl implements IssuedCouponService {
    private final IssuedCouponRepository issuedCouponRepository;

    @Override
    public void useCoupon(IssuedCoupon issuedCoupon) throws Exception {
        issuedCoupon.use();
        issuedCouponRepository.save(issuedCoupon);
    }

    private Optional<IssuedCoupon> findCouponById(Long couponId) {
        return issuedCouponRepository.findById(couponId);
    }
}
