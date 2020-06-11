package com.cognis.app.model;

import java.util.List;

public class ServiceProviderDtlsModel  extends BaseModel{

	private Integer servicePorviderId;
	private String servicePorviderName;
	private Integer serviceId;
	private String serviceName;
	private String price;
	private String startDate;
	private String endDate;
	private String capacity;
	private String bookingDate;
	private List<AvailableTimeSlot> slot;
	
	
	public Integer getServicePorviderId() {
		return servicePorviderId;
	}
	public void setServicePorviderId(Integer servicePorviderId) {
		this.servicePorviderId = servicePorviderId;
	}
	public String getServicePorviderName() {
		return servicePorviderName;
	}
	public void setServicePorviderName(String servicePorviderName) {
		this.servicePorviderName = servicePorviderName;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public List<AvailableTimeSlot> getSlot() {
		return slot;
	}
	public void setSlot(List<AvailableTimeSlot> slot) {
		this.slot = slot;
	}
	@Override
	public String toString() {
		return "ServiceProviderDtlsModel [servicePorviderId=" + servicePorviderId + ", servicePorviderName="
				+ servicePorviderName + ", serviceId=" + serviceId + ", serviceName=" + serviceName + ", price=" + price
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", capacity=" + capacity + ", bookingDate="
				+ bookingDate + ", slot=" + slot + "]";
	}
	
	
}
