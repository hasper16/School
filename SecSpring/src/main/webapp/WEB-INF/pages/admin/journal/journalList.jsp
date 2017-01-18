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
    <title>Журнал</title>

    <link rel="stylesheet" href="/css/bootstrap.css" type="text/css" media="screen">
    <link rel="stylesheet" href="/css/layout.css" type="text/css" media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <!--[if lt IE 9]>
    <link rel="stylesheet" href="/css/ie.css" type="text/css" media="screen"/>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="/js/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script src="/js/hideshow.js" type="text/javascript"></script>
    <script src="/js/jquery.tablesorter.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="/js/jquery.equalHeight.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
                    $(".tablesorter").tablesorter();
                }
        );
        $(document).ready(function () {

            //When page loads...
            $(".tab_content").hide(); //Hide all content
            $("ul.tabs li:first").addClass("active").show(); //Activate first tab
            $(".tab_content:first").show(); //Show first tab content

            //On Click Event
            $("ul.tabs li").click(function () {

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
        $(function () {
            $('.column').equalHeight();
        });
    </script>


</head>


<body>

<header id="header">
    <hgroup>
        <h1 class="site_title"><a href="/admin/">Учительская</a></h1>
        <h2 class="section_title">School</h2>
        <div class="btn_view_site"><a href="/">Открыть сайт</a></div>
    </hgroup>
</header> <!-- end of header bar -->

<section id="secondary_bar">
    <div class="user">
        <p>${login}</p>
        <a class="logout_user" href="/logout" title="Logout">Logout</a>
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
        <li class="icn_categories"><a href="/admin/subjects/list">Все предметы</a></li>
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
        <hr/>
        <p><strong>Copyright &copy; 2016 H@sper</strong></p>
    </footer>
</aside><!-- end of sidebar -->

<section id="main" class="column">

    <script>
        if (${alert}==1
        )
        {

            alert("Оценка ${message} удалена")
        }
        if else (${alert}==2
        )
        {
            alert("Оценка ${message} изменена")
        }

    </script>

    <article class="module width_full">
        <header><h3 class="tabs_involved">Журнал</h3>
            <ul class="tabs" id = "filterTabs">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Класс <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${groups}" var="group">
                        <li><a href="/filter?group=${group.id}&type=progress"><c:out value="${group.name}"/></a></li>
                        </c:forEach>
                    </ul>
                </li>
                <li ><a  href="/filter?group=${selectGroup}&type=progress">Успеваемость</a></li>
                <li ><a  href="/filter?group=${selectGroup}&type=visit">Посещение</a></li>
            </ul>


        </header>
        <div class="tab_container">
            <table class="tablesorter" cellspacing="0">
                <thead>
                <tr>

                    <th>Дата</th>
                    <th>Студент</th>
                    <th>Класс</th>
                    <th>Предмет</th>
                    <th>Учитель</th>
                    <th>Тип</th>
                    <th>Оценка</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <tr>

        <c:forEach items="${markStamps}" var="markStamp">
                    <td><c:out value="${markStamp.dateString}"/></td>
                    <td><c:out value="${markStamp.jurnal.student.name} ${markStamp.jurnal.student.surname}"/></td>
                    <td><c:out value="${markStamp.jurnal.studentsGroup.name}"/></td>
                    <td><c:out value="${markStamp.jurnal.subject.name}"/></td>
                    <td><c:out value="${markStamp.jurnal.teacher.name} ${markStamp.jurnal.teacher.surname}"/></td>
                    <td><c:out value="${markStamp.markType}"/></td>
                    <td><c:out value="${markStamp.mark}"/></td>
                    <td><input type="image" src="/images/icn_edit.png" data-toggle="modal" data-target="#modalMarkEdit_${markStamp.id}" title="Редактировать"><a href="/journalList/delete?id=${markStamp.id}"><img src="/images/icn_trash.png" title="Удалить"></a></td>
            <!-- Modal -->
            <div class="modal fade" id="modalMarkEdit_${markStamp.id}" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Редактировать оценку</h4>
                        </div>
                        <form name="edit_teacher" action="/journalList/edit?id=${markStamp.id}" method="POST">
                            <div class="modal-body">

                                <fieldset style="width:48%; float:left; margin-right: 3%;">
                                    <label>Студент</label>
                                    <select style="width:92%;" name="j_student">
                                        <option value="${markStamp.jurnal.student.id}"><c:out
                                                value="${markStamp.jurnal.student.name} ${markStamp.jurnal.student.surname}"/></option>
                                        <c:forEach items="${students}" var="student">
                                            <c:if test="${markStamp.jurnal.student.id != student.id}">
                                                <option value="${student.id}"><c:out
                                                        value="${student.name} ${student.surname}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </fieldset>
                                <fieldset style="width:48%; float:left;">
                                    <label>Класс</label>
                                    <select style="width:92%;" name="j_group">
                                        <option value="${markStamp.jurnal.studentsGroup.id}"><c:out
                                                value="${markStamp.jurnal.studentsGroup.name}"/></option>
                                        <c:forEach items="${groups}" var="group">
                                            <c:if test="${markStamp.jurnal.studentsGroup.id != group.id}">
                                                <option value="${group.id}"><c:out
                                                        value="${group.name}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </fieldset>
                                <fieldset style="width:48%; float:left; margin-right: 3%;">
                                    <label>Предмет</label>
                                    <select style="width:92%;" name="j_subject">
                                        <option value="${markStamp.jurnal.subject.id}"><c:out
                                                value="${markStamp.jurnal.subject.name}"/></option>
                                        <c:forEach items="${subjects}" var="subject">
                                            <c:if test="${markStamp.jurnal.subject.id != subject.id}">
                                                <option value="${subject.id}"><c:out
                                                        value="${subject.name}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </fieldset>
                                <fieldset style="width:48%; float:left;">
                                    <label>Учитель</label>
                                    <select style="width:92%;" name="j_teacher">
                                        <option value="${markStamp.jurnal.teacher.id}"><c:out
                                                value="${markStamp.jurnal.teacher.name} ${markStamp.jurnal.teacher.surname}"/></option>
                                        <c:forEach items="${teachers}" var="teacher">
                                            <c:if test="${markStamp.jurnal.teacher.id != teacher.id}">
                                                <option value="${teacher.id}"><c:out
                                                        value="${teacher.name} ${teacher.surname}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </fieldset>
                                <fieldset style="width:48%; float:left; margin-right: 3%;">
                                    <label>Тип</label>
                                    <select style="width:92%;" name="j_markType">
                                        <option><c:out value="${markStamp.markType}"/></option>
                                        <c:forEach items="${markTypes}" var="markType">
                                            <c:if test="${markStamp.markType != markType}">
                                                <option><c:out value="${markType}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </fieldset>

                                <fieldset style="width:48%; float:left;">
                                    <label>Оценка</label>
                                    <select style="width:92%;" name="j_mark">
                                        <option value="${markStamp.mark.numMark}"><c:out value="${markStamp.mark}"/></option>
                                        <c:forEach items="${marks}" var="mark">
                                        <c:if test="${markStamp.mark != mark}">
                                        <option value="${mark.numMark}"><c:out value="${mark}"/></option>
                                        </c:if>
                                        </c:forEach>
                                </fieldset>
                                </select>
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
                </tr>
        </c:forEach>

                </tbody>
            </table>
            </div>
    </article><!-- end of content manager article -->

</section>

</body>


</html>
