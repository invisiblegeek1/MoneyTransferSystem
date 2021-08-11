<!DOCTYPE html>
<%@ page import="java.util.*,com.system.entity.Transaction" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TransactionHistory</title>
        <link rel="stylesheet" href="indiex.css">

    </head>

    <body>
        <div class="header">Money Transfering Services</div>
        <nav class="Topnav" id="myTopnav">
            <a class="active" href="index.html">Home</a>
            <a class="active" href="account.html">Services</a>
            <a class="active" href="transfer.html">Transfer</a>
            <a class="active" href="transaction.html">Transactions</a>
            <button class="button" onclick="window.location.href='login.html'">LOGIN</button>
            <button class="button" onclick="window.location.href='login.html'">SIGN UP</button>
        </nav>
        <% List<Transaction> listOfHistory = (List<Transaction>)request.getAttribute("history-list");
            %>
            <h3 style="text-align:center">Transaction List of a User Displayed Here.</h3>

            <table class="tables">
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Amount</th>
                        <th>AccNumber</th>
                        <th>Type</th>
                        <th>Date</tr>
                        <!-- <th>balance</th> -->
                    </tr>
                </thead>
                <tbody>
                    <% for(Transaction transaction : listOfHistory){ %>
                        <tr>
                            <td><%=transaction.getUser()%></td>
                            <td><%=transaction.getAmount()%></td>
                            <td><%=transaction.getAccnumber()%></td>
                            <td><%=transaction.getType()%></td>
                            <td><%=transaction.getDate()%></td>
                        </tr>
                    <% } %>
                </tbody>

            </table>


            <style>
                .tables {
                    font-family: Arial, Helvetica, sans-serif;
                    border-collapse: collapse;
                    margin-top: 2%;
                    margin-left: 20%;
                    width: 50%;
                    border: none;
                    border-radius: 12%;
                    
                  }
                  
                 .tables td, .tables   th {
                     justify-content: center;
                     text-align: center;
                    border: 1px solid #ddd;
                    padding: 15px;
                    
                  }
                
                  
                  .tables th {
                    padding-top: 12px;
                    padding-bottom: 12px;
                    padding: 20px;
                    text-align: center;
                    background-color: #44aedf;
                    color: white;
                    
                  }
                  </style>
    </body>

    </html>