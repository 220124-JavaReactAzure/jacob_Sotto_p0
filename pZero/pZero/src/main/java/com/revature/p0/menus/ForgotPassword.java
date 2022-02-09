package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.util.MenuRouter;

public class ForgotPassword extends Menu{
	public ForgotPassword(BufferedReader consoleReader, MenuRouter router) {
		super("ForgotPassword", "/forgot-password", consoleReader, router);
	}

	@Override
	public void render() throws Exception {
		System.out.println("Please enter your credentials for your bank account.");
		System.out.println("Username: ");
		String username = consoleReader.readLine();
		System.out.println("Password: ");
		String password = consoleReader.readLine();
		
	}
}
