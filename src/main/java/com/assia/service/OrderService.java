package com.assia.service;

import com.assia.domain.order.Order;
import com.assia.model.order.OrderForm;
import com.assia.model.order.OrderModel;
import com.assia.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<OrderModel> getAllOrder(){
        List<OrderModel> rs = new ArrayList<>();
        this.orderRepository.findAll().forEach(order -> {
            rs.add(order.toOrder());
        });
        return rs;
    }

    public Optional<Order> getById(BigInteger id){
        return this.orderRepository.getById(id);
    }
    public void delete(BigInteger id){
        this.getById(id).ifPresent(this.orderRepository::delete);
    }
    public Optional<com.assia.domain.order.Order> update(BigInteger id, OrderForm orderForm){
        return this.getById(id).map(order -> {
            order.setName(orderForm.getName());
            return this.orderRepository.save(order);
        });
    }
    public com.assia.domain.order.Order create(OrderForm orderForm){
        com.assia.domain.order.Order order = new com.assia.domain.order.Order();
        order.setName(order.getName());
        return this.orderRepository.save(order);
    }
}
