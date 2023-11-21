package bank_account.util;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

public class Converter {

	public static DateTimeFormatter formatterPortuguesBrazil = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static LocalDate stringToLocalDate(String date) {

		return LocalDate.parse(date, formatterPortuguesBrazil);
	}
	
	public static String localDateToString(LocalDate date) {

		return date.format(formatterPortuguesBrazil);
	}

}
