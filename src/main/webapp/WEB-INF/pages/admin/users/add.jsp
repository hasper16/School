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

    <link rel="stylesheet" href="<c:url value="/css/admin/bootstrap.min.css"/>" type="text/css" media="screen"/>
    <link rel="stylesheet" href="<c:url value="/css/admin/bootstrap-grid.css"/>" type="text/css" media="screen"/>
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


    <form class="form-horizontal" role="form" method="post" action="/admin/users/add">
        <article class="module width_full">
            <header><h3>Добавить нового пользователя</h3></header>

            <div class="module_content">
                <div class="container-fluid">
                    <div class="row">
                        <fieldset style="width:48%; float:left; margin-right: 3%">
                            <div class="col-md-11">
                                <div class="form-group">
                                    <label for="inputName">Имя</label>
                                    <input class="form-control input-sm" id="inputName" type="text" name="j_name"
                                           value="${j_name}"/>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset style="width:48%; float:left;">
                            <div class="col-md-11">
                                <div class="form-group">
                                    <label for="inputSurname">Фамилия</label>
                                    <input class="form-control input-sm" id="inputSurname" type="text" name="j_surname"
                                           value="${j_surname}"/>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                    <div class="row">
                        <fieldset style="width:48%; float:left; margin-right: 3%">
                            <div class="col-md-11">
                                <div class="form-group">
                                    <label for="inputLogin">Login</label>
                                    <input class="form-control input-sm" id="inputLogin" type="text" name="j_login"
                                           value="${j_login}"/>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset style="width:48%; float:left;">
                            <div class="col-md-11">
                                <div class="form-group">
                                    <label for="inputRole">Роль</label>
                                    <select class="form-control input-sm" id="inputRole"
                                            name="j_role" onchange="disableGroupList()">
                                        <option></option>
                                        <c:forEach items="${j_roles}" var="j_role">
                                            <option><c:out value="${j_role}"/></option>
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
                                    <label for="inputGroup">Класс</label>
                                    <select class="form-control input-sm" id="inputGroup"
                                            name="j_group">
                                        <option></option>
                                        <c:forEach items="${j_groups}" var="j_group">
                                            <option value="${j_group.id}"><c:out value="${j_group.name}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset style="width:48%; float:left;">
                            <div class="col-md-11">
                                <div class="form-group">
                                    <label for="inputBirthday">День рождения</label>
                                    <input class="form-control input-sm" id="inputBirthday" type="date"
                                           name="j_birthday"
                                           value="${j_birthday}"/>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                    <div class="row">
                        <fieldset style="width:48%; float:left; margin-right: 3%">
                            <div class="col-md-11">
                                <div class="form-group">
                                    <label for="inputPhone">Телефон</label>
                                    <input class="form-control input-sm" id="inputPhone" type="tel"
                                           pattern="380[0-9]{2}[0-9]{3}[0-9]{2}[0-9]{2}"
                                           placeholder="380XXXXXXX" name="j_phone"
                                           value="${j_phone}"/>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset style="width:48%; float:left;">
                            <div class="col-md-11">
                                <div class="form-group">
                                    <label for="inputEmail">Email</label>
                                    <input class="form-control input-sm" id="inputEmail" type="email" name="j_email"
                                           value="${j_email}"/>
                                </div>
                            </div>
                        </fieldset>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <footer>
                <div class="submit_link">
                    <input type="submit" value="Добавить" class="alt_btn">
                    <input type="reset" value="Очистить">
                </div>
            </footer>
        </article><!-- end of post new article -->
    </form>
</section>
<script src="/js/validator/jquery.min.js"></script>
<script src="/js/validator/bootstrap.min.js"></script>
<script src="/js/validator/common.js"></script>

<script>
    function disableGroupList() {

        var role = document.getElementById('inputRole').value;
        var group = document.getElementById('inputGroup');
        var name = document.getElementById('inputName');
        var surname = document.getElementById('inputSurname');
        var birthday = document.getElementById('inputBirthday');
        var phone = document.getElementById('inputPhone');
        var email = document.getElementById('inputEmail');
        if (role == "Учитель") {
            group.setAttribute('disabled', 'disabled'),
                name.removeAttribute('disabled'),
                surname.removeAttribute('disabled'),
                birthday.removeAttribute('disabled'),
                phone.removeAttribute('disabled'),
                email.removeAttribute('disabled');
        }
        else if (role == "Администратор") {
            name.setAttribute('disabled', 'disabled'),
                surname.setAttribute('disabled', 'disabled'),
                birthday.setAttribute('disabled', 'disabled'),
                phone.setAttribute('disabled', 'disabled'),
                email.setAttribute('disabled', 'disabled'),
                group.setAttribute('disabled', 'disabled');
        }
        else name.removeAttribute('disabled'),
                surname.removeAttribute('disabled'),
                birthday.removeAttribute('disabled'),
                phone.removeAttribute('disabled'),
                email.removeAttribute('disabled'),
                group.removeAttribute('disabled');

    }
</script>
</body>

</html>
