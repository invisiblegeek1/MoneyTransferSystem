package com.revature.entity;

public class Transaction {
	private int FromAccount;
	private int ToAccount;
	private int Amount;
	private String Type;
	private String Date;
	
	public Transaction(int FromAccount,int ToAccount, int Amount, String Type,String Date) {
		super();
		this.FromAccount = FromAccount;
		this.ToAccount = ToAccount;
		this.Amount = Amount;
		this.Type = Type;
		this.Date = Date;
	}

	public int getFromAcc() {
		return FromAccount;
	}

	public void setFromAcc(int fromAcc) {
		this.FromAccount = fromAcc;
	}

	public int getToAcc() {
		return ToAccount;
	}

	public void setToAcc(int toAcc) {
		ToAccount = toAcc;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}
	
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	@Override
	public String toString() {
		return "Transaction [ fromAcc=" +FromAccount +",ToAcc=" +ToAccount +",Amount=" + Amount + ",Type" + Type + ",Date="+ Date +"]";
	}

}
