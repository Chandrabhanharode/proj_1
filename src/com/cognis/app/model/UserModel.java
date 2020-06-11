package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"userId","name","phone","email","password","createddate","verificationStatus","activeInactive","lastLoginDate","token","createdBy","updatedBy","role","status","message","apiToken","statusCode","verificationCode","nextLink","links"})
public class UserModel extends BaseModel{

	private Integer userId;
	private String name;
	private String phone;
	private String email;
	private String password;
	private String verificationStatus;
	private String activeInactive;
	private String lastLoginDate;
	private String createdBy;
	private String updatedBy;
	private Integer verificationCode;
	private RoleModel role;
	private AddressModel address;
	private CustomerModel customer; 

	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {  
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	public String getActiveInactive() {
		return activeInactive;
	}
	public void setActiveInactive(String activeInactive) {
		this.activeInactive = activeInactive;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public RoleModel getRole() {
		return role;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}
	public AddressModel getAddress() {
		return address;
	}
	public void setAddress(AddressModel address) {
		this.address = address;
	}
	public CustomerModel getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
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
	public Integer getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(Integer verificationCode) {
		this.verificationCode = verificationCode;
	}
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + ", verificationStatus=" + verificationStatus + ", activeInactive="
				+ activeInactive + ", lastLoginDate=" + lastLoginDate + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + ", verificationCode=" + verificationCode + ", role=" + role + ", address=" + address
				+ ", customer=" + customer + "]";
	}
	
	
}
