package com.system.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.entity.User;
import com.system.repository.Accountlist;

@WebServlet(urlPatterns = {"/account-list"})
public class Accountlistservlet extends HttpServlet {
	
	Accountlist accountList = new Accountlist();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<User> list = accountList.getAccountList();
		request.setAttribute("account-list",list);
		request.getRequestDispatcher("accountlist.jsp").forward(request, response);

	}
	
}
