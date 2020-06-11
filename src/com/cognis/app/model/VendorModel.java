package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"userId","vendorId","panNo","otherCardNo","address"})
public class VendorModel extends BaseModel {

	private String panNo;
	private String otherCardNo;
    private Integer userId;
    private String vendorId;
    //private String Capacity;
    private AddressModel address;
    private UserModel userModel;
    
	public String getPanNo() {
	return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getOtherCardNo() {
		return otherCardNo;
	}

	public void setOtherCardNo(String otherCardNo) {
		this.otherCardNo = otherCardNo;
	}
	

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "VendorModel [panNo=" + panNo + ", otherCardNo=" + otherCardNo + ", userId=" + userId + ", vendorId="
				+ vendorId + "]";
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

}
