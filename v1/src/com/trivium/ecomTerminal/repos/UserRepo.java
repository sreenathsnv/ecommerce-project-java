package com.trivium.ecomTerminal.repos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.trivium.ecomTerminal.models.Address;
import com.trivium.ecomTerminal.models.User;

public class UserRepo {
	
	private final String FILE_PATH = "src/com/trivium/ecomTerminal/data/users.txt";
	private ArrayList<User> users = new ArrayList<User>();

	public UserRepo() {
		
		loadUsers();
		User admin = new User(1, "admin", "12345", new Address("xxx", "yyyy", 111111), "99292929292", "admin@email.com",
				true);
		registerUser(admin);
		saveUsers();
		

	}

	public void updateList() {
		saveUsers();
	}

	public static UserRepo getInstance() {

		return new UserRepo();
	}

	public boolean registerUser(User user) {
		
		if(user.isRole() && isUsernameExists(user.getUsername())) {
			return true;
		}
		
		if (isUsernameExists(user.getUsername())) {
			
			System.out.println("Username already exists");
			return false;
		} else if (isEmailIdExists(user.getEmailId())) {

			System.out.println("Email Id already exists");
			return false;
		}

		boolean isAdded = users.add(user);
		saveUsers();
		return isAdded;

	}

	public User getUserByUsername(String username) {

		return users.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
	}

	public boolean isUsernameExists(String username) {

		return users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username));

	}

	public boolean isEmailIdExists(String email) {

		return users.stream().anyMatch(user -> user.getEmailId().equalsIgnoreCase(email));

	}

	public User loginUser(String username, String password) {

		for (User user : users) {

			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public boolean isAdmin(User user) {

		return user.isRole();
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public void loadUsers() {
		
		File file = new File(FILE_PATH);
		if(!file.exists()) {
			return;
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
			users = (ArrayList<User>) ois.readObject();
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void saveUsers() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
			oos.writeObject(users);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	 }
