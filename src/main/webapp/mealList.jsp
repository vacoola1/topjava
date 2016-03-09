<%--
  Created by IntelliJ IDEA.
  User: r.vakulenko
  Date: 09.03.2016
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal List</title>
</head>
<body>
<h2>Meal List</h2>

<table>
    <tbody>
    <tr>
        <th>data</th>
        <th>description</th>
        <th>calories</th>
        <th>exceed</th>
    </tr>
    <c:forEach items="${requestScope.mealList}" var="meal">

        <c:choose>
            <c:when test="${meal.isExceed() eq true}">
                <c:set var="mealsColor" value="red"/>
            </c:when>
            <c:otherwise>
                <c:set var="mealsColor" value="aqua"/>
            </c:otherwise>
        </c:choose>

        <tr bgcolor=${mealsColor}>
            <td><c:out value="${meal.getDateTime().toLocalDate()}"></c:out></td>
            <td><c:out value="${meal.getDescription()}"></c:out></td>
            <td><c:out value="${meal.getCalories()}"></c:out></td>
            <td><c:out value="${meal.isExceed()}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>