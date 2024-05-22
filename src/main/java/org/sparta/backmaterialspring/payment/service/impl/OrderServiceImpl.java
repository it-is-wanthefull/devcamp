package org.sparta.backmaterialspring.payment.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sparta.backmaterialspring.auth.entity.User;
import org.sparta.backmaterialspring.payment.entity.*;
import org.sparta.backmaterialspring.payment.repository.OrderItemRepository;
import org.sparta.backmaterialspring.payment.repository.OrderRepository;
import org.sparta.backmaterialspring.payment.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(User user, List<OrderItem> orderItems, ShippingInfo shippingInfo) {
        Order order = new Order(user, orderItems, shippingInfo);
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) throws Exception {
       return orderRepository.findById(orderId).orElseThrow(Exception::new);
    }

    @Override
    public Double getOrderCheckoutPrice(Long orderId) throws Exception {
        Order orderById = getOrderById(orderId);
        return orderById.getCheckoutPrice();
    }

    @Override
    @Transactional
    public void applyPointToOrder(Long orderId, Double point) throws Exception {
        Order orderById = getOrderById(orderId);
        orderById.applyPointToOrder(point);
    }

    @Override
    @Transactional
    public void applyCouponToOrder(Long orderId, IssuedCoupon issuedCoupon) throws Exception {
        Order orderById = getOrderById(orderId);
        orderById.applyCouponToOrder(issuedCoupon);
    }

    @Override
    @Transactional
    public Order addOrderItem(Order order, Product product, int quantity) throws Exception {
        OrderItem orderItem = new OrderItem(order, product, quantity);
        order.getItems().add(orderItem);
        orderItemRepository.save(orderItem);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Map<Product, Integer> generateEntry(Long orderId) throws Exception {
        Order orderById = getOrderById(orderId);
        HashMap<Product, Integer> result = new HashMap<>();
        orderById.getItems().forEach(item -> {
            result.put(item.getProduct(), item.getQuantity());
        });
        return result;
    }

    @Override
    @Transactional
    public void standbyOrder(Long orderId) throws Exception {
        Order orderById = getOrderById(orderId);
        orderById.standbyOrder();
        orderRepository.save(orderById);
    }

    @Override
    @Transactional
    public void undoOrder(Long orderId) throws Exception {
        Order orderById = getOrderById(orderId);
        orderById.undoOrder();
        orderRepository.save(orderById);
    }

    @Override
    @Transactional
    public void completeOrder(Long orderId) throws Exception {
        Order orderById = getOrderById(orderId);
        orderById.completeOrder();
        orderRepository.save(orderById);
    }
}
