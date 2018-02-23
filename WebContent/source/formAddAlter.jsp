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
<title>INSERISCI/MODIFICA I DATI </title>
</head>
<body>
 
    <div align="center" style="margin-top: 50px;">
    	
    	<h4><a href=/CountryCity/ServletIniziale> HOME</a><br></h4>
    	
    	
    	
    	<FORM ACTION="/CountryCity/ServletAddAlterCity">
    		<input type="hidden" name="id" value=${id}>
    		INSERISCI NOME: <input type="text" name="nome" value=${nome}> <br>
    		INSERISCI IL CODICE DELLO STATO: 
    		<select name="codiceStato">
    			
    			<c:if test="${not empty codiceStato}">
    				<option value=${codiceStato}> ${codiceStato} </option>
				</c:if>
    			
				<c:forEach items="${allCountryCode}" var="x">
 	   				<option value=${x}> ${x} </option>
		    	</c:forEach>
		    </select>
    		<br>
    		INSERISCI IL DISTRETTO: <input type="text" name="distretto" value=${distretto}> <br>
    		INSERISCI IL NUMERO DI POPOLANTI: <input type="number" name="popolazione" value=${popolazione}> <br>
	   	 	<input type="submit" value="INSERISCI/MODIFICA LA CITTA'">
	   	</FORM>
	   	
    </div>
 
</body>
</html>