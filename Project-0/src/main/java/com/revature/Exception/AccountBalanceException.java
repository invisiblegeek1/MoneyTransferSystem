package com.revature.Exception;

public class AccountBalanceException extends Exception {
	public AccountBalanceException(double balance) {
		super(String.valueOf(balance));
	}

}
