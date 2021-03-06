package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.models.BankAccount;
import com.revature.p0.services.BankService;
import com.revature.p0.util.MenuRouter;
import com.revature.p0.util.collections.LinkedList;
import com.revature.p0.util.collections.List;

public class Withdraw extends Menu{
	private final BankService bankService;
	public Withdraw(BufferedReader consoleReader, MenuRouter router, BankService bankService) {
		super("Withdraw", "/withdraw", consoleReader, router);
		this.bankService = bankService;
	}

	@Override
	public void render() throws Exception {
		List<BankAccount> newList = new LinkedList<>();
		newList = bankService.findAllBankAccounts();
		if(newList.size() == 0) {
			System.out.println("You do not have a bank account to withdraw from");
			System.out.println("Rerouting to Dashboard");
			router.transfer("/dashboard");
		}
		double doubleBalance = 0;
		for (int i = 0; i < newList.size(); i++) {
			doubleBalance = newList.get(i).getBankBalance();
		}
		
		System.out.println("User chose to withdraw");
		System.out.println("Your current balance is: " + doubleBalance +"\n");
		System.out.println("How much would you like to withdraw?");
		double subtractBalance = Double.parseDouble(consoleReader.readLine());
		if(subtractBalance < 0) {
			System.out.println("You cannot withdraw negative money, please try again");
			router.transfer("/withdraw");
		}
		double differenceBalance = doubleBalance - subtractBalance;
		if(differenceBalance < 0) {
			System.out.println("Your balance cannot be negative, please try again");
			router.transfer("/withdraw");
		}
		doubleBalance = differenceBalance; 
		System.out.println("You took " + subtractBalance + " from your balance");
		System.out.println("Your new balance is: " + doubleBalance);
		
		bankService.updateBalance(doubleBalance);
		System.out.println("Returning back to Dashboard");
		router.transfer("/dashboard");
		
	}
}
