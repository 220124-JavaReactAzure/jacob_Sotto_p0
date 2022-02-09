package com.revature.p0.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.revature.p0.daos.BankAccountDao;
import com.revature.p0.daos.UserDao;
import com.revature.p0.menus.BankCreation;
import com.revature.p0.menus.BankInfo;
import com.revature.p0.menus.Dashboard;
import com.revature.p0.menus.Deposit;
import com.revature.p0.menus.ForgotPassword;
import com.revature.p0.menus.Login;
import com.revature.p0.menus.Register;
import com.revature.p0.menus.TransactionHistory;
import com.revature.p0.menus.Welcome;
import com.revature.p0.menus.Withdraw;
import com.revature.p0.models.BankAccount;
import com.revature.p0.services.BankService;
import com.revature.p0.services.UserService;

public class AppState {
	private static boolean isRunning;
	private final MenuRouter router;
	public AppState() {
		super();
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		UserDao userDao = new UserDao();
		BankAccountDao accountDao= new BankAccountDao();
		UserService userService = new UserService(userDao);
		BankService bankService = new BankService(accountDao,userService);
		
		
		router.addMenu(new Welcome(consoleReader,router));
		router.addMenu(new Register(consoleReader,router, userService));
		router.addMenu(new Login(consoleReader,router, userService));
		router.addMenu(new ForgotPassword(consoleReader,router));
		router.addMenu(new Dashboard(consoleReader,router, userService));
		router.addMenu(new BankInfo(consoleReader,router, bankService));
		router.addMenu(new Deposit(consoleReader,router, bankService));
		router.addMenu(new Withdraw(consoleReader,router, bankService));
		router.addMenu(new TransactionHistory(consoleReader,router));
		router.addMenu(new BankCreation(consoleReader, router, bankService));
		
	}
	
	public void startUp() {
		try {
			while(isRunning) {
				router.transfer("/welcome");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdown() {
		isRunning = false;
	}
	
}
