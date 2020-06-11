package com.cognis.app.dao;

import java.util.List;

import com.cognis.app.model.VendorModel;

public interface VendorDao {
	
	public void  addVendor(VendorModel model) throws Exception;
	
	public void updateVendorByVendorID(VendorModel model);
	
	public List<VendorModel> getVendorByVendorID(VendorModel vendorModel);

	public static final String SAVE_VENDOR =" INSERT INTO FREELANCE_VENDOR (PAN_NO,USER_FK,OTHER_CARD_NO) VALUES(?,?,?)";	
	
	public static final String GET_VENDOR = "SELECT * FROM FREELANCE_VENDOR FV LEFT OUTER JOIN ADDRESS_INFO AI ON AI.USER_FK=FV.USER_FK"
										  + " WHERE FV.USER_FK = ?";
	
	public static final String UPDATE_VENDOR = "UPDATE FREELANCE_VENDOR SET  PAN_NO=?,OTHER_CARD_NO=?  WHERE USER_FK = ?";
	
	
	public static final String UPDATE_VALIDATION="UPDATE USER_INFO SET VERIFICATION_STATUS='V' WHERE USER_ID=?";

    
	
	
}
