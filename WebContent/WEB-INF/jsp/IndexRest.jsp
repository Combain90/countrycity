<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<style>
    body {
        background-image:url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
    }

    button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    }

    table {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
    }

    td,th {
    border: 1px solid #ddd;
    padding: 8px;
    }

    tr:nth-child(even){background-color: #f2f2f2;}

    tr:hover {background-color: #ddd;}

    th {
    padding-top: 12px;
    padding-bottom: 12px;
    background-color: #4CAF50;
    color: white;
    }
    
    div {
    padding: 8px;
    }

    h1 {
    text-align: center;
    text-transform: uppercase;
    color: #4CAF50;
    }

    p {
    text-indent: 50px;
    text-align: justify;
    letter-spacing: 3px;
    border: 1px solid gray;
    }
</style>
<title>APPLICATION </title>

<script>

    $(document).ready(function(){
        $("#indexh1").mouseover(function(){
            $(".butt").slideDown("slow");
            $("#tbC").empty();
            eliminaDati();
        });

        $("#buttC").click(function(){
            var con=$("#continent");
            $.getJSON("http://localhost:8080/CountryCity/api/countries/continents",function(result){
                con.children().append("<tr><th align='center'><b> CONTINENTI </b></th></tr>");
                $.each(result,function(i,field){
                    con.children().append("<tr><td align='center' id='rigaC"+i+"' onclick='rigaCFunction(this.id)'>"+ field +"</td></tr>"); 
                });
            });
            $(".butt").slideUp("slow");
        }); 

        $("#buttL").click(function(){
            var lang=$("#lang");
            $.getJSON("http://localhost:8080/CountryCity/api/countries/list",function(result){
    
                lang.append("SCEGLI UNO STATO: <select id='mioSel'>");
                $.each(result,function(i,field){
                    lang.children().append("<option value="+ field.codice +" >"+ field.nome +"</option>");
                });
                lang.append("</select>");
                lang.append("<br><button type='button' onclick='langFunction()' >ANALIZZA</button>");
            });
            $(".butt").slideUp("slow");
            eliminaDati();
        });            
    });

    function langFunction(){
        var lang=$("#langT");
        var y=$("#mioSel").val();
        lang.empty();
        $.getJSON("http://localhost:8080/CountryCity/api/countries/languages/"+y+"/list",function(result){
            lang.append("<table border='1px'><tr><th align='center'><b>LINGUE IN "+y+"</b></th></tr>"); 
            
            $.each(result,function(i,lingua){
                lang.children().append("<tr><td align='center'>"+ lingua +"</td></tr>");
            });

            lang.append("</table><br>"); 
        });
    }

    function rigaCFunction(id){  /*--PASSO L'ID DI UNA RIGA DEI CONTINENTI. LO FACCIO APPOSTA PER IMPARARE JQUERY. POTEVO PASSARE IL NOME DEL CONTINENTE PER FACILITARMI LA VITA  */
        var coun=$("#country");
        eliminaDati()
        $.getJSON("http://localhost:8080/CountryCity/api/countries/"+$("#"+id).text()+"/list",function(result){
            coun.append("<table border='1px'><tr><th align='center' colspan='2'><b>"+$("#"+id).text()+"</b></th></tr>"); 
            coun.children().append("<tr><td align='center'><u>NOME</u></td><td align='center'><u>REGIONE</u></td></tr>");
            var msg='----ERRORE: Clicca sul nome!----';
            $.each(result,function(i,paese){
                coun.children().append("<tr><td align='center' onclick='rigaSFunction(\""+paese.codice+"\")'>"+ paese.nome +"</td><td align='center' onclick='functionAlert(\""+msg+"\")' >"+ paese.regione +"</td></tr>");
            });
            coun.append("</table><br>"); 
        });
    }

    function rigaSFunction(code){ /* NON PASSO L'ID DI UNA RIGA MA DIRETTAMENTE IL CODICE DELLO STATO: MI SERVE PER AVERE LA RISORSA DESIDERATA */
        var ci=$("#city");
        eliminaDati();
        $.getJSON("http://localhost:8080/CountryCity/api/cities/"+code+"/list",function(result){
            ci.append("<table border='1px'><tr><th align='center' colspan='3'><b>"+code+"</b></th></tr>"); 
            ci.children().append("<tr><td align='center'><u>NOME</u></td><td align='center'><u>DISTRETTO</u></td><td align='center'><u>POPOLAZIONE</u></td></tr>");
            $.each(result,function(i,citta){
                ci.children().append("<tr><td align='center'>"+ citta.nome +"</td><td align='center'>"+ citta.distretto +"</td><td align='center'>"+ citta.popolazione +"</td></tr>");
            });

            ci.append("</table><br>"); 
        });
    }

    function functionAlert(x){
        window.alert(x);
    }

    function eliminaDati(){
        $("#city").empty();
        $("#country").empty(); 
        $("#lang").empty();
        $("#langT").empty();
    }

</script>
</head>
<body>
    <div align=center>
        <hr size=2 color="grey"><h1><i> Single Page Application </i></h1><hr size=2 color="grey">
        <p><h1 id="indexh1">SERVIZI REST COUNTRY-CITY</h1> </p>
        <button type="button" id="buttC" class="butt" style="display:none"> MOSTRA CONTINENTI </button><br><br>
        <button type="button" id="buttL" class="butt" style="display:none"> ANALIZZA LE LINGUE DEL MONDO </button>
        <div id="continent">
            <table id="tbC" border='1px'></table>
        </div>
        <div id="country" ></div>
        <div id="city" ></div>
        <div id="lang" ></div>
        <div id="langT"></div>
    </div>
</body>
</html>