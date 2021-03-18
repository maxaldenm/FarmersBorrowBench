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
        <li><a href="${pageContext.request.contextPath}/goBackToAccountOverview">Account</a></li>
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
    <h2>Transaction</h2>
    <div class="fadeInOne">
        <div class="form-container">
            <h3>transaction for ${accountNumber}</h3>
            <%--@elvariable id="user" type="com.workshopspringframework.beans.User"--%>
            <form:form action="/moneyTransaction" method="post" modelAttribute="moneytransactionform">

                <label for="amount"><h3>Amount:</h3></label>
                <form:input path="amount" type="text"/><form:errors path="amount" cssClass="error"/><br>

                <label for="description"><h3>Description:</h3></label>
                <form:input path="description"/><form:errors path="description" cssClass="error"/><br>

                <label for="debitIban"><h3>IBAN receiver:</h3></label>
                <form:input path="debitIban" type="text"/><form:errors path="debitIban" cssClass="error"/>
                <form:checkbox path="checkIban" class="checkerCheckbox"></form:checkbox><br>

                <label for="lastName"><h3>Lastname receiver:</h3></label>
                <form:input path="lastName"/><form:errors path="lastName" cssClass="error"/>
                <form:checkbox path="checkLname" class="checkerCheckbox"></form:checkbox><br>


                <form:hidden path="creditIban" value="${accountNumber}"/>

                <div class="buttons">
                    <input class="myButton" type="submit" name="submit" value="Submit"  >
                    <input class="myButton" type="submit" name="checker" value="Checker" >
                </div>

            </form:form>
        </div><!-- form-container -->
    </div><!-- fadeInOne -->
</main>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>

