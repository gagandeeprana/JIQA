package com.jiqa.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Failed {

	@JsonProperty("error-code")
	private String code;
	
	@JsonProperty("error-message")
	private String message;

	public Failed(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
