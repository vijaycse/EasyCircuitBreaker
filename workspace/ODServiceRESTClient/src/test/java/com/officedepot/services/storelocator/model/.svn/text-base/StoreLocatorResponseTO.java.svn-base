package com.officedepot.services.storelocator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StoreLocatorResponseTO extends StoreResponseTO {

	private String centerPoint;
	private List<StoreInfo> storeInfos = new ArrayList<StoreInfo>();
	private List<StoreInfo> suggestions = new ArrayList<StoreInfo>();
	private boolean ambiguousResult = false; 
	// for drilldown
	private Set<String> uniqueCities = new TreeSet<String>();

	public void appendStoreInfo(StoreInfo storeInfo) {
		this.storeInfos.add(storeInfo);
	}

	public void appendSuggestion(StoreInfo storeInfo) {
		this.suggestions.add(storeInfo);
	}
	
	public List<StoreInfo> getStoreInfos() {
		return storeInfos;
	}

	public List<StoreInfo> getSuggestions() {
		return suggestions;
	}

	public String getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(String centerPoint) {
		this.centerPoint = centerPoint;
	}
	
	// added for drilldown
	public Set<String> getUniqueCities() {
		return uniqueCities;
	}

	public void setUniqueCities(Set<String> uniqueCities) {
		this.uniqueCities = uniqueCities;
	}

	public void setAmbiguousResult(boolean ambiguousResult) {
		this.ambiguousResult = ambiguousResult;
	}

	public boolean isAmbiguousResult() {
		return ambiguousResult;
	}
	
}
