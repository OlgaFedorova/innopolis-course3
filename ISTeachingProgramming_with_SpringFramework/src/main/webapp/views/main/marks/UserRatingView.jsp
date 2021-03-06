<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Рейтинг пользователей</title>
</head>
<body>
<table border="1">
    <tr>
        <td>Пользователь</td>
        <td>Балл</td>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="status">
        <tr valign="top">
            <td>${user.getUsername()}</td>
            <td>${user.getMark()}</td>
        </tr>
    </c:forEach>
</table>
<p><a href="${pageContext.servletContext.contextPath}/index">НА ГЛАВНУЮ</a></p>
</body>
</html>