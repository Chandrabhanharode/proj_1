package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AddressDao;
import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.exception.AddressDetailsAlreadyExisException;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.UserModel;
import com.cognis.app.web.utils.Utils;

public class AddressDaoImpl extends AppBaseDao implements AddressDao{

	@Override
	public void addAddress(AddressModel addressModel) throws Exception{
		
		System.out.println("addAddress() called...");
		try {
			getJdbcTemplate().update(ADD_ADDRESS,
					new Object[] {addressModel.getAddress1(),addressModel.getAddress2(),addressModel.getState(),
							addressModel.getDistrict(),addressModel.getCity(),addressModel.getPostalcode(),addressModel.getLatitude(),
							addressModel.getLongitude(),addressModel.getUserId(),addressModel.getCreatedby() });
			
		}catch (DataIntegrityViolationException e) {
			throw new AddressDetailsAlreadyExisException();
		}
		
		
		getJdbcTemplate().update(UPDATE_VALIDATION,
				new Object[] {addressModel.getUserId()});
		
	}

	
	@Override
	public List<AddressModel> getAddressJdbc(AddressModel addressModel) {
		
        System.out.println("getUserdtlsJdbc() called..."+GET_ADDRESS_DTLS);
		 
		 List<AddressModel> list = getJdbcTemplate().query(GET_ADDRESS_DTLS, new Object[] { addressModel.getUserId() },
					new AddrerssMapper());
		 return list;
	}
	
	@Override
	public void updateAddress(AddressModel addressModel) {
		
		System.out.println("updateAddress() called...");
		
		getJdbcTemplate().update(UPDATE_ADDRESS_INFO,
				new Object[] {addressModel.getAddress1(),addressModel.getAddress2(),addressModel.getState(),
						addressModel.getDistrict(),addressModel.getCity(),addressModel.getPostalcode(),addressModel.getLatitude(),
						addressModel.getLongitude(),addressModel.getUpdateby(),addressModel.getUserId() });
	}
	
	class AddrerssMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowmapper) throws SQLException {
			
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
			addModel.setCreateddate(Utils.formatDate(rs.getTimestamp("CREATEDDATE")));
			addModel.setUpdateddate(Utils.formatDate(rs.getTimestamp("UPDATEDDATE")));
			
			return addModel;
		}
		
	}
	
}
