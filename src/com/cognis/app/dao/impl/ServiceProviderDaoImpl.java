package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AddressDao;
import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.ServiceProviderDao;
import com.cognis.app.exception.ServiceProviderDetailsAlreadyExistException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.ServiceProviderModel;

public class ServiceProviderDaoImpl extends AppBaseDao implements ServiceProviderDao{

	@Override
	public void addServiceProviderJdbc(ServiceProviderModel serviceProvider) throws Exception {
			try {
			getJdbcTemplate().update(ADD_SERVICE_PROVIDER,
					new Object[] {serviceProvider.getGstNumber(),serviceProvider.getBusinessRegisistrationId(),
							serviceProvider.getSeatingCapacity(),serviceProvider.getUserId()});
			if(serviceProvider.getAddress()!=null)
			{
				 serviceProvider.getAddress().setUserId(serviceProvider.getUserId());
				 AddressDao addressObj = (AddressDao) AppBeanFactory.getBean("addressdao");
				 addressObj.addAddress(serviceProvider.getAddress());
			}
			}catch (DataIntegrityViolationException e) {
				throw new ServiceProviderDetailsAlreadyExistException();
			}
	}

	@Override
	public List<ServiceProviderModel> getservicePorviderJdbc(ServiceProviderModel serviceProvider) {
		
		List<ServiceProviderModel> list = getJdbcTemplate().query(GET_SERVICE_PROVIDER_DTLS, 
						new Object[] { serviceProvider.getUserId() },  new ServiceProviderRowMapper());
			
		return list;
	}

	@Override
	public void updateServiceProvider(ServiceProviderModel serviceProvider) {
		 
		getJdbcTemplate().update(UPDATE_SERVICE_PROVIDER_DTLS, new Object[] { serviceProvider.getGstNumber(),serviceProvider.getBusinessRegisistrationId(),
				serviceProvider.getSeatingCapacity(),serviceProvider.getUserId() });
		
	}

	class ServiceProviderRowMapper implements RowMapper {

		@Override
		public ServiceProviderModel mapRow(ResultSet rs, int rowmapper) throws SQLException {
			
			ServiceProviderModel serviceProviderModel=new ServiceProviderModel();
			AddressModel addModel=new AddressModel(); 
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
	
}
