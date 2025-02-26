package com.trivium.ecomTerminal.services;

import java.util.ArrayList;
import java.util.Scanner;

import com.trivium.ecomTerminal.models.Product;
import com.trivium.ecomTerminal.repos.ProductRepo;

public class ProductServices {

	private ProductRepo productRepo = ProductRepo.getInstance();
	private static int _id = 43;

	private static int autoIdGenerator() {
		return _id++;
	}
	
public static ProductServices getInstance() {
		
		return new ProductServices();
	}
	
	public void addProduct() {
		Scanner sc = new Scanner(System.in);

		System.out.println("========================================");
		System.out.println("|           ADDING A NEW PRODUCT       |");
		System.out.println("========================================");
		System.out.print("> ");

		System.out.print("Enter product name: ");
		String name = sc.nextLine().trim();

		System.out.print("Enter product category: ");
		String category = sc.nextLine().trim();

		System.out.print("Enter product price: ");
		float price = sc.nextFloat();

		System.out.print("Enter product stock quantity: ");
		int stock = sc.nextInt();
		sc.nextLine(); 

		
		Product prod = new Product(autoIdGenerator(), name, category, price, stock);

		if (productRepo.addProduct(prod)) {
			System.out.println("\n\t\tProduct added successfully!");
		} else {
			System.out.println("\n\t\tFailed to add the product. Please try again.");
		}
	}

		public void removeProduct() {
		Scanner sc = new Scanner(System.in);

		
		getAllProducts();

		System.out.println("=======================");
		System.out.println("| Enter Product ID:   |");
		System.out.println("=======================");
		System.out.print("> ");
		int id = sc.nextInt();
		sc.nextLine(); 

		
		Product product = productRepo.getProductById(id);
		if (product == null) {
			System.out.println("==========================================");
			System.out.println("\t No Product available with ID " + id);
			System.out.println("==========================================");
			return;
		}

		
		System.out.print("Are you sure you want to remove product ID " + id + " (yes/no)? ");
		String confirmation = sc.nextLine().trim();
		if (confirmation.equalsIgnoreCase("yes")) {
			if (productRepo.removeProduct(id)) {
				System.out.println("=======================");
				System.out.println("| PRODUCT REMOVED SUCCESSFULLY:   |");
				System.out.println("=======================");

			} else {
				System.out.println("=======================");
				System.out.println("| PRODUCT REMOVED FAILED:   |");
				System.out.println("=======================");
			}
		} else {
			System.out.println("=======================");
			System.out.println("| PRODUCT REMOVAL CANCELLED:   |");
			System.out.println("=======================");
		}
	}

	
	public void getAllProducts() {
		ArrayList<Product> allProducts = productRepo.getAllProducts();
		if (allProducts.size() == 0) {
			System.out.println("==========================================");
			System.out.println("\t No Products available");
			System.out.println("==========================================");
		}
		for (Product p : allProducts) {
			System.out.println(p.toMinimalDetails());
		}
	}


	public void getProductById() {
		Scanner sc = new Scanner(System.in);
		getAllProducts();
		System.out.print("\n\nEnter the product ID: ");
		int id = sc.nextInt();
		if (productRepo.getProductById(id) == null) {
			System.out.println("==========================================");
			System.out.println("\t No Product available");
			System.out.println("==========================================");
			return;
		}
		System.out.println(productRepo.getProductById(id));
	}

	
	public Product getProductById(int id) {
		return productRepo.getProductById(id);
	}
	public void updateProduct() {
	    Scanner sc = new Scanner(System.in);
	    getAllProducts();

	    System.out.println("=======================");
	     System.out.println("| Enter Product ID:   |");
	     System.out.println("=======================");
	     System.out.print("> ");
	    int id = sc.nextInt();
	    sc.nextLine();

	    Product product = productRepo.getProductById(id);
	    if (product == null) {
	        System.out.println("==========================================");
	        System.out.println("\t No Product available with ID " + id);
	        System.out.println("==========================================");
	        return;
	    }

	    System.out.println("Enter new details (leave blank to keep existing values):");

	    System.out.print("Enter new product name (" + product.getName() + "): ");
	    String name = sc.nextLine().trim();
	    if (name.isEmpty()) {
	        name = product.getName();
	    }

	    System.out.print("Enter new product category (" + product.getCategory() + "): ");
	    String category = sc.nextLine().trim();
	    if (category.isEmpty()) {
	        category = product.getCategory();
	    }

	    System.out.print("Enter new product price (" + product.getPrice() + "): ");
	    String priceInput = sc.nextLine().trim();
	    float price = priceInput.isEmpty() ? product.getPrice() : Float.parseFloat(priceInput);

	    System.out.print("Enter new stock quantity (" + product.getStock() + "): ");
	    String stockInput = sc.nextLine().trim();
	    int stock = stockInput.isEmpty() ? product.getStock() : Integer.parseInt(stockInput);

	   
	    
	    if (productRepo.updateProduct(id, name, category, price, stock)) {
	        System.out.println("\n\t\tProduct updated successfully!");
	    } else {
	        System.out.println("\n\t\tFailed to update the product. Please try again.");
	    }
	}
	
	public ArrayList<Product> returnTheOriginalProductList(){
		return productRepo.getAllProducts();
	}
	public void updateProductList() {
		productRepo.updateList();
	}

}
