<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <h1>Secret page for admins only!</h1>
        <iframe width=\"800\" height=\"600\"
                src=\"https://app.powerbi.com/view?r=eyJrIjoiNTY0OTYyNmEtYzQ4Yy00M2Y2LThlNDktYjhhYTZmMTgxYWMxIiwidCI6IjUyYjM2OTFlLWVmNDYtNGU0Yy05NTM2LWRkMzRkOTU1MDFlYyIsImMiOjl9\"
                frameborder=\"0\" allowFullScreen=\"true\"></iframe>


    <c:url value="/logout" var="logoutUrl" />
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a>"</p>
</div>
</body>
</html>
