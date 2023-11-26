package bank_account.util;

import java.io.IOException;
import java.util.Scanner;

public class Input {
	
	public static Scanner read = new Scanner(System.in);
	
	public static char getChar(String message) {

		return getString(message).charAt(0);
	}

	public static int getInteger(String message) {

		return Integer.parseInt(getString(message));
	}

	public static String getString(String message) {

		System.out.println(Colors.theme + message);
		System.out.printf(Colors.TEXT_RESET);
		return read.nextLine();
	}

	public static double getDouble(String message) {

		return Double.parseDouble(getString(message));
	}
	
	public static void systemPause() {

		try {
			System.out.println(Colors.TEXT_RESET + "\nPress enter to continue:");
			System.in.read();
			Input.read.skip("\\R");

		} catch (IOException e) {
			OutPut.printFailed("KeyPress invalid");
		}
	}

}
