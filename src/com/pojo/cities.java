package com.pojo;

public class cities {
	int mid;
	String city;
	String id;
	double lat;
	double lon;
	String prov;

	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	
	@Override
	public String toString() {
		return "cities [mid=" + mid + ", city=" + city + ", id=" + id + ", lat=" + lat + ", lon=" + lon + ", prov="
				+ prov + "]";
	}
}
