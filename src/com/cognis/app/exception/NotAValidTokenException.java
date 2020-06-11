package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class NotAValidTokenException extends Exception{

	public NotAValidTokenException() {
		super(ErrorCode.INVALID_TOKEN.getErrorCode());
	}
	
	
}
