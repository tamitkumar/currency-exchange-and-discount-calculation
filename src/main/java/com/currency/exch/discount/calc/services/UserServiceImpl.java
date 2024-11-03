package com.currency.exch.discount.calc.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.currency.exch.discount.calc.entity.UserEntity;
import com.currency.exch.discount.calc.model.User;
import com.currency.exch.discount.calc.repository.ExchangeRepository;

@Service
public class UserServiceImpl implements UserService {

	private final ExchangeRepository exchangeRepository;

	public UserServiceImpl(ExchangeRepository exchangeRepository) {
		this.exchangeRepository = exchangeRepository;
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		List<UserEntity> existingUser = (List<UserEntity>) exchangeRepository.findAll();
		existingUser.forEach(dbUser -> {
			User user = new User();
			BeanUtils.copyProperties(dbUser, user);
			users.add(user);
		});
		return users;
	}
	
	@Override
	public String addAndUpdateUser(User user) {
		Optional<UserEntity> oldUser = exchangeRepository.findByIdAndPhone(user.getId(), user.getPhone());
		UserEntity savedUser = oldUser.map(existingUser -> {
			copyNonNullProperties(user, existingUser);
			existingUser.setModifiedOn(LocalDate.now());
			return exchangeRepository.save(existingUser);
		}).orElseGet(() -> {
			UserEntity newUser = new UserEntity();
			BeanUtils.copyProperties(user, newUser);
			newUser.setModifiedOn(LocalDate.now());
			newUser.setCreatedOn(LocalDate.now());
			return exchangeRepository.save(newUser);
		});
		return savedUser.getName() + (oldUser.isPresent() ? "'s data updated successfully." : "'s data saved successfully.");
	}
	
	@Override
	public void deleteUser(int id) {
		exchangeRepository.deleteById(id);
	}
	
}
