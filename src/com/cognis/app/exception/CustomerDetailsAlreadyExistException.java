package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class CustomerDetailsAlreadyExistException extends Exception{

	public CustomerDetailsAlreadyExistException()
	{
		super(ErrorCode.CUSTOMER_DETAILS_ALREADY_EXIST.getErrorCode());
	}
	
	
}
