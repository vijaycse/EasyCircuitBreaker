/*
 *  Store a maneuver object for driving directions
 */
package com.officedepot.services.storelocator.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ManeuverInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private String directions;
	@JsonIgnore
	private double distance;

	public ManeuverInfo() {

	}

	public ManeuverInfo(String directions, double distance) {
		this.directions = directions;
		this.distance = distance;

	}

	/**
	 * @return route direction
	 */
	public String getDirections() {
		return directions;
	}

	/**
	 * @return distance in miles
	 */
	public String getDistance() {
		return String.valueOf(distance);
	}

	public String toString() {
		return "ManueverInfo: directions[" + directions + "] distance["
				+ distance + "]";
	}

}
