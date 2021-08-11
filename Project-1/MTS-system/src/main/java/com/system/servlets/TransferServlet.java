package com.system.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.system.entity.Transaction;
import com.system.entity.Type;
import com.system.entity.User;
import com.system.repository.TransferRepository;


@WebServlet(urlPatterns = {"/transfer"})
public class TransferServlet extends HttpServlet{
	
	TransferRepository transferRepository = new TransferRepository();
	

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException{
		
		int fromAccNumber = Integer.parseInt(request.getParameter("fromAccNum"));
		int toAccNumber = Integer.parseInt(request.getParameter("toAccNum"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		User fromAccount = transferRepository.loadAccount(fromAccNumber);
		User toAccount = transferRepository.loadAccount(toAccNumber);
		
		fromAccount.setBalance(fromAccount.getBalance()-amount);
		toAccount.setBalance(toAccount.getBalance()+amount);
		
		
		transferRepository.updateAccount(fromAccount);
		transferRepository.updateAccount(toAccount);
	
		
		LocalDate today = LocalDate.now();
		String date = today.toString();
		
		Transaction debitHistory = new Transaction(fromAccount.getAccHolderName(),amount,fromAccount.getAccNumber(),Type.DEBIT, date);
		Transaction creditHistory = new Transaction(toAccount.getAccHolderName(),amount,toAccount.getAccNumber(), Type.CREDIT, date);
		
		transferRepository.createTransaction(debitHistory);
		transferRepository.createTransaction(creditHistory);
		System.out.println("Transaction Successfull.");
		
		request.setAttribute("transfer-status", "Transaction successfull");
		request.getRequestDispatcher("transferstatus.jsp").forward(request, response);
	}
	
}
