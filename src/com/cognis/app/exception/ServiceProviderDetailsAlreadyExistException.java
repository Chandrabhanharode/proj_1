package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class ServiceProviderDetailsAlreadyExistException extends Exception{

	public ServiceProviderDetailsAlreadyExistException() {
		super(ErrorCode.SERVICE_PROVIDER_DETAILS_ALREADY_EXIST.getErrorCode());
	}
	
}
