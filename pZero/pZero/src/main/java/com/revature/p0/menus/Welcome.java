package com.revature.p0.menus;


import java.io.BufferedReader;

import com.revature.p0.util.MenuRouter;
import static com.revature.p0.util.AppState.shutdown;

public class Welcome extends Menu{

	// allows Welcome menu to be viewed by appstate
	public Welcome(BufferedReader consoleReader, MenuRouter router) {
		super("Welcome", "/welcome", consoleReader, router);
	}
	@Override
	public void render() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Welcome to this.Bank.\n" + "1) Login\n" + "2) Register\n" + "3) Exit\n" + ">");
		
		String userSelection = consoleReader.readLine();
		
		//based on userselection will transfer the user to different  menus
		switch(userSelection) {
		case"1":
			router.transfer("/login");
			break;
		case"2":
			router.transfer("/register");
			break;
		case"3":
			shutdown();
			break;
		default:
			System.out.println("I can't bptptpt understand bptptpt what bptptpt you're saying");
			break;
		}
		
		
	}

}
