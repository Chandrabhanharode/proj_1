package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"userId","serviceProviderId","gstNumber","businessRegisistrationId","seatingCapacity","address"})
public class ServiceProviderModel extends BaseModel{

	private Integer userId;
	private String gstNumber;
	private String businessRegisistrationId; 
	private Integer serviceProviderId;
	private String seatingCapacity; 
	private AddressModel address;
	private UserModel userModel;
	
	public String getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(String seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getBusinessRegisistrationId() {
		return businessRegisistrationId;
	}
	public void setBusinessRegisistrationId(String businessRegisistrationId) {
		this.businessRegisistrationId = businessRegisistrationId;
	}
	public Integer getServiceProviderId() {
		return serviceProviderId;
	}
	public void setServiceProviderId(Integer serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	public AddressModel getAddress() {
		return address;
	}
	public void setAddress(AddressModel address) {
		this.address = address;
	}
	
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	@Override
	public String toString() {
		return "ServiceProviderModel [userId=" + userId + ", gstNumber=" + gstNumber + ", businessRegisistrationId="
				+ businessRegisistrationId + ", serviceProviderId=" + serviceProviderId + "]";
	}
	
	
	
	
}
