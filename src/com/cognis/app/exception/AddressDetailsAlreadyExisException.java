package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class AddressDetailsAlreadyExisException extends Exception{

	public AddressDetailsAlreadyExisException() {
			super(ErrorCode.ADDRESS_DETAILS_ALREADY_EXIST.getErrorCode());
	}
	
}
