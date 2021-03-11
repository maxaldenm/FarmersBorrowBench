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
        <li><a href="${pageContext.request.contextPath}/goToRegisterNewUser">Register</a></li>
        <li><a href="${pageContext.request.contextPath}/goToContact">Contact</a></li>
        <li><a href="${pageContext.request.contextPath}/goToLogin">Login</a></li>
        <li><a href="${pageContext.request.contextPath}/about">About</a></li>
    </ul>
    <div class="burger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
</nav>

<main>
    <div class="fadeInOne">
        <h2>Welkom bij Farmer's Borrow Bench</h2>
        <div class="contentOne">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Adipisci at autem ea laboriosam laborum maxime minus pariatur
                quibusdam repellat, saepe tempora tempore totam ullam, voluptatibus.</p>
        </div><!-- content -->
    </div><!-- fadeInOne -->
    <div class="fadeInTwo">
        <h3>Onze service</h3>
        <div class="contentOne">
            <p>ipsum dolor sit amet, consectetur adipisicing elit. Asperiores culpa deserunt dicta
                eaque eius, fuga hic molestiae molestias, nesciunt odit officia quaerat qui quis
                sapiente soluta sunt suscipit tempora velit?</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium consequuntur,
                cupiditate deleniti dolor dolorum error iure, molestias natus, nostrum obcaecati pariatur
                placeat quibusdam repellat. Est laudantium, ut? Accusantium, delectus dolorem, dolores
                enim est exercitationem facilis fuga id, molestias nam quibusdam rerum sit ullam. Aliquid
                aut culpa cum dignissimos ea, fuga itaque magni minima molestiae neque numquam odio quasi
                quis voluptatibus.</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Enim et facilis molestiae quidem similique tempore.</p>
        </div><!-- contentTwo -->
    </div><!-- fadeInTwo -->
    <div class="fadeInThree">
        <h3>Geschiedenis</h3>
        <div class="contentOne">
            <p>ipsum dolor sit amet, consectetur adipisicing elit. Asperiores culpa deserunt dicta
                eaque eius, fuga hic molestiae molestias, nesciunt odit officia quaerat qui quis
                sapiente soluta sunt suscipit tempora velit?</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Enim et facilis molestiae quidem similique tempore.</p>
        </div><!-- contentTwo -->
    </div><!-- fadeInThree -->

</main>

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>