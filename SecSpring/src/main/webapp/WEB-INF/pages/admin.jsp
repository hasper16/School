<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <c:url value="/j_spring_security_check" var="loginUrl" />
    <h1>Secret page for admins only!</h1>



    <c:url value="/logout" var="logoutUrl" />
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a>"</p>
</div>
</body>
</html>
