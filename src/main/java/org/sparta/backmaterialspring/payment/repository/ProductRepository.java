package org.sparta.backmaterialspring.payment.repository;

import org.sparta.backmaterialspring.payment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
