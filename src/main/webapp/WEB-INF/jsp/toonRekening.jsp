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
    <title>nieuweRekening</title>
</head>
<body>
<nav>
    <div class="logo">
        <h4>Farmer's Borrow Bench</h4>
    </div>
    <ul class="nav-links">
        <li><a href="home">Home</a></li>
        <li><a href="openRekening">Open Rekening</a></li>
        <li><a href="rekeningOverzicht">Rekeningoverzicht</a></li>
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

        <div class="rekeningGegevens">
            <h3>Nieuwe Rekening:</h3>
            <br>
            <h5 class="userfnaam">Voornaam:</h5>
            <H3 class="userfnaaminfo">${user.fname}</H3><br>
            <h5 class="usertussenvoegsel">Suffix:</h5>
            <H3 class="usertussenvoegselinfo">${user.tussenvoegsel}</H3><br>
            <h5 class="userachternaam">Achternaam:</h5>
            <H3 class="userachternaaminfo">${user.lname}</H3><br>
            <h5 class="bsntext">BSN:</h5>
            <H3 class="bsnuserinfo">${user.bsn}</H3><br>
            <h5 class="accountrekeningnummer">IBAN:</h5>
            <h3 class="accountrekeningnummerïnfo">${account.rekeningNummer}</h3><br>
        </div><!-- rekeningGegevens -->
    </div><!-- fadeInOne -->
</main>

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>