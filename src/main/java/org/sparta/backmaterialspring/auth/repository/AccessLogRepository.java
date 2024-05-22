package org.sparta.backmaterialspring.auth.repository;

import org.sparta.backmaterialspring.auth.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
