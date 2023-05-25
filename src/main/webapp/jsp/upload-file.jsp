<%--
  Created by IntelliJ IDEA.
  User: AYŞE KILIÇ
  Date: 20.04.2023
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>File Upload</title>
 <link
         href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
         rel="stylesheet"
         integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
         crossorigin="anonymous">
</head>
<body>

<div class="container col-lg-6">
 <h1 class="text-center">File Upload to Database</h1>
 <div class="card">
  <div class="card-body">
   <form method="post" class="form-group" enctype= "multipart/form-data" action="${pageContext.request.contextPath}/postFiles/upload">
    <div class="form-group">
     <label for="postId">Post Id: </label>
     <input id="postId" type="text" class="form-control" name="postId" size="50" />
    </div>

    <div class="form-group">
     <label for="file">File:</label>
     <input id="file" type="file" name="file" />
    </div>
    <input type="submit" value="Save" class="btn btn-success">
   </form>
  </div>
 </div>
</div>
</body>
</html>