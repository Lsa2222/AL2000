package BDD;

import java.util.HashSet;

import fc.Abonne;
import fc.Film;
import fc.LocationBR;
import fc.LocationQR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FacadeBD {

	static final String connAdre = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
	static final String connUser = "rognont";//?
	static final String connPasw = "b67429290d";//?
	private Connection conn;
	
	private DAO<Abonne> aboDAO;
	private DAO<Film> filmDAO;
	private DAO<LocationBR> locBRDAO;
	private DAO<LocationQR> locQRDAO;
	
	FacadeBD(){
		try{
			conn=DriverManager.getConnection(connAdre,connUser,connPasw);
			
			aboDAO = new AbonneDAO(conn);
			filmDAO = new FilmDAO(conn);
			locBRDAO = new LocationBRDAO(conn);
			locQRDAO = new LocationQRDAO(conn);
		} catch (SQLException e) {
			System.out.println(e);//CRITIQUE
		}
		
	}
	
	//enregistre l'abonne, renvoi faux si il a deja trop de compte(trigger)
	public boolean newAbonne(Abonne a) {
		return false;
		//Create de AbonneDAO
	}
	//recupére l'abonne avec le numero de carte num 
	public void getAbonne(int num) {
		//Read de AbonneDAO (Objet=num)
	}

	public HashSet<Film> getCatalogueGlobal(){
		return null;
		//Pas le read de FilmDAO mais une autre fonction qui renvois un HashSet<Film>
	}
	
	//met à c le crédit de l'abonne a;
	public void updCredit(Abonne a,int c) {
		//Update de AbonneDAO
	}
	
	public void newLocation(LocationBR br) {
		//Create de LocationBRDAO
		try {
			locBRDAO.create(br);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void newLocation(LocationQR qr) {
		//Create de LocationQRDAO
		try {
			locQRDAO.create(qr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delLocation(LocationBR br) {
		//Delete de LocationBRDAO
		try {
			locBRDAO.delete(br);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delLocation(LocationQR qr) {
		//Delete de LocationQRDAO
		try {
			locQRDAO.delete(qr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
