<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>File Upload</title>
</head>
<body>
<h1>File Upload</h1>
<br/>
<jsp:useBean id="postFiles" scope="request" class="resource.PostFiles"/>
<c:if test="${not empty postFiles}">
        <ul>
            <li>file id: <c:out value="${postFiles.fileId}"/></li>
            <li>post id: <c:out value="${postFiles.postId}"/></li>
    
            <c:choose>
                <c:when test="${postFiles.hasFile()}">
                    <li>file:
                        <ul>
                                <li>File type: <c:out value="${postFiles.fileMediaType}"/> </li>
                                    <li>size: <c:out value="${postFiles.fileSize}"/> </li>
                                    <li>image: <br/>
                                        <img src="<c:url value="postFiles/loadPostFile">
                                <c:param name="file_id" value="${postFiles.fileId}"/></c:url>" alt=""/>
                                </li>
                            </ul>
                    </li>
                </c:when>
    
                <c:otherwise><li>file: not available</li></c:otherwise>
            </c:choose>
        </ul>
    </c:if>
</body>
</html>