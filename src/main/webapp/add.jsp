<%@ page import="com.example.webcatalog2.Adapters.DataAdapter" %>
<%@ page import="com.example.webcatalog2.Model.Article" %><%--
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
    <img src="img/pdfLogo.png" class="logopdf">
    <form class="was-validated" action="add" method="post" enctype="multipart/form-data">
        <div class="col-md-4">
            <label for="titleInput" class="form-label">Название</label>
            <input type="text" class="form-control" id="titleInput" value="" name="title" required>
            <div class="valid-feedback">
                Looks good!
            </div>
        </div>
        <div class="col-md-4">
            <label for="authorInput" class="form-label">Автор</label>
            <input type="text" class="form-control" id="authorInput" value="" name="author" required>
            <div class="valid-feedback">
                Looks good!
            </div>
        </div>
        <div class="col-md-4">
            <label for="keyInput" class="form-label">Ключевые слова</label>
            <input type="text" class="form-control" id="keyInput" name="key" value="">
            <div class="valid-feedback">
                Looks good!
            </div>
        </div>
        <div class="col-md-4">
            <label for="dateInput" class="form-label">Год</label>
            <input type="text" class="form-control" id="dateInput" name="date" value="">
            <div class="valid-feedback">
                Looks good!
            </div>
        </div>

        <div class="mb-3">
            <input type="file" name="file" class="form-control" aria-label="file example" required>
            <div class="invalid-feedback">Загрузите файл</div>
        </div>

        <div class="mb-3">
            <button class="btn btn-primary" type="submit">Загрузить</button>
        </div>
    </form>
</div>
<script>
    <jsp:include page="js/add.js"/>
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>
</html>
