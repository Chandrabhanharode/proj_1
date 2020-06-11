package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class AddressNotFoundException extends Exception{

	public AddressNotFoundException() {
		super(ErrorCode.ADDRESS_NOT_AVAILABLE.toString());
	}
	
}
