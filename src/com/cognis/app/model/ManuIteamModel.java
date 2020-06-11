package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ManuIteamModel extends BaseModel{

	private Integer manuId;
	private Integer userId;
	private  Integer saloonServiceId;
	private String price;
	private String description;
	private String activeInactive;
	private String startTime;
	private String endTime;
	private Integer sequenceNo; 
	
	
	public Integer getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getManuId() {
		return manuId;
	}
	public void setManuId(Integer manuId) {
		this.manuId = manuId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSaloonServiceId() {
		return saloonServiceId;
	}
	public void setSaloonServiceId(Integer saloonServiceId) {
		this.saloonServiceId = saloonServiceId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getActiveInactive() {
		return activeInactive;
	}
	public void setActiveInactive(String activeInactive) {
		this.activeInactive = activeInactive;
	}
	
	@Override
	public String toString() {
		return "ManuIteamModel [manuId=" + manuId + ", userId=" + userId + ", saloonServiceId=" + saloonServiceId
				+ ", price=" + price + ", description=" + description + ", activeInactive=" + activeInactive + "]";
	}
	
	
}
