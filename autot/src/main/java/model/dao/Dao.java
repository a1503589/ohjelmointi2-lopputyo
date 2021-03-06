package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Vene;

public class Dao {
	private Connection con=null;
	private ResultSet rs = null;
	private PreparedStatement stmtPrep=null; 
	private String sql;
	private String db ="Venekanta.sqlite"; //tietokannan nimi
	
	private Connection yhdista(){ //yhteysobjekti
    	Connection con = null;    	
    	String path = System.getProperty("catalina.base");    	
    	path = path.substring(0, path.indexOf(".metadata")).replace("\\", "/"); 
    	//en saanut tätä toimimaan siten, että tietokanta olisi ollut webapp-kansiossa. Muuten kyllä rokkaa.
    	String url = "jdbc:sqlite:"+path+db;    	
    	try {	       
    		Class.forName("org.sqlite.JDBC");
	        con = DriverManager.getConnection(url);	
	        System.out.println("Yhteys avattu.");
	     }catch (Exception e){	
	    	 System.out.println("Yhteyden avaus ep�onnistui.");
	        e.printStackTrace();	         
	     }
	     return con;
	}
	
	public ArrayList<Vene> listaaKaikki(){ 		//Kaikki listataan, ei hakusanaa
		ArrayList<Vene> veneet = new ArrayList<Vene>();
		sql = "SELECT * FROM veneet"; 	
		try {
			con=yhdista();
			if(con!=null){ //jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql);        		
        		rs = stmtPrep.executeQuery();   
				if(rs!=null){ //jos kysely onnistui									
					while(rs.next()){
						Vene vene = new Vene();
						vene.setTunnus(rs.getInt(1));
						vene.setNimi(rs.getString(2));
						vene.setMerkkimalli(rs.getString(3));
						vene.setPituus(rs.getDouble(4));
						vene.setLeveys(rs.getDouble(5));
						vene.setHinta(rs.getInt(6));
						veneet.add(vene);
						}					
				}				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return veneet;
	}
	
	public ArrayList<Vene> listaaKaikki(String hakusana){ //listataan hakusanalla
		ArrayList<Vene> veneet = new ArrayList<Vene>();
		sql = "SELECT * FROM veneet WHERE nimi LIKE ? or merkkimalli LIKE ?";	
		
		try {
			con=yhdista();
			if(con!=null){ //jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql);  
				stmtPrep.setString(1, "%" + hakusana + "%");
				stmtPrep.setString(2, "%" + hakusana + "%");   
				rs = stmtPrep.executeQuery();   
				if(rs!=null){ //jos kysely onnistui							
					while(rs.next()){
						Vene vene = new Vene();
						vene.setTunnus(rs.getInt(1));
						vene.setNimi(rs.getString(2));
						vene.setMerkkimalli(rs.getString(3));
						vene.setPituus(rs.getDouble(4));
						vene.setLeveys(rs.getDouble(5));
						vene.setHinta(rs.getInt(6));
						veneet.add(vene);
						
					}						
				}
				con.close();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return veneet;
	}
	
	
	 public boolean lisaaVene(Vene vene){
		boolean paluuArvo=true;
		sql="INSERT INTO veneet(nimi, merkkimalli, pituus, leveys, hinta) VALUES(?,?,?,?,?)";
		
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, vene.getNimi());
			stmtPrep.setString(2, vene.getMerkkimalli());
			stmtPrep.setString(3, Double.toString(vene.getPituus())); //tehdään tyyppimuunnoksia
			stmtPrep.setString(4, Double.toString(vene.getLeveys()));
			stmtPrep.setString(5, Integer.toString(vene.getHinta()));
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	 
	 public boolean poistaVene(String tunnus){ //Oikeassa el�m�ss� tiedot ensisijaisesti merkit��n poistetuksi.
			boolean paluuArvo=true;
			sql="DELETE FROM veneet WHERE tunnus=?";						  
			try {
				con = yhdista();
				stmtPrep=con.prepareStatement(sql); 
				stmtPrep.setString(1, tunnus);			
				stmtPrep.executeUpdate();
		        con.close();
			} catch (Exception e) {				
				e.printStackTrace();
				paluuArvo=false;
			}				
			return paluuArvo;
		}	
	 
	 public Vene etsiVene (String tunnus) {
			Vene vene = null;
			System.out.println("Venettä etsitään");
			sql = "SELECT * FROM veneet WHERE tunnus=?";       
			try {
				con=yhdista();
				if(con!=null){ 
					stmtPrep = con.prepareStatement(sql); 
					stmtPrep.setString(1, tunnus);
	        		rs = stmtPrep.executeQuery();  
	        		if(rs.isBeforeFirst()){ //jos kysely tuotti dataa, eli tunnus on k�yt�ss�
	        			rs.next();
	        			vene = new Vene();
	        			vene.setTunnus(rs.getInt(1));
	        			vene.setNimi(rs.getString(2));
						vene.setMerkkimalli(rs.getString(3));
						vene.setPituus(Double.parseDouble(rs.getString(4)));	
						vene.setLeveys(Double.parseDouble(rs.getString(5)));
						vene.setHinta(Integer.parseInt(rs.getString(6)));
						
					}        		
				}	
				con.close();  
			} catch (Exception e) {
				e.printStackTrace();
			}		
			return vene;		
		}
	 
	 public boolean muutaVene(Vene vene){
			boolean paluuArvo=true;
			String tunnus = Integer.toString(vene.getTunnus());
			System.out.println(tunnus);
			System.out.println(vene);
			sql="UPDATE veneet SET nimi=?, merkkimalli=?, pituus=?, leveys=?, hinta=? WHERE tunnus=?";						  
			try {
				con = yhdista();
				
				stmtPrep=con.prepareStatement(sql); 
				stmtPrep.setString(1, vene.getNimi());
				stmtPrep.setString(2, vene.getMerkkimalli());
				stmtPrep.setString(3, Double.toString(vene.getPituus())); 
				stmtPrep.setString(4, Double.toString(vene.getLeveys()));
				stmtPrep.setString(5, Integer.toString(vene.getHinta()));
				stmtPrep.setString(6, tunnus);
				stmtPrep.executeUpdate();
		        con.close();
			} catch (Exception e) {				
				e.printStackTrace();
				paluuArvo=false;
			}				
			return paluuArvo;
		}

	

}
	