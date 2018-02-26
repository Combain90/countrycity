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
<title>STATI DI ${continente} </title>
</head>
<body>
 
    <div align="center" style="margin-top: 50px;">
    	<h6><a href=home> INDIETRO</a><br></h6>
    	<h2><font color=green> STATI DI ${continente} </font> </h2>
    	
 		<%-- FOR EACH IN JSTL --%>
		<c:forEach items="${lista}" var="x">
	   	 <p> <h3><font color=blue> <a href=city?codice=${x.codice}&continente=${continente}> ${x.nome} </a> </font></h3> </p>
		</c:forEach>
    </div>
 
</body>
</html>