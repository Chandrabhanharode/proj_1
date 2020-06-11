package com.cognis.app.dao;

import java.util.List;

import com.cognis.app.model.CustomerModel;

public interface CustomerDao {

	public void addcustomerJdbc(CustomerModel customermodel) throws Exception;
	
	public void updateCustomeJdbc(CustomerModel customermodel);
	
	public List<CustomerModel> getCustomerDtls(CustomerModel customermodel);

	public final String ADD_CUSTOMER="INSERT INTO CUSTOMER_INFO(USER_FK)"
			+ 			"VALUES(?)";
	
	public final String GET_CUSTOMER="SELECT * FROM CUSTOMER_INFO CF LEFT OUTER JOIN ADDRESS_INFO AI ON "
									+ " AI.USER_FK=CF.USER_FK WHERE CF.USER_FK=?";
	
	/*public final String  UPDATE_CUSTOMER_INFO="UPDATE CUSTOMER_INFO SET " +   
			                                  " WHERE USER_FK=?";*/
	
}
