package com.trivium.ecomTerminal.menu;

import java.util.Scanner;

import com.trivium.ecomTerminal.models.User;
import com.trivium.ecomTerminal.services.ProductServices;
import com.trivium.ecomTerminal.services.UserServices;

public class AdminMenu {
    
    public static void init(User user) {

        Scanner sc = new Scanner(System.in);

        ProductServices productService = ProductServices.getInstance();
  
        UserServices userService = UserServices.getInstance();

        int choice;
        do {

            System.out.println("\n==========================================");
            System.out.println("            Cart Express Admin Menu      ");
            System.out.println("==========================================");
            System.out.println("\t[1] Add a Product");
            System.out.println("\t[2] Edit Product");
            System.out.println("\t[3] Delete Product");
            System.out.println("\t[4] List All Products");
            System.out.println("\t[5] List Customers");
            System.out.println("\t[6] Edit a Customer");
            System.out.println("\t[7] View Stocks details");
            System.out.println("\t[8] Log out");
            System.out.println("==========================================");
            System.out.print("-> Choose an option from above: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    productService.addProduct();
                    break;
                case 2:
                    productService.updateProduct();
                    break;
                case 3:
                    productService.removeProduct();
                    break;
                case 4:
                    productService.getAllProducts();
                    break;
                case 5:
                	userService.viewAllUsers();
                    break;
                case 6:
                	userService.updateUser();
                    break;
                case 7:
                	productService.getProductById();
                    break;
                case 8:
                    System.out.println("==========================================");
                    System.out.println("\t Sign out successfully! Thank you");
                    System.out.println("==========================================");
                    user = null;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (user != null);
    }
}
