package bank_account.model;

import bank_account.util.OutPut;

public class Current_Account extends Account {

	private double limit;

	public Current_Account(int number, int agency, int type, String holder, double balance, double limit) {
		super(number, agency, type, holder, balance);
		this.limit = limit;
	}

	public double getLimit() {

		return calculateLimit();
	}

	private double calculateLimit() {

		if (this.getBalance() < 0)
			return this.limit + this.getBalance();

		return this.limit;

	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	@Override
	public boolean withdraw(double value) {

		if (this.getBalance() + this.limit < value) {

			OutPut.printFailed("Insufficient funds");
			return false;

		} else {
			this.setBalance(this.getBalance() - value);
			return true;
		}

	}

	@Override
	public String toString() {
		return super.toString() + String.format("Account Limit: $ %.2f\n", getLimit());
	}
}
