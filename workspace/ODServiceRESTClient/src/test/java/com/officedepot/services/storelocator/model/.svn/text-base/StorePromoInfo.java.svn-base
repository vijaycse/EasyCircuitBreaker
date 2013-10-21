package com.officedepot.services.storelocator.model;


import org.codehaus.jackson.annotate.JsonIgnore;


 public class StorePromoInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = -7296781284625907328L;
	private String promoUrl = "";
	private String promoText = "";
	
	public StorePromoInfo(){}

	public String getPromoUrl() {
		return promoUrl;
	}
	
	public String getPromoText() {
		return promoText;
	}
	
	public void setPromoUrl(String promoUrl) {
		this.promoUrl = promoUrl;
	}

	public void setPromoText(String promoText) {
		this.promoText = promoText;
	}

	public String toString(){
		return "StorePromoInfo: promoUrl[" + promoUrl + "]" + "promoText[" + promoText + "]";
	}
	@JsonIgnore 
	public boolean isValidPromo(){
		//its valid if either promoUrl or promoText has a value
		if(promoUrl.trim().length() > 0 || promoText.trim().length() > 0){
			return true;
		}
		return false;
	}
	@JsonIgnore 
	public boolean isPromoLink(){
			if(promoUrl.trim().length() > 0 && promoText.trim().length() > 0){
				return true;
			}
		return false;
	}
	@JsonIgnore 
	public boolean isPromoTextOnly(){
		if(promoUrl.trim().length() == 0 && promoText.trim().length() > 0){
			return true;
		}
		return false;
	}

}
