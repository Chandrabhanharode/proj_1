package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookingModel extends BaseModel{

	private Integer serviceId;
	private Integer customerId;
	private Integer serviceProvideId;
	private String bookingRate;
	private String bookingStatus;
	private String paymentStatus;
	private String bookingStartTime;
	private String bookingEndTime;
	private String bookingDate;
	
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getServiceProvideId() {
		return serviceProvideId;
	}
	public void setServiceProvideId(Integer serviceProvideId) {
		this.serviceProvideId = serviceProvideId;
	}
	public String getBookingRate() {
		return bookingRate;
	}
	public void setBookingRate(String bookingRate) {
		this.bookingRate = bookingRate;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getBookingStartTime() {
		return bookingStartTime;
	}
	public void setBookingStartTime(String bookingStartTime) {
		this.bookingStartTime = bookingStartTime;
	}
	public String getBookingEndTime() {
		return bookingEndTime;
	}
	public void setBookingEndTime(String bookingEndTime) {
		this.bookingEndTime = bookingEndTime;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	@Override
	public String toString() {
		return "BookingModel [serviceId=" + serviceId + ", customerId=" + customerId + ", serviceProvideId="
				+ serviceProvideId + ", bookingRate=" + bookingRate + ", bookingStatus=" + bookingStatus
				+ ", paymentStatus=" + paymentStatus + ", bookingStartTime=" + bookingStartTime + ", bookingEndTime="
				+ bookingEndTime + ", bookingDate=" + bookingDate + "]";
	}
	
}
