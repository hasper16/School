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
    <title>Расписание</title>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css" media="screen">
    <link rel="stylesheet" href="<c:url value="/css/layout.css"/>" type="text/css" media="screen"/>


    <script src="<c:url value="/js/hideshow.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/jquery-3.1.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/bootstrap.js"/>" type="text/javascript"></script>

    <script src="<c:url value="/js/jquery-1.5.2.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/hideshow.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/jquery.tablesorter.min.js"/>" type="text/javascript"></script>

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
            <li class="icn_categories"><a href="<c:url value="/admin/reports/studentsRating"/>">Рейтинг учеников</a>
            </li>
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
        <hr/>
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
        <header><h3 class="tabs_involved">Все расписания</h3>
            <ul class="tabs">
                <c:forEach items="${weekDayName}" var="name">
                    <c:if test="${curWeekDay == name}">
                        <li class="active"><a href="/schedule/?weekDay=${name}&page=0"><c:out value="${name}"/></a></li>
                    </c:if>
                    <c:if test="${curWeekDay != name}">
                        <li><a href="/schedule/?weekDay=${name}&page=0"><c:out value="${name}"/></a></li>
                    </c:if>
                </c:forEach>
            </ul>
        </header>

        <div class="tab_container">
            <table class="tablesorter" cellspacing="0">
                <thead>
                <tr>

                    <th>#</th>
                    <th>Время</th>
                    <th>Предмет</th>
                    <th>Класс</th>
                    <th>Учитель</th>
                    <th>Длительность</th>
                    <th>Действие</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:forEach items="${schedules}" var="schedule">
                    <td><c:out value="${schedule.scheduleTimes.lessonNum}"/></td>
                    <td><c:out value="${schedule.scheduleTimes.sdt}"/></td>
                    <td><c:out value="${schedule.subject.name}"/></td>
                    <td><c:out value="${schedule.studentsGroup.name}"/></td>
                    <td><c:out value="${schedule.teacher.name} ${schedule.teacher.surname}"/></td>
                    <td><c:out value="${schedule.scheduleTimes.durationMin}"/></td>
                    <td><input type="image" src="<c:url value="/images/icn_edit.png"/>" data-toggle="modal"
                               data-target="#modalScheduleEdit_${schedule.id}" title="Редактировать"><a
                            href="<c:url value="/schedule/delete?id=${schedule.id}"/>"><img
                            src="<c:url value="/images/icn_trash.png"/>" title="Удалить"></a></td>
                </tr>


                <!-- Modal -->
                <div class="modal fade" id="modalScheduleEdit_${schedule.id}" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Редактировать расписание</h4>
                            </div>
                            <form name="edit_teacher" action="/schedule/edit?id=${schedule.id}" method="POST">
                                <div class="modal-body">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <fieldset style="width:48%; float:left; margin-right: 3%">
                                                <div class="col-md-11">
                                                    <div class="form-group">
                                                        <label for="inputGroup">Класс</label>
                                                        <select class="form-control input-sm" id="inputGroup"
                                                                placeholder="Введите класс"
                                                                name="j_group">
                                                            <option value="${schedule.studentsGroup.id}"><c:out
                                                                    value="${schedule.studentsGroup.name}"/></option>
                                                            <c:forEach items="${groups}" var="group">
                                                                <c:if test="${schedule.studentsGroup.id!=group.id}">
                                                                    <option value="${group.id}"><c:out
                                                                            value="${group.name}"/></option>
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
                                                                placeholder="Введите предмет"
                                                                name="j_subject">
                                                            <option value="${schedule.subject.id}"><c:out
                                                                    value="${schedule.subject.name}"/></option>
                                                            <c:forEach items="${subjects}" var="subject">
                                                                <c:if test="${schedule.subject.id != subject.id}">
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
                                                                placeholder="Введите учителя"
                                                                name="j_teacher">
                                                            <option value="${schedule.teacher.id}"><c:out
                                                                    value="${schedule.teacher.name} ${schedule.teacher.surname}"/></option>
                                                            <c:forEach items="${teachers}" var="teacher">
                                                                <c:if test="${schedule.teacher.id!=teacher.id}">
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
                                                        <label for="inputWeekDay">День недели</label>
                                                        <select class="form-control input-sm" id="inputWeekDay"
                                                                placeholder="Введите день недели"
                                                                name="j_weekday">
                                                            <option>${schedule.weekDayName.name}</option>
                                                            <c:forEach items="${weekdays}" var="weekday">
                                                                <c:if test="${schedule.weekDayName != weekday}">
                                                                <option><c:out value="${weekday}"/></option>
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
                                                        <label for="inputTime">Урок</label>
                                                        <select class="form-control input-sm" id="inputTime"
                                                                placeholder="Введите урок"
                                                                name="j_lessonNum">
                                                            <option value="${schedule.scheduleTimes.id}"><c:out
                                                                    value="${schedule.scheduleTimes.lessonNum}) ${schedule.scheduleTimes.sdt}"/></option>
                                                            <c:forEach items="${times}" var="time">
                                                                <c:if test="${time.id != schedule.scheduleTimes.id}">
                                                                    <option value="${time.id}"><c:out
                                                                            value="${time.lessonNum}) ${time.sdt}"/></option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                            </fieldset>
                                            <fieldset style="width:48%; float:left; ">
                                                <div class="col-md-11">
                                                    <div class="form-group">
                                                        <label for="inputDuration">Длительность (мин)</label>
                                                        <input type="number" name="j_duration" id="inputDuration"
                                                               value="${schedule.scheduleTimes.durationMin}">
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </div>
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
        </div><!-- end of .tab_container -->

        <!--Pages -->
        <ul class="pagination">
            <c:if test="${curPage == 0}">
                <li class="disabled"><a href="/schedule/?weekDay=${curWeekDay}&page=0">&laquo;</a></li>
            </c:if>
            <c:if test="${curPage > 0}">
                <li><a href="/schedule/?weekDay=${curWeekDay}&page=0">&laquo;</a></li>
            </c:if>

            <c:if test="${curPage <= 3}"><c:set var="begin" value="0"/></c:if>
            <c:if test="${curPage > 3}"><c:set var="begin" value="${curPage-3}"/></c:if>

            <c:forEach var="i" begin="${begin}" end="${maxCountScheduleRows}">
                <c:if test="${i <= curPage+3}">
                    <c:if test="${curPage == i}">
                        <li class="active"><a href="/schedule/?weekDay=${curWeekDay}&page=${i}"><c:out value="${i+1}"/>
                            <span class="sr-only">(current)</span></a></li>
                    </c:if>
                    <c:if test="${curPage != i}">
                        <li><a href="/schedule/?weekDay=${curWeekDay}&page=${i}"><c:out value="${i+1}"/> <span
                                class="sr-only">(current)</span></a></li>
                    </c:if>
                </c:if>
            </c:forEach>

            <c:if test="${curPage == maxCountScheduleRows}">
                <li class="disabled"><a href="/schedule/?weekDay=${curWeekDay}&page=${maxCountScheduleRows}">&raquo;</a>
                </li>
            </c:if>
            <c:if test="${curPage < maxCountScheduleRows}">
                <li><a href="/schedule/?weekDay=${curWeekDay}&page=${maxCountScheduleRows}">&raquo;</a></li>
            </c:if>
        </ul>
        <!--end Pages -->

    </article><!-- end of content manager article -->

</section>
<script src="/js/validator/jquery.min.js"></script>
<script src="/js/validator/bootstrap.min.js"></script>
<script src="/js/validator/common.js"></script>
</body>

</html>