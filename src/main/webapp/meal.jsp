<%--
  Created by IntelliJ IDEA.
  User: r.vakulenko
  Date: 09.03.2016
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meal</title>
</head>
<body>
<h2>Meal</h2>

<form method="POST" action='meal' name="frmAddUser">
    ID : <input type="text" readonly="readonly" name="mealId"
                value="<c:out value="${requestScope.mealId}" />"/> <br/><br/>
    Description : <input
        type="text" name="description"
        value="<c:out value="${requestScope.description}" />"/> <br/><br/>
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${requestScope.calories}" />"/> <br/><br/>
    Date :
        <fmt:parseDate value="${requestScope.dateTime}" var="parsedDate" pattern="dd-MM-yyyy HH:mm"/>
        <input
            type="text" name="dateTime"
            value="<fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDate}"/>" /> <br/><br/>
        <input
            type="submit" value="Submit" />

</form>
</body>
</html>
