package org.sparta.backmaterialspring.payment.dto;

import lombok.Getter;
import lombok.Setter;
import org.sparta.backmaterialspring.auth.entity.User;
import org.sparta.backmaterialspring.payment.entity.IssuedCoupon;
import org.sparta.backmaterialspring.payment.entity.OrderItem;
import org.sparta.backmaterialspring.payment.entity.ShippingInfo;

import java.util.List;

@Getter
@Setter
public class CreateOrderDto {
    private User user;
    private List<OrderItem> orderItems;
    private IssuedCoupon coupon;
    private Double pointAmountToUse;
    private ShippingInfo shippingInfo;
}
