package bank_account.controller;

import java.util.ArrayList;
import java.util.Optional;

import bank_account.model.Account;
import bank_account.repository.AccountRepository;
import bank_account.util.Colors;

public class AccountController implements AccountRepository {

	private ArrayList<Account> accounts = new ArrayList<Account>();
	private int id;

	@Override
	public void findByAccountNumber(int accountNumber) {
		var account = findAccount(accountNumber);

		if (account.isPresent())
			System.out.println(Colors.ANSI_GREEN_BACKGROUND + account.get());
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
		System.out.printf(Colors.ANSI_GREEN_BACKGROUND + "Account %d successfully created\n", account.getNumber());
	}

	@Override
	public void update(Account account) {

		var findAccount = findAccount(account.getNumber());

		if (findAccount.isPresent()) {
			accounts.set(accounts.indexOf(findAccount.get()), account);
			System.out.printf("Account number: %d has been successfully removed\n", account.getNumber());
		} else
			System.err.println("Account number not found");

	}

	@Override
	public void delete(int number) {

		var account = findAccount(number);

		if (account.isPresent())
			if (accounts.remove(account.get()))
				System.out.printf("Account number: %d has been successfully removed\n", account.get());
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

	public Optional<Account> findAccount(int number) {

		for (var account : accounts)
			if (account.getNumber() == number)
				return Optional.of(account);

		return Optional.empty();
	}

}
