package com.trivium.ecomTerminal.services;

import java.util.ArrayList;
import java.util.Scanner;

import com.trivium.ecomTerminal.models.Address;
import com.trivium.ecomTerminal.models.Order;
import com.trivium.ecomTerminal.models.Product;
import com.trivium.ecomTerminal.models.User;
import com.trivium.ecomTerminal.repos.OrderRepo;
import com.trivium.ecomTerminal.repos.ProductRepo;

public class OrderService {

	private OrderRepo orderRepo = OrderRepo.getInstance();
	private ProductServices prodService = ProductServices.getInstance();

	private static int _id = 123;

	private static int autoIdGenerator() {
		return _id++;
	}

	public static OrderService getInstance() {

		return new OrderService();
	}

	public ArrayList<Product> takeOrders() {

		ArrayList<Product> productOrders = new ArrayList<>();
		int choice = 1;
		do {
			prodService.getAllProducts();
			Scanner sc = new Scanner(System.in);

			System.out.println("=======================");
			System.out.println("| Enter Product ID:   |");
			System.out.println("=======================");
			System.out.print("> ");
			int productId = sc.nextInt();

			Product prod = prodService.getProductById(productId);
			
			if (prod == null) {
				System.out.println("=======================");
				System.out.println("| Unknown ID! Enter Correct ID  |");
				System.out.println("=======================");
				continue;
			}
			if (prod.getStock() < 1) {
				System.out.println("=======================");
				System.out.println("| Out of Stock!   |");
				System.out.println("=======================");
				continue;
			}

			productOrders.add(prod);
			
			
			System.out.println("=======================");
			System.out.println("| Do you want to Add more to the cart?(0.No / 1.YES)   |");
			System.out.println("=======================");
			System.out.print("> ");
			choice = sc.nextInt();

		} while (choice  >=1);

		return productOrders;
	}

	public void placeOrder(User user) {

		ArrayList<Product> cart = takeOrders();
		float totalAmount = cart.stream().map(Product::getPrice).reduce(0.0f, Float::sum);
		Order order = new Order(autoIdGenerator(), user, cart, totalAmount);
		
		boolean isPlaced = orderRepo.placeOrder(order);
		
		updateStock(prodService.returnTheOriginalProductList(),-1);
		prodService.updateProductList();
		
		
		if (isPlaced) {

			System.out.println("========================================");
			System.out.println("|          ORDER RECEIPT               |");
			System.out.println("========================================");
			System.out.println("========================================");
			System.out.println("| ORDER ID : "+order.getId() +"              |");
			System.out.println("========================================");
			System.out.printf("| Username : %-25s |\n", user.getUsername());
			System.out.printf("| Email    : %-25s |\n", user.getEmailId());
			System.out.println("----------------------------------------");
			System.out.println("| Shipping Address                     |");
			System.out.println("----------------------------------------");
			Address address = user.getAddress();
			System.out.printf("| House Name : %-22s |\n", address.getHousname());
			System.out.printf("| Location   : %-22s |\n", address.getLocation());
			System.out.printf("| Zip Code   : %-22d |\n", address.getZipcode());
			System.out.println("========================================");
			System.out.println("| Ordered Items                         |");
			System.out.println("========================================");

			for (Product product : cart) {
				System.out.println(product.toMinimalDetails());
			}

			System.out.println("========================================");
			System.out.printf("| TOTAL AMOUNT : $%-22.2f |\n", totalAmount);
			System.out.println("========================================");
			System.out.println("|    THANK YOU FOR YOUR PURCHASE!      |");
			System.out.println("========================================");

		} else {
			System.out.println("=======================");
			System.out.println("| Error Occuerd! Try again next time  |");
			System.out.println("=======================");
		}

	}
	
	public static void updateStock(ArrayList<Product> prods,int val) {
		
		for(Product prod : prods) {
			
			prod.setStock(prod.getStock() +val);		}
	}
	
