<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
body {
    background-image:
        url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INDEX - CONTINENT</title>
</head>
<body>
 
    <div align="center" style="margin-top: 50px;">
    
 		<%-- FOR EACH IN JSTL --%>
		<c:forEach items="${lista}" var="x">
	   	 <p> <h3><font color=blue> <a href=/CountryCity/ServletContinenti?continente=${x.continente}> ${x.continente } </a> </font></h3></p> <br>
		</c:forEach>
    </div>
 
</body>
</html>