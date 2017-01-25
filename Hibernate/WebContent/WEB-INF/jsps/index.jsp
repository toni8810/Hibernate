<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<h1 style="text-align: center; color: fuchsia;">Hello Punk</h1>
	<form method="post" action="uploadImage" enctype="multipart/form-data" >
	
		<input name="username" placeholder="Your name" type="text"/>
		<input name="imageName" placeholder="The name of your image" type="text" />
		<input type="file" name="image"/>
		<input type="submit" value="Upload" />
	
	</form>
	<c:choose>
		<c:when test="${not empty users}">
			<ol>
				<c:forEach var="user" items="${users}">
					<li>${user.username}</li>
				</c:forEach>
			</ol>
		</c:when>
		<c:otherwise>
			<a href="allUsers">Show all users</a>
		</c:otherwise>
	</c:choose>
	<form action="getImages" method="get">
		<input placeholder="Your name" name="name" type="text" />
		<input type="submit" value="Show Files"/>
	</form>
	<c:if test="${not empty images}">
		<c:forEach var="image" items="${images}">
			<div style="width: 450px; margin: auto">
				${image.imageName}
				
				<img src="<c:url value="${image.imageURL}" />" width="400" />
			</div>
		</c:forEach>
	</c:if>
</body>
</html>