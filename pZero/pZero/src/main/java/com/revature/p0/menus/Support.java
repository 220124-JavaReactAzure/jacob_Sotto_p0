package com.revature.p0.menus;

import java.io.BufferedReader;

import com.revature.p0.util.MenuRouter;
import static com.revature.p0.util.AppState.shutdown;

public class Support extends Menu{

	public Support(BufferedReader consoleReader, MenuRouter router) {
		super("Support", "/support", consoleReader, router);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void render() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Sorry we do not do that here");
		shutdown();
		
	}
	

}
