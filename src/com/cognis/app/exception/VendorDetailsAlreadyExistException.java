package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class VendorDetailsAlreadyExistException extends Exception{

	
	public VendorDetailsAlreadyExistException() {
	super(ErrorCode.VENDOR_DETAILS_ALREADY_EXIST.getErrorCode());
	}
}
