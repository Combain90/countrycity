var assolutePath="http://localhost:8080/CountryCity/api";
$(document).ready(function(){
        $("#indexh1").mouseover(function(){
            $(".butt").slideDown("slow");
            $("#tbC").empty();
            eliminaDati();
        });

        $("#buttC").click(function(){
            var con=$("#continent");
            $.getJSON(assolutePath+"/countries/continents",function(result){
                con.children().append("<tr><th align='center'><b> CONTINENTI </b></th></tr>");
                $.each(result,function(i,field){
                    con.children().append("<tr><td align='center' id='rigaC"+i+"' onclick='rigaCFunction(this.id)'>"+ field +"</td></tr>"); 
                });
            });
            $(".butt").slideUp("slow");
        }); 

        $("#buttL").click(function(){
            var lang=$("#lang");
            $.getJSON(assolutePath+"/countries/list",function(result){
    
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
        
        $("#buttAdd").click(function(){ /*DEVO CREARE IL FORM PER INSERIRE LA CITY */
            var add=$("#formAdd");
            $.getJSON(assolutePath+"/countries/list",function(result){
    
                $.each(result,function(i,field){
                    add.find("#mioSel2").append("<option value="+ field.codice +" >"+ field.nome +"</option>");
                });
                add.show();
            });
           
            $(".butt").slideUp("slow");
            eliminaDati();
        });
    });

    function addFunction(){
        var v1 =$("#nomeStato")[0]; 
        var v2 =$("#distrettoStato")[0];
        var v3 =$("#popolazioneStato")[0];
        var verifica=validate(v1) && validate(v2) && validate(v3) ;
        if(verifica){
            var obj={id:"-1",nome:v1.value , distretto:v2.value, popolazione:v3.value, countryCode: $("#mioSel2").val()}; // CREO UN OGGETTO CHE PASSERO' COME JSON
            /* CHIAMATA AJAX NUDA E CRUDA CON JQUERY */
            $.ajax({
                type: "POST",
                url:assolutePath+"/cities/add",
                data: JSON.stringify(obj),  /* se passo soltanto obj non funziona. Così funziona */
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json; charset=utf-8' 
                },
                dataType: "json",
                success: function(result){
                    eliminaDati();
                    if(result.status==304){
                        window.alert("STATUS "+ result.status + ": "+result.msg);
                    }else{
                        window.alert("STATUS "+result.status + ":"+ result.cb.nome + " inserita correttamente!");
                        rigaSFunction(result.cb.countryCode);
                    }
                },
                error: function(){window.alert("STATUS 404 : CHIAMATA FALLITA. SERVER NON RAGGIUNTO")}
            });
        }else{
          window.alert("RIPROVA. INSERIRE CORRETTAMENTE I DATI");   
        }
    }
    
    function validate(x){
        var verifica=true;
        if (!x.checkValidity()){
            verifica=false;
        }
        return verifica;
    }


    function langFunction(){
        var lang=$("#langT");
        var y=$("#mioSel").val();
        lang.empty();
        $.getJSON(assolutePath+"/languages/"+y+"/list",function(result){
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
        $.getJSON(assolutePath+"/countries/"+$("#"+id).text()+"/list",function(result){
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
        $.getJSON(assolutePath+"/cities/"+code+"/list",function(result){
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

    function formAddHide(){
        $("#formAdd").find("#mioSel2").empty();
        $("#nomeStato").val("INSERISCI IL NOME");
        $("#distrettoStato").val("INSERISCI IL DISTRETTO");
        $("#popolazioneStato").val("0");
        $("#formAdd").hide();
    }

    function eliminaDati(){
        $("#city").empty();
        $("#country").empty(); 
        $("#lang").empty();
        $("#langT").empty();
        formAddHide();
    }