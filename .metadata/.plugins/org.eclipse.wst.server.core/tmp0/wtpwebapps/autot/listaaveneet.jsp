<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script src="scripts/main.js"></script>
<title>Listaa asiakkaat</title>
<style>
.oikealle{
	text-align: right;
}
</style>
</head>
<body onkeydown="tutkiKey(event)">
<table id="listaus">
	<thead>	
		<tr>
			<th colspan="6" id="ilmo"></th>
			<th><a id="uusiVene" href="lisaavene.jsp">Lis?? uusi vene</a></th>
		</tr>	
		<tr>
			<th class="oikealle">Hakusana:</th>
			<th colspan="5"><input type="text" id="hakusana"></th>
			<th><input type="button" value="hae" id="hakunappi" onclick="haeTiedot()"></th>
		</tr>			
		<tr>
			<th>Tunnus</th>
			<th>Nimi</th>
			<th>Merkkimalli</th>
			<th>Pituus</th>
			<th>Leveys</th>
			<th>Hinta</th>
			<th>Toiminnot</th>							
		</tr>
	</thead>
	<tbody id="tbody">
	</tbody>
</table>
<script>
haeTiedot();	
document.getElementById("hakusana").focus();//vied??n kursori hakusana-kentt??n sivun latauksen yhteydess?

function tutkiKey(event){
	if(event.keyCode==13){//Enter
		haeTiedot();
	}		
}
//Funktio tietojen hakemista varten
//GET   /autot/{hakusana}
function haeTiedot(){
	
	document.getElementById("tbody").innerHTML = "";
	
	fetch("veneet/" + document.getElementById("hakusana").value,{//L?hetet??n kutsu backendiin
	      method: 'GET'
	    	  
	    })
	   
	.then(function (response) {//Odotetaan vastausta ja muutetaan JSON-vastaus objektiksi
		
		return response.json()	
		
		
	})
	.then(function (responseJson) {//Otetaan vastaan objekti responseJson-parametriss?		
				
		var veneet = responseJson.veneet;	
		
		var htmlStr="";
		for(var i=0;i<veneet.length;i++){			
        	htmlStr+="<tr>";
        	htmlStr+="<td>"+veneet[i].tunnus+"</td>";
        	htmlStr+="<td>"+veneet[i].nimi+"</td>";
        	htmlStr+="<td>"+veneet[i].merkkimalli+"</td>";
        	htmlStr+="<td>"+veneet[i].pituus+"</td>";
        	htmlStr+="<td>"+veneet[i].leveys+"</td>";
        	htmlStr+="<td>"+veneet[i].hinta+"</td>";
        	htmlStr+="<td><a href='muutavene.jsp?tunnus="+veneet[i].tunnus+"'>Muuta</a>&nbsp;";
        	htmlStr+="<span class='poista' onclick=poista('"+veneet[i].tunnus+"','"+veneet[i].nimi+"','"+veneet[i].merkkimalli+"')>Poista</span></td>";
        	htmlStr+="</tr>";        	
		}
		document.getElementById("tbody").innerHTML = htmlStr;		
	})	
}

//Funktio tietojen poistamista varten. Kutsutaan backin DELETE-metodia ja v?litet??n poistettavan tiedon id. 
//DELETE /autot/id
function poista(tunnus, nimi, merkkimalli){
	if(confirm("Poista vene " + tunnus + " "+ nimi +" " + merkkimalli+" ?")){	
		fetch("veneet/"+tunnus,{//L?hetet??n kutsu backendiin
		      method: 'DELETE'		      	      
		    })
		.then(function (response) {//Odotetaan vastausta ja muutetaan JSON-vastaus objektiksi
			return response.json()
		})
		.then(function (responseJson) {//Otetaan vastaan objekti responseJson-parametriss?		
			var vastaus = responseJson.response;		
			if(vastaus==0){
				document.getElementById("ilmo").innerHTML= "Venen poisto ep?onnistui.";
	        }else if(vastaus==1){	        	
	        	document.getElementById("ilmo").innerHTML="Veneen " +tunnus + " " + nimi + " " +merkkimalli + " poisto onnistui.";
				haeTiedot();        	
			}	
			setTimeout(function(){ document.getElementById("ilmo").innerHTML=""; }, 5000);
		})		
	}	
}
</script>
</body>
</html>