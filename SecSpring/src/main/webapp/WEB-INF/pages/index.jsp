
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
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


</head>

<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
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

            <ul class="nav navbar-nav navbar-right"> <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${login} <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/profile"/>">Профиль</a></li>
                </ul>
            </li></ul>
            <c:url value="/logout" var="logoutUrl" />
            <form class="navbar-form navbar-right" action="${logoutUrl}">
                <button type="submit" class="btn btn-primary">Выйти</button>
            </form>
        </div>

    </div>
</div>


<div class="jumbotron">
    <div class="container">
        <div class="col-md-3">
            <h2 class="text-center">Всего заданий</h2>
            <h3 class="text-center">${countHomeWorks}</h3>
        </div>
        <div class="col-md-3">
            <h2 class="text-center">Всего классов</h2>
            <h3 class="text-center">${countGroups}</h3>
        </div>
        <div class="col-md-3">
            <h2 class="text-center">Всего студентов</h2>
            <h3 class="text-center">${countStudents}</h3>
        </div>
        <div class="col-md-3">
            <h2 class="text-center">Всего оценок</h2>
            <h3 class="text-center">${countMarks}</h3>
        </div>
    </div>
</div>

<div class="container">
    <!-- Example row of columns -->
    <div class="row">
        <div class="col-md-6">
            <h2 class="text-center">Топ 5 студентов</h2>
            <c:forEach items="${top5Students}" var="st">
            <table style="margin-left: 35%;">
                <td><img src="/image/${st.photo.id}" class="img-responsive" style="height: 20px;"></td>
                <td><h4 style="margin-left: 5px;"><c:out value="${st.name} ${st.surname}"/></h4></td>
            </table>
            </c:forEach>
        </div>
        <div class="col-md-6">
            <h2 class="text-center">Топ 5 классов</h2>
            <c:forEach items="${top5Groups}" var="g">
                <table style="margin-left: 45%;">
                    <td><span class="glyphicon glyphicon-certificate"></span></td>
                    <td><h4 style="margin-left: 5px;"><c:out value="${g.name}"/></h4></td>
                </table>
            </c:forEach>
           

        </div>
    </div>

    <hr>


</div> <!-- /container -->
<div class="footer">
    <div class="container">
        <p class="text-muted">Create by H@sper</p>
    </div>
</div>


</body>
</html>
