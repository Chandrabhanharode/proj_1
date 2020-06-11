package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.cognis.app.web.filter.ResponseFilter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"serviceId","name","activeInactive","description"})
public class SaloonServiceModel extends BaseModel{

	
	private Integer saloonServiceId;
	private String serviceName;
	private String description;
	private String activeInactive;
	private Integer sequenceNo;
	private String  imageUrl;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl=ResponseFilter.REUQEST_URL+"/"+imageUrl;
	}
	public Integer getSequenceNo() {
		return sequenceNo;
	}
	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}
	public Integer getSaloonServiceId() {
		return saloonServiceId;
	}
	public void setSaloonServiceId(Integer saloonServiceId) {
		this.saloonServiceId = saloonServiceId;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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
	
	
	

	
}
