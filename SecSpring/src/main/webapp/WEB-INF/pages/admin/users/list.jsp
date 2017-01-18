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


    <link rel="stylesheet" href="/css/bootstrap.css" type="text/css" media="screen">
    <link rel="stylesheet" href="/css/layout.css" type="text/css" media="screen" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <!--[if lt IE 9]>
    <link rel="stylesheet" href="/css/ie.css" type="text/css" media="screen" />
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="/js/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="/js/hideshow.js" type="text/javascript"></script>
    <script src="/js/jquery.tablesorter.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/jquery.equalHeight.js"></script>
    <script type="text/javascript">
        $(document).ready(function()
                {
                    $(".tablesorter").tablesorter();
                }
        );
        $(document).ready(function() {

            //When page loads...
            $(".tab_content").hide(); //Hide all content
            $("ul.tabs li:first").addClass("active").show(); //Activate first tab
            $(".tab_content:first").show(); //Show first tab content

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
        <h1 class="site_title"><a href="/admin/">Учительская</a></h1>
        <h2 class="section_title">School</h2><div class="btn_view_site"><a href="/">Открыть сайт</a></div>
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
        <li class="icn_new_article active"><a href="admin/timetable/add">Новое расписание</a></li>
        <li class="icn_categories"><a href="admin/timetable/list">Все расписания</a></li>
        <li class="icn_new_article"><a href="admin/subjects/add">Новый предмет</a></li>
        <li class="icn_categories"><a href="admin/subject/list">Все предметы</a></li>
        <li class="icn_new_article"><a href="admin/groups/add">Новый класс</a></li>
        <li class="icn_categories"><a href="admin/groups/list">Все классы</a></li>
    </ul>
    <h3>Пользователи</h3>
    <ul class="toggle">
        <li class="icn_add_user"><a href="/admin/users/add">Добавить нового пользователя</a></li>
        <li class="icn_view_users"><a href="/admin/users/list">Список пользователей</a></li>
    </ul>
    <h3>Домашние задания</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="admin/homeworks/add">Новое задание</a></li>
        <li class="icn_categories"><a href="admin/homeworks/list">Все задания</a></li>
    </ul>
    <h3>Журнал оценок и посещений</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="admin/journal/addMark">Новая оценка</a></li>
        <li class="icn_folder"><a href="admin/journal/journalList">Журнал</a></li>
    </ul>
    <h3>Отчеты</h3>
    <ul class="toggle">
        <li class="icn_categories"><a href="admin/reports/progress">Успеваемость</a></li>
        <li class="icn_categories"><a href="admin/reports/visits">Посещения</a></li>
        <li class="icn_categories"><a href="admin/reports/studentsRating">Рейтинг учеников</a></li>
    </ul>
    <h3>Admin</h3>
    <ul class="toggle">
        <li class="icn_audio"><a href="#">Объявление</a></li>
        <li class="icn_settings"><a href="#">Options</a></li>
        <li class="icn_security"><a href="#">Security</a></li>
        <li class="icn_jump_back"><a href="#">Logout</a></li>
    </ul>

    <footer>
        <hr />
        <p><strong>Copyright &copy; 2016 H@sper</strong></p>
    </footer>
</aside><!-- end of sidebar -->


<section id="main" class="column">

<script>
    if(${alert}==1){

        alert("Пользователь ${message} удален")
    }
    else if(${alert}==2){
        alert("Пользователь ${message} изменен")
    }
</script>

    <article class="module width_full">
        <header><h3 class="tabs_involved">Список пользователей</h3>
            <ul class="tabs">
                <li><a href="#tab1">Учителя</a></li>
                <li><a href="#tab2">Студенты</a></li>
                <li><a href="#tab3">Админы</a></li>
            </ul>
        </header>

        <div class="tab_container">
            <div id="tab1" class="tab_content">
                <table class="tablesorter" cellspacing="0">
                    <thead>
                    <tr>

                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Login</th>
                        <th>Email</th>
                        <th>Телефон</th>
                        <th>Дата рождения</th>
                        <th>Действие</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${teachers}" var="teacher">
                        <tr>
                            <td><c:out value="${teacher.name}"/></td>
                            <td><c:out value="${teacher.surname}"/></td>
                            <td><c:out value="${teacher.user.login}"/></td>
                            <td><c:out value="${teacher.email}"/></td>
                            <td><c:out value="${teacher.phone}"/></td>
                            <td><c:out value="${teacher.birthdayString}"/></td>
                            <td><input type="image" src="/images/icn_edit.png" data-toggle="modal" data-target="#modalTeachEdit_${teacher.id}" title="Редактировать"><a href="/teacher/delete?id=${teacher.id}"><img src="/images/icn_trash.png" title="Удалить"></a></td>
                        </tr>


                        <!-- Modal -->
                        <div class="modal fade" id="modalTeachEdit_${teacher.id}" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Редактировать учителя</h4>
                                    </div>
                                    <form name="edit_teacher" action="/teacher/edit?id=${teacher.id}" method="POST">
                                    <div class="modal-body">

                                        <fieldset style="width:48%; float:left; margin-right: 3%;">
                                            <label>Имя</label>
                                            <input type="text" style="width:92%;" name="j_name" value="${teacher.name}">
                                        </fieldset>
                                        <fieldset style="width:48%; float:left;">
                                            <label>Фамилия</label>
                                            <input type="text" style="width:92%;" name="j_surname" value="${teacher.surname}">
                                        </fieldset>
                                        <fieldset style="width:48%; float:left; margin-right: 3%;">
                                            <label>Login</label>
                                            <input type="text" style="width:92%;" name="j_login" value="${teacher.user.login}">
                                        </fieldset>
                                        <fieldset style="width:48%; float:left;">
                                            <label>Роль</label>
                                            <select style="width:92%;" name="j_role" >
                                                <option>${teacher.user.role}</option>
                                                <c:forEach items="${j_roles}" var="j_role">
                                                    <option><c:out value="${j_role}"/></option>
                                                </c:forEach>
                                            </select>
                                        </fieldset>
                                        <fieldset style="width:48%; float:left; margin-right: 3%;">
                                            <label>Дата рождения</label>
                                               <input type="date" style="width:92%;" name="j_birthday" value="${teacher.birthdayHtml}">
                                        </fieldset>

                                        <fieldset style="width:48%; float:left;">
                                            <label>Телефон</label>
                                            <input type="number" style="width:92%;" name="j_phone" value="${teacher.phone}">
                                        </fieldset>
                                        <fieldset style="width:48%; float:left; margin-right: 3%; ">
                                            <label>Email</label>
                                            <input type="text" style="width:92%; " name="j_email" value="${teacher.email}">
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
            </div><!-- end of #tab1 -->

            <div id="tab2" class="tab_content">
                <table class="tablesorter" cellspacing="0">
                    <thead>
                    <tr>

                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Login</th>
                        <th>Класс</th>
                        <th>Email</th>
                        <th>Телефон</th>
                        <th>Дата рождения</th>
                        <th>Действие</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${students}" var="student">
                        <tr>
                            <td><c:out value="${student.name}"/></td>
                            <td><c:out value="${student.surname}"/></td>
                            <td><c:out value="${student.user.login}"/></td>
                            <td><c:out value="${student.studentsGroup.name}"/></td>
                            <td><c:out value="${student.email}"/></td>
                            <td><c:out value="${student.phone}"/></td>
                            <td><c:out value="${student.birthdayString}"/></td>
                            <td><input type="image" src="/images/icn_edit.png" data-toggle="modal" data-target="#modalStudentEdit_${student.id}" title="Редактировать"><a href="/student/delete?id=${student.id}"><img src="/images/icn_trash.png" title="Удалить"></a></td>
                        </tr>
                        <!-- Modal -->
                        <div class="modal fade" id="modalStudentEdit_${student.id}" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Редактировать студента</h4>
                                    </div>
                                    <form name="edit_teacher" action="/student/edit?id=${student.id}" method="POST">
                                        <div class="modal-body">

                                            <fieldset style="width:48%; float:left; margin-right: 3%;">
                                                <label>Имя</label>
                                                <input type="text" style="width:92%;" name="j_name" value="${student.name}">
                                            </fieldset>
                                            <fieldset style="width:48%; float:left;">
                                                <label>Фамилия</label>
                                                <input type="text" style="width:92%;" name="j_surname" value="${student.surname}">
                                            </fieldset>
                                            <fieldset style="width:48%; float:left; margin-right: 3%;">
                                                <label>Login</label>
                                                <input type="text" style="width:92%;" name="j_login" value="${student.user.login}">
                                            </fieldset>
                                            <fieldset style="width:48%; float:left;">
                                                <label>Роль</label>
                                                <select style="width:92%;" name="j_role" >
                                                    <option>${student.user.role}</option>
                                                    <c:forEach items="${j_roles}" var="j_role">
                                                        <option><c:out value="${j_role}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </fieldset>
                                            <fieldset style="width:48%; float:left; margin-right: 3%;">
                                                <label>Дата рождения</label>
                                                <input type="date" style="width:92%;" name="j_birthday" value="${student.birthdayHtml}">
                                            </fieldset>

                                            <fieldset style="width:48%; float:left;">
                                                <label>Телефон</label>
                                                <input type="number" style="width:92%;" name="j_phone" value="${student.phone}">
                                            </fieldset>
                                            <fieldset style="width:48%; float:left; margin-right: 3%; ">
                                                <label>Email</label>
                                                <input type="text" style="width:92%; " name="j_email" value="${student.email}">
                                            </fieldset>
                                            <fieldset style="width:48%; float:left;">
                                                <label>Класс</label>
                                                <select style="width:92%;" name="j_group" >
                                                    <option>${student.studentsGroup.name}</option>
                                                    <c:forEach items="${allGroups}" var="group">
                                                        <c:if test="${group.name!=student.studentsGroup.name}">
                                                        <option><c:out value="${group.name}"/></option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
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

            </div><!-- end of #tab2 -->

            <div id="tab3" class="tab_content">
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
                        <td><input type="image" src="/images/icn_edit.png" data-toggle="modal" data-target="#modalAdminEdit_${admin.id}" title="Редактировать"><a href="/admin/delete?id=${admin.id}"><img src="/images/icn_trash.png" title="Удалить"></a></td>
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
                                            <fieldset style="width:48%; float:left; margin-right: 3%;">
                                                <label>Login</label>
                                                <input type="text" style="width:92%;" name="j_login" value="${admin.login}">
                                            </fieldset>
                                            <fieldset style="width:48%; float:left;">
                                                <label>Роль</label>
                                                <select style="width:92%;" name="j_role" >
                                                    <option>${admin.role}</option>
                                                    <c:forEach items="${j_roles}" var="j_role">
                                                        <option><c:out value="${j_role}"/></option>
                                                    </c:forEach>
                                                </select>
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

            </div><!-- end of #tab3 -->

        </div><!-- end of .tab_container -->

    </article><!-- end of content manager article -->

</section>

</body>

</html>
