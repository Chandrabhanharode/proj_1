package com.cognis.app.dao;

import com.cognis.app.model.UserModel;

public interface UserDao {

	public UserModel adduserdtlsJdbc(UserModel usermodel) throws Exception;
	
	public int validationJdbc(UserModel usermodel); 

	public void updateUserdtlsJdbc(UserModel usermodel);
	
	public final String  ADD_USER_DTLS="INSERT INTO USER_INFO(USER_NAME,PHONE_NO,ROLE_VALUE,EMIL_ID,"
			+ "PASSWORD) "
			+ "VALUES(?,?,?,?,?)";  
	
	public final String GET_PK_NO="SELECT USER_ID FROM USER_INFO WHERE PHONE_NO=? AND PASSWORD=?";

	public final String ADD_TOKEN="INSERT INTO ACC_VERIFICATION_TOKEN (TOKEN_NUMBER,USER_FK) VALUES(?,?)";
	
	public final String VALIDATE_TOKEN="SELECT COUNT(*) FROM USER_INFO UI INNER JOIN ACC_VERIFICATION_TOKEN TN ON TN.USER_FK=UI.USER_ID WHERE UI.PHONE_NO=? AND TN.TOKEN_NUMBER=?";
	
	/*User This One User Info*/
	
	public final String UPDATE_VERIFICATION_STATUS="UPDATE USER_INFO SET VERIFICATION_STATUS='V' WHERE PHONE_NO=?  ";
	
	/*Queries For Update All User Info*/
	
	public final String  UPDATE_USER_INFO="UPDATE USER_INFO SET USER_NAME = ?,ROLE_VALUE=?, EMIL_ID = ?," + 
			                              "UPDATE_BY=?,UPDATEDDATE=? WHERE PHONE_NO=?";  
	
	
	
	


}
