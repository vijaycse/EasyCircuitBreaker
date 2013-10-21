package com.officedepot.services.storelocator.model;

/** Only support single direction search yet. */
public class StoreRouteRequestTO extends StoreRequestTO {

	// starting location
	private String longitudeFrom;
	private String latitudeFrom;
	/** street address, city, state or postal code */
	private String addresslineFrom;
	private String cityFrom;
	private String stateFrom;
	private String zipCodeFrom;

	// destination location
	// It is highly preferred to use the latitude/longitude values for the driving direction end points.
	private String longitudeTo;
	private String latitudeTo;
	/** street address, city, state or postal code */
	private String addresslineTo;
	private String cityTo;
	private String stateTo;
	private String zipCodeTo;

	/** Recommend constructor */
	public StoreRouteRequestTO(String longitudeFrom, String latitudeFrom, String longitudeTo, String latitudeTo) {
		super();
		buildDistanceDegreesFrom(longitudeFrom, latitudeFrom);
		buildDistanceDegreesTo(longitudeTo, latitudeTo);
	}

	public StoreRouteRequestTO buildDistanceDegreesFrom(String longitudeFrom, String latitudeFrom) {
		this.longitudeFrom = longitudeFrom;
		this.latitudeFrom = latitudeFrom;
		return this;
	}

	public StoreRouteRequestTO buildDistanceDegreesTo(String longitudeTo, String latitudeTo) {
		this.longitudeTo = longitudeTo;
		this.latitudeTo = latitudeTo;
		return this;
	}

	/**
	 * @param addresslineFrom
	 *            street address, city, state or postal code
	 * @param addresslineTo
	 *            street address, city, state or postal code
	 */
	public StoreRouteRequestTO(String addresslineFrom, String addresslineTo) {
		super();
		this.addresslineFrom = addresslineFrom;
		this.addresslineTo = addresslineTo;
	}

	/**
	 * @param addresslineFrom
	 *            street address, city, state or postal code
	 * @param addresslineTo
	 *            street address, city, state or postal code
	 */
	public StoreRouteRequestTO(String addresslineFrom, String cityFrom, String stateFrom, String zipCodeFrom, String addresslineTo, String cityTo, String stateTo, String zipCodeTo) {
		super();
		this.addresslineFrom = addresslineFrom;
		this.addresslineTo = addresslineTo;
		buildAddressFrom(cityFrom, stateFrom, zipCodeFrom);
		buildAddressTo(cityTo, stateTo, zipCodeTo);
	}

	public StoreRouteRequestTO buildAddressFrom(String cityFrom, String stateFrom, String zipCodeFrom) {
		this.cityFrom = cityFrom;
		this.stateFrom = stateFrom;
		this.zipCodeFrom = zipCodeFrom;
		return this;
	}

	public StoreRouteRequestTO buildAddressTo(String cityTo, String stateTo, String zipCodeTo) {
		this.cityTo = cityTo;
		this.stateTo = stateTo;
		this.zipCodeTo = zipCodeTo;
		return this;
	}

	public String getLongitudeFrom() {
		return longitudeFrom;
	}

	public void setLongitudeFrom(String longitudeFrom) {
		this.longitudeFrom = longitudeFrom;
	}

	public String getLatitudeFrom() {
		return latitudeFrom;
	}

	public void setLatitudeFrom(String latitudeFrom) {
		this.latitudeFrom = latitudeFrom;
	}

	public String getAddresslineFrom() {
		return addresslineFrom;
	}

	public void setAddresslineFrom(String addresslineFrom) {
		this.addresslineFrom = addresslineFrom;
	}

	public String getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}

	public String getStateFrom() {
		return stateFrom;
	}

	public void setStateFrom(String stateFrom) {
		this.stateFrom = stateFrom;
	}

	public String getZipCodeFrom() {
		return zipCodeFrom;
	}

	public void setZipCodeFrom(String zipCodeFrom) {
		this.zipCodeFrom = zipCodeFrom;
	}

	public String getLongitudeTo() {
		return longitudeTo;
	}

	public void setLongitudeTo(String longitudeTo) {
		this.longitudeTo = longitudeTo;
	}

	public String getLatitudeTo() {
		return latitudeTo;
	}

	public void setLatitudeTo(String latitudeTo) {
		this.latitudeTo = latitudeTo;
	}

	public String getAddresslineTo() {
		return addresslineTo;
	}

	public void setAddresslineTo(String addresslineTo) {
		this.addresslineTo = addresslineTo;
	}

	public String getCityTo() {
		return cityTo;
	}

	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}

	public String getStateTo() {
		return stateTo;
	}

	public void setStateTo(String stateTo) {
		this.stateTo = stateTo;
	}

	public String getZipCodeTo() {
		return zipCodeTo;
	}

	public void setZipCodeTo(String zipCodeTo) {
		this.zipCodeTo = zipCodeTo;
	}

	@Override
	public String getMethod() {
		return "drivingdirections";
	}
}
