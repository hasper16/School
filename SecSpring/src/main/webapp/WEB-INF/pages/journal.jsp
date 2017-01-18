<%--
  Created by IntelliJ IDEA.
  User: Pavel.Eremenko
  Date: 01.09.2016
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shool</title>

    <link rel="stylesheet" href="/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="/css/assets/ie10-viewport-bug-workaround.css" type="text/css" />
    <link rel="stylesheet" href="/css/jumbotron.css" type="text/css" />
    <link rel="stylesheet" href="/css/footer.css" type="text/css" />

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">School</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <!-- <li class="active"><a href="#">Ссылка</a></li>-->
                <li><a href="/homework">Домашние задания</a></li>
                <li><a href="/schedule">Расписание уроков</a></li>
                <li class="active"><a href="/journal">Журнал</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${login} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="/profile">Профиль</a></li>
                    </ul>
                </li>
            </ul>
            <c:url value="/logout" var="logoutUrl"/>
            <form class="navbar-form navbar-right" action="${logoutUrl}">
                <button type="submit" class="btn btn-primary">Выйти</button>
            </form>
        </div>

    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <h1>Журнал</h1>
        <div class="row">
            <form name="journal_search" action="journal_filter" method="POST">
                <div class="col-md-2">
                    <select class="form-control" name="j_subject">
                        <c:forEach items="${subject}" var="sub">
                            <option><c:out value="${sub}"/></option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-3">
                    <div class="input-group">
                        <span class="input-group-addon">From </span>
                        <input type="date" class="form-control" name="j_sdt" value="${start_date}">
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="input-group">
                        <span class="input-group-addon">To </span>
                        <input type="date" class="form-control"  name="j_edt" value="${end_date}">
                    </div>
                </div>
                <div class="col-md-1">
                    <button type="submit" class="btn btn-default">Search</button>
                </div>
            </form>
        </div>
    </div>
</div> <!-- /container -->

<div class="homeworktable">
    <div class="container">
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead class="ct">
                <tr>
                    <td><h4>Предмет</h4></td>
                    <c:forEach items="${headerCalendar}" var="headcal">
                    <td><h4><c:out value="${headcal}"/></h4></td>
                    </c:forEach>
                </tr>
                </thead>
                ${jurtab}
            </table>

        </div>
        </div>
    </div>



<div id="footer">
    <div class="container">
        <p class="text-muted">Create by H@sper</p>
    </div>
</div>
<!-- Bootstrap core JavaScript
   ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
    window.jQuery || document.write(' < script src = "../../assets/js/vendor/jquery.min.js" > < \/script>')
</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</body>

</html>