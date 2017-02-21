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

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css" media="screen">
    <link rel="stylesheet" href="<c:url value="/css/layout.css"/>" type="text/css" media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


    <!--[if lt IE 9]>
    <link rel="stylesheet" href="/css/ie.css" type="text/css" media="screen"/>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="<c:url value="/js/jquery-1.5.2.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/hideshow.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/jquery.tablesorter.min.js"/>" type="text/javascript"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery.equalHeight.js"/>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
                $(".tablesorter").tablesorter();
            }
        );
        $(document).ready(function () {

            /* //When page loads...
             $(".tab_content").hide(); //Hide all content
             $("ul.tabs li:first").addClass("active").show(); //Activate first tab
             $(".tab_content:first").show(); //Show first tab content*/

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
        <h1 class="site_title"><a href="<c:url value="/admin/"/>">Учительская</a></h1>
        <h2 class="section_title">School</h2>
        <div class="btn_view_site"><a href="<c:url value="/"/>">Открыть сайт</a></div>
    </hgroup>
</header> <!-- end of header bar -->

<section id="secondary_bar">
    <div class="user">
        <p>${login}</p>
        <a class="logout_user" href="<c:url value="/logout"/>" title="Logout">Logout</a>
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
    </ul>
    <h3>Предметы</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="<c:url value="/admin/subjects/add"/>">Новый предмет</a></li>
        <li class="icn_categories"><a href="<c:url value="/admin/subjects/list"/>">Все предметы</a></li>
    </ul>
    <h3>Классы</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="<c:url value="/admin/groups/add"/>">Новый класс</a></li>
        <li class="icn_categories"><a href="<c:url value="/admin/groups/list"/>">Все классы</a></li>
    </ul>
    <h3>Время уроков</h3>
    <ul class="toggle">
        <li class="icn_new_article"><a href="<c:url value="/admin/times/add"/>">Новый урок</a></li>
        <li class="icn_categories"><a href="<c:url value="/admin/times/list"/>">Все уроки</a></li>
    </ul>
    <h3>Пользователи</h3>
    <ul class="toggle">
        <li class="icn_add_user"><a href="<c:url value="/admin/users/add"/>">Добавить нового пользователя</a></li>
        <li class="icn_view_users"><a href="<c:url value="/admin/users/teachers"/>">Список пользователей</a></li>
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
        <header><h3 class="tabs_involved">Журнал</h3>
            <ul class="tabs" id="filterTabs">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        Класс <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:forEach items="${groups}" var="group">
                            <li><a href="<c:url value="/filter?group=${group.id}&type=${type}&page=0"/>"><c:out
                                    value="${group.name}"/></a></li>
                        </c:forEach>
                    </ul>
                </li>

                <c:forEach items="${markTypes}" var="markType">
                    <c:if test="${type == markType}">
                        <li class="active"><a href="/filter?group=${selectGroup}&type=${markType}&page=0">
                            <c:out value="${markType}"/></a></li>
                    </c:if>
                    <c:if test="${type != markType}">
                        <li><a href="/filter?group=${selectGroup}&type=${markType}&page=0">
                            <c:out value="${markType}"/></a></li>
                    </c:if>
                </c:forEach>


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
                    <td><input type="image" src="<c:url value="/images/icn_edit.png"/>" data-toggle="modal"
                               data-target="#modalMarkEdit_${markStamp.id}" title="Редактировать"><a
                            href="<c:url value="/journalList/delete?id=${markStamp.id}"/>"><img
                            src="<c:url value="/images/icn_trash.png"/>" title="Удалить"></a></td>
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
                                        <div class="container-fluid">
                                            <div class="row">
                                                <fieldset style="width:48%; float:left; margin-right: 3%;">
                                                    <div class="col-md-11">
                                                        <div class="form-group">
                                                            <label for="inputStudent">Студент</label>
                                                            <select class="form-control input-sm" id="inputStudent"
                                                                    name="j_student">
                                                                <option value="${markStamp.jurnal.student.id}"><c:out
                                                                        value="${markStamp.jurnal.student.name} ${markStamp.jurnal.student.surname}"/></option>
                                                                <c:forEach items="${students}" var="student">
                                                                    <c:if test="${markStamp.jurnal.student.id != student.id}">
                                                                        <option value="${student.id}"><c:out
                                                                                value="${student.name} ${student.surname}"/></option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset style="width:48%; float:left;">
                                                    <div class="col-md-11">
                                                        <div class="form-group">
                                                            <label for="inputSubject">Предмет</label>
                                                            <select class="form-control input-sm" id="inputSubject"
                                                                    name="j_subject">
                                                                <option value="${markStamp.jurnal.subject.id}"><c:out
                                                                        value="${markStamp.jurnal.subject.name}"/></option>
                                                                <c:forEach items="${subjects}" var="subject">
                                                                    <c:if test="${markStamp.jurnal.subject.id != subject.id}">
                                                                        <option value="${subject.id}"><c:out
                                                                                value="${subject.name}"/></option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>
                                            <div class="row">
                                                <fieldset style="width:48%; float:left; margin-right: 3%">
                                                    <div class="col-md-11">
                                                        <div class="form-group">
                                                            <label for="inputTeacher">Учитель</label>
                                                            <select class="form-control input-sm" id="inputTeacher"
                                                                    name="j_teacher">
                                                                <option value="${markStamp.jurnal.teacher.id}"><c:out
                                                                        value="${markStamp.jurnal.teacher.name} ${markStamp.jurnal.teacher.surname}"/></option>
                                                                <c:forEach items="${teachers}" var="teacher">
                                                                    <c:if test="${markStamp.jurnal.teacher.id != teacher.id}">
                                                                        <option value="${teacher.id}"><c:out
                                                                                value="${teacher.name} ${teacher.surname}"/></option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                                <fieldset style="width:48%; float:left;">
                                                    <div class="col-md-11">
                                                        <div class="form-group">
                                                            <label for="inputType">Тип оценки</label>
                                                            <select class="form-control input-sm" id="inputType"
                                                                    name="j_markType" onchange="selectType()">
                                                                <option><c:out value="${markStamp.markType}"/></option>
                                                                <c:forEach items="${markTypes}" var="markType">
                                                                    <c:if test="${markStamp.markType != markType}">
                                                                        <option><c:out value="${markType}"/></option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>
                                            <div class="row">
                                                <fieldset style="width:48%; float:left; margin-right: 3%;">
                                                    <div class="col-md-11">
                                                        <div class="form-group">
                                                            <label for="inputMark">Оценка</label>
                                                            <select class="form-control input-sm" id="inputMark"
                                                                    name="j_mark">
                                                                <option value="${markStamp.mark.numMark}"><c:out
                                                                        value="${markStamp.mark}"/></option>
                                                                <c:forEach items="${marks}" var="mark">
                                                                    <c:if test="${(markStamp.mark != mark) && (markStamp.markType == mark.markType)}">
                                                                        <option value="${mark.numMark}"><c:out
                                                                                value="${mark}"/></option>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="clear"></div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть
                                        </button>
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
        <!--Pages -->
        <ul class="pagination">
            <c:if test="${curPage == 0}">
                <li class="disabled"><a href="/filter?group=${selectGroup}&type=${type}&page=0">&laquo;</a></li>
            </c:if>
            <c:if test="${curPage > 0}">
                <li><a href="/filter?group=${selectGroup}&type=${type}&page=0">&laquo;</a></li>
            </c:if>

            <c:if test="${curPage <= 3}"><c:set var="begin" value="0"/></c:if>
            <c:if test="${curPage > 3}"><c:set var="begin" value="${curPage-3}"/></c:if>

            <c:forEach var="i" begin="${begin}" end="${totalPages}">
                <c:if test="${i <= curPage+3}">
                    <c:if test="${curPage == i}">
                        <li class="active"><a href="/filter?group=${selectGroup}&type=${type}&page=${i}"><c:out
                                value="${i+1}"/>
                            <span class="sr-only">(current)</span></a></li>
                    </c:if>
                    <c:if test="${curPage != i}">
                        <li><a href="/filter?group=${selectGroup}&type=${type}&page=${i}"><c:out value="${i+1}"/> <span
                                class="sr-only">(current)</span></a></li>
                    </c:if>
                </c:if>
            </c:forEach>

            <c:if test="${curPage == totalPages}">
                <li class="disabled"><a href="/filter?group=${selectGroup}&type=${type}&page=${totalPages}">&raquo;</a>
                </li>
            </c:if>
            <c:if test="${curPage < totalPages}">
                <li><a href="/filter?group=${selectGroup}&type=${type}&page=${totalPages}">&raquo;</a></li>
            </c:if>
        </ul>
        <!--end Pages -->
    </article><!-- end of content manager article -->

</section>
<script src="/js/validator/jquery.min.js"></script>
<script src="/js/validator/bootstrap.min.js"></script>
<script src="/js/validator/common.js"></script>
<script>

    var students = [];
    <c:forEach items="${students}" var="student" varStatus="loop">
    students[${loop.index}] = [];
    students[${loop.index}][0] = <c:out value="${student.studentsGroup.id}"/>;
    students[${loop.index}][1] = <c:out value="${student.id}"/>;
    students[${loop.index}][2] = '<c:out value="${student.name} ${student.surname}"/>';
    </c:forEach>

    var marks = [];
    <c:forEach items="${marks}" var="mark" varStatus="loop">
    marks[${loop.index}] = [];
    marks[${loop.index}][0] = "<c:out value="${mark.markType}"/>";
    marks[${loop.index}][1] = <c:out value="${mark.numMark}"/>;
    marks[${loop.index}][2] = "<c:out value="${mark}"/>";
    </c:forEach>

    function selectType() {
        var inType = document.getElementById('inputType').value;
        var inMark = document.getElementById('inputMark');

        while (inMark.length > 0) {
            inMark[inMark.length - 1] = null;
        }

        inMark.appendChild(document.createElement('option'));
        for (var i = 0; i < marks.length; i++) {
            if (marks[i][0] == inType) {
                var opt = document.createElement('option');
                opt.value = marks[i][1];
                opt.innerHTML = marks[i][2];
                inMark.appendChild(opt);
            }
        }
    }
</script>
</body>
</html>
