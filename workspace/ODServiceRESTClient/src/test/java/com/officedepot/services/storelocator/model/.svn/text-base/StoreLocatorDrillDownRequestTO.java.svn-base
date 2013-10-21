package com.officedepot.services.storelocator.model;

import org.apache.commons.lang.StringUtils;

public class StoreLocatorDrillDownRequestTO extends StoreRequestTO {
	private String state;
	private String city;

	public StoreLocatorDrillDownRequestTO(String state) {
		super();
		this.state = state;
	}

	public StoreLocatorDrillDownRequestTO(String state, String city) {
		super();
		this.state = state;
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String drillDownType(){
        String type = "UNIQUE_CITIES";
		if (StringUtils.isNotBlank(this.city)){
			type = "STORES";
		}
	   return type;	
	}

	@Override
	public String getMethod() {
		return "getlist";
	}
}
