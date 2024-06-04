<%--
  Created by IntelliJ IDEA.
  User: Володимир Жительний
  Date: 21.05.2024
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession session1 = request.getSession();
    Long id = (Long) session.getAttribute("id");
%>

<html>
<head>
    <title>Adding person to queue</title>

</head>
<body>

    <form action="queueController" method="post">
        <label for="nameField">Введіть своє ім'я</label>
        <input type="text" id="nameField" name="userName" placeholder="Введіть текст" required>

        <input type="hidden" name="id" value="<%=id%>">
        <input type="hidden" name="command" value="addUser">

        <input type="submit" value="Підвердити">
    </form>
</body>
</html>
