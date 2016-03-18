<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h3>User list</h3>
<jsp:useBean id="loggedUser" type="ru.javawebinar.topjava.LoggedUser" scope="request"/>
Logged user "${loggedUser.id()}"
</body>
</html>
