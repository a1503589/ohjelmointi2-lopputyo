package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.Vene;
import model.dao.Dao;

@WebServlet("/veneet/*")
public class Veneet extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public Veneet() {
        super();
        System.out.println("Veneet.Veneet()");
    }
	
  //Haetaan veneiden tiedot
    //GET  /veneet/{hakusana}
    //GET /veneet/haeyksi/tunnus
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Veneet.doGet()");
		String pathInfo = request.getPathInfo();	//haetaan kutsun polkutiedot, esim. /Leila-vene
		System.out.println("polku: "+pathInfo);	
		Dao dao = new Dao();
		ArrayList<Vene> veneet;
		String strJSON="";
		if(pathInfo==null) { //Haetaan kaikki veneet, koska backendiä kutsutaan ilman hakupolkua 
			veneet = dao.listaaKaikki();
			strJSON = new JSONObject().put("veneet", veneet).toString(); //muodostetaan strJSON-vastausobjekti
			
			
		}else if(pathInfo.indexOf("haeyksi")!=-1) {		//polussa on sana "haeyksi", eli haetaan yhden veneen tiedot
			String tunnus = pathInfo.replace("/haeyksi/", ""); //poistetaan polusta "/haeyksi/", joten jää tunnus		
			Vene vene = dao.etsiVene(tunnus);
			
			JSONObject JSON = new JSONObject();
			JSON.put("tunnus", vene.getTunnus());
			JSON.put("nimi", vene.getNimi());
			JSON.put("merkkimalli", vene.getMerkkimalli());
			JSON.put("pituus", vene.getPituus());
			JSON.put("leveys", vene.getLeveys());
			JSON.put("hinta", vene.getHinta());
			
			strJSON = JSON.toString();		
		}else{ //Haetaan hakusanan mukaiset veneet
			String hakusana = pathInfo.replace("/", ""); //poistetaan polusta kauttaviiva, vain hakusana jää
			veneet = dao.listaaKaikki(hakusana);
			strJSON = new JSONObject().put("veneet", veneet).toString();
			
		}	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");//parseroidaan teksti ääkköselliseksi
		PrintWriter out = response.getWriter();
		out.println(strJSON);//kirjoitetaan vastaus	
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Veneet.doPost()");
		JSONObject jsonObj = new JsonStrToObj().convert(request); //Muutetaan kutsun mukana tuleva json-string json-objektiksi			
		Vene vene = new Vene();
		vene.setNimi(jsonObj.getString("nimi"));
		vene.setMerkkimalli(jsonObj.getString("merkkimalli"));
		vene.setPituus(Double.parseDouble(jsonObj.getString("pituus"))); //tehdään tyyppimuunnoksia
		vene.setLeveys(Double.parseDouble(jsonObj.getString("leveys")));
		vene.setHinta(Integer.parseInt(jsonObj.getString("hinta")));
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Dao dao = new Dao();			
		if(dao.lisaaVene(vene)){ //metodi palauttaa true/false
			out.println("{\"response\":1}");  //Veneen lis��minen onnistui {"response":1}
		}else{
			out.println("{\"response\":0}");  //Veneen lis��minen ep�onnistui {"response":0}
		}		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Veneet.doPut()");
				
		JSONObject jsonObj = new JsonStrToObj().convert(request); //Muutetaan kutsun mukana tuleva json-string json-objektiksi			
				
		Vene vene = new Vene();
		vene.setTunnus(Integer.parseInt(jsonObj.getString("tunnus")));
		vene.setNimi(jsonObj.getString("nimi"));
		vene.setMerkkimalli(jsonObj.getString("merkkimalli"));
		vene.setPituus(Double.parseDouble(jsonObj.getString("pituus"))); //tehdään tyyppimuunnoksia
		vene.setLeveys(Double.parseDouble(jsonObj.getString("leveys")));
		vene.setHinta(Integer.parseInt(jsonObj.getString("hinta")));
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		Dao dao = new Dao();
		if(dao.muutaVene(vene)){ //metodi palauttaa true/false
			out.println("{\"response\":1}");  //Veneen muuttaminen onnistui {"response":1}
		}else{
			out.println("{\"response\":0}");  //Veneen muuttaminen ep�onnistui {"response":0}
		}			
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Veneet.doDelete()");	
		String pathInfo = request.getPathInfo();	//haetaan kutsun polkutiedot, esim. /10		
		System.out.println("polku: "+pathInfo);
		String poistettavaTunnus = pathInfo.replace("/", "");		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Dao dao = new Dao();			
		if(dao.poistaVene(poistettavaTunnus)){ //metodi palauttaa true/false
			out.println("{\"response\":1}");  //Veneen poistaminen onnistui {"response":1}
		}else{
			out.println("{\"response\":0}");  //Veneen poistaminen ep�onnistui {"response":0}
		}	
	}

}



 