package bank_account;

import java.util.Scanner;

import bank_account.model.*;
import bank_account.util.Colors;
import bank_account.util.Converter;

public class Menu {

	public static Scanner read = new Scanner(System.in);

	public static String theme = Colors.TEXT_WHITE_BOLD_BRIGHT + Colors.TEXT_BLUE_BRIGHT;

	public static void main(String[] args) {

		Savings_Account savesAccount = new Savings_Account(20, 410, 1, "Antonio Bandeira", 
				1400.00d, Converter.stringToLocalDate("02/03/2023"));

		System.out.println(savesAccount);

		int option;

		do {
			showOperationsMenu();
			option = read.nextInt();

		} while (option != 9);

		showAboutProgram();

	}

	public static void showOperationsMenu() {
		System.out.println(theme + "o--------------------------------------------------o");
		System.out.println(theme + "|-------------- BANCO GENERATION BRAZIL -----------|");
		System.out.println(theme + "o--------------------------------------------------o");
		System.out.println("|            1- Create Account                     |");
		System.out.println("|            2- List All Accounts                  |");
		System.out.println("|            3- Find Account by number             |");
		System.out.println("|            4- Update Data of Account             |");
		System.out.println("|            5- Delete Account                     |");
		System.out.println("|            6- Withdraw Money                     |");
		System.out.println("|            7- Cash Deposit                       |");
		System.out.println("|            8- Make Transfers                     |");
		System.out.println("|            9- Exit                               |");
		System.out.println(theme + "o--------------------------------------------------o");
		System.out.println(theme + "|           Select the desired option:             |");
		System.out.println(theme + "o--------------------------------------------------o");
		System.out.println(Colors.TEXT_RESET);

	}

	public static void showAboutProgram() {
		System.out.println(theme + "o-------------------------------------------------------o");
		System.out.println(theme + "| Project: Bank Account                                 |");
		System.out.println(theme + "| https://github.com/antonioviniciusvb/Bank_Account.git |");
		System.out.println(theme + "| Developed by: Antonio Bandeira                        |");
		System.out.println(theme + "| Generation Brasil - generation@generation.org         |");
		System.out.println(theme + "o-------------------------------------------------------o");
	}

}
