<%--
  Created by IntelliJ IDEA.
  User: AYŞE KILIÇ
  Date: 24.05.2023
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List files</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>

  <button onclick="getimages()">Click</button>
  <script>

    getimages = () => {
      $.ajax({
        method: "GET",
        url: "${pageContext.request.contextPath}/postFiles/getAllPostFiles",
        success: function (response) {

          let data = JSON.parse(response).data;
          data.forEach(elem => {
              let img = document.createElement('img');

              img.src = 'data:image/jpeg;base64,' + elem.base64;
              document.body.appendChild(img);

          });

        }
      })
    }
  </script>
</body>
</html>
