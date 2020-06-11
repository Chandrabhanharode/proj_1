package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"addressId","userId","address1","address2","state","district","city","state","postalcode","lattitude","longitude","createdby","updateby","createdBy","updatedBy"})
public class AddressModel extends BaseModel{

	private Integer addressId;
	private Integer userId;
	private String address1;
	private String address2;
	private String state;
	private String district;
	private String city;
	private String postalcode;
	private String createdby;
	private String updateby;


	private String latitude;
	private String longitude;
	
	
	private String createdBy;
	private String updatedBy;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "AddressModel [addressId=" + addressId + ", userId=" + userId + ", address1=" + address1 + ", address2="
				+ address2 + ", district=" + district + ", city=" + city + ", postalcode=" + postalcode + ", createdby="
				+ createdby + ", updateby=" + updateby + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", state=" + state + ", createdBy=" + createdBy + ", apiToken=" + updatedBy + "]";
	}
	
}
