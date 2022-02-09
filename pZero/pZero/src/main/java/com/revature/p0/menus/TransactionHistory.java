package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.util.MenuRouter;

public class TransactionHistory extends Menu{
	public TransactionHistory(BufferedReader consoleReader, MenuRouter router) {
		super("TransactionHistory", "/transaction-history", consoleReader, router);
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
