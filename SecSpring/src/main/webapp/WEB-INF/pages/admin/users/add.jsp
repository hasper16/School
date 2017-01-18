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
    <title>Создать пользователя</title>

    <link rel="stylesheet" href="/css/layout.css" type="text/css" media="screen" />
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
        if(${addStatus}==1) alert("Пользователь добавлен")
    </script>


    <form name="new_user" action="add" method="POST">
    <article class="module width_full">
        <header><h3>Добавить нового пользователя</h3></header>
        <div class="module_content">
            <fieldset style="width:48%; float:left; margin-right: 3%;">
                <label>Имя</label>
                <input type="text" style="width:92%;" name="j_name" value="${j_name}">
            </fieldset>
            <fieldset style="width:48%; float:left;">
                <label>Фамилия</label>
                <input type="text" style="width:92%;" name="j_surname" value="${j_surname}">
            </fieldset>
            <fieldset style="width:48%; float:left; margin-right: 3%;">
                <label>Login</label>
                <input type="text" style="width:92%;" name="j_login" value="${j_login}">
            </fieldset>
            <fieldset style="width:48%; float:left;">
                <label>Роль</label>
                <select style="width:92%;" name="j_role" >
                    <option></option>
                    <c:forEach items="${j_roles}" var="j_role">
                        <option><c:out value="${j_role}"/></option>
                    </c:forEach>
                </select>
            </fieldset>
            <fieldset style="width:48%; float:left; margin-right: 3%;">
                <label>Класс</label>
                <select style="width:92%;" name="j_group">
                    <option></option>
                    <c:forEach items="${j_groups}" var="j_group">
                        <option><c:out value="${j_group}"/></option>
                    </c:forEach>
                </select>
            </fieldset>
            <fieldset style="width:48%; float:left;">
                <label>Дата рождения</label>
                <input type="date" name="j_birthday" value="${j_birthday}">
            </fieldset>

            <fieldset style="width:48%; float:left; margin-right: 3%;">
                <label>Телефон</label>
                <input type="text" style="width:92%;" name="j_phone" value="${j_phone}">
            </fieldset>
            <fieldset style="width:48%; float:left;">
                <label>Email</label>
                <input type="text" style="width:92%;" name="j_email" value="${j_email}">
            </fieldset>


            <div class="clear"></div>
        </div>
        <footer>
            <div class="submit_link">
                <input type="submit" value="Добавить" class="alt_btn">
                <input type="reset"  value="Очистить">
            </div>
        </footer>
    </article><!-- end of post new article -->


</section>


</body>

</html>
