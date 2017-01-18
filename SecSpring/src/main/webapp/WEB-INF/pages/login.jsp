<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Login School</title>

    <!-- Bootstrap core CSS -->


    <style>
        <%@include file='/css/bootstrap.css' %>
        <%@include file='/css/assets/ie10-viewport-bug-workaround.css' %>
        <%@include file='/css/signin.css' %>
    </style>
    <!-- <link href="css/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!--<link href="css/assets/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <!--<link href="css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]<script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

     <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <c:url value="/j_spring_security_check" var="loginUrl" />
            <form class="form-signin" action="${loginUrl}" method="POST">
                <h2 class="form-signin-heading">Please sign in</h2>
                <label for="inputLogin" class="sr-only">Email address</label>
                <input type="login" id="inputLogin" class="form-control" placeholder="Login" name="j_login" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="j_password" required>
                <!--<div class="checkbox">
                  <label>
                    <input type="checkbox" value="remember-me"> Remember me
                  </label>
                </div>-->
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                <c:if test="${param.error ne null}">
                    <p>Wrong login or password!</p>
                </c:if>
            </form>

        </div>
        <div class="col-md-4"></div>
    </div>


</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!--<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

