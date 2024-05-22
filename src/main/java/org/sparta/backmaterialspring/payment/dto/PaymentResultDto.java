package org.sparta.backmaterialspring.payment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResultDto {
    private boolean isPaymentSuccess;
    private String message;
}
