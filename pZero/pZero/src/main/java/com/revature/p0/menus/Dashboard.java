package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.models.Users;
import com.revature.p0.services.UserService;
import com.revature.p0.util.MenuRouter;

public class Dashboard extends Menu {

	private final UserService userService;

	public Dashboard(BufferedReader consoleReader, MenuRouter router, UserService userService) {
		super("Dashboard", "/dashboard", consoleReader, router);
		this.userService = userService;
	}

	@Override
	public void render() throws Exception {

		Users sessionUser = userService.getSessionUser();

		if (sessionUser == null) {
			System.out.println("You are currently not logged in. Returning you to the login screen!");
			router.transfer("/login");
			return;
		}

		while (userService.isSessionActive()) {
			System.out.println("Welcome " + sessionUser.getUsername());
			String menu = "1) View Bank Information\n" + "2) Create Bank Account \n" + "3) Deposit\n" + "4) Withdraw\n"
					+ "5) Support \n" + "> ";

			System.out.print(menu);

			String userSelection = consoleReader.readLine();
			
			//based on user selection will select a case to transfer the user to specified menus
			switch(userSelection) {
			case "1":
				System.out.println("View Bank Information Selected");
				router.transfer("/bank-info");
				break;
			case "2":
				System.out.println("Create Bank Account Selected");
				router.transfer("/bank-creation");
				break;
			case "3":
				System.out.println("Deposit Selected");
				router.transfer("/deposit");
				break;
			case "4":
				System.out.println("Withdraw Selected");
				router.transfer("/withdraw");
				break;
			case "5":
				System.out.println("Support Selected");
				router.transfer("/support");
				break;
				
			default:
				System.out.println("Invalid selection");
			}
		}

	}
}
