package bank_account.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bank_account.model.Account;
import bank_account.repository.AccountRepository;
import bank_account.util.OutPut;

public class AccountController implements AccountRepository {

	private ArrayList<Account> accounts = new ArrayList<Account>();
	private int id;
	public String errorOperation = "Account number not found";

	@Override
	public void findByAccountNumber(int accountNumber) {
		var account = findAccount(accountNumber);

		if (account.isPresent())
			OutPut.printSucess(account.get().toString());
		else
			OutPut.printFailed(errorOperation);

	}

	@Override
	public void listAll() {

		accounts.forEach(account -> OutPut.printSucess(account.toString()));

	}

	@Override
	public void registrer(Account account) {
		accounts.add(account);
		OutPut.printfSucess("Account %d successfully created\n", account.getNumber());
	}

	@Override
	public void update(Account account) {

		var accountToUpdate = findAccount(account.getNumber());

		if (accountToUpdate.isPresent()) {

			var objectUpdated = accounts.set(accounts.indexOf(accountToUpdate.get()), account);

			if (objectUpdated != null)
				OutPut.printfSucess("Account: %d was updated successfully\n", account.getNumber());
		} else {

			OutPut.printFailed(errorOperation);
		}

	}

	@Override
	public void delete(int number) {

		var account = findAccount(number);

		if (account.isPresent()) {
			if (accounts.remove(account.get()))
				OutPut.printfSucess("Account: %d has been successfully removed\n", number);
			else
				OutPut.printFailed(errorOperation);
		}else
			OutPut.printFailed(errorOperation);
	}

	@Override
	public void withdraw(int number, double withdrawValue) {

		var account = findAccount(number);

		if (account.isPresent()) {
			if (account.get().withdraw(withdrawValue))
				OutPut.printfSucess("The withdrawal of $ %.2f made successfully\n", withdrawValue);
		} else
			OutPut.printFailed(errorOperation);
	}

	@Override
	public void deposit(int number, double depositValue) {

		var account = findAccount(number);

		if (account.isPresent()) {
			account.get().deposit(depositValue);
			OutPut.printfSucess("The Deposit of $ %.2f made successfully\n", depositValue);
		} else
			OutPut.printFailed(errorOperation);
	}

	@Override
	public void transfer(int originNumber, int destinationNumber, double transferValue) {

		var originAccount = findAccount(originNumber);
		var destinationAccount = findAccount(destinationNumber);

		if (originAccount.isPresent() && destinationAccount.isPresent()) {

			if (originAccount.get().withdraw(transferValue)) {

				destinationAccount.get().deposit(transferValue);

				OutPut.printfSucess("Transfer carried out successfully: %.2f" + " from Account: %d to Account: %\n",
						transferValue, originNumber, destinationNumber);
			}
		} else {
			OutPut.printFailed(errorOperation);
		}

	}

	@Override
	public void transfer(Optional<Account> origin, Optional<Account> destination, double transfer) {

		if (origin.isPresent() && destination.isPresent()) {

			if (origin.get().withdraw(transfer)) {

				destination.get().deposit(transfer);

				OutPut.printfSucess("Transfer carried out successfully: %.2f\n" + " from Account: %d to Account: %d\n",
						transfer, origin.get().getNumber(), destination.get().getNumber());
			}
		} else {
			OutPut.printFailed(errorOperation);
		}
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

	@Override
	public void findByAgency(int accountNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findByHolder(String holder) {
		var account = accounts.stream()
				.filter(a -> a.getHolder().contains(holder))
				.collect(Collectors.toList());
		
	 account.forEach(x -> System.out.println(x));

	}

}
