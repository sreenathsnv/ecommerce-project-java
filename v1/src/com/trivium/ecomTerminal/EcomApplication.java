package com.trivium.ecomTerminal;

import com.trivium.ecomTerminal.menu.AdminMenu;
import com.trivium.ecomTerminal.menu.CustomerMenu;
import com.trivium.ecomTerminal.menu.HomeMenu;
import com.trivium.ecomTerminal.models.User;

public class EcomApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User currentUser = null;
		do {

			currentUser = HomeMenu.init();

			if (currentUser.isRole()) {
				AdminMenu.init(currentUser);
			} else {
				CustomerMenu.init(currentUser);
			}
		} while (currentUser != null);

	}

}

// models package contains all the models that are required for this project
// services contains functions to perform all the operations
// repositories contains data that is made using the models

//The static getInstance method is used to maintain persistence of the records throughout the program - didnt work 
// switched to files for better persistence of data