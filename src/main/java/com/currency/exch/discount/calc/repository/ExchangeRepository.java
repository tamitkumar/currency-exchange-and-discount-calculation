package com.currency.exch.discount.calc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.currency.exch.discount.calc.entity.UserEntity;

@Repository
public interface ExchangeRepository extends CrudRepository<UserEntity, Integer> {

	public Optional<UserEntity> findByIdAndPhone(Integer id, Long phone);
}
