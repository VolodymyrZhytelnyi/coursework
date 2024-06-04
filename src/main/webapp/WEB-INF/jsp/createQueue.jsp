<%--
  Created by IntelliJ IDEA.
  User: Володимир Жительний
  Date: 19.05.2024
  Time: 0:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="queueController" method="post">
        <label for="nameField">Введіть назву черги</label>
        <input type="text" id="nameField" name="name" placeholder="Введіть текст" required>


        <input type="hidden" name="command" value="create">

        <input type="submit" value="Підвердити">
    </form>
</body>
</html>
