package com.cognis.app.dao;

import com.cognis.app.model.ServiceProviderModel;
import com.cognis.app.model.VendorAndServiceListModel;
import com.cognis.app.model.VendorModel;

public interface VendorAndServiceListDao {

	public static final String GET_VENDOR = "SELECT * FROM FREELANCE_VENDOR FV LEFT OUTER JOIN ADDRESS_INFO AI ON AI.USER_FK=FV.USER_FK";
	public static final String GET_SERVICE_PROVIDER_DTLS = "SELECT * FROM SERVICE_PROVIDER SP "
			+ "LEFT OUTER JOIN ADDRESS_INFO AI ON AI.USER_FK=SP.USER_FK";

	public static final String SEARCH_VENDOR = " SELECT * FROM user_info UI JOIN service_provider FV ON UI.USER_ID=FV.USER_FK JOIN address_info AI ON FV.USER_FK=AI.USER_FK WHERE 1=1 ";
	public static final String SEARCH_SERVICE_PROVIDER = " SELECT * FROM user_info UI JOIN freelance_vendor FV ON UI.USER_ID=FV.USER_FK JOIN address_info AI ON FV.USER_FK=AI.USER_FK WHERE 1=1 ";

	public VendorAndServiceListModel getVendorAndServiceList();

	public ServiceProviderModel searchtVendor(String filterBy, String value);

	public VendorModel searchtServiceprovider(String filterBy, String value);
}
