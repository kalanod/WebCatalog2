<%@ page import="com.example.webcatalog2.Adapters.DataAdapter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webcatalog2.Model.Article" %>
<%@ page import="com.example.webcatalog2.Adapters.UserAdapter" %>
<%@ page import="com.example.webcatalog2.db.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Articles</title>
    <style>
        <jsp:include page="css/index.css"/>
    </style>
</head>
<body>
<div class="modal modal-lg fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Поиск статей</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="search-collomn">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="authorInput" placeholder="name@example.com">
                        <label for="authorInput">Автор:</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="titleInput" placeholder="name@example.com">
                        <label for="titleInput">Название:</label>
                    </div>
                </div>
                <div class="search-collomn">
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="keyWordsInput">
                        <label for="keyWordsInput">Ключевые слова:</label>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="dateInput">
                        <label for="dateInput">Год:</label>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                <button type="button" id="searchBtn" class="btn btn-primary">Поиск</button>
            </div>
        </div>
    </div>
</div>
<%
    UserAdapter userAdapter = new UserAdapter();
    if (userAdapter.isAuthorized((User) request.getSession().getAttribute("User"))) {%>
<div class="controls">
    <!-- Modal -->

    <button type="button" class="btn btn-primary control-btn" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Поиск
    </button>
    <a href="<%=request.getContextPath()%>/add" type="button" class="btn btn-primary control-btn">Добавить статью</a>
</div>
<div class="browser">
    <%
        int pageN;
        String author;
        String key;
        String title;
        String date;
        if (request.getParameter("page") != null) {
            pageN = Integer.parseInt(request.getParameter("page"));
        } else {
            pageN = 0;
        }
        if (request.getParameter("title") != null) {
            title = request.getParameter("title");
        } else {
            title = "all";
        }
        if (request.getParameter("author") != null) {
            author = request.getParameter("author");
        } else {
            author = "all";
        }
        if (request.getParameter("date") != null) {
            date = request.getParameter("date");
        } else {
            date = "all";
        }
        if (request.getParameter("key") != null) {
            key = request.getParameter("key");
        } else {
            key = "all";
        }
        ArrayList<ArrayList<Article>> lines = DataAdapter.getLines(String.valueOf(pageN), title, author, key, date);
        for (int i = 0; i < Math.min(5, lines.size()); i++) {%>
    <div class="line">
        <%for (int j = 0; j < Math.min(3, lines.get(i).size()); j++) {%>

        <div class="card" style="width: 18rem;">
            <a href="<%=request.getContextPath()%>/read?id=<%=lines.get(i).get(j).getId()%>">
                <img src="img/pdfLogo.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><%=lines.get(i).get(j).getAuthor()%>
                    </h5>

                    <p class="card-text"><%=lines.get(i).get(j).getTitle()%>
                    </p>

                    <a href="<%=request.getContextPath()%>/download?id=<%=lines.get(i).get(j).getId()%>"
                       class="btn btn-primary">скачать</a>
                </div>
            </a>
        </div>

        <%}%>
    </div>
    <%}%>
</div>
<nav aria-label="Page navigation example">
    <ul class="pagination  justify-content-center">
        <%
            int itemsOnPage = 3;
            int i = 0;
            if (DataAdapter.getLinesCount(String.valueOf(pageN), title, author, key, date) > itemsOnPage) {
                if (pageN != 0) { %>
        <li class="page-item"><a class="page-link"
                                 href="<%=request.getContextPath()+"/index?page="+(pageN-1)+
                                 "&title="+title+"&author="+author+"&key="+key+"&date="+date%>">Previous</a>
        </li>
        <%
            }
            for (i = Math.max(0, pageN * itemsOnPage - 5 * itemsOnPage);
                 i < DataAdapter.getLinesCount(String.valueOf(pageN), title, author, key, date) && i < 9 * itemsOnPage + Math.max(0, pageN * itemsOnPage - 5 * itemsOnPage);
                 i += itemsOnPage) {
                if (i / itemsOnPage != pageN) {
        %>
        <li class="page-item"><a class="page-link"
                                 href="<%=request.getContextPath()+"/index?page="+i/itemsOnPage+"&title="+title+"&author="+author+"&key="+key+"&date="+date%>"><%=i / itemsOnPage%>
        </a></li>
        <%} else {%>
        <li class="page-item active" aria-current="page">
            <a class="page-link"
               href="<%=request.getContextPath()+"/index?page="+i/itemsOnPage+"&title="+title+"&author="+author+"&key="+key+"&date="+date%>"><%=i / itemsOnPage %>
            </a>
        </li>
        <%
                    }
                }
            }
        %>


        <%if ((pageN + 1) * itemsOnPage < DataAdapter.getLinesCount(String.valueOf(pageN), title, author, key, date)) {%>
        <li class="page-item"><a class="page-link"
                                 href="<%=request.getContextPath()+"/index?page="+(pageN+1)+"&title="+title+"&author="+author+"&key="+key+"&date="+date%>">Next</a>
        </li>
        <%}%>
    </ul>
</nav>
<%} else {%>

<form class="row g-3" action="${pageContext.request.contextPath}/login" method="post">
    <div class="col-auto">
        <input type="text" class="form-control" name="username" id="usenameInput" placeholder="">
        <label for="usenameInput">Username</label>
    </div>
    <div class="col-auto">
        <input type="text" class="form-control" name="password" id="passwordInput" placeholder="">
        <label for="passwordInput">password</label>
    </div>
    <div class="col-auto">
        <button type="submit" class="login close btn btn-primary">Добавить</button>
    </div>
</form>
<%}%>
<script src="js/jquery-3.7.1.min.js"></script>
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