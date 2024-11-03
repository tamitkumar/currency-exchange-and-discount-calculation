package com.currency.exch.discount.calc.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.currency.exch.discount.calc.exception.ErrorCode;
import com.currency.exch.discount.calc.exception.ExchangeException;
import com.currency.exch.discount.calc.utils.ExchangeConstant;


@Configuration
public class ExchangeDBConfig {
	
	@Bean
    DataSource dataSource() {
		String dbUser = ExchangeConstant.DB_USER_NAME;
		String dbPassword = ExchangeConstant.DB_PASSWORD;
		String driverClassName = ExchangeConstant.DB_DRIVER_CLASS_NAME;
		DriverManagerDataSource ds = new DriverManagerDataSource(getDBUrl(), dbUser, dbPassword);
		try {
			ds.setDriverClassName(driverClassName);
		} catch (Exception e) {
			throw new ExchangeException(ErrorCode.ERR002.getErrorCode(), ErrorCode.FATAL,
					ErrorCode.ERR002.getErrorMessage(), e);
		}
		try {
			ds.getConnection().close();
		} catch (SQLException e) {
			throw new ExchangeException(ErrorCode.ERR002.getErrorCode(), ErrorCode.FATAL,
					ErrorCode.ERR002.getErrorMessage(), e);
		}
		return ds;
	}

	private String getDBUrl() {
		String dbHost = ExchangeConstant.DB_HOST;
		String dbPort = ExchangeConstant.DB_PORT;
		String dbName = ExchangeConstant.DB_NAME;
		String dbUrlPrefix = ExchangeConstant.DB_URL_PREFIX;
		StringBuilder baseUrl = new StringBuilder(dbUrlPrefix);
		baseUrl.append(dbHost);
		baseUrl.append(ExchangeConstant.COLON);
		baseUrl.append(dbPort);
//		baseUrl.append(EMPConstant.COLON);
		baseUrl.append(dbName);
		return baseUrl.toString();
	}
	
	public boolean isPrimaryDbAvailable() {
        try (var conn = dataSource().getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            System.err.println("Primary database connection failed. Switching to fallback.");
            return false;
        }
    }
}
