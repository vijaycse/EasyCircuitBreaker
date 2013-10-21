package com.officedepot.services.storelocator.model;

import java.io.Serializable;

public class StoreLocatorRequestInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String zip = "";
	private String city = "";
	private String state = "";
	private String storeCode = "";
	private String mapId = "";
	private int zoomLevel = 0;
	private String panDirection = "";
	private int x;
	private int y;
	private boolean center = false;
	private boolean nowDocs = false;
	private String longitude = "";
	private String latitude = "";
	private int radius = 0;
	private int storeLimit = 0;

	public StoreLocatorRequestInfo(String storeCode, String city, String state,
			String zip, String mapId, String panDirection, int zoomLevel,
			boolean center, int x, int y, boolean nowDocs, String longitude,
			String latitude, int radius, int storeLimit) {
		super();

		this.zip = zip;
		this.city = city;
		this.state = state;
		this.storeCode = storeCode;
		this.mapId = mapId;
		this.zoomLevel = zoomLevel;
		this.panDirection = panDirection;
		this.x = x;
		this.y = y;
		this.center = center;
		this.nowDocs = nowDocs;
		this.longitude = longitude;
		this.latitude = latitude;
		this.radius = radius;
		this.storeLimit = storeLimit;
	}

	public String getZip() {
		return zip;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public String getMapId() {
		return mapId;
	}

	public String getPanDirection() {
		return panDirection;
	}

	public int getZoomLevel() {
		return zoomLevel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getCenter() {
		return center;
	}

	public boolean isNowDocs() {
		return nowDocs;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public int getRadius() {
		return radius;
	}

	public int getStoreLimit() {
		return storeLimit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((mapId == null) ? 0 : mapId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((storeCode == null) ? 0 : storeCode.hashCode());
		result = prime * result + storeLimit;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreLocatorRequestInfo other = (StoreLocatorRequestInfo) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (mapId == null) {
			if (other.mapId != null)
				return false;
		} else if (!mapId.equals(other.mapId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (storeCode == null) {
			if (other.storeCode != null)
				return false;
		} else if (!storeCode.equals(other.storeCode))
			return false;
		if (storeLimit != other.storeLimit)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

}