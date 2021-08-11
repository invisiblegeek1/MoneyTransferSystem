package com.system.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.system.entity.Transaction;
import com.system.repository.TransactionHistoryRepository;


@WebServlet(urlPatterns = {"/transaction-history"})
public class TransactionHistoryservlet extends HttpServlet{
	
	TransactionHistoryRepository txrhistoryRepository = new TransactionHistoryRepository();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int AccNumber = Integer.parseInt(request.getParameter("AccNumber"));
		String typeOfHistory = request.getParameter("choose");
		System.out.println("type = "+typeOfHistory);
		if(typeOfHistory.equals("Top-10")) {
			List<Transaction> history = txrhistoryRepository.getTop10History(AccNumber);
			request.setAttribute("history-list", history);
			
			request.getRequestDispatcher("transactionHistory.jsp").forward(request, response);
		}

	}

}
