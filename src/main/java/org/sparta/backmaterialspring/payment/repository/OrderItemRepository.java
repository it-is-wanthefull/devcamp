package org.sparta.backmaterialspring.payment.repository;

import org.sparta.backmaterialspring.payment.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
