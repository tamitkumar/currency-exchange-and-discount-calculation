package com.currency.exch.discount.calc.services;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

import com.currency.exch.discount.calc.model.User;

public interface UserService {

	public List<User> getAllUsers();
	
	public String addAndUpdateUser(User user);
	
	public void deleteUser(int id);
	
	default void copyNonNullProperties(Object dto, Object entity) {
		BeanUtils.copyProperties(dto, entity, getNullPropertyName(dto));
	}
	
	private String[] getNullPropertyName(Object dto) {
		final BeanWrapper beanWrapper = new BeanWrapperImpl(dto);
		PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<>();
		for (PropertyDescriptor pd : pds) {
			Object dtoValue = beanWrapper.getPropertyValue(pd.getName());
			if (ObjectUtils.isEmpty(dtoValue)) {
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
