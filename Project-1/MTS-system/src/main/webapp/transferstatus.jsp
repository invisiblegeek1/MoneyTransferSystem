<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MTS-System</title>
    <link rel="stylesheet" href="indiex.css">
</head>

<body >
    <div class="header">Money Transfering Services</div>
    <nav class="Topnav" id="myTopnav">
        <a class="active" href="index.html">Home</a>
        <a class="active" href="account.html">Services</a>
        <a class="active" href="transfer.html">Transfer</a>
        <a class="active" href="transaction.html">Transactions</a>
        <button class="button" onclick="window.location.href='login.html'">LOGIN</button>
        <button class="button" onclick="window.location.href='login.html'">SIGN UP</button>
    </nav>

    <div class="home"><%=request.getAttribute("transfer-status")%></div>
</body>

</html>