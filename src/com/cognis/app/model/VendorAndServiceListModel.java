package com.cognis.app.model;

import java.util.List;

public class VendorAndServiceListModel extends BaseModel {
	List<VendorModel> vendorModleList;
	List<ServiceProviderModel> ServiceProviderModel;
     
	public List<VendorModel> getVendorModleList() {
		return vendorModleList;
	}

	public void setVendorModleList(List<VendorModel> vendorModleList) {
		this.vendorModleList = vendorModleList;
	}

	public List<ServiceProviderModel> getServiceProviderModel() {
		return ServiceProviderModel;
	}

	public void setServiceProviderModel(List<ServiceProviderModel> serviceProviderDetailsModel) {
		this.ServiceProviderModel = serviceProviderDetailsModel;
	}

	@Override
	public String toString() {
		return "VendorAndServiceListModel [vendorModleList=" + vendorModleList + ", ServiceProviderModel="
				+ ServiceProviderModel + "]";
	}
	
}
