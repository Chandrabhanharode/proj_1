package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.LoginDao;
import com.cognis.app.model.RoleModel;
import com.cognis.app.model.UserModel;
import com.cognis.app.web.utils.Utils;

public class LoginDaoImpl extends AppBaseDao implements LoginDao{

	
	
	@Override  
	public List<UserModel> loginJdbc(UserModel usermodel) {
		 
		 logger.info("getUserdtlsJdbc() called..."+USER_LOGIN_DTLS);
		 
		 List<UserModel> list =getJdbcTemplate().query(USER_LOGIN_DTLS,
					new Object[] { usermodel.getPhone(),usermodel.getPassword() },new UserLoginMapper());
		 //TODO need to update last login date
		 
		 return list;
	}
	class UserLoginMapper implements RowMapper {

		@Override
		public UserModel mapRow(ResultSet rs, int rowmapper) throws SQLException {
			
			UserModel userModel=new UserModel();
			RoleModel rolemodel=new RoleModel();
						userModel.setUserId(rs.getInt("USER_ID"));
						userModel.setName(rs.getString("USER_NAME"));
						userModel.setPhone(rs.getString("PHONE_NO"));
						userModel.setPassword(rs.getString("PASSWORD"));
						rolemodel.setRoleId(rs.getInt("ROLE_VALUE"));
						userModel.setEmail(rs.getString("EMIL_ID"));
						userModel.setLastLoginDate(Utils.formatDate(rs.getTimestamp("LAST_LOGIN_TIME")));
						userModel.setActiveInactive(rs.getString("ACTIVE_IN_STATUS"));
						userModel.setVerificationStatus(rs.getString("VERIFICATION_STATUS"));
						userModel.setCreatedBy(rs.getString("CREATE_BY"));
						userModel.setUpdatedBy(rs.getString("UPDATE_BY"));
						userModel.setCreateddate(Utils.formatDate(rs.getTimestamp("CREATEDDATE")));
						userModel.setUpdateddate(Utils.formatDate(rs.getTimestamp("UPDATEDDATE")));
						
						rolemodel.setRoleName(rs.getString("ROLE_TYPE"));
						rolemodel.setRemark(rs.getString("REMARK"));
			
			userModel.setRole(rolemodel);
			
			return userModel;
		}
		
	}
	@Override
	public UserModel updateToken(UserModel usermodel) {
		
		logger.info("updateToken() called..");
		
		String[] arrOfStr = Utils.genarateToken().split("-"); 
		Date lastLoginDate=(new java.sql.Timestamp(new java.util.Date().getTime()));
		
		getJdbcTemplate().update(LOGIN_TOKEN_LAST_LOGIN, new Object[] { lastLoginDate,
				arrOfStr[0],usermodel.getPhone(),usermodel.getPassword()});
		
		Utils.setToken(arrOfStr[0]);
		usermodel.setApiToken(arrOfStr[0]);
		usermodel.setLastLoginDate(Utils.formatDate(lastLoginDate)  );
		
		return usermodel;
	} 
}
