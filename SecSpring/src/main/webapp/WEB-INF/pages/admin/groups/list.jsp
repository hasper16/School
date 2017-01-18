<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavel.Eremenko
  Date: 27.12.2016
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Список класов</title>


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
        <a class="logout_user" href="/logout"  title="Logout">Logout</a>
    </div>
    <!--<div class="breadcrumbs_container">
        <article class="breadcrumbs"><a href="./index.html">Учительская</a> <div class="breadcrumb_divider"></div> <a class="current">Dashboard</a></article>
    </div>-->
</section><!-- end of secondary bar -->

<aside id="sidebar" class="column">

    <h3>Расписание занятий</h3>
    <ul class="toggle">
        <li class="icn_new_article active"><a href="/admin/timetable/add">Новое расписание</a></li>
        <li class="icn_categories"><a href="/admin/timetable/list">Все расписания</a></li>
        <li class="icn_new_article"><a href="/admin/subjects/add">Новый предмет</a></li>
        <li class="icn_categories"><a href="/admin/subject/list">Все предметы</a></li>
        <li class="icn_new_article"><a href="/admin/groups/add">Новый класс</a></li>
        <li class="icn_categories"><a href="/admin/groups/list">Все классы</a></li>
    </ul>
    <h3>Пользователи</h3>
    <ul class="toggle">
        <li class="icn_add_user"><a href="/admin/users/add">Добавить нового пользователя</a></li>
        <li class="icn_view_users"><a href="/admin/users/list">Список пользователей</a></li>
    </ul>
    <h3>Домашние задания</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="/admin/homeworks/add">Новое задание</a></li>
        <li class="icn_categories"><a href="/admin/homeworks/list">Все задания</a></li>
    </ul>
    <h3>Журнал оценок и посещений</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="/admin/journal/addMark">Новая оценка</a></li>
        <li class="icn_folder"><a href="/admin/journal/journalList">Журнал</a></li>
    </ul>
    <h3>Отчеты</h3>
    <ul class="toggle">
        <li class="icn_categories"><a href="/admin/reports/progress">Успеваемость</a></li>
        <li class="icn_categories"><a href="/admin/reports/visits">Посещения</a></li>
        <li class="icn_categories"><a href="/admin/reports/studentsRating">Рейтинг учеников</a></li>
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

            alert("Класс ${message} удален")
        }
        else if(${alert}==2){
            alert("Класс ${message} изменен")
        }
    </script>

    <article class="module width_full">
        <header><h3 class="tabs_involved">Список класов</h3>
            <ul class="tabs">
                <li><a href="#tab1">Классы</a></li>
            </ul>
        </header>

        <div class="tab_container">
            <div id="tab1" class="tab_content">
                <table class="tablesorter" cellspacing="0">
                    <thead>
                    <tr>

                        <th>Название</th>
                        <th>Действие</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${groups}" var="group">
                        <tr>
                            <td><c:out value="${group.name}"/></td>
                            <td><input type="image" src="/images/icn_edit.png" data-toggle="modal" data-target="#modalGroupEdit_${group.id}" title="Редактировать"><a href="/groups/delete?id=${group.id}"><img src="/images/icn_trash.png" title="Удалить"></a></td>
                        </tr>


                        <!-- Modal -->
                        <div class="modal fade" id="modalGroupEdit_${group.id}" role="dialog">
                            <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Редактировать класс</h4>
                                    </div>
                                    <form name="edit_group" action="/groups/edit?id=${group.id}" method="POST">
                                        <div class="modal-body">

                                            <fieldset style="width:48%; float:left; margin-right: 3%;">
                                                <label>Название</label>
                                                <input type="text" style="width:92%;" name="j_group" value="${group.name}">
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

        </div><!-- end of .tab_container -->

    </article><!-- end of content manager article -->

</section>


</body>

</html>
