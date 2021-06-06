package net.jonteohr.referee;

import net.jonteohr.referee.sql.DataSave;

import javax.security.auth.login.LoginException;

public class Launcher {
	public static void main(String[] args) throws LoginException {

		// Start the bot instance
		Bot bot = new Bot();
		bot.start();
	}

	public static void onDisable() {
		System.out.println("***** SAVING DATA *****");

		if(DataSave.saveCodes())
			System.out.println("Saved codes.");
		else
			System.out.println("Failed saving codes...");

		System.out.println("***** SHUTTING DOWN *****");
		System.exit(0);
	}
}
