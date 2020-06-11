package com.cognis.app.dao;

import java.util.List;

import com.cognis.app.model.AddressModel;
import com.cognis.app.model.UserModel;

public interface AddressDao {
    
	public void addAddress(AddressModel addressModel) throws Exception;
	
	public List<AddressModel> getAddressJdbc(AddressModel addressModel);
	
	public void updateAddress(AddressModel addressModel);
	
	
	public final String  ADD_ADDRESS="INSERT INTO ADDRESS_INFO(ADDRESS_1,ADDRESS_2,STATE,DISTRICT,CITY,POSTAL_CODE,"
									+ "LATITUDE,LONGITUDE,USER_FK,CREATE_BY) VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	public final String GET_ADDRESS_DTLS="SELECT * "
			+ " FROM ADDRESS_INFO ASS WHERE USER_FK=?";
	
	public final String  UPDATE_ADDRESS_INFO="UPDATE ADDRESS_INFO SET ADDRESS_1 = ?,"
			+ "ADDRESS_2=?,STATE=?, DISTRICT = ?,CITY=?,POSTAL_CODE=?,LATITUDE=?,"
			+ "LONGITUDE=?,UPDATE_BY=? WHERE USER_FK=?";
	
	
	public final String  UPDATE_VALIDATION="UPDATE USER_INFO SET VERIFICATION_STATUS='V' WHERE USER_ID=?";
}
