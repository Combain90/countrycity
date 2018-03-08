<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script src="js/scripts.js"></script>
<link href="assets/css/stile.css" rel="stylesheet" type="text/css">
<title>APPLICATION </title>

</head>
<body>
    <div align=center>
        <hr size=2 color="grey"><h1><i> Single Page Application </i></h1><hr size=2 color="grey"><br><br><br>
        <p><h1 id="indexh1">SERVIZI REST COUNTRY-CITY</h1> </p>
        <button type="button" id="buttC" class="butt" style="display:none"> MOSTRA CONTINENTI </button><br><br>
        <button type="button" id="buttL" class="butt" style="display:none"> ANALIZZA LE LINGUE DEL MONDO </button><br><br>
        <button type="button" id="buttAdd" class="butt" style="display:none"> AGGIUNGI UNA NUOVA CITY </button>
        <div id="continent">
            <table id="tbC" border='1px'></table>
        </div>
        <div id="country" ></div>
        <div id="city" ></div>
        <div id="lang" ></div>
        <div id="langT"></div>
        <div id="formAdd" style="display:none">
             SCEGLI UNO STATO: <select id='mioSel2'></select>
            <br> INSERISCI IL NOME: <input type='text' id='nomeStato' placeholder="Inserisci il nome" required>
            <br> INSERISCI IL DISTRETTO: <input type='text' id='distrettoStato' placeholder="Inserisci il distretto" required>
            <br> INSERISCI N. ABITANTI:  <input type='number' id='popolazioneStato' min='0' placeholder="n. abitanti" required>
            <br><button type='button' onclick='addFunction()' >AGGIUNGI </button>
        </div>
    </div>
</body>
</html>