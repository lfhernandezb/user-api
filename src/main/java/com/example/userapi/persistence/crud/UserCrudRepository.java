package com.example.userapi.persistence.crud;

import com.example.userapi.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCrudRepository extends CrudRepository<User, UUID> {
    public Optional<User> findByEmail(String email);
}
