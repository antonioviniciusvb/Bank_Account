package bank_account.repository;

import java.util.Optional;

import bank_account.model.Account;

public interface AccountRepository {

	public void findByAccountNumber(int accountNumber);
	public void findByAgency(int accountNumber);
	public void findByHolder(String holder);
	public void listAll();
	public void registrer(Account account);
	public void update(Account account);
	public void delete(int accountNumber);
	public void withdraw(int number, double withdrawValue);
	public void deposit(int number, double depositValue);
	public void transfer(int originNumber, int destinationNumber, double transferValue);
	public void transfer(Optional<Account> origin, Optional<Account> destination, double transfer);
}
