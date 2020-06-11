package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.ManuIteamDao;
import com.cognis.app.model.ManuIteamModel;

public class ManuItemDaoImpl extends AppBaseDao implements ManuIteamDao{

	@Override
	public void addManuIteam(ManuIteamModel manuIteamModel) {
		
		getJdbcTemplate().update(ADD_MANU_DTLS,
				new Object[] {manuIteamModel.getUserId(),manuIteamModel.getSaloonServiceId(),manuIteamModel.getPrice(),manuIteamModel.getDescription(),
						      manuIteamModel.getActiveInactive(),manuIteamModel.getStartTime(),manuIteamModel.getEndTime()});
		
	}

	@Override
	public List<ManuIteamModel> getManuIteamList(ManuIteamModel manuIteamModel) {
		
		List<ManuIteamModel> list= getJdbcTemplate().query(GET_MANU_LIST, new Object[] { manuIteamModel.getUserId() },
				new ManuItemRowMapper());
	 
		return list;
	}

	@Override
	public void updateManuItem(ManuIteamModel manuIteamModel) {
		
		getJdbcTemplate().update(UPDATE_MANU_ITEAM,
				new Object[] {manuIteamModel.getPrice(),manuIteamModel.getDescription(),
						      manuIteamModel.getActiveInactive(),manuIteamModel.getStartTime(),manuIteamModel.getEndTime(),manuIteamModel.getManuId()});
		
	}
	
	public class ManuItemRowMapper implements RowMapper{

		@Override
		public ManuIteamModel mapRow(ResultSet rs, int rowmapper) throws SQLException {
			
			ManuIteamModel model=new ManuIteamModel();
			
			  model.setUserId(rs.getInt("USER_FK"));
			  model.setSaloonServiceId(rs.getInt("SERVICE_FK"));
			  model.setManuId(rs.getInt("MANU_ID"));
			  model.setActiveInactive(rs.getString("ACTIVE_IN_STATUS"));
			  model.setPrice(rs.getString("PRICE"));
			  model.setDescription(rs.getString("DESCRIPTION"));
			  model.setEndTime(rs.getString("END_TIME"));
			  model.setStartTime(rs.getString("START_TIME"));
			  model.setSequenceNo(rowmapper+1);
			
			return model;
		}
		
	}

}
