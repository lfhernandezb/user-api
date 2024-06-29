package com.example.userapi.persistence.crud;

import com.example.userapi.persistence.entity.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneCrudRepository extends CrudRepository<Phone, Long> {
}
