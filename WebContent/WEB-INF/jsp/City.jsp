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
<title>CITTA' DI ${codiceStato} </title>
</head>
<body>
 
    <div align="center" style="margin-top: 50px;">
    	<h6><a href=country?continente=${continente}> INDIETRO</a><br></h6>
    	<h2><font color=green> CITTA' DELLO STATO CON CODICE ${codiceStato} </font> </h2>
    	
    	<FORM ACTION="form">
    		<input type="hidden" name="id" value="-1">
	   	 	<input type="submit" value="AGGIUNGI CITTA'" style="width:320px;">
	   	</FORM>
	   	<br><br>
	   	
 		<%-- FOR EACH IN JSTL --%>
		<c:forEach items="${lista}" var="x">
	   	 	
	   	 		<h3>
	   	 			<font color=blue> ${x.nome } - POP. : ${x.popolazione} - ID: ${x.id} </font>
	   	 			<FORM ACTION="delete">
	   	 				<input type="hidden" name="id" value=${x.id}>
	   	 				<input type="submit" value="CANCELLA">
	   	 			</FORM>
	   	 			<FORM ACTION="form">
	   	 				<input type="hidden" name="id" value=${x.id}>
	   	 				<input type="submit" value="MODIFICA">
	   	 			</FORM>
	   	 		</h3> 
	   	   
		</c:forEach>
    </div>
 
</body>
</html>