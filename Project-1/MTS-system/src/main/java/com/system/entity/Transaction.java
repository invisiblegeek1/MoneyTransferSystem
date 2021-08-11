package com.system.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="transferinfo",schema = "project1")

public class Transaction {
	
	@Id
	@Column(name="user")
	private String user;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="AccNumber")
	private int accnumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type")
	private Type type;
	
//	@Temporal(TemporalType.DATE)
	@Column(name="date")
	private String date;
	
	
	
	public Transaction(String user,double amount,int accnumber,Type type,String date) {
		super();
		this.user = user;
		this.amount = amount;
		this.type = type;
		this.date = date;
		this.accnumber = accnumber;
	}
	



	public Transaction() {
		super();
	}




	public int getAccnumber() {
		return accnumber;
	}



	public void setAccnumber(int accnumber) {
		this.accnumber = accnumber;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public Type getType() {
		return type;
	}



	public void setType(Type type) {
		this.type = type;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Transaction [user=" + user + ", amount=" + amount + ", accnumber=" + accnumber + ", type=" + type
				+ ", date=" + date + "]";
	}



	

	

	

}
