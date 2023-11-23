package com.vishesh.student.registration.repository;

import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;

public interface UserPasswordStorageRepository extends CrudRepository<UserPasswordStorage,BigDecimal>{

}
