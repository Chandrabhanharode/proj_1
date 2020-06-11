package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.VendorAndServiceListDao;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.ServiceProviderModel;
import com.cognis.app.model.UserModel;
import com.cognis.app.model.VendorAndServiceListModel;
import com.cognis.app.model.VendorModel;

public class VendorAndServiceListDaoImpl extends AppBaseDao implements VendorAndServiceListDao {

	@Override
	public VendorAndServiceListModel getVendorAndServiceList() {
		logger.info("getVendorAndServiceList() called..");
		VendorAndServiceListModel model = new VendorAndServiceListModel();
		List<VendorModel> listOfVendor = getJdbcTemplate().query(GET_VENDOR, new Object[] {}, new VendorMapper());

		List<ServiceProviderModel> listOfService = getJdbcTemplate().query(GET_SERVICE_PROVIDER_DTLS, new Object[] {},
				new ServiceProviderRowMapper());
		model.setVendorModleList(listOfVendor);
		model.setServiceProviderModel(listOfService);
		return model;
	}

	class VendorMapper implements RowMapper {
		public VendorModel mapRow(ResultSet rs, int rowNum) throws SQLException {

			VendorModel model = new VendorModel();
			AddressModel addModel = new AddressModel();
			model.setPanNo(rs.getString("PAN_NO"));
			model.setOtherCardNo(rs.getString("OTHER_CARD_NO"));
			addModel.setAddress1(rs.getString("ADDRESS_1"));
			addModel.setAddress2(rs.getString("ADDRESS_2"));
			addModel.setState(rs.getString("STATE"));
			addModel.setDistrict(rs.getString("DISTRICT"));
			addModel.setCity(rs.getString("CITY"));
			addModel.setPostalcode(rs.getString("POSTAL_CODE"));
			addModel.setLatitude(rs.getString("LATITUDE"));
			addModel.setLongitude(rs.getString("LONGITUDE"));
			addModel.setCreatedby(rs.getString("CREATE_BY"));
			addModel.setUpdateby(rs.getString("UPDATE_BY"));

			model.setAddress(addModel);

			return model;
		}
	}

	class ServiceProviderRowMapper implements RowMapper {

		@Override
		public ServiceProviderModel mapRow(ResultSet rs, int rowmapper) throws SQLException {

			ServiceProviderModel serviceProviderModel = new ServiceProviderModel();
			AddressModel addModel = new AddressModel();
			serviceProviderModel.setGstNumber(rs.getString("GST_NUMBER"));
			serviceProviderModel.setBusinessRegisistrationId(rs.getString("BUSINESS_REGISISTRATION_ID"));
			serviceProviderModel.setSeatingCapacity(rs.getString("SEATING_CAPACITY"));
			serviceProviderModel.setServiceProviderId(rs.getInt("SERVICE_PROVIDER_ID"));
			addModel.setAddress1(rs.getString("ADDRESS_1"));
			addModel.setAddress2(rs.getString("ADDRESS_2"));
			addModel.setState(rs.getString("STATE"));
			addModel.setDistrict(rs.getString("DISTRICT"));
			addModel.setCity(rs.getString("CITY"));
			addModel.setPostalcode(rs.getString("POSTAL_CODE"));
			addModel.setLatitude(rs.getString("LATITUDE"));
			addModel.setLongitude(rs.getString("LONGITUDE"));
			addModel.setCreatedby(rs.getString("CREATE_BY"));
			addModel.setUpdateby(rs.getString("UPDATE_BY"));

			serviceProviderModel.setAddress(addModel);

			return serviceProviderModel;
		}
	}

