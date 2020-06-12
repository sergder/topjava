<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        .red {
            color: red;
        }
    </style>
</head>
<body>
<h2>Meals</h2>
<table>
    <c:forEach items="${meals}" var="meal">
        <tr <c:if test="${meal.isExcess()}">class="red" </c:if>>
            <td>${meal.getDateTimeFormatted()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
