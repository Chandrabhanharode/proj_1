package com.cognis.app.dao;

import java.util.List;

import com.cognis.app.model.UserModel;

public interface LoginDao {

	public List<UserModel> loginJdbc(UserModel usermodel);
	
	public UserModel updateToken(UserModel usermodel);
	
	public final String USER_LOGIN_DTLS=" SELECT UI.USER_ID, UI.USER_NAME,UI.PHONE_NO,UI.PASSWORD,UI.ROLE_VALUE,UI.EMIL_ID,"
			+ " UI.LAST_LOGIN_TIME,UI.ACTIVE_IN_STATUS,UI.VERIFICATION_STATUS,UI.CREATE_BY,"
			+ " UI.UPDATE_BY,UI.UPDATEDDATE,UI.CREATEDDATE,RI.ROLE_TYPE,RI.REMARK "
			+ " FROM USER_INFO UI  "
			+ " INNER JOIN  ROLE_INFO RI ON RI.ROLE_VALUE=UI.ROLE_VALUE  "
			+ " WHERE PHONE_NO=? AND PASSWORD=? ";
	
	public final String LOGIN_TOKEN_LAST_LOGIN="UPDATE USER_INFO SET LAST_LOGIN_TIME =? , LOGIN_TOKEN=? "
												+ "WHERE PHONE_NO=? AND PASSWORD=? ";
	
}