	public VendorModel searchtServiceprovider(String filterBy, String value) {
		
		logger.info("searchtVendorAndService    DAO");
		logger.info("    filterBy   " + filterBy + "  value " + value);
		String sqlQuery = "";
		String where = "";
		List<VendorModel> vendorModel = new ArrayList<>();

		if (filterBy.equals("NAME"))
			where += " AND UI.USER_NAME = '" + value + "'";
		if (filterBy.equals("ADDRESS"))
			where += "AND  AI.ADDRESS_1 =" + value;
		if (filterBy.equals("SEATING_CAPACITY"))
			where += "AND SEATING_CAPACITY  like '%" + value + "%'";

		sqlQuery = SEARCH_VENDOR + where;
		logger.info(" SQL Query " + sqlQuery);

		vendorModel = getJdbcTemplate().query(sqlQuery, new Object[] {}, new VendorRowSearchMapper());

	
	return vendorModel.get(0);
	}

	public ServiceProviderModel searchtVendor(String filterBy, String value) {
		logger.info("searchtVendorAndService    DAO");
		logger.info("    filterBy   " + filterBy + "  value " + value);
		String sqlQuery = "";
		String where = "";
		List<ServiceProviderModel> serviceProviderModel = new ArrayList<>();

		if (filterBy.equals("NAME"))
			where += " AND UI.USER_NAME = '" + value + "'";
		if (filterBy.equals("ADDRESS"))
			where += "AND  AI.ADDRESS_1 =" + value;
		if (filterBy.equals("SEATING_CAPACITY"))
			where += "AND SEATING_CAPACITY  like '%" + value + "%'";

		sqlQuery = SEARCH_SERVICE_PROVIDER + where;
		logger.info(" SQL Query " + sqlQuery);

		serviceProviderModel = getJdbcTemplate().query(sqlQuery, new Object[] {}, new ServiceProviderRowSearchMapper());

		return serviceProviderModel.get(0);
	}

	class ServiceProviderRowSearchMapper implements RowMapper {

		@Override
		public ServiceProviderModel mapRow(ResultSet rs, int rowmapper) throws SQLException {

			ServiceProviderModel serviceProviderModel = new ServiceProviderModel();
			AddressModel addModel = new AddressModel();

			addModel.setAddress1(rs.getString("ADDRESS_1"));
			addModel.setAddress2(rs.getString("ADDRESS_2"));
			addModel.setState(rs.getString("STATE"));
			addModel.setDistrict(rs.getString("DISTRICT"));
			addModel.setCity(rs.getString("CITY"));
			addModel.setPostalcode(rs.getString("POSTAL_CODE"));
			addModel.setLatitude(rs.getString("LATITUDE"));
			addModel.setLongitude(rs.getString("LONGITUDE"));

			UserModel userModel = new UserModel();
			userModel.setUserId(rs.getInt("USER_ID"));
			userModel.setPhone(rs.getString("PHONE_NO"));
			userModel.setName(rs.getString("USER_NAME"));
			userModel.setEmail(rs.getString("EMIL_ID"));

			serviceProviderModel.setAddress(addModel);
			serviceProviderModel.setUserModel(userModel);
			return serviceProviderModel;
		}
	}
	class VendorRowSearchMapper implements RowMapper {

		@Override
		public VendorModel mapRow(ResultSet rs, int rowmapper) throws SQLException {

			VendorModel vendorModel = new VendorModel();
			AddressModel addModel = new AddressModel();

			addModel.setAddress1(rs.getString("ADDRESS_1"));
			addModel.setAddress2(rs.getString("ADDRESS_2"));
			addModel.setState(rs.getString("STATE"));
			addModel.setDistrict(rs.getString("DISTRICT"));
			addModel.setCity(rs.getString("CITY"));
			addModel.setPostalcode(rs.getString("POSTAL_CODE"));
			addModel.setLatitude(rs.getString("LATITUDE"));
			addModel.setLongitude(rs.getString("LONGITUDE"));

			UserModel userModel = new UserModel();
			userModel.setUserId(rs.getInt("USER_ID"));
			userModel.setPhone(rs.getString("PHONE_NO"));
			userModel.setName(rs.getString("USER_NAME"));
			userModel.setEmail(rs.getString("EMIL_ID"));

			vendorModel.setAddress(addModel);
			vendorModel.setUserModel(userModel);
			return vendorModel;
		}
	}
}
