package bank_account.controller;

import java.util.ArrayList;

import bank_account.model.Account;
import bank_account.repository.AccountRepository;
import bank_account.util.Colors;

public class AccountController implements AccountRepository {

	private ArrayList<Account> accounts = new ArrayList<Account>();
	private int id;

	@Override
	public void findByAccountNumber(int accountNumber) {
		var account = findAccount(accountNumber);

		if (account != null)
			System.out.println(Colors.ANSI_GREEN_BACKGROUND + account);
		else
			System.err.println("Account number not found");

	}

	@Override
	public void findByAgency(int agency) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listAll() {

		accounts.forEach(account -> System.out.println(account));

	}

	@Override
	public void registrer(Account account) {
		accounts.add(account);
		System.out.printf(Colors.ANSI_GREEN_BACKGROUND + 
				"Account %d successfully created\n", account.getNumber());
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int number) {

		var account = findAccount(number);

		if (account != null)
			if (accounts.remove(account))
				System.out.printf("Account number: %d has been successfully removed\n", account.getNumber());
			else
				System.err.println("Account number not found");

	}

	@Override
	public void withdraw(int number, double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deposit(int number, double value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transfer(int originNumber, int destinationNumber, double value) {
		// TODO Auto-generated method stub

	}

	public int generateId() {
		return ++id;
	}

	private Account findAccount(int number) {

		for (var account : accounts)
			if (account.getNumber() == number)
				return account;

		return null;
	}

}
