package com.revature.entity;

public class User {
	private String AccHolderName;
		private int AccNumber;
		private double Balance;
		//private String CreditorDebit;
		
		public User(String AccHolderName,int AccNumber,double Balance) {
			super();
			this.AccHolderName = AccHolderName;
			this.AccNumber = AccNumber;
			this.Balance = Balance;
			
		}
	
		public String getAccHolderName() {
			return AccHolderName;
		}
	
		public void setAccHolderName(String accHolderName) {
			AccHolderName = accHolderName;
		}
	
		public int getAccNumber() {
			return AccNumber;
		}
	
		public void setAccNumber(int accNumber) {
			AccNumber = accNumber;
		}
	
		public double getBalance() {
			return Balance;
		}
	
		public void setBalance(double balance) {
			Balance = balance;
		}
	
		
		@Override
		public String toString() {
			return "user [AccNumber=" + AccNumber +",AccHolderName"+AccHolderName+ ", Balance=" + Balance+ "]";
		}
}
