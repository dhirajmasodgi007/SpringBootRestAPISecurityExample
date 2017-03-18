package com.spring.security.restful.springboot.util;

public class CustomErrorMessage {
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public CustomErrorMessage(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	

}
