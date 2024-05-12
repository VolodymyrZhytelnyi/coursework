<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Володимир Жительний
  Date: 03.05.2024
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Queue</title>
</head>
<body>
<table border ="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>People</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="queueModel" items="${queueModels}">
        <tr>
            <td><c:out value="${queueModel.name}"/></td>
            <td>
                <c:forEach var="user" items="${queueModel.users}" varStatus="loop">
                    <c:out value="${user}"/><c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>



    </tbody>
</table>
</body>
</html>
