package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class UserNotFountException extends Exception{
	
	public UserNotFountException() {
		super(ErrorCode.USER_NOT_FOUND.toString());
	}

}
