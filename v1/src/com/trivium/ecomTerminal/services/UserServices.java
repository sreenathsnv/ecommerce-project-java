package com.trivium.ecomTerminal.services;

import java.util.ArrayList;
import java.util.Scanner;

import com.trivium.ecomTerminal.models.Address;
import com.trivium.ecomTerminal.models.User;
import com.trivium.ecomTerminal.repos.UserRepo;

public class UserServices {

	private static int _id = 101;

	private static int autoIdGenerator() {
		return _id++;
	}

	private UserRepo userRepo = UserRepo.getInstance();

	private ArrayList<User> users = userRepo.getUsers();

	public void registerUser(String username, String password, String housename, String location, int zipcode, String phn,
			String email) {

		Address addr = new Address(housename, location, zipcode);
		User user = new User(autoIdGenerator(), username, password, addr, phn, email);
		if (userRepo.registerUser(user)) {
			System.out.println("\t\tSuccessfully registered");
			System.out.println("\t\tWelcome " + username);
		} else {
			System.out.println("Try again.!");
		}

	}

	public static UserServices getInstance() {

		return new UserServices();
	}

	public User loginUser(String username, String password) {
		User user = userRepo.loginUser(username, password);

		if (user != null) {
			System.out.println("Login successfully!");
			System.out.println("\t\t Welecom " + username);
		} else {
			System.out.println("\t\tLogin failed");
			System.out.println("Check your credentials again");

		}
		return user;
	}

	public void viewAllUsers() {

		if (users.isEmpty()) {
			System.out.println("=======================");
			System.out.println("| No User FOUND    |");
			System.out.println("=======================");

		} else {
			System.out.println("=======================");
			System.out.println("| ALL REGISTERED USERS   |");
			System.out.println("=======================");

			System.out.println("=============================================");
			for (User user : users) {
				System.out.println(user);
			}
			System.out.println("=============================================");
		}
	}

	public void viewUserById(int userId) {
		User user = findUserById(userId);
		if (user != null) {
			System.out.println("\n\t\tUser details:");
			System.out.println("=============================================");
			System.out.println(user);
			System.out.println("=============================================");
		} else {
			System.out.println("User with ID " + userId + " not found.");
		}
	}

	public void updateUser() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("=======================");
		System.out.println("| Enter User ID:   |");
		System.out.println("=======================");
		System.out.print("> ");
		int userId = scanner.nextInt();
		scanner.nextLine();
		User user = findUserById(userId);

		if (user != null) {
			System.out.println("\n\t\tUpdate details for User ID: " + userId);
			System.out.println("=============================================");
			System.out.print("Enter new username (leave empty to keep current): ");
			String newUsername = scanner.nextLine().trim();
			if (!newUsername.isEmpty()) {
				user.setUsername(newUsername);
			}

			System.out.print("Enter new password (leave empty to keep current): ");
			String newPassword = scanner.nextLine().trim();
			if (!newPassword.isEmpty()) {
				user.setPassword(newPassword);
			}

			System.out.print("Enter new house name (leave empty to keep current): ");
			String newHousename = scanner.nextLine().trim();
			if (!newHousename.isEmpty()) {
				user.getAddress().setHousname(newHousename);
			}

			System.out.print("Enter new location (leave empty to keep current): ");
			String newLocation = scanner.nextLine().trim();
			if (!newLocation.isEmpty()) {
				user.getAddress().setLocation(newLocation);
			}

			System.out.print("Enter new zipcode (enter 0 to keep current): ");
			int newZipcode = scanner.nextInt();
			scanner.nextLine();
			if (newZipcode != 0) {
				user.getAddress().setZipcode(newZipcode);
			}

			
			System.out.print("Enter new phone number (leave empty to keep current): ");
			String newPhn = scanner.nextLine().trim();
			if (!newPhn.isEmpty()) {
				user.setMobileNumber(newPhn);
			}


			System.out.print("Enter new email (leave empty to keep current): ");
			String newEmail = scanner.nextLine().trim();
			if (!newEmail.isEmpty()) {
				user.setEmailId(newEmail);
			}
			userRepo.updateList();
			System.out.println("\t\tUser details updated successfully.");
		} else {
			System.out.println("User with ID " + userId + " not found.");
		}
	}

	public void removeUser(int userId) {
		User user = findUserById(userId);

		if (user != null) {
			users.remove(user);
			userRepo.updateList();
			System.out.println("\t\tUser removed successfully.");
		} else {
			System.out.println("User with ID " + userId + " not found.");
		}
	}

	private User findUserById(int userId) {
		for (User user : userRepo.getUsers()) {
			if (user.getId() == userId) {
				return user;
			}
		}
		return null;
	}

}
