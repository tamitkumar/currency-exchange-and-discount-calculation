package com.currency.exch.discount.calc.config;

import org.hibernate.dialect.MySQLDialect;

public class DialectConfig extends MySQLDialect {
	
	@Override
	public boolean dropConstraints() {
		return false;
	}
}
