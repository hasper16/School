<%--
  Created by IntelliJ IDEA.
  User: Pavel.Eremenko
  Date: 04.01.2017
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Список пользователей</title>


    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css" media="screen">
    <link rel="stylesheet" href="<c:url value="/css/layout.css"/>" type="text/css" media="screen" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <!--[if lt IE 9]>
    <link rel="stylesheet" href="/css/ie.css" type="text/css" media="screen" />
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="<c:url value="/js/jquery-1.5.2.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/hideshow.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/jquery.tablesorter.min.js"/>" type="text/javascript"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.equalHeight.js"/>"></script>
    <script type="text/javascript">
        $(document).ready(function()
                {
                    $(".tablesorter").tablesorter();
                }
        );
        $(document).ready(function() {



            //On Click Event
            $("ul.tabs li").click(function() {

                $("ul.tabs li").removeClass("active"); //Remove any "active" class
                $(this).addClass("active"); //Add "active" class to selected tab
                $(".tab_content").hide(); //Hide all tab content

                var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
                $(activeTab).fadeIn(); //Fade in the active ID content
                return false;
            });

        });
    </script>
    <script type="text/javascript">
        $(function(){
            $('.column').equalHeight();
        });
    </script>

</head>


<body>

<header id="header">
    <hgroup>
        <h1 class="site_title"><a href="<c:url value="/admin/"/>">Учительская</a></h1>
        <h2 class="section_title">School</h2><div class="btn_view_site"><a href="<c:url value="/"/>">Открыть сайт</a></div>
    </hgroup>
</header> <!-- end of header bar -->

<section id="secondary_bar">
    <div class="user">
        <p>${login}</p>
        <a class="logout_user" href="#" title="Logout">Logout</a>
    </div>
    <!--<div class="breadcrumbs_container">
        <article class="breadcrumbs"><a href="./index.html">Учительская</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
    </div>-->
</section><!-- end of secondary bar -->


<aside id="sidebar" class="column">

    <h3>Расписание занятий</h3>
    <ul class="toggle">
        <li class="icn_new_article active"><a href="<c:url value="/admin/timetable/add"/>">Новое расписание</a></li>
        <li class="icn_categories"><a href="<c:url value="/admin/timetable/list"/>">Все расписания</a></li>
        <li class="icn_new_article"><a href="<c:url value="/admin/subjects/add"/>">Новый предмет</a></li>
        <li class="icn_categories"><a href="<c:url value="/admin/subjects/list"/>">Все предметы</a></li>
        <li class="icn_new_article"><a href="<c:url value="/admin/groups/add"/>">Новый класс</a></li>
        <li class="icn_categories"><a href="<c:url value="/admin/groups/list"/>">Все классы</a></li>
    </ul>
    <h3>Пользователи</h3>
    <ul class="toggle">
        <li class="icn_add_user"><a href="<c:url value="/admin/users/add"/>">Добавить нового пользователя</a></li>
        <li class="icn_view_users"><a href="<c:url value="/admin/users/list"/>">Список пользователей</a></li>
    </ul>
    <h3>Домашние задания</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="<c:url value="/admin/homeworks/add"/>">Новое задание</a></li>
        <li class="icn_categories"><a href="<c:url value="/admin/homeworks/list"/>">Все задания</a></li>
    </ul>
    <h3>Журнал оценок и посещений</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="<c:url value="/admin/journal/addMark"/>">Новая оценка</a></li>
        <li class="icn_folder"><a href="<c:url value="/admin/journal/journalList"/>">Журнал</a></li>
    </ul>
    <div style="visibility: hidden;">
        <h3>Отчеты</h3>
        <ul class="toggle">
            <li class="icn_categories"><a href="<c:url value="/admin/reports/progress"/>">Успеваемость</a></li>
            <li class="icn_categories"><a href="<c:url value="/admin/reports/visits"/>">Посещения</a></li>
            <li class="icn_categories"><a href="<c:url value="/admin/reports/studentsRating"/>">Рейтинг учеников</a></li>
        </ul>
        <h3>Admin</h3>
        <ul class="toggle">
            <li class="icn_audio"><a href="#">Объявление</a></li>
            <li class="icn_settings"><a href="#">Options</a></li>
            <li class="icn_security"><a href="#">Security</a></li>
            <li class="icn_jump_back"><a href="#">Logout</a></li>
        </ul>
    </div>

    <footer>
        <hr />
        <p><strong>Copyright &copy; 2016 H@sper</strong></p>
    </footer>
