<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 10/11/2024
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body>
<div>
    <h2 class="h2 text-center">HOME</h2>
    <a href="addProduct.jsp" class="btn btn-primary">Add Product</a>
    <form action="controller" method="GET">
        <button type="submit" class="btn btn-success">Danh sách sản phẩm</button>
    </form>
</div>
</body>
</html>
