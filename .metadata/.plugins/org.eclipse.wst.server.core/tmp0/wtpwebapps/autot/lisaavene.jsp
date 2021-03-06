<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="scripts/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Lis?? vene</title>
</head>
<body onkeydown="tutkiKey(event)">
<form id="tiedot">
	<table>
		<thead>	
			<tr>
				<th colspan="4" id="ilmo"></th>
				<th colspan="2" class="oikealle"><a href="listaaveneet.jsp" id="takaisin">Takaisin listaukseen</a></th>
			</tr>		
			<tr>
				<th>Nimi</th>
				<th>Merkkimalli</th>
				<th>Pituus</th>
				<th>Leveys</th>
				<th>Hinta</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" name="nimi" id="nimi"></td>
				<td><input type="text" name="merkkimalli" id="merkkimalli"></td>
				<td><input type="text" name="pituus" id="pituus"></td>
				<td><input type="text" name="leveys" id="leveys"></td>
				<td><input type="text" name="hinta" id="hinta"></td> 
				<td><input type="button" name="nappi" id="tallenna" value="Lis??" onclick="lisaaTiedot()"></td>
			</tr>
		</tbody>
	</table>
</form>
<span id="ilmo"></span>
</body>
<script>
function tutkiKey(event){
	if(event.keyCode==13){//Enter
		lisaaTiedot();
	}
	
}

document.getElementById("nimi").focus();//vied??n kursori nimikentt??n sivun latauksen yhteydess?

//funktio tietojen lis??mist? varten. Kutsutaan backin POST-metodia ja v?litet??n kutsun mukana uudet tiedot json-stringin?.
//POST /veneet/
function lisaaTiedot(){	
	var ilmo="";
	var nimi = document.getElementById("nimi").value;
	var merkkimalli = document.getElementById("merkkimalli").value;
	var pituus = siivoa(document.getElementById("pituus").value); //korvataan sy?tteen merkki ennen validointia, jotta voidaan laskea
	var leveys = siivoa(document.getElementById("leveys").value); 
	var hinta = document.getElementById("hinta").value;
	
	if(nimi.length<2||merkkimalli.length<2||pituus*1!=pituus||leveys*1!=leveys||hinta*1!=hinta){ //validoidaan arvot, luvut kerrotaan itsell??n numeraalisen arvon varmistamiseksi
		document.getElementById("ilmo").innerHTML = "Antamasi arvot eiv?t kelpaa!"
		return;
	}
	
	if(ilmo!=""){
		document.getElementById("ilmo").innerHTML=ilmo;
		setTimeout(function(){ document.getElementById("ilmo").innerHTML=""; }, 3000);
		return;
	}
	
	document.getElementById("nimi").value=siivoa(document.getElementById("nimi").value);
	document.getElementById("merkkimalli").value=siivoa(document.getElementById("merkkimalli").value);
	document.getElementById("pituus").value=siivoa(document.getElementById("pituus").value);
	document.getElementById("leveys").value=siivoa(document.getElementById("leveys").value);
	document.getElementById("hinta").value=siivoa(document.getElementById("hinta").value);	
	
	var formJsonStr=formDataToJSON(document.getElementById("tiedot")); //muutetaan lomakkeen tiedot json-stringiksi
		
	fetch("veneet",{//L?hetet??n kutsu backendiin
	      method: 'POST',
	      body:formJsonStr
	    })
	.then( function (response) {//Odotetaan vastausta ja muutetaan JSON-vastaus objektiksi		
		return response.json()
	})
	.then( function (responseJson) {//Otetaan vastaan objekti responseJson-parametriss?	
		var vastaus = responseJson.response;		
		if(vastaus==0){
			document.getElementById("ilmo").innerHTML= "Veneen lis??minen ep?onnistui";
      	}else if(vastaus==1){	        	
      		document.getElementById("ilmo").innerHTML= "Veneen lis??minen onnistui";			      	
		}
		setTimeout(function(){ document.getElementById("ilmo").innerHTML=""; }, 5000);
	});	
	document.getElementById("tiedot").reset(); //tyhjennet??n tiedot -lomake
}
</script>
</html>