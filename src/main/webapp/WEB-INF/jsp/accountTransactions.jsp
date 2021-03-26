<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Slab:wght@300&display=swap" rel="stylesheet">
    <title>accountOverview</title>
</head>
<body>
<nav>
    <div class="logo">
        <a href="home">
            <img class="logoImg" src="${pageContext.request.contextPath}/images/FBBLogo.jpg" alt="LOGO">
        </a>
        <h4 class="logoText">Farmer's Borrow Bench</h4>
    </div>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/goBackToAccountOverview">Account</a></li>
        <li><a href="${pageContext.request.contextPath}/goToContact">Contact</a></li>
        <li><a href="${pageContext.request.contextPath}/about">About</a></li>
        <li><a href="${pageContext.request.contextPath}/goToLogout">Logout</a></li>
    </ul>
    <div class="burger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
</nav>

<main>
    <h2>Account Transactions</h2>

    <div class="fadeInOne">

        <div class="form-container">
            <form action="/searchTransactionOverview" method="post">
                <label><h3>Search in accounts:</h3></label>
                <input path="search" name="search" value="${search}"/><br>
                <div class="buttons">
                    <input class="myButton" type="submit" value="Search">
                </div>
            </form>
            <form action="/accountTransactions" method="post">
                <div class="buttons">
                    <input class="myButton" type="submit" value="Cancel">
                </div>
            </form>
        </div>
        <br><br><br>

        <div class="thisAccount">
            <div class="thisAccountInfoBar">
                <h5 class="userAccountOneIban">IBAN:</h5>
                <h4 class="userAccountOneIbanInfo">${account.accountNumber}</h4>
                <h5 class="userAccountOnebalance">balance: </h5>
                <h4 class="userAccountOnebalanceInfo">${account.balance}</h4>
            </div>
            <!-- accountOneInfoBar -->
            <div class="thisTransactionInfoBar">
                <a href="${pageContext.request.contextPath}/sortAccountTransactions?sort=date" class="transferDate columnSort">transfer date</a>
                <a href="${pageContext.request.contextPath}/sortAccountTransactions?sort=account" class="creditAccount columnSort">transfer account</a>
                <a href="${pageContext.request.contextPath}/sortAccountTransactions?sort=description" class="transferDescription columnSort">transfer Description</a>
                <a href="${pageContext.request.contextPath}/sortAccountTransactions?sort=amount" class="transferAmount columnSort">Amount</a>
            </div>
            <!-- thisTransactionInfoBar -->
            <div class="thisAccountTransactionView">
                <c:if test="${!empty(moneyTransactions)}">
                <c:forEach var="transaction" items="${moneyTransactions}">

                    <div class="transactionGrid">
                        <div class="transferDate">
                            ${transaction.localDateTimeToDateWithSlash()}
                        </div>
                        <div class="creditAccount">
                            ${transaction.otherAccount}
                        </div>
                        <div class="transferDescription">
                                ${transaction.description}
                        </div>
                        <c:choose>
                            <c:when test="${transaction.currentAccount==account.accountNumber}">
                                <div class="plusMinus">
                                    -
                                </div>
                                <div class="transferAmountTrue">
                                        ${transaction.amountWithoutDecimalToString()} ,
                                    <sup>${transaction.amountOnlyDecimalPlacesToString()}</sup>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="plusMinus">
                                    +
                                </div>
                                <div class="transferAmountFalse">
                                        ${transaction.amountWithoutDecimalToString()} ,
                                    <sup>${transaction.amountOnlyDecimalPlacesToString()}</sup>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                </c:forEach>
            </div>
            </c:if>

        </div>
        <div class="selectionBar">
            <a href="${pageContext.request.contextPath}/goBackToAccountOverview" class="myButton">Account Overview</a>
            <a href="${pageContext.request.contextPath}/goToMoneyTransaction?accountNumber=${account.accountNumber}"
               class="myButton">Transaction</a>
        </div>
    </div>


</main>

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>

