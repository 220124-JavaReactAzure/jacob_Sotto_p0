package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.exceptions.InvalidRequestException;
import com.revature.p0.models.Users;
import com.revature.p0.services.UserService;
import com.revature.p0.util.MenuRouter;

public class Register extends Menu {
	private final UserService userService;

	public Register(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Register", "/register", consoleReader, router);
		this.userService = userService;
	}

	@Override
		public void render() throws Exception {
			System.out.println("The user selected Register");
			System.out.println("Please provide us with registration information");
			
			System.out.println("First Name: ");
			String firstName = consoleReader.readLine();
			
			System.out.println("Last Name: ");
			String lastName = consoleReader.readLine();
			
			System.out.println("Email: ");
			String email = consoleReader.readLine();
			
			System.out.println("Username: ");
			String username = consoleReader.readLine();
			
			System.out.println("Password: ");
			String password = consoleReader.readLine();
			
			
			Users user = new Users(firstName, lastName, email, username, password);
			
			try {
				userService.registerNewUser(user);
				
			}catch(InvalidRequestException e) {
				System.out.println("You have provided invalid data, try again :(");
				
				router.transfer("/register");
			}
		}
}
