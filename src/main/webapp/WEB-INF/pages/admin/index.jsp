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
    <title>Учительская</title>

    <link rel="stylesheet" href="<c:url value="/css/layout.css"/>" type="text/css" media="screen" />

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


    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
    google.charts.load('current', {packages: ['corechart', 'line']});
    google.charts.setOnLoadCallback(drawBasic);

    function drawBasic() {

        var data = new google.visualization.DataTable();
        data.addColumn('date', 'X');
        data.addColumn('number', '1');
        data.addColumn('number', '2');
        data.addColumn('number', '3');
        data.addColumn('number', '4');
        data.addColumn('number', '5');
        data.addColumn('number', '6');
        data.addColumn('number', '7');
        data.addColumn('number', '8');
        data.addColumn('number', '9');
        data.addColumn('number', '10');
        data.addColumn('number', '11');
        data.addColumn('number', '12');

        data.addRows([<c:forEach items="${statistic}" var="t">
            [new Date("<c:out value="${t.reportDate}"/>"),
                <c:out value="${t.countM1}"/>,
                <c:out value="${t.countM2}"/>,
                <c:out value="${t.countM3}"/>,
                <c:out value="${t.countM4}"/>,
                <c:out value="${t.countM5}"/>,
                <c:out value="${t.countM6}"/>,
                <c:out value="${t.countM7}"/>,
                <c:out value="${t.countM8}"/>,
                <c:out value="${t.countM9}"/>,
                <c:out value="${t.countM10}"/>,
                <c:out value="${t.countM11}"/>,
                <c:out value="${t.countM12}"/>
            ],
                </c:forEach>
        ]);

        var options = {
            title: 'Статистика по оценкам',
            hAxis: {
                title: 'Дата'
            },
            vAxis: {
                title: 'Кол-во'
            }
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

        chart.draw(data, options);
    }
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
        <a class="logout_user" href="<c:url value="/logout"/>"  title="Logout">Logout</a>
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

    <article class="module width_full">
        <header><h3>Статистика</h3></header>
        <div class="module_content">
            <article class="stats_graph">
                <div id="chart_div" style="height: 60%;"></div>
            </article>

            <article class="stats_overview">
                <div class="overview_today">
                    <p class="overview_day">Студентов</p>
                    <p class="overview_count">${countStudents}</p>
                    <p class="overview_day">Домашних заданий</p>
                    <p class="overview_count">${countHomeWorks}</p>

                </div>
                <div class="overview_previous">
                    <p class="overview_day">Учителей</p>
                    <p class="overview_count">${countTeachers}</p>
                    <p class="overview_day">Оценок</p>
                    <p class="overview_count">${countMarks}</p>

                </div>
            </article>
            <div class="clear"></div>
        </div>
    </article><!-- end of stats article -->

</section>
</body>

</html>
