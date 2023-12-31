<%@ page import="com.example.webcatalog2.Adapters.DataAdapter" %>
<%@ page import="com.example.webcatalog2.Model.Article" %>
<%@ page import="com.example.webcatalog2.db.User" %><%--
  Created by IntelliJ IDEA.
  User: yaidf
  Date: 15.12.2023
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        <jsp:include page="css/read.css"/>
    </style>
</head>
<body>
<a href="<%=request.getContextPath()%>/index"
   class="btn btn-primary">назад</a>
<div class="data">
    <%
        Article article = DataAdapter.getArticle(request.getParameter("id"));%>
    <img src="img/pdfLogo.png" class="logopdf">
    <h2>Название статьи: <%=article.getTitle()%></h2>
    <h2 id="author">Автор статьи: <%=article.getAuthor()%></h2>
    <h2 class="date">Год: <%=article.getDate()%></h2>
    <h2 class="keyWords">Ключевые слова: <%=article.getKeyWords()%></h2>
    <h2 class="keyWords">Владелец: <%=article.getOwner()%>
    <a href="<%=request.getContextPath()%>/download?id=<%=request.getParameter("id")%>"
       class="btn btn-primary">скачать</a>
        <% if (article.getOwner().equals(((User)request.getSession().getAttribute("User")).getUsername())){%>
        <a href="<%=request.getContextPath()%>/del?id=<%=request.getParameter("id")%>"
           class="btn btn-primary">уничтожить</a>
        <%}%>
</div>
<script>
    <jsp:include page="js/index.js"/>
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>
</html>
