package com.officedepot.services.storelocator.model;


public class InStorePickupInfo extends StoreInfo {

	private static final long serialVersionUID = 7165380641985589287L;
	
	private String sku;
	private int availableQty;
	private boolean available;
	private boolean lowStock;
	private String errorMsg; 

	
	
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public InStorePickupInfo(StoreInfo storeInfo, String sku, int availableQty, boolean available, boolean lowStock){
		super();
		this.sku = sku;
		this.availableQty = availableQty;
		this.setStoreId(storeInfo.getStoreId());
		this.setAddress(storeInfo.getAddress());
		this.setAddress2(storeInfo.getAddress2());
		this.setCity(storeInfo.getCity());
		this.setState(storeInfo.getState());
		this.setZip(storeInfo.getZip());
		this.setPhone(storeInfo.getPhone());
		this.setHoursMon(storeInfo.getHoursMon());
		this.setHoursTue(storeInfo.getHoursTue());
		this.setHoursWed(storeInfo.getHoursWed());
		this.setHoursThu(storeInfo.getHoursThu());
		this.setHoursFri(storeInfo.getHoursFri());
		this.setHoursSat(storeInfo.getHoursSat());
		this.setHoursSun(storeInfo.getHoursSun());
		this.setNewStore(storeInfo.getNewStore());
		this.setInkRefill(storeInfo.getInkRefill());
		this.setFurnitureShowroom(storeInfo.getFurnitureShowroom());
		// storeinfo has distace getter as string type... 
		this.distance =  storeInfo.distance;
		this.latitude =  storeInfo.latitude;
		this.longitude =  storeInfo.longitude;
		this.setAvailable(available);
		this.setLowStock(lowStock);
		
	}

	public InStorePickupInfo(String errorMsg){
		this.errorMsg = errorMsg;
	}
	
	
	public int getAvailableQty() {
		return availableQty;
		
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isLowStock() {
		return lowStock;
	}

	public void setLowStock(boolean lowStock) {
		this.lowStock = lowStock;
	}
}
