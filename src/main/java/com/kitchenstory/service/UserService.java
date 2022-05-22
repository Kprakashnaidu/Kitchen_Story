package com.kitchenstory.service;

import com.kitchenstory.entity.UserEntity;
import com.kitchenstory.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Optional<UserEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<UserEntity> findById(String id) {
        return repository.findById(id);
    }

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    public List<UserEntity> saveAll(List<UserEntity> users) {
        return repository.saveAll(users);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
