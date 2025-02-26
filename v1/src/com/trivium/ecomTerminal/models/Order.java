package com.trivium.ecomTerminal.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private User user;
	private ArrayList<Product> products;
	private float totalAmount;
	private Date date;

	public Order(int id, User user, ArrayList<Product> products, float totalAmount) {
		super();
		this.id = id;
		this.user = user;
		this.products = products;
		this.totalAmount = totalAmount;
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

}
