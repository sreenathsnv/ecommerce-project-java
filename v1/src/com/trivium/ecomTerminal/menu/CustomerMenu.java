package com.trivium.ecomTerminal.menu;

import java.util.Scanner;

import com.trivium.ecomTerminal.models.User;
import com.trivium.ecomTerminal.services.OrderService;
import com.trivium.ecomTerminal.services.ProductServices;

public class CustomerMenu {

	public static void init(User user) {

		Scanner sc = new Scanner(System.in);

		ProductServices productService = ProductServices.getInstance();
		OrderService orderService =  OrderService.getInstance();

		int choice;
		do {

			System.out.println("\n==========================================");
			System.out.println("            Cart Express Menu          ");
			System.out.println("==========================================");
			System.out.println("\t[1] View Products");
			System.out.println("\t[2] Get Product Details by ID");
			System.out.println("\t[3] Buy Product");
			System.out.println("\t[4] Vew all Orders");
			System.out.println("\t[5] Find order");
			System.out.println("\t[6] Cancel order");
			System.out.println("\t[7] View all Cancelled Orders");
			System.out.println("\t[8] Show Profile");
			System.out.println("\t[9] Logout");
			System.out.println("==========================================");
			System.out.print("-> Choose an option from above: ");
			sc.nextLine(); 

			
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				productService.getAllProducts();
				break;
			case 2:
				productService.getProductById();
				break;
			case 3:
				orderService.placeOrder(user);
				break;
			case 4:
				orderService.viewAllOrders(user);
				break;
			case 5:
				orderService.viewOrderByID(user);
				break;
			case 6:
				orderService.cancelOrder(user);
				break;
			case 7:
				orderService.viewAllCancelledOrders(user);
				break;
			case 8:
				System.out.println(user);
				break;
			case 9:
				System.out.println("==========================================");
				System.out.println("\t Sign out successfully! Thank you");
				System.out.println("==========================================");
				user = null;
				break;
			default:
				System.out.println("==========================================");
				System.out.println("\t Sign out successfully! Thank you");
				System.out.println("==========================================");
			}
			

		} while (user != null);
		


	}

}
