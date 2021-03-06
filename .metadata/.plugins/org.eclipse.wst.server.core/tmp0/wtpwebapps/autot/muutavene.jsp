<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="scripts/main.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>Muuta venett?</title>
</head>
<body onkeydown="tutkiKey(event)">
<form id="tiedot">
	<table>
		<thead>	
			<tr>
				<th colspan="4" id="ilmo"></th>
				<th colspan="3" class="oikealle"><a href="listaaveneet.jsp" id="takaisin">Takaisin listaukseen</a></th>
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
				<td><input type="button" id="tallenna" value="Hyv?ksy" onclick="vieTiedot()"></td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" name="tunnus" id="tunnus">	
</form>
<span id="ilmo"></span>
</body>
<script>

function tutkiKeyX(event){
	if(event.keyCode==13){//Enter
		vieTiedot();
	}		
}

var tutkiKey = (event) => {
	if(event.keyCode==13){//Enter
		vieTiedot();
	}	
}

document.getElementById("nimi").focus();//vied??n kursori Nimi-kentt??n sivun latauksen yhteydess?

//Haetaan muutettavan veneen tiedot. Kutsutaan backin GET-metodia ja v?litet??n kutsun mukana muutettavan veneen tunnus
//GET /veneet/haeyksi/tunnus
var tunnus = requestURLParam("tunnus"); //Funktio l?ytyy scripts/main.js 
fetch("veneet/haeyksi/" + tunnus,{//L?hetet??n kutsu backendiin
      method: 'GET'	      
    })
.then( function (response) {//Odotetaan vastausta ja muutetaan JSON-vastausteksti objektiksi
	return response.json()
})
.then( function (responseJson) {//Otetaan vastaan objekti responseJson-parametriss?	
	console.log(responseJson);

	document.getElementById("tunnus").value = responseJson.tunnus;
	document.getElementById("nimi").value = responseJson.nimi.replace(/&nbsp;/g, ' ');	//muokataan sy?tekentt??n v?lily?nti takaisin
	document.getElementById("merkkimalli").value = responseJson.merkkimalli.replace(/&nbsp;/g, ' '); //samoin t?h?n kentt??n
	document.getElementById("pituus").value = responseJson.pituus;	
	document.getElementById("leveys").value = responseJson.leveys;
	document.getElementById("hinta").value = responseJson.hinta;
	});	

//Funktio tietojen muuttamista varten. Kutsutaan backin PUT-metodia ja v?litet??n kutsun mukana muutetut tiedot json-stringin?.
//PUT /autot/
function vieTiedot(){	
	var ilmo="";
	var nimi = document.getElementById("nimi").value;
	var merkkimalli = document.getElementById("merkkimalli").value;
	var pituus = siivoa(document.getElementById("pituus").value); //korvataan sy?tteen merkki ennen validointia
	var leveys = siivoa(document.getElementById("leveys").value); 
	var hinta = document.getElementById("hinta").value;
	
	if(nimi.length<2||merkkimalli.length<2||pituus*1!=pituus||leveys*1!=leveys||hinta*1!=hinta){ //validoidaan arvot, luvut kerrotaan itsell??n lu
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
	
	//L?het??n muutetut tiedot backendiin
	fetch("veneet",{//L?hetet??n kutsu backendiin
	      method: 'PUT',
	      body:formJsonStr
	    })
	.then( function (response) {//Odotetaan vastausta ja muutetaan JSON-vastaus objektiksi
		return response.json();
	})
	.then( function (responseJson) {//Otetaan vastaan objekti responseJson-parametriss?	
		var vastaus = responseJson.response;		
		if(vastaus==0){
			document.getElementById("ilmo").innerHTML= "Tietojen p?ivitys ep?onnistui";
        }else if(vastaus==1){	        	
        	document.getElementById("ilmo").innerHTML= "Tietojen p?ivitys onnistui";			      	
		}	
		setTimeout(function(){ document.getElementById("ilmo").innerHTML=""; }, 5000);
	});	
	document.getElementById("tiedot").reset(); //tyhjennet??n tiedot -lomake
}
</script>
</html>