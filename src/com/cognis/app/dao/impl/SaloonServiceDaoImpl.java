package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.SaloonServiceDao;
import com.cognis.app.exception.ServiceAlreadyExistException;
import com.cognis.app.model.SaloonServiceModel;

public class SaloonServiceDaoImpl extends AppBaseDao implements SaloonServiceDao{

	@Override
	public void addSaloonService(SaloonServiceModel saloonModel) throws Exception {
		
		try {
			
		getJdbcTemplate().update(ADD_SALOON_SERVICE,
				new Object[] {saloonModel.getServiceName(),saloonModel.getDescription() });
		
		}catch (DataIntegrityViolationException e) {
			throw new ServiceAlreadyExistException();
		}
	}

	@Override
	public List<SaloonServiceModel> getSaloonService() {
		
		List<SaloonServiceModel> list = getJdbcTemplate().query(GET_SALOON_SERVICE, new Object[] { },
				new SaloonServiceRowMapper());
		
		return list;
	}

	
	@Override
	public void updateSaloonService(SaloonServiceModel serviceModel) {
		
		getJdbcTemplate().update(UPDATE_SALOON_SERVICE,
				new Object[] {serviceModel.getServiceName(),serviceModel.getActiveInactive(),serviceModel.getDescription(),serviceModel.getSaloonServiceId() });
		
	}
	
	@Override
	public void deleteSaloonService(SaloonServiceModel serviceModel) {
		
		getJdbcTemplate().update(DELETE_SALOON_PROVIDER,
				new Object[] {serviceModel.getSaloonServiceId() });
		
	}
	
	class SaloonServiceRowMapper implements RowMapper{

		@Override
		public SaloonServiceModel mapRow(ResultSet rs, int rowmapper) throws SQLException {
		
			SaloonServiceModel model=new SaloonServiceModel();
			
			model.setSaloonServiceId(rs.getInt("SERVICE_ID"));
			model.setServiceName(rs.getString("SERVICE_NAME"));
			model.setDescription(rs.getString("DESCRIPTION"));
			model.setActiveInactive(rs.getString("SERVICE_STATUS"));
			model.setSequenceNo(rowmapper+1);
			
			return model;
		}
		
	}

	
}
