package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AddressDao;
import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.CustomerDao;
import com.cognis.app.exception.CustomerDetailsAlreadyExistException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.CustomerModel;

public class CustomerDaoImpl extends AppBaseDao implements CustomerDao{

	@Override
	public void addcustomerJdbc(CustomerModel customermodel) throws Exception {
		try {
		getJdbcTemplate().update(ADD_CUSTOMER,
				new Object[] {customermodel.getUserId()});
		if(customermodel.getAddress()!=null) {
			customermodel.getAddress().setUserId(customermodel.getUserId());
			 AddressDao addressObj = (AddressDao) AppBeanFactory.getBean("addressdao");
			 addressObj.addAddress(customermodel.getAddress());
		}
		}catch (DataIntegrityViolationException e) {
			throw new CustomerDetailsAlreadyExistException();
		}
	}

	@Override
	public void updateCustomeJdbc(CustomerModel customermodel) {
		/*getJdbcTemplate().update(UPDATE_CUSTOMER_INFO,
				new Object[] {customermodel.getAdharCard(),customermodel.getUserId()});*/
	}

	@Override
	public List<CustomerModel> getCustomerDtls(CustomerModel customermodel) {
		
		System.out.println("getCustomerDtls() called...");
		
		List<CustomerModel> list = getJdbcTemplate().query(GET_CUSTOMER, new Object[] { customermodel.getUserId() },
				new CustomerMapper());
		return list;
	}

	class CustomerMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowmapper) throws SQLException {
			
			CustomerModel customerModel= new CustomerModel();
			AddressModel addModel= new AddressModel();
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
			
			customerModel.setAddress(addModel);
			
			return customerModel;
		}
		
	}
	
	
}
