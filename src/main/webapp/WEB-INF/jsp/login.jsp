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
        <li><a href="home">Home</a></li>
        <li><a href="registerNewUser">Register</a></li>
        <li><a href="accountOverview">Account</a></li>
        <li><a href="login">Login</a></li>
    </ul>
    <div class="burger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
</nav>
<main>
    <div class="fadeInOne">
        <div class="form-container">
            <h3>Login:</h3>
            <%--@elvariable id="user" type="com.workshopspringframework.beans.User"--%>
            <form:form action="/login" method="post" modelAttribute="login">

                <label for="username"><h3>Username:</h3></label>
                <form:input path="username" type="text"/><form:errors path="username" cssClass="error"/><br>

                <label for="password"><h3>Password:</h3></label>
                <form:password path="password"/><form:errors path="password" cssClass="error"/><br>

                <div class="buttons">
                    <input class="myButton" type="submit" value="Submit">
                </div>

            </form:form>
            <label>${loginError}</label>
        </div><!-- form container -->
    </div><!-- fadeInOne -->
</main>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>