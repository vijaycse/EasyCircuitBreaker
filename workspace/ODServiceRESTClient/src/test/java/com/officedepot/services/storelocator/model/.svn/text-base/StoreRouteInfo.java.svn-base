/*
 * Class stores Direction information from where2getit and returns from EJB
 */

package com.officedepot.services.storelocator.model;

import java.io.Serializable;

public class StoreRouteInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fromLine1;
	private String fromCity;
	private String fromState;
	private String fromZip;
	private String toLine1;
	private String toLine2;
	private String toCity;
	private String toState;
	private String toZip;
	private String toPhone;
	private ManeuverInfo[] directions;
	private String mapUrl;
	private String totalTime;
	private double totalMiles;

	public StoreRouteInfo() {}

	public StoreRouteInfo(
		String fromLine1,
		String fromCity,
		String fromState,
		String fromZip,
		String toLine1,
		String toLine2,
		String toCity,
		String toState,
		String toZip,
		String toPhone) {
		buildAddressFrom(fromLine1, fromCity, fromState, fromZip);
		buildAddressTo(toLine1, toLine2, toCity, toState, toZip, toPhone);
	}

	public StoreRouteInfo buildAddressFrom(
			String fromLine1,
			String fromCity,
			String fromState,
			String fromZip) {
		this.fromLine1 = fromLine1;
		this.fromCity = fromCity;
		this.fromState = fromState;
		this.fromZip = fromZip;
		return this;
	}

	public StoreRouteInfo buildAddressTo(
			String toLine1,
			String toLine2,
			String toCity,
			String toState,
			String toZip,
			String toPhone) {
		this.toLine1 = toLine1;
		this.toLine2 = toLine2;
		this.toCity = toCity;
		this.toState = toState;
		this.toZip = toZip;
		this.toPhone = toPhone;
		return this;
	}

	/**
	 * @return
	 */
	public ManeuverInfo[] getDirections() {
		return directions.clone();
	}

	/**
	 * @return
	 */
	public String getFromCity() {
		return fromCity;
	}

	/**
	 * @return
	 */
	public String getFromLine1() {
		return fromLine1;
	}

	/**
	 * @return
	 */
	public String getFromState() {
		return fromState;
	}

	/**
	 * @return
	 */
	public String getFromZip() {
		return fromZip;
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
	public String getToCity() {
		return toCity;
	}

	/**
	 * @return
	 */
	public String getToLine1() {
		return toLine1;
	}
	/**
	 * @return
	 */
	public String getToLine2() {
		return toLine2;
	}
	/**
	 * @return
	 */
	public String getToPhone() {
		return toPhone;
	}

	/**
	 * @return
	 */
	public String getToState() {
		return toState;
	}

	/**
	 * @return
	 */
	public String getTotalMiles() {
		return String.valueOf(totalMiles);
	}

	/**
	 * @return
	 */
	public String getTotalTime() {
		return totalTime;
	}

	/**
	 * @return
	 */
	public String getToZip() {
		return toZip;
	}

	/**
	 * @param maneuverInfo
	 */
	public void setDirections(ManeuverInfo[] maneuverInfo) {
		directions = maneuverInfo.clone();
	}

	/**
	 * @param string
	 */
	public void setMapUrl(String string) {
		mapUrl = string;
	}

	/**
	 * @param tm
	 */
	public void setTotalMiles(double tm) {
		totalMiles = tm;
	}

	/**
	 * @param string
	 */
	public void setTotalTime(String string) {
		totalTime = string;
	}

	public void setFromLine1(String fromLine1) {
		this.fromLine1 = fromLine1;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public void setFromZip(String fromZip) {
		this.fromZip = fromZip;
	}

	public void setToLine1(String toLine1) {
		this.toLine1 = toLine1;
	}

	public void setToLine2(String toLine2) {
		this.toLine2 = toLine2;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public void setToState(String toState) {
		this.toState = toState;
	}

	public void setToZip(String toZip) {
		this.toZip = toZip;
	}

	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n\tStoreRouteInfo: fromCity["
		+ fromCity
		+ "] fromLine1["
		+ fromLine1
		+ "] fromState["
		+ fromState
		+ "] fromZip["
		+ fromZip
		+ "] mapUrl["
		+ mapUrl
		+ "] toCity["
		+ toCity
		+ "] toLine1["
		+ toLine1
		+ "] toLine2["
		+ toLine2
		+ "] toPhone["
		+ toPhone
		+ "] toState["
		+ toState
		+ "] totalMiles["
		+ totalMiles
		+ "] totalTime["
		+ totalTime
		+ "] toZip["
		+ toZip
		+ "]");
		if (directions != null){
			for(int i = 0; i < directions.length; i++) {
				sb.append("\n\t" + directions[i].toString());
			}
		}
		return sb.toString();
	}
}