	public void viewAllOrders(User user) {
		if (orderRepo.getAllUserOrders(user).size()<1) {
			System.out.println("==========================================");
			System.out.println("\t No ORDERS AVAILABLE  ");
			System.out.println("==========================================");
			return;
		}
		for (Order order : orderRepo.getAllUserOrders(user)) {

			System.out.println("========================================");
			System.out.println("|          YOUR ORDERS                  |");
			System.out.println("========================================");
			System.out.println("========================================");
			System.out.println("| ORDER ID : "+order.getId() +"              |");
			System.out.println("========================================");
			System.out.println("========================================");
			System.out.println("| Ordered Items                         |");
			System.out.println("========================================");
			for (Product product : order.getProducts()) {
				System.out.println(product.toMinimalDetails());
			}
			System.out.println("========================================");
		}

	}

	public void viewAllCancelledOrders(User user) {
		
		if (orderRepo.getAllUserCancelledOrders(user).size()<1) {
			System.out.println("==========================================");
			System.out.println("\t No ORDERS AVAILABLE  ");
			System.out.println("==========================================");
			return;
		}
		
		for (Order order : orderRepo.getAllUserCancelledOrders(user)) {

			System.out.println("========================================");
			System.out.println("|        CANCELLED ORDERS               |");
			System.out.println("========================================");
			System.out.println("========================================");
			System.out.println("| Ordered Items                         |");
			System.out.println("========================================");
			System.out.println("| Orders ID " + order.getId());
			System.out.println("========================================");
			for (Product product : order.getProducts()) {
				System.out.println(product.toMinimalDetails());
			}
			System.out.println("========================================");
		}

	}

	public void viewOrderByID(User user) {
		Scanner sc = new Scanner(System.in);

		System.out.println("=======================");
		System.out.println("| Enter Order ID:     |");
		System.out.println("=======================");
		System.out.print("> ");
		int id = sc.nextInt();
		sc.nextLine();
		Order order = orderRepo.getOrderById(id);
		if (order == null) {
			System.out.println("==========================================");
			System.out.println("\t No ORDERS available with ID " + id);
			System.out.println("==========================================");
			return;
		}
		ArrayList<Product> cart = order.getProducts();
		System.out.println("========================================");
		System.out.println("|          ORDER RECEIPT               |");
		System.out.println("========================================");
		System.out.printf("| Username : %-25s |\n", user.getUsername());
		System.out.printf("| Email    : %-25s |\n", user.getEmailId());
		System.out.println("----------------------------------------");
		System.out.println("| Shipping Address                     |");
		System.out.println("----------------------------------------");
		Address address = user.getAddress();
		System.out.printf("| House Name : %-22s |\n", address.getHousname());
		System.out.printf("| Location   : %-22s |\n", address.getLocation());
		System.out.printf("| Zip Code   : %-22d |\n", address.getZipcode());
		System.out.println("========================================");
		System.out.println("| Ordered Items                         |");
		System.out.println("========================================");

		for (Product product : cart) {
			System.out.println(product.toMinimalDetails());
		}

		System.out.println("========================================");
		System.out.printf("| TOTAL AMOUNT : $%-22.2f |\n", order.getTotalAmount());
		System.out.println("========================================");
		System.out.println("|    THANK YOU FOR YOUR PURCHASE!      |");
		System.out.println("========================================");

	}
	
	public void updateOrderList() {
		
		orderRepo.updateList();
	}
	
	public void cancelOrder(User user) {
		Scanner sc = new Scanner(System.in);

		System.out.println("=======================");
		System.out.println("| Enter Order ID:     |");
		System.out.println("=======================");
		System.out.print("> ");
		int id = sc.nextInt();
		sc.nextLine();
		Order order = orderRepo.getOrderById(id);
		if (order == null) {
			System.out.println("==========================================");
			System.out.println("\t No ORDERS available with ID " + id);
			System.out.println("==========================================");
			return;
		}
		
		updateStock(prodService.returnTheOriginalProductList(),1);
		prodService.updateProductList();
		
		boolean isCancelled = orderRepo.cancelOrder(user, id);
		if (isCancelled) {
			System.out.println("========================================");
			System.out.println("|    ORDER CANCELLED  SUCCESSFULLY        |");
			System.out.println("========================================");
		} else {
			System.out.println("========================================");
			System.out.println("|    ORDER CANCELLED  FAILED        |");
			System.out.println("========================================");
		}

	}

}
