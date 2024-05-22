package org.sparta.backmaterialspring.payment.service;

import org.sparta.backmaterialspring.payment.entity.Point;

public interface PointService {
    /**
     * 포인트를 사용처리한다
     * @param point point entity
     * @param amountToUse 사용할 금액
     * @param reason 사용 사유
     */
    void usePoint(Point point, int amountToUse, String reason);
}
