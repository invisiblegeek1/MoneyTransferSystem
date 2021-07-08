package com.revature.app;

import java.util.Scanner;

import com.revature.repository.JdbcMainRepository;

public class MtsApp {
	
	static JdbcMainRepository todorepository = new JdbcMainRepository();
	
	//Main function for the process.
	public static void main(String []args) {		
		//Details
		System.out.println("Welcome to Banking System...\n"
				+ "Select the process...\n"
				+ "1.Deposit\n"
				+ "2.Money Transfer\n"
				+ "3.TopTen Transaction.\n"
				+ "4.Monthly Transacrion.\n");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int method = sc.nextInt();
		mainmethod(method);			
		}	
	//Method to do the process.
	static void mainmethod(int method){
		switch (method) {
		case 1:
	//			todorepository.deposit();
			break;
		case 2:
			todorepository.Transaction();
			break;
		case 3:
			todorepository.TopTen();
			break;
		case 4:
			todorepository.MonthlyTransaction();
			break;
		default:
			System.out.println("Enter the Correct method.");
			Scanner sc =new Scanner(System.in);
			method = sc.nextInt();
			mainmethod(method);
			break;
		}
	}
	
}
