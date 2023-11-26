package bank_account.util;

public class OutPut {

	public static void printFailed(String message) {
		System.out.println(Colors.failed + message);
	}
	
	public static void printSucess(String message) {
		System.out.println(Colors.sucess + message);
	}
	
	public static void printfFailed(String message, Object... args) {
		System.out.println(Colors.failed + String.format(message, args).toString());
	}
	
	public static void printfSucess(String message, Object... args) {
		System.out.println(Colors.sucess + String.format(message, args).toString());
	}
}
