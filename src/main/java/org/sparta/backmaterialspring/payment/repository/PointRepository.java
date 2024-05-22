package org.sparta.backmaterialspring.payment.repository;

import org.sparta.backmaterialspring.payment.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
}
