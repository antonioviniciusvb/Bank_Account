package bank_account;

import bank_account.controller.AccountController;
import bank_account.model.Account;
import bank_account.model.Current_Account;
import bank_account.model.Savings_Account;
import bank_account.util.Colors;
import bank_account.util.Converter;
import bank_account.util.Input;
import bank_account.util.OutPut;

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
			+ "|            9- Find Account by Holder             |\n"
			+ "|            0- Exit                               |\n"
			+ "o--------------------------------------------------o\n"
			+ "|           Select the desired option:             |\n"
			+ "o--------------------------------------------------o";

	static AccountController controller = new AccountController();

	public static void main(String[] args) {

		int option;

		do {

			showOptionsMain();
			option = Input.getInteger("");
			System.out.println();

			showOperation(option);

			switch (option) {

			case 1 -> controller.registrer(createAccount(0, 0));

			case 2 -> controller.listAll();

			case 3 -> controller.findByAccountNumber(Input.getInteger("Enter the number to search:"));

			case 4 -> {

				var account = controller.findAccount(Input.getInteger("Enter account number to update:"));

				if (account.isPresent())
					controller.update(createAccount(account.get().getNumber(), account.get().getType()));
				else
					OutPut.printFailed(controller.errorOperation);
			}

			case 5 -> controller.delete(Input.getInteger("Enter account number to delete:"));

			case 6 -> controller.withdraw(Input.getInteger("Account number:"), Input.getDouble("Withdrawal amount:"));

			case 7 -> controller.deposit(Input.getInteger("Account number:"), Input.getDouble("Deposit amount:"));

			case 8 -> {

				var origin = controller.findAccount(Input.getInteger("Origin Account:"));
				var destination = controller.findAccount(Input.getInteger("Destination Account:"));
				var transfer = Input.getDouble("Transfer value:");

				if (origin.isPresent() && destination.isPresent()) {

					controller.transfer(origin, destination, transfer);
				} else {
					OutPut.printFailed(controller.errorOperation);
				}
			}

			case 9 -> controller.findByHolder(Input.getString("Enter account holder to find:"));

			}

			Input.systemPause();

		} while (continueLoop(option));

		showAboutProgram();
	}

	public static boolean continueLoop(int option) {
		return option != 0;
	}

	private static Account createAccount(int accountNumber, int accountType) {

		int agency, type, number;
		String holder, dateOfBirth;
		double balance = 0.0d, limit = 0.0d;

		number = defineAccountNumber(accountNumber);
		type = defineAccountType(accountType);

		agency = Input.getInteger("Enter the agency number:");

		holder = Input.getString("Enter the name of the holder:");

		balance = Input.getDouble("Enter the account balance:");

		if (type == 0)
			type = Input.getInteger("1- Current account\n2- Savings account\nChoose account option:");

		if (type == 1) {

			limit = Input.getDouble("Enter the account limit:");
			return new Current_Account(number, agency, type, holder, balance, limit);
		} else {
			dateOfBirth = Input.getString("Enter your birthday:");

			return new Savings_Account(number, agency, type, holder, balance, Converter.stringToLocalDate(dateOfBirth));
		}
	}

	private static int defineAccountType(int accountType) {
		return accountType == 1 || accountType == 2 ? accountType : 0;
	}

	private static int defineAccountNumber(int accountNumber) {
		return accountNumber > 0 ? accountNumber : controller.generateId();
	}

	public static void showOperation(int operation) {

		var menu = optionsMenuMain.split("\n");

		System.out.println(Colors.theme + menu[0] + "\n" + menu[2 + operation] + "\n" + menu[2]);
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
