/**
 * Class to return panzoom info from pan method in EJB
 */
package com.officedepot.services.storelocator.model;

public class PanInfo {
	private String mapUrl;
	private String mapId;
	private int zoomLevel;
	
	
	

	/**
	 * @return
	 */
	public String getMapId() {
		return mapId;
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
	public int getZoomLevel() {
		return zoomLevel;
	}

	/**
	 * @param string
	 */
	public void setMapId(String string) {
		mapId = string;
	}

	/**
	 * @param string
	 */
	public void setMapUrl(String string) {
		mapUrl = string;
	}

	/**
	 * @param integer
	 */
	public void setZoomLevel(int integer) {
		zoomLevel = integer;
	}
	
	public String toString() {
		return "PanInfo: mapid["+mapId+"] mapUrl["+mapUrl+"] zoomLevel["+zoomLevel+"]";
	}

}
