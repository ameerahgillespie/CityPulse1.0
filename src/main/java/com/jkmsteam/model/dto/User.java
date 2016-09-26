package com.jkmsteam.model.dto;

public class User {
	private int id;
	private long fbId;
	private String email;
	private String firstName;
	private String lastName;
	private double latitude;
	private double longitude;
	private int zoom;
	
	public int getId() {
		return id;
	}
	public long getFbId() {
		return fbId;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFbId(long fbId) {
		this.fbId = fbId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getZoom() {
		return zoom;
	}
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
	
	public String toString() {
		return "id: " + id + ", fbId: " + fbId + ", email: " + email;
	}

}
