package bank_account;

import java.io.IOException;
import java.util.Scanner;

import bank_account.controller.AccountController;
import bank_account.model.Account;
import bank_account.model.Current_Account;
import bank_account.model.Savings_Account;
import bank_account.util.Colors;
import bank_account.util.Converter;
import bank_account.util.Text;
import bank_account.util.Validator;

public class Menu {

	public static String optionsMenuMain = "o--------------------------------------------------o\n"
			+ "|-------------- BANCO GENERATION BRAZIL -----------|\n"
			+ "o--------------------------------------------------o\n"
			+ "|            1- Create Account                     |\n"
			+ "|            2- List All Accounts                  |\n"
			+ "|            3- Find Account by number             |\n"
			+ "|            4- Update Data of Account             |\n"
			+ "|            5- Delete Account                     |\n"
			+ "|            6- Withdraw Money                     |\n"
			+ "|            7- Cash Deposit                       |\n"
			+ "|            8- Make Transfers                     |\n"
			+ "|            9- Exit                               |\n"
			+ "o--------------------------------------------------o\n"
			+ "|           Select the desired option:             |\n"
			+ "o--------------------------------------------------o";

	public static Scanner read = new Scanner(System.in);

	static AccountController controller = new AccountController();

	public static void main(String[] args) {

		int option;

		do {

			showOptionsMain();
			option = getIntegerInput("");
			System.out.println();

			showOperation(option);

			switch (option) {

			case 1 -> {

				controller.registrer(createAccount());

			}

			case 2 -> {
				controller.listAll();

			}

			case 3 -> {
				controller.findByAccountNumber(getIntegerInput("Enter the number to search:"));
			}

			case 4 -> {

				int find = getIntegerInput("Enter account number to update:");

				var account = controller.findAccount(find);

				if (account.isPresent()) {

					controller.update(createAccount());
				}
			}

			case 5 -> controller.delete(getIntegerInput("Enter account number to delete:"));

			case 6 -> {

				controller.withdraw(getIntegerInput("Account number:"), getDoubleInput("Withdrawal amount:"));
			}

			case 7 -> {

				controller.deposit(getIntegerInput("Account number:"), getDoubleInput("Deposit amount:"));
			}

			default -> System.out.println("to do");
			}

			systemPause();

		} while (continueLoop(option));

		showAboutProgram();

	}

	public static boolean continueLoop(int option) {
		return option != 9;
	}

	private static char getCharInput(String message) {

		return getStringInput(message).charAt(0);
	}

	private static int getIntegerInput(String message) {

		return Integer.parseInt(getStringInput(message));
	}

	private static String getStringInput(String message) {

		System.out.println(Colors.theme + message);
		System.out.printf(Colors.TEXT_RESET);
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
			return new Current_Account(controller.generateId(), agency, type, holder, balance, limit);
		} else {
			dateOfBirth = getStringInput("Enter your birthday:");

			return new Savings_Account(controller.generateId(), agency, type, holder, balance,
					Converter.stringToLocalDate(dateOfBirth));
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

	public static void showOperation(int operation) {
		
		var menu = optionsMenuMain.split("\n");
		
		System.out.println(Colors.theme + menu[0] +
				"\n" + menu[2 + operation] + "\n" + menu[2]);
	}

	public static void showOptionsMain() {
		System.out.println(Colors.theme + optionsMenuMain);
		System.out.printf(Colors.TEXT_RESET);
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
