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
import com.revature.p0.menus.Support;
import com.revature.p0.menus.TransactionHistory;
import com.revature.p0.menus.Welcome;
import com.revature.p0.menus.Withdraw;
import com.revature.p0.models.BankAccount;
import com.revature.p0.services.BankService;
import com.revature.p0.services.UserService;

// creates class appstate, the heart of the entire program
public class AppState {
	//defines static a boolean
	private static boolean isRunning;
	//defines menu router object
	private final MenuRouter router;
	public AppState() {
		super(); //calls super class
		isRunning = true; //sets isRunning to true
		router = new MenuRouter(); // initialize router
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in)); //defines and initializes consolereader to prepare for inputs
		
		UserDao userDao = new UserDao(); //creates object userdao
		BankAccountDao accountDao= new BankAccountDao(); //creates object accountdao
		UserService userService = new UserService(userDao); //creates object userService
		BankService bankService = new BankService(accountDao,userService); //creates object bankService
		
		//adds the many menus that will be used in the program
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
		router.addMenu(new Support(consoleReader, router));
		
	}
	//  starts the program up
	public void startUp() {
		try {
			while(isRunning) {
				router.transfer("/welcome");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//when called, the program will stop
	public static void shutdown() {
		isRunning = false;
		System.out.println("Shutting Down...");
		System.exit(0);
	}
	
}
