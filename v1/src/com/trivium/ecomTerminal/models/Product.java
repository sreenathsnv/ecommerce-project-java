package com.trivium.ecomTerminal.models;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String category;
	private float price;
	private int stock;


	public Product(int id, String name, String category, float price,int stock) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stock = stock;
		
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
	    return String.format(
	        "--------------------------------\n" +
	        "Product Details:\n" +
	        "--------------------------------\n" +
	        "ID       : %s\n" +
	        "Name     : %s\n" +
	        "Category : %s\n" +
	        "Price    : %.2f\n" +
	        "Stock    : %d\n" +
	        "--------------------------------\n",
	        id, name, category, price, stock
	    );
	}
	
	public String toMinimalDetails() {
		return String.format(
		        "--------------------------------\n" +
		    	        "Product Details:\n" +
		    	        "--------------------------------\n" +
		    	        "ID       : %s\n" +
		    	        "Name     : %s\n" +
		    	        "Price    : %.2f\n" +
		    	        "--------------------------------\n",
		    	        id, name, price
		    	    );
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
