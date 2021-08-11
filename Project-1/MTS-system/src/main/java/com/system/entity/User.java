package com.system.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "userinfo",schema = "project1")

public class User {
	
	
	
	@Column(name="AccHolderName")
	 String AccHolderName;
	@Id
	@Column(name = "AccNumber")
	 int AccNumber;
	@Column(name = "balance")
	 double Balance;
	
//	
//	public User(String AccHolderName,int AccNumber,double Balance) {
//		super();
//		this.AccHolderName = AccHolderName;
//		this.AccNumber = AccNumber;
//		this.Balance = Balance;
//		
//	}

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
