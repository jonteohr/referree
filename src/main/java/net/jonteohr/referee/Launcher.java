package net.jonteohr.referee;

import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;

public class Launcher {
	public static void main(String[] args) throws LoginException {

		// Start the bot instance
		Bot bot = new Bot();
		bot.start();
	}
}
