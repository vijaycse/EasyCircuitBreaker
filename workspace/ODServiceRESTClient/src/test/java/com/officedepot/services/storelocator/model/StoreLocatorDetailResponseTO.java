package com.officedepot.services.storelocator.model;

public class StoreLocatorDetailResponseTO extends StoreResponseTO {

	private StoreInfo storeInfo;

	public StoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	@Override
	public String getMapURL() {
		// Not supported Map, ignore it
		return "";
	}

	@Override
	public void setMapURL(String mapURL) {
		// Not supported Map, ignore it
	}
}
