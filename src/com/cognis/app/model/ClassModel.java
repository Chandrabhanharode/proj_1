package com.cognis.app.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClassModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String clsName;
	private String startDate;
	private String endDate;
	private String chengDate;
	
	public String getClsName() {
		return clsName;
	}
	public void setClsName(String clsName) {
		this.clsName = clsName;
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
	public String getChengDate() {
		return chengDate;
	}
	public void setChengDate(String chengDate) {
		this.chengDate = chengDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "SCHClassesDtlsModel [clsName=" + clsName + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", chengDate=" + chengDate + "]";
	}
	
}
