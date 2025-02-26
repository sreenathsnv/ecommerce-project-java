package com.trivium.ecomTerminal.models;

import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	private String housname;
	private String location;
	private int zipcode;

	public Address(String housname, String location,  int zipcode) {
		super();
		this.housname = housname;
		this.location = location;
		this.zipcode = zipcode;
	}

	public String getHousname() {
		return housname;
	}

	public void setHousname(String housname) {
		this.housname = housname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

}