</aside>
<!-- end of sidebar -->


<section id="main" class="column">
    <%-- Notifications--%>
    <c:if test="${alert == 1}"><h4 class="alert_success">${message}</h4></c:if>
    <c:if test="${alert == 2}"><h4 class="alert_warning">${message}</h4></c:if>
    <c:if test="${alert == 3}"><h4 class="alert_error">${message}</h4></c:if>
    <c:if test="${alert == 4}"><h4 class="alert_info">${message}</h4></c:if>
    <%-- Notifications--%>

    <article class="module width_full">
        <header><h3 class="tabs_involved">Список пользователей</h3>
            <ul class="tabs">
                <li><a href="/admin/users/teachers?page=0">Учителя</a></li>
                <li><a href="/admin/users/students?page=0">Студенты</a></li>
                <li class="active"><a href="/admin/users/admins?page=0">Админы</a></li>
            </ul>
        </header>

        <div class="tab_container">
                <table class="tablesorter" cellspacing="0">
                    <thead>
                    <tr>

                        <th>Login</th>
                        <th>Действие</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${admins}" var="admin">
                    <tr>

                        <td><c:out value="${admin.login}"/></td>
                        <td><input type="image" src="<c:url value="/images/icn_edit.png"/>" data-toggle="modal" data-target="#modalAdminEdit_${admin.id}" title="Редактировать"><a href="<c:url value="/admin/delete?id=${admin.id}"/>"><img src="<c:url value="/images/icn_trash.png"/>" title="Удалить"></a></td>
                    </tr>
                        <!-- Modal -->
                        <div class="modal fade" id="modalAdminEdit_${admin.id}" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Редактировать админа</h4>
                                    </div>
                                    <form name="edit_teacher" action="/admin/edit?id=${admin.id}" method="POST">
                                        <div class="modal-body">
                                            <fieldset style="width:48%; float:left; margin-right: 3%">
                                                <div class="col-md-11">
                                                    <div class="form-group">
                                                        <label for="inputLogin">Login</label>
                                                        <input class="form-control input-sm" id="inputLogin" type="text" name="j_login"
                                                               value="${admin.login}"/>
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                        <div class="clear"></div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                                            <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            <!--Pages -->
            <ul class="pagination">
                <c:if test="${curPage == 0}">
                    <li class="disabled"><a href="/admin/users/admins?page=0">&laquo;</a></li>
                </c:if>
                <c:if test="${curPage > 0}">
                    <li><a href="/admin/users/admins?page=0">&laquo;</a></li>
                </c:if>

                <c:if test="${curPage <= 3}"><c:set var="begin" value="0"/></c:if>
                <c:if test="${curPage > 3}"><c:set var="begin" value="${curPage-3}"/></c:if>

                <c:forEach var="i" begin="${begin}" end="${totalPages}">
                    <c:if test="${i <= curPage+3}">
                        <c:if test="${curPage == i}">
                            <li class="active"><a href="/admin/users/admins?page=${i}"><c:out value="${i+1}"/>
                                <span class="sr-only">(current)</span></a></li>
                        </c:if>
                        <c:if test="${curPage != i}">
                            <li><a href="/admin/users/admins?page=${i}"><c:out value="${i+1}"/> <span
                                    class="sr-only">(current)</span></a></li>
                        </c:if>
                    </c:if>
                </c:forEach>

                <c:if test="${curPage == totalPages}">
                    <li class="disabled"><a href="/admin/users/admins?page=${totalPages}">&raquo;</a>
                    </li>
                </c:if>
                <c:if test="${curPage < totalPages}">
                    <li><a href="/admin/users/admins?page=${totalPages}">&raquo;</a></li>
                </c:if>
            </ul>
            <!--end Pages -->
            </div><!-- end of .tab_container -->
    </article><!-- end of content manager article -->

</section>
<script src="/js/validator/jquery.min.js"></script>
<script src="/js/validator/bootstrap.min.js"></script>
<script src="/js/validator/common.js"></script>

</body>

</html>
