package com.kitchenstory.service;

import com.kitchenstory.entity.OrderEntity;
import com.kitchenstory.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public OrderEntity save(OrderEntity order) {
        return repository.save(order);
    }

    public List<OrderEntity> saveAll(List<OrderEntity> orders) {
        return repository.saveAll(orders);
    }

    public Optional<OrderEntity> findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<OrderEntity> findAll() {
        return repository.findAll();
    }
}
