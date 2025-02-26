package com.trivium.ecomTerminal.repos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.trivium.ecomTerminal.models.Product;

public class ProductRepo {

	private final static String FILE_PATH = "src/com/trivium/ecomTerminal/data/products.txt";

	private ArrayList<Product> products = new ArrayList<Product>();

	public ProductRepo() {
		super();
		loadProducts();
	}

	public static ProductRepo getInstance() {

		return new ProductRepo();
	}

	public boolean addProduct(Product product) {
		boolean isAdded = products.add(product);
		saveProducts();
		return isAdded;
	}

	public Product getProductById(int id) {
		return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}

	public boolean updateProduct(int id, String name, String category, float price, int stock) {

		Product p = getProductById(id);
		if (p != null) {

			p.setName(name);
			p.setCategory(category);
			p.setPrice(price);
			p.setStock(stock);
			saveProducts();
			return true;
		}
		return false;
	}

	public ArrayList<Product> getAllProducts() {
		return products;
	}

	public boolean removeProduct(int id) {

		Product filteredProduct = getProductById(id);
		boolean isRemoved = products.remove(filteredProduct);
		saveProducts();
		return isRemoved;
	}

	public boolean isAvailable(int id) {

		Product requestedProduct = products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
		if (requestedProduct == null) {
			System.out.println("Product Does not exist!");
			return false;
		} else if (requestedProduct.getStock() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void updateList() {
		saveProducts();
	}

	private void saveProducts() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
			oos.writeObject(products);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadProducts() {
		File file = new File(FILE_PATH);
		if (!file.exists())
			return;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
			products = (ArrayList<Product>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
