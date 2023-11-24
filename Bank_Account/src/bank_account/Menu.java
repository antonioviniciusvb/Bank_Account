package bank_account;

import java.io.IOException;
import java.util.Scanner;

import bank_account.controller.AccountController;
import bank_account.model.Account;
import bank_account.model.Current_Account;
import bank_account.model.Savings_Account;
import bank_account.util.Colors;
import bank_account.util.Converter;
import bank_account.util.Validator;

public class Menu {

	public static Scanner read = new Scanner(System.in);

	public static void main(String[] args) {

		AccountController controller = new AccountController();

		char option;

		do {

			showOperationsMenu();
			option = getCharInput("");

			switch (option) {

			case '1' -> controller.registrer(createAccount());

			case '2' -> controller.listAll();

			case '3' -> controller.findByAccountNumber(getIntegerInput("Enter the number to search:"));

			case '4' -> {

				int find = getIntegerInput("Enter account number to update:");

				var account = controller.findAccount(find);
				
				System.out.println(account);
				if (account.isPresent()) {

					controller.update(createAccount());
				}
			}

			case '5' -> controller.delete(getIntegerInput("Enter the number to delete:"));

			default -> System.out.println("to do");
			}

			systemPause();

		} while (continueLoop(option));

		showAboutProgram();

	}

	public static boolean continueLoop(char option) {
		return option != '9';
	}

	private static char getCharInput(String message) {

		return getStringInput(message).charAt(0);
	}

	private static int getIntegerInput(String message) {

		return Integer.parseInt(getStringInput(message));
	}

	private static String getStringInput(String message) {

		System.out.println(message);
		return read.nextLine();
	}

	private static double getDoubleInput(String message) {

		return Double.parseDouble(getStringInput(message));
	}

	private static Account createAccount() {

		int agency, type;
		String holder, dateOfBirth;
		double balance = 0.0d, limit = 0.0d;

		agency = getIntegerInput("Enter the agency number:");

		holder = getStringInput("Enter the name of the holder:");

		balance = getDoubleInput("Enter the account balance:");

		type = getIntegerInput("1- Current account\n2- Savings account\nChoose account option:");

		if (type == 1) {

			limit = getDoubleInput("Enter the account limit:");
			return new Current_Account(1, 410, agency, holder, balance, limit);
		} else {
			dateOfBirth = getStringInput("Enter your birthday:");

			return new Savings_Account(1, 410, agency, holder, balance, Converter.stringToLocalDate(dateOfBirth));
		}

	}

	public static void systemPause() {

		try {
			System.out.println(Colors.TEXT_RESET + "\nPress enter to continue:");
			System.in.read();
			read.skip("\\R");

		} catch (IOException e) {
			System.err.println("KeyPress invalid");
		}
	}

	public static void showOperationsMenu() {
		System.out.println(Colors.theme + "o--------------------------------------------------o");
		System.out.println("|-------------- BANCO GENERATION BRAZIL -----------|");
		System.out.println("o--------------------------------------------------o");
		System.out.println("|            1- Create Account                     |");
		System.out.println("|            2- List All Accounts                  |");
		System.out.println("|            3- Find Account by number             |");
		System.out.println("|            4- Update Data of Account             |");
		System.out.println("|            5- Delete Account                     |");
		System.out.println("|            6- Withdraw Money                     |");
		System.out.println("|            7- Cash Deposit                       |");
		System.out.println("|            8- Make Transfers                     |");
		System.out.println("|            9- Exit                               |");
		System.out.println("o--------------------------------------------------o");
		System.out.println("|           Select the desired option:             |");
		System.out.println("o--------------------------------------------------o");
		System.out.println(Colors.TEXT_RESET);

	}

	public static void showAboutProgram() {
		System.out.println(Colors.theme + "o-------------------------------------------------------o");
		System.out.println("| Project: Bank Account                                 |");
		System.out.println("| https://github.com/antonioviniciusvb/Bank_Account.git |");
		System.out.println("| Developed by: Antonio Bandeira                        |");
		System.out.println("| Generation Brasil - generation@generation.org         |");
		System.out.println("o-------------------------------------------------------o");
	}

}
