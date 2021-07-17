package com.revature.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.security.auth.login.AccountException;

import org.apache.log4j.Logger;

import com.revature.Exception.AccountBalanceException;
//import com.revature.Exception.AccountBalanceException;
import com.revature.Exception.AccountNotFoundExeption;
import com.revature.Exception.DateNotFoundExeption;
import com.revature.Exception.InvalidEnteriesException;
import com.revature.connection.ConnectionFactory;
import com.revature.entity.Type;
import com.revature.entity.User;

public class JdbcMainRepository implements MainRepository{
	
	private static Logger logger = Logger.getLogger("From Transfering-system\n");
	public void deposit()  {
		Connection con =null;
		
		//Declaring Variables
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the AccounHolderName:");
		String AccHolderName = sc.next();
		System.out.println("Enter the AccountNumber:");
		int AccNumber = sc.nextInt();
		System.out.println("Enter the Amount to Deposit:");
		double Amount = sc.nextDouble();
		if(AccHolderName == null | AccNumber <=0 |Amount <= 0) {
			logger.error("Invalid Enteries.");
		}
		else {
			try {
				con = ConnectionFactory.getConnection();
				logger.info("Deposit System intiated...");
				ArrayList<Integer> AccNumberList = new ArrayList<Integer>();
				String query ="select * from userinfo";
				PreparedStatement p = con.prepareStatement(query);
				ResultSet r = p.executeQuery(query);
				while(r.next()) {
					AccNumberList.add(r.getInt(2));
				}
				System.out.println(AccNumberList);
				
				//Checking the Existing User:
				if(Amount > 0) {
					
					if(AccNumberList.contains(AccNumber)) {
						//Get the current Balance
						String query1 ="select * from userinfo where AccNumber = '"+AccNumber+"'and AccHolderName = '"+AccHolderName+"'";
						PreparedStatement p1 = con.prepareStatement(query1);
						ResultSet r1 = p1.executeQuery(query1);
						r1.next();
						double Balance = r1.getDouble(3);
						System.out.println(Balance);
						//Update the balance 
						String sql = "update userinfo set balance = ? where AccNumber = ?;";
						PreparedStatement ps = con.prepareStatement(sql);
	//					ps.setString(1, AccHolderName);
	//					AccNumber.setBalance(User.getBalance() + Amount);
						Balance = Amount + Balance;
						ps.setInt(2, AccNumber);
						ps.setDouble(1, Balance);
						int rowCount = ps.executeUpdate();
						if (rowCount == 1) {
							System.out.println("Money Deposited Successfully..");
							UpdateTransact(AccHolderName, Amount, Type.DEBIT, con);
						}	
					}
					else 
					{
						String sql1 = "insert into userinfo(AccHolderName,AccNumber,Balance) values (?,?,?)";
						PreparedStatement ps = con.prepareStatement(sql1);
						ps.setString(1, AccHolderName);
						ps.setInt(2, AccNumber);
						ps.setDouble(3, Amount);
						int rowCount = ps.executeUpdate();
						if (rowCount == 1) {
							System.out.println("Account Created and Money Deposited Successfully..");
							UpdateTransact(AccHolderName, Amount, Type.CREDIT, con);
						}	
					}
					
				}
				else {
					logger.info("Invalide Entries.");
				}
					
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	public void withdraw() {
		Connection con =null;
		
		//Declaring Variables
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the AccounHolderName:");
		String AccHolderName = sc.next();
		System.out.println("Enter the AccountNumber:");
		int AccNumber = sc.nextInt();
		System.out.println("Enter the Amount to Withdraw:");
		double Amount = sc.nextDouble();
		if(AccHolderName == null | AccNumber <=0) {
			logger.error("Invalid Enteries.");
		}
		else {
			try {
				con = ConnectionFactory.getConnection();
				logger.info("Withdraw System intiated...");
				ArrayList<Integer> AccNumberList = new ArrayList<Integer>();
				String query ="select * from userinfo";
				PreparedStatement p = con.prepareStatement(query);
				ResultSet r = p.executeQuery(query);
				while(r.next()) {
					AccNumberList.add(r.getInt(2));
				}
	//			System.out.println(AccNumberList);
				
				//Checking the Existing User:
				if(Amount>=0) {
					
					if(AccNumberList.contains(AccNumber)) {
						//Get the current Balance
						String query1 ="select * from userinfo where AccNumber = '"+AccNumber+"'and AccHolderName = '"+AccHolderName+"' ";
						PreparedStatement p1 = con.prepareStatement(query1);
						ResultSet r1 = p1.executeQuery(query1);
						r1.next();
						double Balance = r1.getDouble(3);
//						System.out.println(Balance);
						//Update the balance 
						if(Amount<=Balance) {
							
							String sql = "update userinfo set balance = ? where AccNumber = ? and AccHolderName = '"+AccHolderName+"'";
							PreparedStatement ps = con.prepareStatement(sql);
							Balance =Balance - Amount;
							ps.setInt(2, AccNumber);
							ps.setDouble(1, Balance);
							int rowCount = ps.executeUpdate();
							if (rowCount == 1) {
								System.out.println("Money Withdrawed Successfully..");
								logger.info("Successfull");
								UpdateTransact(AccHolderName, Amount, Type.DEBIT, con);
							}	
							
						}
						else {
							logger.error("Insufficient Balance. ");
						}
						
					}
					else 
					{
						logger.error("Invalid Account");
					}
					
				}
				else {
					logger.info("Invalid Enteries.");
				}
					
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	public static void UpdateTransact(String AccHolderName,double Amount,Type type, Connection con) {
//		Date dateNow = new Date();
		LocalDate today = LocalDate.now();
		String date = today.toString();
//		SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyy");
//		String date = ft.format(dateNow);
		String Type = type.toString();
		Logger logger = Logger.getLogger("Updating-system initiated");
		try {
			String q2 = "insert into transferinfo(User,Amount,Type,Date) values (?,?,?,?)";
			PreparedStatement ps2 = con.prepareStatement(q2);
			ps2.setString(1,AccHolderName);
			ps2.setDouble(2,Amount);
			ps2.setString(3,Type);
			ps2.setString(4,date);
			
			int rowcount = ps2.executeUpdate();
			if(rowcount == 1) {
				System.out.println("Tranasaction of "+AccHolderName+"Amount = "+Amount+" Type =  "+type+"ED Updated.");
//				logger.info("Balance is"+AccN.getBalance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void Transaction() {
		Connection con =null;
		
		try {
			con = ConnectionFactory.getConnection();
			logger.info("Transfer initiated....");
			Scanner sc = new Scanner(System.in);
			//Enter the TransferDetails
			System.out.println("Enter the Details......");
			System.out.println("Enter the Your Account Number:");
			int fromAcc = sc.nextInt();
			System.out.println("Enter the ToAccount Number:");
			int toAcc = sc.nextInt();
			System.out.println("Enter the Amount to Transfer");
			double Amount = sc.nextDouble();
			
			if (fromAcc <= 0) {
				logger.error("account not found " + fromAcc);
				throw new AccountNotFoundExeption(fromAcc);
			}

			if (toAcc <= 0) {
				logger.error("account not found " + toAcc);
				throw new AccountNotFoundExeption(toAcc);
			}
			
			if(Amount <= 0) {
				logger.error("Invalid Enteries.");
				throw new InvalidEnteriesException(Amount);
			}
			
			
			//Fetch the from Account Balance
			String q = "select * from userinfo where AccNumber = "+fromAcc+" ";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(q);
			rs.next();
			User FromAccount = new User(rs.getString(1),rs.getInt(2),rs.getDouble(3));
//			System.out.println(FromAccount);
//			System.out.println("Balance of fromaccount"+FromAccount.getBalance());
			
			//Fetch the To Account Balance
			String q1 = "select * from userinfo where AccNumber = "+toAcc+" ";
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery(q1);
			rs1.next();
			User ToAccount = new User(rs1.getString(1),rs1.getInt(2),rs1.getDouble(3));
//			System.out.println(ToAccount);
//			System.out.println("Balance of ToAccount"+ToAccount.getBalance());
//			System.out.println(Amount);
			Transact(FromAccount,ToAccount,Amount,con);
			
			
			
		}catch(SQLException | AccountNotFoundExeption | InvalidEnteriesException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void Transact(User FromAccount, User ToAccount, double Amount, Connection con ) {
		try {
			
			if (FromAccount.getBalance() < Amount) {
				logger.error("Insufficient Balance " + FromAccount.getBalance());
				throw new AccountBalanceException(FromAccount.getBalance());
			}
			
			if(FromAccount.getBalance() >= Amount) {
				//Transacted the amount
				FromAccount.setBalance(FromAccount.getBalance() - Amount);
				ToAccount.setBalance(ToAccount.getBalance() + Amount);
				
				//Update the remaining balance amount to the database
				String q = "update userinfo set balance = ? where AccNumber = ?";
				PreparedStatement ps = con.prepareStatement(q);
				ps.setDouble(1, FromAccount.getBalance());
				ps.setInt(2,FromAccount.getAccNumber());
				ps.executeUpdate();
				
				String q1 = "update userinfo set balance = ? where AccNumber = ?";
				PreparedStatement ps1 = con.prepareStatement(q1);
				ps1.setDouble(1, ToAccount.getBalance());
				ps1.setInt(2,ToAccount.getAccNumber());
				ps1.executeUpdate();
				
//				System.out.println("Transfered Successfully");
				logger.info("Transferred Successfully");
				//update the transaction to the transaction table
				UpdateTransact(FromAccount,ToAccount,Amount,con);
				
			}
			
		} catch (SQLException | AccountBalanceException e) {
			e.printStackTrace();
		}
	}
	
	public static void UpdateTransact(User FromAccount,User ToAccount,double Amount,Connection con) {
//		Date dateNow = new Date();
		LocalDate today = LocalDate.now();
		String date = today.toString();
//		SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyy");
//		String date = ft.format(dateNow);
		Logger logger = Logger.getLogger("Updating-system initiated");
		try {
			String q2 = "insert into transferinfo(User,Amount,Type,Date) values (?,?,?,?)";
			PreparedStatement ps2 = con.prepareStatement(q2);
			ps2.setString(1,FromAccount.getAccHolderName());
			ps2.setDouble(2,Amount);
			ps2.setString(3,"Debited");
			ps2.setString(4,date);
			
			int rowcount = ps2.executeUpdate();
			if(rowcount == 1) {
				logger.info("Tranasaction of"+FromAccount.getAccHolderName()+"Account Number"+FromAccount.getAccNumber()+" Updated.");
				logger.info("Balance is"+FromAccount.getBalance());
			}
			String q3 = "insert into transferinfo(User,Amount,Type,Date) values (?,?,?,?)";
			PreparedStatement ps3 = con.prepareStatement(q3);
			ps3.setString(1,ToAccount.getAccHolderName());
			ps3.setDouble(2,Amount);
			ps3.setString(3,"Credited");
			ps3.setString(4,date);
			
			int rowcount1 = ps3.executeUpdate();
			if(rowcount1 == 1) {
				logger.info("Tranasaction of "+ToAccount.getAccHolderName()+"Account Number"+FromAccount.getAccNumber()+"Updated.");
				logger.info("Balance is"+ToAccount.getBalance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void TopTen() {
		Connection con = null;
		try {
			
			con =ConnectionFactory.getConnection();
			logger.info("Top Ten Transaction...");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the UserName:");
			String User = sc.next();
			String query = "SELECT  * FROM  transferinfo  where User ='"+User+"'limit 10";
			
			Statement stm = con.createStatement();
			ResultSet rst = stm.executeQuery(query);
			
			while(rst.next())
			{
				System.out.println("---------------------------------------");
				System.out.println("AccountHolderName:"+rst.getString(1)+"\nAmount : "+ rst.getString(2)+"\n Type: "+rst.getString(3));
				System.out.println("---------------------------------------");
			}
			logger.info("Successfully Executed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void MonthlyTransaction() {
		Connection con = null;
		try {
			con =ConnectionFactory.getConnection();
			logger.info("Monthly  Transaction...");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the From date YYYY-MM-DD:");
			String Date = sc.next();
			System.out.println("Enter the To date YYYY-MM-DD:");
			String Date1 = sc.next();
			if (Date == null | Date1==null) {
				logger.error("Invalid Date"); 
				throw new DateNotFoundExeption(Date);
			}
			String query = "SELECT  * FROM  transferinfo  where Date between '"+Date+"' and '"+Date1+"'";
			
			Statement stm = con.createStatement();
			ResultSet rst = stm.executeQuery(query);
			
			while(rst.next())
			{
				System.out.println("---------------------------------------");
				System.out.println(rst.getString(1)+" "+ rst.getString(2)+" "+rst.getString(3));
				System.out.println("---------------------------------------");
			}
			logger.info("Successfullly Executed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
		
}
	
	