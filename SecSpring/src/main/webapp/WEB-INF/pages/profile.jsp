<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shool</title>

    <link rel="stylesheet" href="/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="/css/assets/ie10-viewport-bug-workaround.css" type="text/css" />
    <link rel="stylesheet" href="/css/jumbotron.css" type="text/css" />
    <link rel="stylesheet" href="/css/footer.css" type="text/css" />
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">School</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <!-- <li class="active"><a href="#">Ссылка</a></li>-->
                <li><a href="/homework">Домашние задания</a></li>
                <li><a href="/schedule">Расписание уроков</a></li>
                <li><a href="/journal">Журнал</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right"> <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${login} <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="/profile">Профиль</a></li>
                </ul>
            </li></ul>
            <c:url value="/logout" var="logoutUrl" />
            <form class="navbar-form navbar-right" action="${logoutUrl}">
                <button type="submit" class="btn btn-primary">Выйти</button>
            </form>
        </div>

    </div>
</nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <h1>Profile</h1>
 <div class="container">
     <div class="row">
     <div class="col-md-5">
         <form role="form" action="/profile" method="POST">
          <div class="form-studentsGroup">
            <div class="row">
     <div class="col-md-8">
                <div class="col-md-10">
            <label for="InputLogin">Login</label>
                    <input type="text" class="form-control" id="InputLogin" name="j_login" value="${login}">
         </div>
                </div>
     </div>
     <div class="row">
     <div class="col-md-8">
                <div class="col-md-10">
                    <label for="InputName">Имя</label>
                    <input type="text" class="form-control" id="InputName" name="j_name" value="${name}">
         </div>

                </div>
     </div>
     <div class="row">
     <div class="col-md-8">
                <div class="col-md-10"><label for="InputSurname">Фамилия</label>
                    <input type="text" class="form-control" id="InputSurname" name="j_surname" value="${surname}">
         </div>

                </div>
     </div>
     <div class="row">
     <div class="col-md-8">
                <div class="col-md-10">
                    <label for="InputBirthday">Дата рождения</label>
                    <input type="date" class="form-control" id="InputBirthday" name="j_birthday" value="${birthday}">
         </div>

                </div>
     </div>
     <div class="row">
     <div class="col-md-8">
                <div class="col-md-10">
                    <label for="InputEmail">Email</label>
                    <input type="email" class="form-control" id="InputEmail" name="j_email" value="${email}">
         </div>

                </div>
     </div>
     <div class="row">
     <div class="col-md-8">
                <div class="col-md-10">
         <label for="InputPhone">Телефон</label>
                    <input type="tel" class="form-control" id="InputPhone" name="j_phone" value="${phone}">
         </div>

                </div>
     </div>
              
              <div class="row"> 
     <div class="col-md-8">
                <div class="col-md-10 pudbtn">
                   
         <button type="submit" class="btn btn-success" >Применить</button>
                    
         </div>

                </div>
     </div>
         </div>
     </form>
         </div>
        
     <div class="col-md-7">
          <div class="row"><img src="https://pbs.twimg.com/profile_images/378800000232458460/b5d097e08c63a6530e9751bcb0a13a57_400x400.png" alt="..." class="img-thumbnail"></div>
         <div class="row"> <button type="button" class="btn btn-link">Обновить фото</button></div></div>
     </div>
    
            </div>             
                  
            
          
        
    
            </div>
            </div>
 
    
    


        

    <div id="footer">
      <div class="container">
        <p class="text-muted">Create by H@sper</p>
      </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>
        window.jQuery || document.write(' < script src = "../../assets/js/vendor/jquery.min.js" > < \/script>')
    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>

</html>