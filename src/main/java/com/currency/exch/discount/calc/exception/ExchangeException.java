package com.currency.exch.discount.calc.exception;

import com.currency.exch.discount.calc.utils.ExchangeConstant;

public class ExchangeException extends RuntimeException {

	private static final long serialVersionUID = -4211759203249691019L;

	private String code;
	private String message;
	
	public ExchangeException(Exception exception) {
		super(exception);
		StringBuilder builder = new StringBuilder(ErrorCode.HTTP_CODE_500.getErrorCode()).append(ExchangeConstant.HYPHEN)
				.append(ErrorCode.FATAL).append(ExchangeConstant.HYPHEN).append(ErrorCode.HTTP_CODE_500.getErrorMessage());
		this.code = ErrorCode.HTTP_CODE_500.getErrorCode();
		this.message = builder.toString();
	}
	
	public ExchangeException(String code, String message) {
		super(message);
		StringBuilder builder = new StringBuilder(code).append(ExchangeConstant.HYPHEN).append(message);
		this.code = code;
		this.message = builder.toString();
	}
	
	public ExchangeException(String code, ErrorCode severity, String message) {
		super(message);
		StringBuilder builder = new StringBuilder(code).append(ExchangeConstant.HYPHEN).append(severity).append(ExchangeConstant.HYPHEN).append(message);
		this.code = code;
		this.message = builder.toString();
	}
	
	public ExchangeException(String code, ErrorCode severity, String message, Exception exception) {
		super(exception);
		StringBuilder builder = new StringBuilder(code).append(ExchangeConstant.HYPHEN).append(severity).append(ExchangeConstant.HYPHEN).append(message);
		this.code = code;
		this.message = builder.toString();
	}

	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
