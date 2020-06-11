package com.cognis.app.dao;

import java.util.List;

import com.cognis.app.model.SaloonServiceModel;

public interface SaloonServiceDao {

	public void addSaloonService(SaloonServiceModel saloonModel) throws Exception;
	
	public List<SaloonServiceModel> getSaloonService();
	
	public void updateSaloonService(SaloonServiceModel serviceModel);  
	
	public void deleteSaloonService(SaloonServiceModel serviceModel);
	
	public final String ADD_SALOON_SERVICE="INSERT INTO SERVICE (SERVICE_NAME,DESCRIPTION) VALUES(?,?)";

	public final String GET_SALOON_SERVICE="SELECT * FROM SERVICE";

	public final String UPDATE_SALOON_SERVICE="UPDATE SERVICE SET SERVICE_NAME=?,SERVICE_STATUS=?,DESCRIPTION=? WHERE SERVICE_ID=?";
	
	public final String DELETE_SALOON_PROVIDER="DELETE FROM SERVICE WHERE SERVICE_ID=?";


	
}
