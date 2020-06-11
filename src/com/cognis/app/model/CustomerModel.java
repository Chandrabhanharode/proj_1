package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"customurId","userId","adharCard","address"})
public class CustomerModel extends BaseModel{

	private Integer customurId;
	private Integer userId;
	private AddressModel address;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCustomurId() {
		return customurId;
	}

	public void setCustomurId(Integer customurId) {
		this.customurId = customurId;
	}
	
	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "CustomerModel [customurId=" + customurId + ", userId=" + userId + ", address=" + address + "]";
	}

}
