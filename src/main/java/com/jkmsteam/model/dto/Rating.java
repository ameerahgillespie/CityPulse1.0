package com.jkmsteam.model.dto;

public class Rating {
	private int id;
	private long userId;
	private String placeId;
	private int dead;
	private int justRight;
	private int jumping;
	private int coverCharge;
	private int crowded;
	private int expensive;
	private int loud;
	private int bigGroups;
	private int smallGroups;
	private int safePlace;
	private int goodParking;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
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
	public int isJustRight() {
		return justRight;
	}
	public void setJustRight(int justRight) {
		this.justRight = justRight;
	}
	public int isJumping() {
		return jumping;
	}
	public void setJumping(int jumping) {
		this.jumping = jumping;
	}
	public int isCoverCharge() {
		return coverCharge;
	}
	public void setCoverCharge(int coverCharge) {
		this.coverCharge = coverCharge;
	}
	public int isCrowded() {
		return crowded;
	}
	public void setCrowded(int crowded) {
		this.crowded = crowded;
	}
	public int isExpensive() {
		return expensive;
	}
	public void setExpensive(int expensive) {
		this.expensive = expensive;
	}
	public int isLoud() {
		return loud;
	}
	public void setLoud(int loud) {
		this.loud = loud;
	}
	public int isBigGroups() {
		return bigGroups;
	}
	public void setBigGroups(int bigGroups) {
		this.bigGroups = bigGroups;
	}
	public int isSmallGroups() {
		return smallGroups;
	}
	public void setSmallGroups(int smallGroups) {
		this.smallGroups = smallGroups;
	}
	public int isSafePlace() {
		return safePlace;
	}
	public void setSafePlace(int safePlace) {
		this.safePlace = safePlace;
	}
	public int isGoodParking() {
		return goodParking;
	}
	public void setGoodParking(int goodParking) {
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