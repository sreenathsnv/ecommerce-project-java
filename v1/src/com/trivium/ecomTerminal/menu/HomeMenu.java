package com.trivium.ecomTerminal.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.trivium.ecomTerminal.models.User;
import com.trivium.ecomTerminal.services.UserServices;

public class HomeMenu {

	public static User init() {
		User user = null;
		int choice = 0;

		UserServices userService = UserServices.getInstance();
		Scanner sc = new Scanner(System.in);

		do {
			try {
				System.out.println("==========================================");
				System.out.println("\t    Welcome To Cart Express");
				System.out.println("==========================================");
				System.out.println("\t[1] Register");
				System.out.println("\t[2] Login");
				System.out.println("\t[3] Exit");
				System.out.println("==========================================");
				System.out.print("-> Choose an option from above: ");
				choice = sc.nextInt();

				switch (choice) {

				case 1:
					System.out.print("Enter new username : ");
					String username = sc.next();
					String password = null;
					String cpassword = null;

					do {

						System.out.print("Enter new password : ");
						password = sc.next();
						System.out.print("Enter confirm password : ");
						cpassword = sc.next();

						if (!password.equals(cpassword)) {
							System.out.println("\n\tPassword not matching!");
							choice = 1;
						}
					} while (!password.equals(cpassword));

					System.out.print("Enter  housename : ");
					String housename = sc.next();

					System.out.print("Enter  location : ");
					String location = sc.next();
					System.out.print("Enter  email Id : ");
					String email = sc.next();
					System.out.print("Enter  phone number : ");
					String phn = sc.next();

					System.out.print("Enter  Zipcode : ");
					int zipcode = sc.nextInt();

					userService.registerUser(username, password, housename, location, zipcode, phn, email);
					choice = 2;
					break;
				case 2:
					System.out.print("Enter  username : ");
					username = sc.next();
					System.out.print("Enter  password : ");
					password = sc.next();

					user = userService.loginUser(username, password);
					if (user == null) {
						System.out.println("Inavalid credentials!");
						continue;
					}
					break;
				case 3:
					System.out.println("==========================================");
					System.out.println("\t Sign out successfully! Thank you");
					System.out.println("==========================================");
					System.exit(0);

				}
			} catch (InputMismatchException e) {
				System.out.println("==========================================");
				System.out.println("\t    Invallid Input! Give proper values");
				System.out.println("==========================================");
				continue;
			}

		} while (choice != 3 && user == null);
		

		return user;

	}

}
