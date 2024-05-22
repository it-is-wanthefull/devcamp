package org.sparta.backmaterialspring.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfoDto {
    private Double totalPrice;
    private Long orderId;
}
