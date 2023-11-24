package bank_account.model;

public abstract class Account {

	private int number;
	private int agency;
	private int type;
	private String holder;
	private double balance;

	public Account(int number, int agency, int type, String holder, double balance) {
		this.number = number;
		this.agency = agency;
		this.type = type;
		this.holder = holder;
		this.balance = balance;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getAgency() {
		return agency;
	}

	public void setAgency(int agency) {
		this.agency = agency;
	}

	public int getType() {
		return type;
	}

	public String getTypeDescription() {
		return type == 1 ? "Current Account" : "Savings account";
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean withdraw(double value) {

		if (this.balance < value) {

			System.out.println("Insufficient funds");
			return false;

		} else {
			this.balance -= value;
			return true;
		}

	}

	public void deposit(double value) {

		setBalance(this.balance + value);

	}

	public String toString() {

		return "******** Data Account ********\n" + String.format("Holder: %s\n", this.holder)
				+ String.format("Account Number: %d\n", this.number) + String.format("Agency: %d\n", this.agency)
				+ String.format("Type: %s\n", getTypeDescription()) + String.format("Balance: $ %.2f\n", this.balance);
	}

}
