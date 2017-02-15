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
                <li class="active"><a href="<c:url value="/schedule"/>">Расписание уроков</a></li>
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

<div class="jumbotron">
    <div class="container">


                <div class="col-md-4">
                    <label for="Class">Класс</label>
                    <form id="selclass" action="<c:url value="/schedule"/>" method="POST">
                        <select class="form-control" id="Class" name="class" onchange="this.form.submit()">

                            <option value="${curStudentGroup.id}"><c:out value="${curStudentGroup.name}"/></option>
                            <c:forEach items="${groupsname}" var="cl">
                                <c:if test="${cl.id != curStudentGroup.id}">
                                    <option value="${cl.id}"><c:out value="${cl.name}"/></option>
                                </c:if>
                            </c:forEach>

                        </select>

                    </form>
                </div>
    </div>
</div>


<div class="homeworktable">
    <div class="container">
        <div class="row">
            <div class="col-md-6"><h3>Понедельник</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Время начала</th>
                        <th>Длительность</th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${monday}">
                        <tr>
                            <td><c:out value="${s.scheduleTimes.lessonNum}"/></td>
                            <td><c:out value="${s.scheduleTimes.sdt}"/></td>
                            <td><c:out value="${s.scheduleTimes.durationMin} мин"/></td>
                            <td><c:out value="${s.subject.name}"/></td>
                            <td><c:out value="${s.teacher.surname} ${s.teacher.name}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-md-6"><h3>Вторник</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Время начала</th>
                        <th>Длительность</th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${thuesday}">
                        <tr>
                            <td><c:out value="${s.scheduleTimes.lessonNum}"/></td>
                            <td><c:out value="${s.scheduleTimes.sdt}"/></td>
                            <td><c:out value="${s.scheduleTimes.durationMin} мин"/></td>
                            <td><c:out value="${s.subject.name}"/></td>
                            <td><c:out value="${s.teacher.surname} ${s.teacher.name}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6"><h3>Среда</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Время начала</th>
                        <th>Длительность</th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${wednesday}">
                        <tr>
                            <td><c:out value="${s.scheduleTimes.lessonNum}"/></td>
                            <td><c:out value="${s.scheduleTimes.sdt}"/></td>
                            <td><c:out value="${s.scheduleTimes.durationMin} мин"/></td>
                            <td><c:out value="${s.subject.name}"/></td>
                            <td><c:out value="${s.teacher.surname} ${s.teacher.name}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </table>
            </div>
            <div class="col-md-6"><h3>Четверг</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Время начала</th>
                        <th>Длительность</th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${thurday}">
                        <tr>
                            <td><c:out value="${s.scheduleTimes.lessonNum}"/></td>
                            <td><c:out value="${s.scheduleTimes.sdt}"/></td>
                            <td><c:out value="${s.scheduleTimes.durationMin} мин"/></td>
                            <td><c:out value="${s.subject.name}"/></td>
                            <td><c:out value="${s.teacher.surname} ${s.teacher.name}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6"><h3>Пятница</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Время начала</th>
                        <th>Длительность</th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${friday}">
                        <tr>
                            <td><c:out value="${s.scheduleTimes.lessonNum}"/></td>
                            <td><c:out value="${s.scheduleTimes.sdt}"/></td>
                            <td><c:out value="${s.scheduleTimes.durationMin} мин"/></td>
                            <td><c:out value="${s.subject.name}"/></td>
                            <td><c:out value="${s.teacher.surname} ${s.teacher.name}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-md-6"><h3>Суббота</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Время начала</th>
                        <th>Длительность</th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${saturday}">
                        <tr>
                            <td><c:out value="${s.scheduleTimes.lessonNum}"/></td>
                            <td><c:out value="${s.scheduleTimes.sdt}"/></td>
                            <td><c:out value="${s.scheduleTimes.durationMin} мин"/></td>
                            <td><c:out value="${s.subject.name}"/></td>
                            <td><c:out value="${s.teacher.surname} ${s.teacher.name}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6"><h3>Воскреснье</h3>
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>Время начала</th>
                        <th>Длительность</th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="s" items="${sunday}">
                        <tr>
                            <td><c:out value="${s.scheduleTimes.lessonNum}"/></td>
                            <td><c:out value="${s.scheduleTimes.sdt}"/></td>
                            <td><c:out value="${s.scheduleTimes.durationMin} мин"/></td>
                            <td><c:out value="${s.subject.name}"/></td>
                            <td><c:out value="${s.teacher.surname} ${s.teacher.name}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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

</body>

</html>