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
        <li><a href="${pageContext.request.contextPath}/goToContactLoggedIn">Contact</a></li>
        <li><a href="${pageContext.request.contextPath}/goToLogout">Logout</a></li>
    </ul>
    <div class="burger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
</nav>

<main>
    <h2>Account Overview</h2>
    <em>Welcome ${sessionScope.user.fname} ${sessionScope.user.getsuffix()} ${sessionScope.user.lname}</em>

    <div class="accountOverviewButtons">
        <div class="userInformation opmaakAccountOverview">
            <h4>Edit User Information</h4>
        </div>
        <a class="addNewAccount opmaakAccountOverview" href="${pageContext.request.contextPath}/goToRegisterNewAccount">
            <h4>Add new account</h4></a>
        <div class="editAccountInformation opmaakAccountOverview">
            <h4>Edit Account Information</h4>
        </div>
        <div class="linkNewUserToAccount opmaakAccountOverview">
            <h4>Link New User To Account</h4>
        </div>
    </div><!-- accountOverviewButtons -->

    <div class="form-container">
        <form action="/searchAccountOverview" method="post">
            <label><h3>Search in accounts:</h3></label>
            <input path="search" name="search" value="${search}"/><br>
            <div class="buttons">
                <input class="myButton" type="submit" value="Search">
            </div>
        </form>
        <form action="/accountOverview" method="post">
            <div class="buttons">
                <input class="myButton" type="submit" value="Cancel">
            </div>
        </form>
    </div>
    <br><br><br>
    <c:if test="${!empty(accountList)}">
        <div class="fadeInOne">
            <div class="account">
                <c:forEach var="account" items="${accountList}">
                        <div class="accountOneInfoBar">
                            <h5 class="userAccountOneIban">IBAN:</h5>
                            <h4 class="userAccountOneIbanInfo">${account.accountNumber}</h4>
                            <h5 class="userAccountOnebalance">balance: </h5>
                            <h4 class="userAccountOnebalanceInfo">${account.balance}</h4>
                            <div class="selectionBar selectionBarButton">
                                <a class="myButton"
                                   href="${pageContext.request.contextPath}/goToAccountTransactions?accountNumber=${account.accountNumber}">Select</a>
                            </div>
                        </div><!-- accountOneInfoBar -->
                </c:forEach>
            </div><!-- account -->
        </div>
        <!-- fadeInOne -->
    </c:if>
</main>

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>

