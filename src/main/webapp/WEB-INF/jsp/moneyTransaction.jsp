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
    <title>Register New User</title>
</head>
<body>
<nav>
    <div class="logo">
        <h4>Farmer's Borrow Bench</h4>
    </div>
    <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/goToAccountOverview">Account</a></li>
        <li><a href="${pageContext.request.contextPath}/goToContact">Contact</a></li>
        <li><a href="${pageContext.request.contextPath}/goToLogout">Logout</a></li>
        <li><a href="${pageContext.request.contextPath}/about">About</a></li>
    </ul>
    <div class="burger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
</nav>

<div class="fadeInOne">
    <div class="form-container">
        <%--@elvariable id="user" type="com.workshopspringframework.beans.User"--%>
        <form:form action="/moneyTransaction" method="post" modelAttribute="moneytransactionform">

            <label for="amount"><h3>Amount:</h3></label>
            <form:input path="amount" type="text"/><form:errors path="amount" cssClass="error"/><br>

            <label for="description"><h3>Description:</h3></label>
            <form:input path="description"/><form:errors path="description" cssClass="error"/><br>

            <label for="debitIban"><h3>IBAN receiver:</h3></label>
            <form:input path="debitIban" type="text"/><form:errors path="debitIban" cssClass="error"/><br>
            <form:hidden path="creditIban" value="${accountNumber}"/>

            <div class="buttons">
                <input class="myButton" type="submit" value="Submit">
            </div>

        </form:form>
    </div><!-- form-container -->
</div><!-- fadeInOne -->

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>