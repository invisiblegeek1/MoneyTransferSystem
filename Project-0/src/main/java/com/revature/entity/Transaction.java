package com.revature.entity;

public class Transaction {
	private int fromAcc;
	private int toAcc;
	private int Amount;
	private String Type;
	private String Date;
	
	public Transaction(int FromAccount,int ToAccount, int Amount, String Type,String Date) {
		super();
		this.fromAcc = FromAccount;
		this.toAcc = ToAccount;
		this.Amount = Amount;
		this.Type = Type;
		this.Date = Date;
	}

	public int getFromAcc() {
		return fromAcc;
	}

	public void setFromAcc(int fromAcc) {
		this.fromAcc = fromAcc;
	}

	public int getToAcc() {
		return toAcc;
	}

	public void setToAcc(int toAcc) {
		this.toAcc = toAcc;
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
		return "Transaction [ fromAcc=" +fromAcc +",ToAcc=" +toAcc +",Amount=" + Amount + ",Type" + Type + ",Date="+ Date +"]";
	}

	public String getAccHolderName() {
		// TODO Auto-generated method stub
		return null;
	}

}
