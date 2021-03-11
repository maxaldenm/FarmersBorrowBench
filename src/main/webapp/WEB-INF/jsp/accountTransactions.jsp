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
        <li><a href="${pageContext.request.contextPath}/goToHomeLoggedIn">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/goToAccountOverview">Account</a></li>
        <li><a href="${pageContext.request.contextPath}/goToContactLoggedIn">Contact</a></li>
        <li><a href="${pageContext.request.contextPath}/goToLogout">Logout</a></li>
        <li><a href="${pageContext.request.contextPath}/about">About</a></li>
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

        <div class="thisAccount">
            <div class="thisAccountInfoBar">
                <h5 class="userAccountOneIban">IBAN:</h5>
                <h4 class="userAccountOneIbanInfo">${account.accountNumber}</h4>
                <h5 class="userAccountOnebalance">balance: </h5>
                <h4 class="userAccountOnebalanceInfo">${account.balance}</h4>
            </div><!-- accountOneInfoBar -->
            <div class="thisTransactionInfoBar">
                <h5 class="creditAccount">credit account</h5>
                <h5 class="transferDescription">transfer Description</h5>
                <h5 class="transferAmount">Amount</h5>
            </div><!-- thisTransactionInfoBar -->
            <div class="thisAccountTransactionView">
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionEven">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
                <div class="transactionOdd">
                    <div class="creditAccount">
                        IBAN Credit account
                    </div>
                    <div class="transferDescription">
                        Transfer Description
                    </div>
                    <div class="transferAmount">
                        Amount
                    </div>
                </div><!-- transaction -->
            </div><!-- accountOneTransactionView -->
            <div class="selectionBar">
                <a href="/goToMoneyTransaction?accountNumber=${account.accountNumber}" class="myButton">Transaction</a>
            </div>
        </div>
        <!-- account -->

    </div><!-- fadeInOne -->

</main>

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>

