package com.currency.exch.discount.calc.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.currency.exch.discount.calc.utils.ExchangeConstant;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.currency.exch.discount.calc.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "platformTransactionManager")
public class DBConfiguration {

	private final ExchangeDBConfig exchangeDBConfig;

	public DBConfiguration(ExchangeDBConfig exchangeDBConfig) {
		this.exchangeDBConfig = exchangeDBConfig;
	}
	
	@Bean
    JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

    @Bean
    @Autowired
    PlatformTransactionManager platformTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}
	
	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.put(ExchangeConstant.DIALECT_KEY, ExchangeConstant.DIALECT_VALUE);
		properties.put("hibernate.dialect", "com.currency.exch.discount.calc.config.DialectConfig");
		properties.put(ExchangeConstant.SHOW_SQL_KEY, ExchangeConstant.SHOW_SQL_VALUE);
		properties.put(ExchangeConstant.FORMAT_SQL_KEY, ExchangeConstant.FORMAT_SQL_VALUE);
		properties.put("spring.jpa.hibernate.ddl-auto", "create");
		return properties;
	}

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(exchangeDBConfig.dataSource());
		factoryBean.setPackagesToScan(new String [] {"com.currency.exch.discount.calc.entity"});
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		factoryBean.setJpaProperties(jpaProperties());
		return factoryBean;
	}
}
