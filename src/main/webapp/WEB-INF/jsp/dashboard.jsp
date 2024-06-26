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
<h1>Hello, <%=request.getRemoteUser()%></h1>

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
            <td>
                <form action="queueController" method="post">
                    <input type="hidden" name="command" value="addUser">
                    <input type="submit" value="Take a place">
                    <input type="hidden" name="id" value="${queueModel.id}">
                </form>
            </td>
            <td>
                <form action="queueController" method="post">
                    <input type="hidden" name="command" value="delete">
                    <input type="submit" value="delete">
                    <input type="hidden" name="id" value="${queueModel.id}">
                </form>
            </td>
            <td>
                <form action="queueController" method="post">
                    <input type="hidden" name="command" value="nextUser">
                    <input type="submit" value="NEXT">
                    <input type="hidden" name="id" value="${queueModel.id}">
                </form>
            </td>
            <td>
                <form action="queueController" method="post">
                    <input type="hidden" name="command" value="writeControl">
                    <input type="hidden" name="value" value="close">
                    <input type="submit" value="Close queue">
                    <input type="hidden" name="id" value="${queueModel.id}">
                </form>

            </td>
            <td>
                <form action="queueController" method="post">
                    <input type="hidden" name="command" value="writeControl">
                    <input type="hidden" name="value" value="open">
                    <input type="submit" value="Open queue">
                    <input type="hidden" name="id" value="${queueModel.id}">
                </form>

            </td>
            <td>
                <form action="queueController" method="post">
                    <input type="hidden" name="command" value="deleteUserByName">
                    <input type="text" name="name" placeholder="Enter the name...">
                    <input type="submit" value="Delete user by name">
                    <input type="hidden" name="id" value="${queueModel.id}">
                </form>

            </td>
        </tr>

    </c:forEach>
    <form action="queueController" method="post">
        <input type="hidden" name="command" value="create">
        <input type="submit" value="create">
    </form>

<%--    <form action=""></form>--%>


    </tbody>
</table>
</body>
</html>
