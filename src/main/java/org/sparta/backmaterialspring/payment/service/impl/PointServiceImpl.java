package org.sparta.backmaterialspring.payment.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sparta.backmaterialspring.payment.entity.Point;
import org.sparta.backmaterialspring.payment.entity.PointLog;
import org.sparta.backmaterialspring.payment.repository.PointLogRepository;
import org.sparta.backmaterialspring.payment.repository.PointRepository;
import org.sparta.backmaterialspring.payment.service.PointService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;
    private final PointLogRepository pointLogRepository;

    @Override
    @Transactional
    public void usePoint(Point point, int amountToUse, String reason) {
        PointLog log = PointLog.use(point, amountToUse, reason);
        pointLogRepository.save(log);
        point.getLogs().add(log);
        point.use(amountToUse);
        pointRepository.save(point);
    }
}
