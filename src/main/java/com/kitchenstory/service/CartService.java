package com.kitchenstory.service;

import com.kitchenstory.entity.CartEntity;
import com.kitchenstory.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CartService {

    private final CartRepository repository;

    public CartEntity save(CartEntity cart) {
        return repository.save(cart);
    }

    public List<CartEntity> saveAll(List<CartEntity> carts) {
        return repository.saveAll(carts);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAll(List<CartEntity> carts) {
        repository.deleteAll(carts);
    }

    public Optional<CartEntity> findById(Integer id) {
        return repository.findById(id);
    }

    public List<CartEntity> findAll() {
        return repository.findAll();
    }
}
