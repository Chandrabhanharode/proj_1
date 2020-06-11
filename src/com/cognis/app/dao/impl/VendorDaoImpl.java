package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AddressDao;
import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.VendorDao;
import com.cognis.app.exception.VendorDetailsAlreadyExistException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.VendorModel;

public class VendorDaoImpl extends AppBaseDao implements VendorDao{
	
	
	@Override
	public void  addVendor(VendorModel model) throws Exception {
		logger.info("Inside Dao Impl addVendor");
		try {
			getJdbcTemplate().update(SAVE_VENDOR,
					new Object[] {model.getPanNo(),model.getUserId(),model.getOtherCardNo()});

			getJdbcTemplate().update(UPDATE_VALIDATION,
					new Object[] {model.getUserId()});

			if(model.getAddress()!=null) {
				
					model.getAddress().setUserId(model.getUserId());
					AddressDao addressObj = (AddressDao) AppBeanFactory.getBean("addressdao");
					addressObj.addAddress(model.getAddress());
			}
		}catch (DataIntegrityViolationException e){
			throw new VendorDetailsAlreadyExistException();
		}
	}
	
	@Override
	public List<VendorModel> getVendorByVendorID(VendorModel vendorModel){
		
		logger.info("getVendorByVendorID() called..");
		
		List<VendorModel> list = getJdbcTemplate().query(GET_VENDOR, new Object[] { vendorModel.getUserId() },
														new VendorMapper());
		
		return list;
	}
	
	public void updateVendorByVendorID(VendorModel model) {
		
		logger.info("updateVendorByVendorID() call..");
		
		 getJdbcTemplate().update(UPDATE_VENDOR, new Object[] { model.getPanNo(),model.getOtherCardNo(),
				                   model.getUserId() });
	
	}
     
	class VendorMapper implements RowMapper {
		public VendorModel mapRow(ResultSet rs, int rowNum) throws SQLException {

			VendorModel model = new VendorModel();
			AddressModel addModel = new AddressModel();
			model.setPanNo(rs.getString("PAN_NO"));
			model.setOtherCardNo(rs.getString("OTHER_CARD_NO"));
			//model.setS
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
	
}
