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

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/assets/ie10-viewport-bug-workaround.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/jumbotron.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>" type="text/css"/>
    <style>
        table th p {
            text-align: center;
            white-space: nowrap;
            -webkit-transform: rotate(-90deg);
            -moz-transform: rotate(-90deg);
            -ms-transform: rotate(-90deg);
            -o-transform: rotate(-90deg);
            transform: rotate(-90deg);
            margin: 0 0;

        }

        table th p:before {
            content: '';
            width: 0;
            padding-top: 110%;
            display: inline-block;
            vertical-align: middle;
        }

    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>">School</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <!-- <li class="active"><a href="#">Ссылка</a></li>-->
                <li><a href="<c:url value="/homework"/>">Домашние задания</a></li>
                <li><a href="<c:url value="/schedule"/>">Расписание уроков</a></li>
                <li class="active"><a href="<c:url value="/journal"/>">Журнал</a></li>
                <li ${noAdminHide}><a href="<c:url value="/admin/"/>">Учительская</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${login} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/profile"/>">Профиль</a></li>
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

        <form name="journal_search" action="journal" method="POST">
            <div class="col-md-2">
                <div class="input-group input-group-sm">
                    <span class="input-group-addon">Предмет</span>
                    <select class="form-control" name="j_subject">
                        <option value="${curSubject.id}"><c:out value="${curSubject.name}"/></option>
                        <c:forEach items="${subjects}" var="sub">
                            <c:if test="${curSubject.id != sub.id}">
                                <option value="${sub.id}"><c:out value="${sub.name}"/></option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3">
                <div class="input-group input-group-sm">
                    <span class="input-group-addon">С </span>
                    <input type="date" class="form-control" name="j_sdt" value="${start_date}">
                </div>
            </div>
            <div class="col-md-3">
                <div class="input-group input-group-sm">
                    <span class="input-group-addon">По </span>
                    <input type="date" class="form-control" name="j_edt" value="${end_date}">
                </div>
            </div>
            <div class="col-md-1">
                <div class="btn-group btn-group-sm">
                <button type="submit" class="btn btn-default">Поиск</button>
                </div>
            </div>
        </form>
    </div>
</div>


<div class="homeworktable">
    <div class="container">
        <div class="table-responsive">
            <table class="table table-bordered table-hover table-condensed">
                <thead>
                <tr>
                    <th>Предмет</th>
                    <c:forEach items="${headerCalendar}" var="headcal">
                        <th><p><c:out value="${headcal}"/></p></th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody>
                ${jurtab}
                </tbody>
            </table>
        </div>
    </div>
</div>


<div class="footer">
    <div class="container">
        <p class="text-muted">Create by H@sper</p>
    </div>
</div>
<!-- Bootstrap core JavaScript
   ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="/js/jquery-3.1.1.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</body>

</html>