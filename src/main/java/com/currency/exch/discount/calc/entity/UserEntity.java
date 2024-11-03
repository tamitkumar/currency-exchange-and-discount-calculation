package com.currency.exch.discount.calc.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NAME")
    private String name;
	
	@Column(name = "EMAIL")
    private String email;
	
	@Column(name = "PHONE")
    private long phone;
	
	@Column(name = "IS_EMPLOYEE")
    private Boolean employee;
	
	@Column(name = "IS_AFFILIATE")
    private Boolean affiliate;
	
	@Column(name = "CREATED_ON")
    private LocalDate createdOn;
	
	@Column(name = "MODIFIED_ON")
    private LocalDate modifiedOn;
}
