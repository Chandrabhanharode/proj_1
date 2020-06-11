package com.cognis.app.dao;

import java.util.List;

import com.cognis.app.model.ServiceProviderModel;

public interface ServiceProviderDao {

	public void addServiceProviderJdbc(ServiceProviderModel serviceProvider) throws Exception;
	
	public List<ServiceProviderModel> getservicePorviderJdbc(ServiceProviderModel serviceProvider);
	
	public void updateServiceProvider(ServiceProviderModel serviceProvider);

	public final String ADD_SERVICE_PROVIDER="INSERT INTO SERVICE_PROVIDER "
			+ "(GST_NUMBER,BUSINESS_REGISISTRATION_ID,SEATING_CAPACITY,USER_FK) "
			+ "VALUES(?,?,?,?)";
	
	public final String GET_SERVICE_PROVIDER_DTLS ="SELECT * FROM SERVICE_PROVIDER SP "
			+ "LEFT OUTER JOIN ADDRESS_INFO AI ON AI.USER_FK=SP.USER_FK"
			+ " WHERE SP.USER_FK=?";
	
	public final String UPDATE_SERVICE_PROVIDER_DTLS="UPDATE SERVICE_PROVIDER SET "
			+ "GST_NUMBER=? , BUSINESS_REGISISTRATION_ID=?,SEATING_CAPACITY=? WHERE USER_FK=?";

	
}
