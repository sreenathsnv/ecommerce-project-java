package com.trivium.ecomTerminal.models;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private Address address;
	private String mobileNumber;
	private String emailId;
	private boolean role;

	public User(int id, String username, String password, Address address, String mobileNumber, String emailId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.role = false;
	}

	public User(int id, String username, String password, Address address, String mobileNumber, String emailId,
			boolean role) {
		this(id, username, password, address, mobileNumber, emailId);

		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String newPhn) {
		this.mobileNumber = newPhn;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isRole() {
		return role;
	}

	@Override
	public String toString() {
	    return String.format(
	        "====================================\n" +
	        "|           USER DETAILS           |\n" +
	        "====================================\n" +
	        "| ID         : %-20d |\n" +
	        "| Username   : %-20s |\n" +
	        "| Email      : %-20s |\n" +
	        "| Mobile No. : %-20s |\n" +
	        "| Role       : %-20s |\n" +
	        "------------------------------------\n" +
	        "| Address                           |\n" +
	        "------------------------------------\n" +
	        "| House Name : %-20s |\n" +
	        "| Location   : %-20s |\n" +
	        "| Zip Code   : %-20d |\n" +
	        "====================================",
	        id, username, emailId, mobileNumber, role ? "Admin" : "User",
	        address.getHousname(), address.getLocation(), address.getZipcode()
	    );
	}


	public void setRole(boolean role) {
		this.role = role;
	}

}
