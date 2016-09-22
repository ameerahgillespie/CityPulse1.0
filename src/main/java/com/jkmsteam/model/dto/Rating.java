package com.jkmsteam.model.dto;

public class Rating {
	private int id;
	private int userId;
	private String placeId;
	private int dead;
	private boolean justRight;
	private boolean jumping;
	private boolean coverCharge;
	private boolean crowded;
	private boolean expensive;
	private boolean loud;
	private boolean bigGroups;
	private boolean smallGroups;
	private boolean safePlace;
	private boolean goodParking;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public boolean isJustRight() {
		return justRight;
	}
	public void setJustRight(boolean justRight) {
		this.justRight = justRight;
	}
	public boolean isJumping() {
		return jumping;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public boolean isCoverCharge() {
		return coverCharge;
	}
	public void setCoverCharge(boolean coverCharge) {
		this.coverCharge = coverCharge;
	}
	public boolean isCrowded() {
		return crowded;
	}
	public void setCrowded(boolean crowded) {
		this.crowded = crowded;
	}
	public boolean isExpensive() {
		return expensive;
	}
	public void setExpensive(boolean expensive) {
		this.expensive = expensive;
	}
	public boolean isLoud() {
		return loud;
	}
	public void setLoud(boolean loud) {
		this.loud = loud;
	}
	public boolean isBigGroups() {
		return bigGroups;
	}
	public void setBigGroups(boolean bigGroups) {
		this.bigGroups = bigGroups;
	}
	public boolean isSmallGroups() {
		return smallGroups;
	}
	public void setSmallGroups(boolean smallGroups) {
		this.smallGroups = smallGroups;
	}
	public boolean isSafePlace() {
		return safePlace;
	}
	public void setSafePlace(boolean safePlace) {
		this.safePlace = safePlace;
	}
	public boolean isGoodParking() {
		return goodParking;
	}
	public void setGoodParking(boolean goodParking) {
		this.goodParking = goodParking;
	}
	@Override
	public String toString() {
		return "Rating [id=" + id + ", userId=" + userId + ", placeId=" + placeId + ", dead=" + dead + ", justRight="
				+ justRight + ", jumping=" + jumping + ", coverCharge=" + coverCharge + ", crowded=" + crowded
				+ ", expensive=" + expensive + ", loud=" + loud + ", bigGroups=" + bigGroups + ", smallGroups="
				+ smallGroups + ", safePlace=" + safePlace + ", goodParking=" + goodParking + "]";
	}
	
}