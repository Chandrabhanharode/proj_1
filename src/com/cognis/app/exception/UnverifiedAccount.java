package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class UnverifiedAccount extends Exception{

	public UnverifiedAccount() {
		super(ErrorCode.VARIFICATION_FAILED.getErrorCode());
	}
	
}
