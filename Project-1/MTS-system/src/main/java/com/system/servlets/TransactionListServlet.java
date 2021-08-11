package com.system.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.entity.Transaction;
import com.system.repository.TransactionList;
@WebServlet(urlPatterns = {"/transaction-list"})
public class TransactionListServlet extends HttpServlet{
	
	
	TransactionList transactionList = new TransactionList();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Transaction> list = transactionList.getAllTransactionHistory();
		request.setAttribute("transaction-list",list);
		request.getRequestDispatcher("transactionlist.jsp").forward(request, response);

		}
	}

