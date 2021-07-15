package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.connection.ConnectionFactory;
import com.revature.entity.User;

public class Deposit {
	private static Logger logger = Logger.getLogger("Transfering-system");
	public static void main(String []args) {
		Connection con =null;
		logger.info("txr intiated...");
		Scanner sc=new Scanner(System.in);
		int AccNumber = sc.nextInt();
		double Amount = sc.nextDouble();
		String AccHolderName = sc.next();
		User user = new User( AccHolderName,AccNumber, Amount);
		try {
			con = ConnectionFactory.getConnection();
//			double Balance = AccNumber.getBalance(); 
			
			String query1 ="select * from userinfo where AccNumber = '"+AccNumber+"'";
			PreparedStatement p1 = con.prepareStatement(query1);
			ResultSet r1 = p1.executeQuery(query1);
			r1.next();
			double Balance = r1.getDouble(3);
			System.out.println(Balance);
			
			ArrayList<Integer> AccNumberList = new ArrayList<Integer>();
			String query ="select * from userinfo";
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery(query);
			while(r.next()) {
				AccNumberList.add(r.getInt(2));
			}
			System.out.println(AccNumberList);
			if(AccNumberList.contains(AccNumber)) {
				String sql = "update userinfo set balance = ? where AccNumber = ?;";
				PreparedStatement ps = con.prepareStatement(sql);
//				ps.setString(1, AccHolderName);
//				AccNumber.setBalance(User.getBalance() + Amount);
				Balance = Amount + Balance;
				ps.setInt(2, AccNumber);
				ps.setDouble(1, Balance);
				int rowCount = ps.executeUpdate();
				if (rowCount == 1) {
					System.out.println("Money Deposited..");
				}	
				System.out.println("True");
			}
			else 
			{
				String sql = "insert into userinfo (AccHolderName,AccNumber,Balance) values (?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
//				ResultSet r = p.executeQuery(query);
				ps.setString(1, AccHolderName);
				ps.setInt(2, AccNumber);
				ps.setDouble(3, Amount);
				int rowCount = ps.executeUpdate();
				
				if (rowCount == 1) {
					System.out.println("Money Deposited..");
				}	
				System.out.println("False");
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
