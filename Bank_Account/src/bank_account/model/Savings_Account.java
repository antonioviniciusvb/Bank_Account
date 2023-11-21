package bank_account.model;

import java.time.LocalDate;

import bank_account.util.Converter;

public class Savings_Account extends Account {

	private LocalDate dateOfBirth;

	public Savings_Account(int number, int agency, int type, String holder, double balance, LocalDate dateOfBirth) {
		super(number, agency, type, holder, balance);

		this.dateOfBirth = dateOfBirth;
	}
	
	public LocalDate getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	@Override
	public String toString() {
		return super.toString()
				+ String.format("Account Date of Birth: %s\n",  Converter.localDateToString(dateOfBirth));
	}

}
