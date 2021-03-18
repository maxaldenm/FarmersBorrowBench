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
    <c:if test="${!isLoggedIn}">
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/goToRegisterNewUser">Register</a></li>
            <li><a href="${pageContext.request.contextPath}/goToContact">Contact</a></li>
            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
            <li><a href="${pageContext.request.contextPath}/goToLogin">Login</a></li>
        </ul>
    </c:if>
    <c:if test="${isLoggedIn}">
        <ul class="nav-links">
            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/goBackToAccountOverview">Account</a></li>
            <li><a href="${pageContext.request.contextPath}/goToContact">Contact</a></li>
            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
            <li><a href="${pageContext.request.contextPath}/goToLogout">Logout</a></li>
        </ul>
    </c:if>

    <div class="burger">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
    </div>
</nav>

<main>
    <div class="container">
        <div class="fadeInOne">
            <div class="contentHomeOne">
                <div class="intro">
                    <h1>We are the Farmer's Borrow Bench</h1><br>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis corporis debitis dolor
                        doloremque ducimus eligendi eum eveniet illo illum incidunt iste, magnam molestiae nostrum
                        numquam
                        perspiciatis placeat quae quibusdam, quisquam quod quos rem sequi, temporibus tenetur unde vitae
                        voluptate voluptatibus. Blanditiis deleniti doloremque ea earum ex excepturi itaque, nesciunt
                        odit
                        optio porro quidem quisquam reprehenderit sit tempora ullam unde ut.</p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet aperiam asperiores aspernatur
                        beatae
                        dicta distinctio dolorem et facilis, laborum modi mollitia natus necessitatibus, non officiis
                        rem!
                        Inventore omnis repudiandae voluptate.</p>
                </div>
                <div class="orangediv">
                    <h5><em>Your mum:</em></h5>
                    <h3>"Het is een hele fijne bank hoor!"</h3>
                </div>
                <div class="blog">
                    <h1>Follow our <br>BLOG</h1>
                </div>
                <div class="testimonials">

                </div>
                <div class="blackdiv"></div>

            </div><!-- content -->
        </div><!-- fadeInOne -->
        <div class="fadeInTwo">
            <div class="contentHomeTwo">
                <h1>Onze service</h1>
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

            <div class="contentHomeThree">
                <h1>Geschiedenis</h1>
                <p>ipsum dolor sit amet, consectetur adipisicing elit. Asperiores culpa deserunt dicta
                    eaque eius, fuga hic molestiae molestias, nesciunt odit officia quaerat qui quis
                    sapiente soluta sunt suscipit tempora velit?</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                    Enim et facilis molestiae quidem similique tempore.</p>
            </div><!-- contentTwo -->
        </div><!-- fadeInThree -->
    </div>
</main>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>