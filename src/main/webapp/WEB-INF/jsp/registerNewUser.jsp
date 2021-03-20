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
        <li><a href="${pageContext.request.contextPath}/goToContact">Contact</a></li>
        <li><a href="${pageContext.request.contextPath}/about">About</a></li>
        <li><a href="${pageContext.request.contextPath}/goToLogin">Login</a></li>
    </ul>
    <div class="burger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
</nav>
<main>
    <h2>Register New User</h2>
    <div class="fadeInOne">
        <div class="form-container">
            <%--@elvariable id="user" type="com.workshopspringframework.beans.User"--%>
            <form:form action="/registerNewUser" method="post" modelAttribute="user">

                <label for="username"><h3>Username:</h3></label>
                <form:input path="username" type="text"/><form:errors path="username" cssClass="error"/><br>

                <label for="password"><h3>Password:</h3></label>
                <form:password path="password"/><form:errors path="password" cssClass="error"/><br>

                <label for="bsn"><h3>BSN:</h3></label>
                <form:input path="bsn" type="text"/><form:errors path="bsn" cssClass="error"/><br>

                <label for="fname"><h3>First name:</h3></label>
                <form:input path="fname" type="text"/><form:errors path="fname" cssClass="error"/><br>

                <label for="suffix"><h3>suffix:</h3></label>
                <form:input path="suffix" type="text"/><br>

                <label for="lname"><h3>Last name:</h3></label>
                <form:input path="lname" type="text"/><form:errors path="lname" cssClass="error"/><br>

                <label for="postcode"><h3>Postcode: </h3></label>
                <form:input path="postcode" type="text"/><form:errors path="postcode" cssClass="error"/><br>

                <label for="city"><h3>City:</h3></label>
                <form:input path="city" type="text"/><form:errors path="city" cssClass="error"/><br>

                <label for="streetname"><h3>Streetname:</h3></label>
                <form:input path="streetname" type="text"/><form:errors path="streetname" cssClass="error"/><br>

                <label for="houseNr"><h3>HouseNumber:</h3></label>
                <form:input path="houseNr" type="text"/><form:errors path="houseNr" cssClass="error"/><br>

                <label><h3>MKB:</h3></label>

                <input type="checkbox" class="trigger" value="MKB"/>
                <div class="showMkb">
                    <label for="sector"><h3>Sector:</h3></label>
                    <form:select path="sector" items="${sectorItems}"/><br>
                </div>


                <div class="buttons">
                    <input class="myButton" type="submit" value="Submit">
                </div>

            </form:form>
        </div><!-- form-container -->
    </div><!-- fadeInOne -->
</main>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>