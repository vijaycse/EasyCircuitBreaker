/*
 * Represents a Store, includes all info needed
 */
package com.officedepot.services.storelocator.model;

import java.io.Serializable;
import java.util.HashMap;

import com.officedepot.services.storelocator.model.PhoneInfo.PhoneLineType;
import org.codehaus.jackson.annotate.JsonIgnore;
 

public class StoreInfo implements Serializable {

	/**
	 * Please keep this object simple. It is returned to the front end by an DWR Ajax call
	 * If you create another object within this, it needs to be defined within the DWR.xml
	 */
	private static final long serialVersionUID = -1004436713016128294L;
	private String storeId;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String hoursMon;
	private String hoursTue;
	private String hoursWed;
	private String hoursThu;
	private String hoursFri;
	private String hoursSat;
	private String hoursSun;
	private StorePromoInfo storePromoInfo;

	private boolean newStore;
	private boolean inkRefill;
	private boolean furnitureShowroom;
	private boolean usps;
	private boolean photoPrint;
	private boolean selfServeWorkStations;
	private boolean notaryServices;
	private boolean shreddingServices;
	//need to be accessed by subclass
	protected double distance;
	protected double latitude;
	protected double longitude;
	public StoreInfo(){
		this("","","","","","","","","","","","","","",new StorePromoInfo(),false,false,false,false,false,0,0,0);
	}

	public StoreInfo(
		String storeId,
		String address,
		String address2,
		String city,
		String state,
		String zip,
		String phone,
		String hoursMon,
		String hoursTue,
		String hoursWed,
		String hoursThu,
		String hoursFri,
		String hoursSat,
		String hoursSun,
		StorePromoInfo storePromoInfo,
		boolean newStore,
		boolean inkRefill,
		boolean furnitureShowroom,
		boolean usps,
		boolean photoPrint,
		double distance,
		double latitude,
		double longitude) {
		this.storeId = storeId;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.hoursMon = hoursMon;
		this.hoursTue = hoursTue;
		this.hoursWed = hoursWed;
		this.hoursThu = hoursThu;
		this.hoursFri = hoursFri;
		this.hoursSat = hoursSat;
		this.hoursSun = hoursSun;
		this.storePromoInfo = storePromoInfo;
		this.newStore = newStore;
		this.inkRefill = inkRefill;
		this.furnitureShowroom = furnitureShowroom;
		this.usps = usps;
		this.photoPrint = photoPrint;
		this.distance = distance;
		this.latitude = latitude;
		this.longitude = longitude;

	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @return Returns the hoursFri.
	 */
	public String getHoursFri() {
		return hoursFri;
	}
	/**
	 * @return Returns the hoursMon.
	 */
	public String getHoursMon() {
		return hoursMon;
	}
	/**
	 * @return Returns the hoursThu.
	 */
	public String getHoursThu() {
		return hoursThu;
	}
	/**
	 * @return Returns the hoursTue.
	 */
	public String getHoursTue() {
		return hoursTue;
	}
	/**
	 * @return Returns the hoursWed.
	 */
	public String getHoursWed() {
		return hoursWed;
	}
	/**
	 * @return
	 */
	public String getHoursSat() {
		return hoursSat;
	}

	/**
	 * @return
	 */
	public String getHoursSun() {
		return hoursSun;
	}


	/**
	 * @return
	 */
	public String getPhone() {
		return phone;
	}
	@JsonIgnore 
	public String getPhoneWithHyphens() {
		PhoneInfo phoneInfo = PhoneInfo.createPhone(PhoneLineType.GEN,
				getPhone(),"");
		return phoneInfo.getAreaCodeAndNumberWithHyphen();
	}

	/**
	 * @return
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @return
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @return
	 */
	public String getDistance() {
		return String.valueOf(distance);
	}
    /**
     *
     * @return
     */
	public String getLatitude() {
		return String.valueOf(latitude);

	}
	/**
	 *
	 * @return
	 */
	public String getLongitude() {
		return String.valueOf(longitude);
	}
	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return
	 */
	public boolean getNewStore() {
		return newStore;
	}

	public StorePromoInfo getStorePromoInfo() {
		return storePromoInfo;
	}

	public void setStorePromoInfo(StorePromoInfo promo) {
		this.storePromoInfo = promo;
	}

	@Override
	public String toString() {
		return "StoreInfo: storeId[" + storeId + "] address[" + address + "] address2[" + address2 + "] city[" + city
		+ "] state[" + state + "] zip[" + zip + "] phone[" + phone + "] hoursSat[" + hoursSat + "] hoursSun["
		+ hoursSun + "] newStore[" + newStore + "] distance[" + distance + "] latitude[" + latitude
		+ "] longitude[" + longitude + "] inkRefill[" + inkRefill + "] furnitureShowroom[" + furnitureShowroom + "] storePromoInfo[" + storePromoInfo + ']';
	}
	/**
	 * HashMap builds a parameter list for direction functionality
	 * @return
	 */
	@JsonIgnore
	public HashMap<String,String> getParams() {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("toLine1", address);
		params.put("toLine2", address2);
		params.put("toCity", city);
		params.put("toState", state);
		params.put("toZip", zip);
		params.put("toPhone", phone);
		params.put("storeCode", storeId);
		params.put("toLatitude", Double.toString(latitude));
		params.put("toLongitude", Double.toString(longitude));
		return params;
	}

	public boolean getInkRefill() {
		return inkRefill;
	}

	public boolean getFurnitureShowroom() {
	    return furnitureShowroom;
	}

	public boolean getUsps() {
		return usps;
	}

	public boolean isPhotoPrint() {
		return photoPrint;
	}

	public void setPhotoPrint(boolean photoPrint) {
		this.photoPrint = photoPrint;
	}

	public void setSelfServeWorkStations(boolean selfServeWorkStations) {
		this.selfServeWorkStations = selfServeWorkStations;
	}

	public boolean isSelfServeWorkStations() {
		return selfServeWorkStations;
	}

	public void setNotaryServices(boolean notaryServices) {
		this.notaryServices = notaryServices;
	}

	public boolean isNotaryServices() {
		return notaryServices;
	}

	public void setShreddingServices(boolean shreddingServices) {
		this.shreddingServices = shreddingServices;
	}

	public boolean isShreddingServices() {
		return shreddingServices;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setHoursMon(String hoursMon) {
		this.hoursMon = hoursMon;
	}

	public void setHoursTue(String hoursTue) {
		this.hoursTue = hoursTue;
	}

	public void setHoursWed(String hoursWed) {
		this.hoursWed = hoursWed;
	}

	public void setHoursThu(String hoursThu) {
		this.hoursThu = hoursThu;
	}

	public void setHoursFri(String hoursFri) {
		this.hoursFri = hoursFri;
	}

	public void setHoursSat(String hoursSat) {
		this.hoursSat = hoursSat;
	}

	public void setHoursSun(String hoursSun) {
		this.hoursSun = hoursSun;
	}

	public void setNewStore(boolean newStore) {
		this.newStore = newStore;
	}

	public void setInkRefill(boolean inkRefill) {
		this.inkRefill = inkRefill;
	}

	public void setFurnitureShowroom(boolean furnitureShowroom) {
		this.furnitureShowroom = furnitureShowroom;
	}

	public void setUsps(boolean usps) {
		this.usps = usps;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
