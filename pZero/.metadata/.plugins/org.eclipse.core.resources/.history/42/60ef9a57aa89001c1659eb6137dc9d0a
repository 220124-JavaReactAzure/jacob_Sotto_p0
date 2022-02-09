package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.models.BankAccount;
import com.revature.p0.services.BankService;
import com.revature.p0.util.MenuRouter;
import com.revature.p0.util.collections.LinkedList;
import com.revature.p0.util.collections.List;

public class BankInfo extends Menu{
	private final BankService bankService;

	public BankInfo(BufferedReader consoleReader, MenuRouter router, BankService bankService) {
		super("BankInfo", "/bank-info", consoleReader, router);
		this.bankService = bankService;
	}

	@Override
	public void render() throws Exception {
		List<BankAccount> newList = new LinkedList<>();
		newList = bankService.findAllBankAccounts();
		if(newList.size() == 0) {
			System.out.println("You do not have a bank account");
			System.out.println("Rerouting to Dashboard");
			router.transfer("/dashboard");
		}
		double doubleBalance = 0;
		String id = null;
		for (int i = 0; i < newList.size(); i++) {
			doubleBalance = newList.get(i).getBankBalance();
			id = newList.get(i).getBankId();
		}
		
		System.out.println("User chose to view Bank information");
		System.out.println("Bank id: " + id + "\n");
		System.out.println("Your balance is: " + doubleBalance +"\n");
		
		System.out.println("Returning back to Dashboard");
		router.transfer("/dashboard");
		
	}
}
