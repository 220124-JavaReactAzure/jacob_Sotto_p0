package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.models.BankAccount;
import com.revature.p0.services.BankService;
import com.revature.p0.util.MenuRouter;

public class BankCreation extends Menu{
	
	private final BankService bankService;
	
	public BankCreation(BufferedReader consoleReader, MenuRouter router, BankService bankService) {
		super("BankCreation", "/bank-creation", consoleReader, router);
		this.bankService = bankService;
	}

	@Override
	public void render() throws Exception {
		System.out.println("Bank Account Creator \n" + "Fill out the information below");
		
		System.out.println("How much money do you want to start with in the bank?\n$");
		double balance = Double.parseDouble(consoleReader.readLine());
		
		if(balance <= 0) {
			System.out.println("You cannot do that. Please try again");
			router.transfer("/bank-creation");
		}
		BankAccount newBAccount = new BankAccount(balance);
		
		bankService.createBankAccount(newBAccount);
		System.out.println("Returning back to Dashboard");
		router.transfer("/dashboard");
	}
	

}
