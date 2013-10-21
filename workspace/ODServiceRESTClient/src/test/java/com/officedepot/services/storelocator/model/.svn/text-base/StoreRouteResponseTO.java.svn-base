package com.officedepot.services.storelocator.model;

import java.util.ArrayList;
import java.util.List;

public class StoreRouteResponseTO extends StoreResponseTO {

	private List<ManeuverInfo> directions = new ArrayList<ManeuverInfo>();
	private String totalTime;
	private String timeUnit;
	private double totalDistance;
	private String distanceUnit;

	public List<ManeuverInfo> getDirections() {
		return directions;
	}

	public void appendDirection(ManeuverInfo maneuverInfo) {
		this.directions.add(maneuverInfo);
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public String getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}
}
