package com.cognis.app.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"status","errorCode","errorMessage"})
public class SaloonErrorModel {
	/**
	 * Error Code like 404 or 500
	 */
	private int errorCode;
	/**
	 * Detail message root cause of exception.
	 */
	private String errorMessage;
	/**
	 * Status like failed or any other
	 */
	private boolean status;
	

	public SaloonErrorModel(int errorCode, String errorMessage, boolean status) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

}
