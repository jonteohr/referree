package net.jonteohr.referee;

import java.util.Scanner;

public class Console extends Thread {
	@Override
	public void run() {
		Scanner in = new Scanner(System.in);

		while(in.hasNext()) {
			String s = in.nextLine();

			if(s.equalsIgnoreCase("stop"))
				Launcher.onDisable();
		}
	}
}
