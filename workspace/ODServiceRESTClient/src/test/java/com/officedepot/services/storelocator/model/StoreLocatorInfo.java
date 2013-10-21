package com.officedepot.services.storelocator.model;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;


public class StoreLocatorInfo implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7296781284625607328L;
	private String mapUrl;
	private String mapId;
	private int mapZoomLevel;
	private StoreInfo[] stores;
	private StoreInfo[] suggestions;
	private boolean nowDocFilter;
	double requestLatitude;
	double requestLongitude;
	private boolean ambiguousResult = false; 
	 
	
	public static final StoreLocatorInfo NONE_FOUND = new StoreLocatorInfo("", "", 0, new StoreInfo[0], new StoreInfo[0], false, 0, 0);
	
	public StoreLocatorInfo(){}
	
	public StoreLocatorInfo(
			String mapUrl,
			String mapId, 
		    int mapZoomLevel, 
		    StoreInfo[] stores, 
		    StoreInfo[] suggestions, 
		    boolean nowDocFilter,
		    double requestLongitude,
		    double requestLatitude
		    ) {
			this.mapUrl = mapUrl;	 	
		    this.mapId = mapId;
		    this.mapZoomLevel = mapZoomLevel;
		    this.stores = stores;
		    this.suggestions = suggestions; 
		    this.nowDocFilter = nowDocFilter;
		    this.requestLongitude = requestLongitude;
		    this.requestLatitude = requestLatitude;
		    }
	public StoreInfo[] getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(StoreInfo[] suggestions) {
		this.suggestions = suggestions;
	}

	/**
	 * @return
	 */
	public String getMapUrl() {
		return mapUrl;
	}

	/**
	 * @return
	 */
	public StoreInfo[] getStores() {
		return stores;
	}

	/**
	 * @return
	 */
	@JsonIgnore
	public StoreInfo getFirstStore() {
		return stores != null && stores.length > 0?stores[0]:new StoreInfo();
	}
	
	/**
	 * @param string
	 */
	public void setMapUrl(String string) {
		mapUrl = string;
	}

	/**
	 * @param infos
	 */
	public void setStores(StoreInfo[] infos) {
		stores = infos;
	}



	/**
	 * @return
	 */
	public String getMapId() {
		return mapId;
	}

	/**
	 * @param string
	 */
	public void setMapId(String string) {
		mapId = string;
	}

	/**
	 * @return
	 */
	public int getMapZoomLevel() {
		return mapZoomLevel;
	}

	/**
	 * @param integer
	 */
	public void setMapZoomLevel(int integer) {
		mapZoomLevel = integer;
	}

	/**
	 * @param bool
	 */	
	public void setNowDocFilter(boolean bool) {
		nowDocFilter = bool;
	}

	/**
	 * @return
	 */	
	public boolean isNowDocFilter() {
		return nowDocFilter;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n\tStoreLocatorInfo: mapUrl["+mapUrl+"] mapId["+mapId+"] mapZoomLevel["+mapZoomLevel+"]");
		if (stores != null) {
			for(int i = 0; i<stores.length; i++) {
				sb.append("\n\t" + stores[i].toString());		
			}
		}
		
		return sb.toString(); 
	}
	public double getRequestLatitude() {
		return requestLatitude;
	}

	public void setRequestLatitude(double requestLatitude) {
		this.requestLatitude = requestLatitude;
	}

	public double getRequestLongitude() {
		return requestLongitude;
	}

	public void setRequestLongitude(double requestLongitude) {
		this.requestLongitude = requestLongitude;
	}
	
	
	public boolean isAmbiguousResult() {
		return ambiguousResult;
	}

	public void setAmbiguousResult(boolean ambiguousResult) {
		this.ambiguousResult = ambiguousResult;
	}


}
