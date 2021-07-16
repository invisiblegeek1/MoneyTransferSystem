package com.revature.Exception;

public class InvalidEnteriesException extends Exception {
	public InvalidEnteriesException(String AccHolderName,int AccNumber,double Amount) {
		super();
	}

	public InvalidEnteriesException(double amount) {
		super();
	}
}
