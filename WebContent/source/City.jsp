<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
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
    	<h6><a href=/CountryCity/ServletContinenti?continente=${continente}> INDIETRO</a><br></h6>
    	<h2><font color=green> CITTA' DELLO STATO CON CODICE ${codiceStato} </font> </h2>
    	
    	<FORM ACTION="/CountryCity/ServletTransfer">
    		<input type="hidden" name="id" value="-1">
	   	 	<input type="submit" value="AGGIUNGI CITTA'" style="width:320px;">
	   	</FORM>
	   	<br><br>
	   	
 		<%-- FOR EACH IN JSTL --%>
		<c:forEach items="${lista}" var="x">
	   	 	
	   	 		<h3>
	   	 			<font color=blue> ${x.nome } - POP. : ${x.popolazione} - ID: ${x.id} </font>
	   	 			<form ACTION="/CountryCity/ServletDelCity">
	   	 				<input type="hidden" name="id" value=${x.id}>
	   	 				<input type="submit" value="CANCELLA">
	   	 			</form>
	   	 			<form ACTION="/CountryCity/ServletTransfer">
	   	 				<input type="hidden" name="id" value=${x.id}>
	   	 				<input type="submit" value="MODIFICA">
	   	 			</form>
	   	 		</h3> 
	   	   
		</c:forEach>
    </div>
 
</body>
</html>