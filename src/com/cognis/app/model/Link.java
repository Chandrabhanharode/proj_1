package com.cognis.app.model;

import com.cognis.app.web.filter.ResponseFilter;

public class Link {

	private String merthod;
	private String url;
	
	public Link(String method ,String url) {
		this.merthod=method;
		
		this.url=ResponseFilter.REUQEST_URL+url;
	}
	
	public String getMerthod() {
		return merthod;
	}
	
	public String getUrl() {
		return url;
	}
	
	
	
}
