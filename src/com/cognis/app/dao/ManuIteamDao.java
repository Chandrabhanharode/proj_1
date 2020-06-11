package com.cognis.app.dao;

import java.util.List;

import com.cognis.app.model.ManuIteamModel;

public interface ManuIteamDao {

	public void addManuIteam(ManuIteamModel manuIteamModel);
	
	public List<ManuIteamModel> getManuIteamList(ManuIteamModel manuIteamModel);
	
	public void updateManuItem(ManuIteamModel manuIteamModel);
	
	public final String ADD_MANU_DTLS="INSERT INTO MANU_TABLE (USER_FK,SERVICE_FK,PRICE,DESCRIPTION,ACTIVE_IN_STATUS,START_TIME,END_TIME)"
			+ " VALUES(?,?,?,?,?,?,?)";

	public final String GET_MANU_LIST="SELECT * FROM MANU_TABLE WHERE USER_FK=?";

	public final String UPDATE_MANU_ITEAM="UPDATE MANU_TABLE SET PRICE=?,DESCRIPTION=?,ACTIVE_IN_STATUS=?,START_TIME=?,"
			+ "END_TIME=? WHERE MANU_ID=?";

}
