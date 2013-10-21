package com.officedepot.services.storelocator.model;

import java.util.HashMap;
import java.util.Map;

public class StoreLocatorRequestTO extends StoreRequestTO {

	/** street address, city, state or postal code */
	private String addressline;
	private String longitude;
	private String latitude;

	private int radius;
	private int storeLimit;

	/**
	 * Equals(criteria logic) filter map. key: filter item name; value: filter item value<br>
	 * For example:<br>
	 * <code>
	 * filter.put("expanded_furn", "y"); // Expanded Furniture Showroom<br>
	 * filter.put("usps", "y"); // U.S. Postal Service� Available
	 * </code>
	 */
	private Map<String, String> filterEq = new HashMap<String, String>();

	/**
	 * @param addressline
	 *            street address, city, state or postal code
	 */
	public StoreLocatorRequestTO(String addressline, Map<String, String> filterEq) {
		super();
		this.addressline = addressline;
		this.filterEq = filterEq;
	}

	public StoreLocatorRequestTO(String longitude, String latitude, Map<String, String> filterEq) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.filterEq = filterEq;
	}

	public StoreLocatorRequestTO buildLocator(String longitude, String latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		return this;
	}

	public StoreLocatorRequestTO buildSearchCondition(int radius, int storeLimit) {
		this.radius = radius;
		this.storeLimit = storeLimit;
		return this;
	}

	public String getAddressline() {
		return addressline;
	}

	public void setAddressline(String addressline) {
		this.addressline = addressline;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getStoreLimit() {
		return storeLimit;
	}

	public void setStoreLimit(int storeLimit) {
		this.storeLimit = storeLimit;
	}

	public Map<String, String> getFilterEq() {
		return filterEq;
	}

	public void setFilterEq(Map<String, String> filterEq) {
		this.filterEq = filterEq;
	}

	public void appendfilterEq(String key, String value) {
		this.filterEq.put(key, value);
	}

	@Override
	public String getMethod() {
		return "locatorsearch";
	}
}
