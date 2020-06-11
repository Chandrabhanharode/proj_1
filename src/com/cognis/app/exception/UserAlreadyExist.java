package com.cognis.app.exception;

import com.cognis.app.web.utils.ErrorCode;

public class UserAlreadyExist extends Exception{

	public UserAlreadyExist() {
		super(ErrorCode.USER_ALREADY_EXIST.getErrorCode());
	}
	
}
