package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.UserDao;
import com.cognis.app.dao.impl.LoginDaoImpl.UserLoginMapper;
import com.cognis.app.exception.UserAlreadyExist;
import com.cognis.app.model.RoleModel;
import com.cognis.app.model.UserModel;
import com.cognis.app.web.utils.Utils;

public class UserDaoImpl extends AppBaseDao implements UserDao {

	public static int getroleid=0;
	
	@Override
	public UserModel adduserdtlsJdbc(UserModel usermodel) throws Exception {
		
		Random rm=new Random();
		int rendom=rm.nextInt(1000);
		logger.info("  Inside adduserdtlsJdbc() ");
		
		int userpk=0;
		logger.info("usermodel   ==>   "+ usermodel.toString());
		/*Insert Into Address Table*/ 
		//TODO need to implement transaction 
		try {
			 	getJdbcTemplate().update(ADD_USER_DTLS,
			 			new Object[] {usermodel.getName(),usermodel.getPhone(),
			 					usermodel.getRole().getRoleId(),usermodel.getEmail(),usermodel.getPassword()});
		 
			 	List<UserModel> list =getJdbcTemplate().query(GET_PK_NO,
						new Object[] { usermodel.getPhone(),usermodel.getPassword() },new UserPkNumber()); 
			 	userpk= list.get(0).getUserId();
			 
			 	getJdbcTemplate().update(ADD_TOKEN,
			 			new Object[] {rendom , userpk});

		}
		catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			throw new UserAlreadyExist();
		}
		
		usermodel.setUserId(userpk);
		usermodel.setVerificationCode(rendom);
		
		return usermodel;
		
	}
  
	@Override
	public int validationJdbc(UserModel usermodel) {
		logger.info("In side validationJdbc usermodel is " +usermodel);
		logger.info("In side validationJdbc  usermodel.getPhone() is " + usermodel.getPhone());

		logger.info("In side validationJdbc usermodel.getVerificationCode() is " + usermodel.getVerificationCode());
		int count=getJdbcTemplate().queryForInt(VALIDATE_TOKEN, new Object[] { usermodel.getPhone(),usermodel.getVerificationCode() });
		
		if(count>0) {
			
			getJdbcTemplate().update(UPDATE_VERIFICATION_STATUS,
					new Object[] { usermodel.getPhone() });

		}
		logger.info("In side validationJdbc count is " +count);
		return count;
	}
	
	
	
   
	@Override
	public void updateUserdtlsJdbc(UserModel usermodel) {
		
		System.out.println("updateUserdtlsJdbc() called...");
		
		getJdbcTemplate().update(UPDATE_USER_INFO,
				new Object[] {usermodel.getName(),
						usermodel.getRole().getRoleId(),usermodel.getEmail(),
						usermodel.getUpdatedBy(),(new java.sql.Timestamp(new java.util.Date().getTime())),usermodel.getPhone()});
		
		
	}
	
	
	class UserPkNumber implements RowMapper {

		@Override
		public UserModel mapRow(ResultSet rs, int rowmapper) throws SQLException {
			
			UserModel userModel=new UserModel();
			
						userModel.setUserId(rs.getInt("USER_ID"));
						
			
			return userModel;
		}
		
	} 

}
