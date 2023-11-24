package bank_account.repository;

import bank_account.model.Account;

public interface AccountRepository {

	public void findByAccountNumber(int accountNumber);
	public void findByAgency(int agency);
	public void listAll();
	public void registrer(Account account);
	public void update(Account account);
	public void delete(int accountNumber);
	public void withdraw(int number, double value);
	public void deposit(int number, double value);
	public void transfer(int originNumber, int destinationNumber, double value);
}
