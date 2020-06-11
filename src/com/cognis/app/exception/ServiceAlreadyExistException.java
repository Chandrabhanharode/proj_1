package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class ServiceAlreadyExistException extends Exception{

	public ServiceAlreadyExistException() {
		super(ErrorCode.SERVICE_ALREADY_EXIST.getErrorCode());
	}
	
}
