package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.exceptions.AuthenticationException;
import com.revature.p0.services.UserService;
import com.revature.p0.util.MenuRouter;

public class Login extends Menu{
	private final UserService userService;
	public Login(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Login", "/login", consoleReader, router);
		this.userService = userService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("Please enter your credentials for your bank account.");
		System.out.println("Username: ");
		String username = consoleReader.readLine();
		System.out.println("Password: ");
		String password = consoleReader.readLine();
		
		
		try {
			userService.authenticateUser(username, password);
			router.transfer("/dashboard");
		} catch(AuthenticationException e) {
			System.out.println("Incorrect credentials provided! No matching user account found");
		}
	}
	
}
