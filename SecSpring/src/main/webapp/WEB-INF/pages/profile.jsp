<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shool</title>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/assets/ie10-viewport-bug-workaround.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/jumbotron.css"/>" type="text/css"/>
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>" type="text/css"/>
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
                <li><a href="<c:url value="/journal"/>">Журнал</a></li>
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
<div class="container" >
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <%-- Notifications--%>
        <c:if test="${alert == 1}">
            <div class="alert alert-success alert-dismissable" style="margin-top: 20px;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}</div>
        </c:if>
        <c:if test="${alert == 2}">
            <div class="alert alert-warning alert-dismissable" style="margin-top: 20px;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}</div>
        </c:if>
        <c:if test="${alert == 3}">
            <div class="alert alert-danger alert-dismissable" style="margin-top: 20px;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}</div>
        </c:if>
        <c:if test="${alert == 4}">
            <div class="alert alert-info alert-dismissable" style="margin-top: 20px;">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    ${message}</div>
        </c:if>
        <%-- Notifications--%>
    </div>
    <div class="col-md-1"></div>
</div>
<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">

    <div class="container">

        <h1>Профиль</h1>
        <div class="container-fluid">
            <div class="row">
                <form class="form-horizontal form-validation" role="form" enctype="multipart/form-data"
                      action="/profile" method="POST">

                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="inputLogin">Login</label>
                            <input id="inputLogin" type="text" class="form-control" name="j_login" value="${login}">
                        </div>
                        <div class="form-group novalid">
                            <label for="inputPassword">New Password</label>
                            <input id="inputPassword" type="password" class="form-control" name="j_password"
                                   value="${password}">
                        </div>
                        <div class="form-group">
                            <label for="inputName">Имя</label>
                            <input id="inputName" type="text" class="form-control" name="j_name"
                                   value="${student.name}">
                        </div>
                        <div class="form-group">
                            <label for="inputSurname">Фамилия</label>
                            <input id="inputSurname" type="text" class="form-control" name="j_surname"
                                   value="${student.surname}">
                        </div>
                        <div class="form-group">
                            <label for="inputBirthday">Дата рождения</label>
                            <input id="inputBirthday" type="date" class="form-control" name="j_birthday"
                                   value="${student.birthdayHtml}">
                        </div>
                        <div class="form-group">
                            <label for="inputEmail">Email</label>
                            <input id="inputEmail" type="email" class="form-control" name="j_email"
                                   value="${student.email}">
                        </div>
                        <div class="form-group">
                            <label for="inputPhone">Телефон</label>
                            <input id="inputPhone" type="tel" class="form-control" name="j_phone"
                                   value="${student.phone}">
                        </div>

                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-6">
                        <div class="form-group novalid">
                            <img src="/image/${student.photo.id}"
                                style="max-height: 50%;" class="img-thumbnail img-responsive">
                            <input type="file" class="btn btn-link" name="j_photo">
                        </div>
                    </div>
                    <div class="clear"></div>
                    <button id="save" type="submit" class="btn btn-success" ${buttonStatus}>Применить</button>
                </form>
            </div>
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
<script src="/js/validator/common.js"></script>
</body>

</html>