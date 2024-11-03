package com.currency.exch.discount.calc.exception;

public enum ErrorCode {
	FATAL,
	ERR000( "ERR.000", "The Web service authentication is invalid."),
	ERR001( "ERR.001", "Bad Request."),
	ERR002( "ERR.002", "Database connection issue."),
	ERR003( "ERR.003", "Date format error."),
	ERR004( "ERR.004", "Ineternal server error."),
	HTTP_CODE_500( "500", "Error when processing the request.");
	
	private String errorCode;
	private String errorMessage;
	private ErrorCode() {
		
	}
	private ErrorCode(String code, String message) {
		this.errorCode = code;
		this.errorMessage = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}