package com.cognis.app.model;

import java.util.List;

public class BaseModel {

	private Boolean status;
	private String updateddate;
	private String createddate;
	private String message;
	private Link nextLink;
	private String apiToken;
	private String statusCode;
	private List<Link> links ;
	
	
	public Link getNextLink() {
		return nextLink;
	}
	public void setNextLink(Link nextLink) {
		this.nextLink = nextLink;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public String getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getApiToken() {
		return apiToken;
	}
	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BaseModel [status=" + status + ", updateddate=" + updateddate + ", createddate=" + createddate
				+ ", message=" + message + ", nextLink=" + nextLink + ", apiToken=" + apiToken + ", links=" + links
				+ "]";
	}
	
}
