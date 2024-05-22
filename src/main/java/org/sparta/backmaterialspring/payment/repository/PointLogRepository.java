package org.sparta.backmaterialspring.payment.repository;

import org.sparta.backmaterialspring.payment.entity.PointLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointLogRepository extends JpaRepository<PointLog, Long> {
}
