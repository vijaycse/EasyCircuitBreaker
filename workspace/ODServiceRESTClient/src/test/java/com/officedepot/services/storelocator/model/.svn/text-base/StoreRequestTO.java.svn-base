package com.officedepot.services.storelocator.model;

public abstract class StoreRequestTO {

	/** whether generate map info */
	private boolean generateMap;
	private int mapWidth;
	private int mapHight;

	public StoreRequestTO buildMap(int mapWidth, int mapHight) {
		this.generateMap = true;
		this.mapWidth = mapWidth;
		this.mapHight = mapHight;
		return this;
	}

	public boolean isGenerateMap() {
		return generateMap;
	}

	public void setGenerateMap(boolean generate) {
		this.generateMap = generate;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHight() {
		return mapHight;
	}

	public void setMapHight(int mapHight) {
		this.mapHight = mapHight;
	}

	abstract public String getMethod();
}
