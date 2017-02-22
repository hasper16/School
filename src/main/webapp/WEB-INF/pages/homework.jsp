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

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css" />
    <link rel="stylesheet" href="<c:url value="/css/assets/ie10-viewport-bug-workaround.css"/>" type="text/css" />
    <link rel="stylesheet" href="<c:url value="/css/jumbotron.css"/>" type="text/css" />
    <link rel="stylesheet" href="<c:url value="/css/footer.css"/>" type="text/css" />


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
                <li class="active"><a href="<c:url value="/homework"/>">Домашние задания</a></li>
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

<div class="jumbotron">
    <div class="container">
        <div class="row">
            <form name="home_work_seach" action="<c:url value="/homework"/>" method="POST">
<fieldset style="float: left; width: 25%;">
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
</fieldset>
<fieldset style="float: left; width: 12%; margin-left: 1%;">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">С </span>
                        <input type="date" class="form-control" name="j_sdt" value="${start_date}">
                    </div>
</fieldset>
                <fieldset style="float: left; width: 12%; margin-left: 6%;">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">По </span>
                        <input type="date" class="form-control"  name="j_edt" value="${end_date}">
                    </div>
                </fieldset>
<fieldset style="float: left; width: 25%; margin-left: 7%;">
    <div class="input-group input-group-sm">
                 <span class="input-group-addon">Статус</span>
                    <select class="form-control" name="j_status">
                        <option><c:out value="${curStatus}"/></option>
                        <c:forEach items="${hmstatus}" var="st">
                            <option><c:out value="${st}"/></option>
                        </c:forEach>
                    </select>
    </div>
</fieldset>

<fieldset style="float: left; width: 5%; margin-left: 1%;">
    <div class="btn-group btn-group-sm">
                    <button type="submit" class="btn btn-default">Поиск</button>
    </div>
</fieldset>
            </form>
        </div>
    </div>
</div> <!-- /container -->

<div class="homeworktable">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="table-responsive">
                <table class="table table-condensed table-hover table-bordered">
                    <thead>
                        <th>Описание</th>
                        <th>Предмет</th>
                        <th>Учитель</th>
                        <th>Дата</th>
                        <th>Статус</th>
                        </tr>
                    </thead>

                    <c:forEach items="${homeworks}" var="hw">
                    <form id="strhomework" action="<c:url value="/homework_?id=${hw.id}"/>" method="POST">
                        <tr class=<c:out value="${hw.status.tableClassName}"/>>
                            <td>${hw.homeWork.description}</td>
                            <td>${hw.homeWork.subject.name}</td>
                            <td>${hw.homeWork.teacher.surname} ${hw.homeWork.teacher.name}</td>
                            <td>${hw.homeWork.strDate}</td>
                            <td>
                                <select class="form-control" name="j_hwStatus" onchange="this.form.submit()">
                                    <option><c:out value="${hw.status}"/></option>
                                    <c:forEach items="${statusList}" var="st">
                                        <c:if test="${st != hw.status}">
                                        <option><c:out value="${st}"/></option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        </form>
                    </c:forEach>
                </table>
                </div>
                <!--Pages -->
                <ul class="pagination">
                    <c:if test="${curPage == 0}">
                        <li class="disabled"><a href="/homework_?page=0">&laquo;</a></li>
                    </c:if>
                    <c:if test="${curPage > 0}">
                        <li><a href="/homework_?page=0">&laquo;</a></li>
                    </c:if>

                    <c:if test="${curPage <= 2}"><c:set var="begin" value="0"/></c:if>
                    <c:if test="${curPage > 2}"><c:set var="begin" value="${curPage-2}"/></c:if>
                    <c:if test="${totalPages == 0}"><c:set var="end" value="0"/></c:if>
                    <c:if test="${totalPages > 0}"><c:set var="end" value="${totalPages-1}"/></c:if>

                    <c:forEach var="i" begin="${begin}" end="${end}">
                        <c:if test="${i <= curPage+2}">
                            <c:if test="${curPage == i}">
                                <li class="active"><a href="/homework_?page=${i}"><c:out
                                        value="${i+1}"/>
                                    <span class="sr-only">(current)</span></a></li>
                            </c:if>
                            <c:if test="${curPage != i}">
                                <li><a href="/homework_?page=${i}"><c:out value="${i+1}"/> <span
                                        class="sr-only">(current)</span></a></li>
                            </c:if>
                        </c:if>
                    </c:forEach>

                    <c:if test="${curPage == totalPages}">
                        <li class="disabled"><a href="/homework_?page=${totalPages-1}">&raquo;</a>
                        </li>
                    </c:if>
                    <c:if test="${curPage < totalPages}">
                        <li><a href="/homework_?page=${totalPages-1}">&raquo;</a></li>
                    </c:if>
                </ul>
                <!--end Pages -->
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