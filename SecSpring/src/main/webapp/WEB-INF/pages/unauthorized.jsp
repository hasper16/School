<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>School</title>

    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css" />
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="container">
    <div class="page-header">
        <h1>Access denied for ${login}!</h1>
    </div>
    <c:url value="/logout" var="logoutUrl" />
    <p class="lead">Click to logout: <a href="${logoutUrl}">Logout</a></p>

</div>
<div class="footer">
    <div class="container">
        <p class="text-muted">Create by H@sper</p>
    </div>
</div>

</body></html>


