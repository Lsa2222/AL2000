package BDD;

import java.util.HashSet;

import fc.Abonne;
import fc.Enfant;
import fc.Film;
import fc.LocationBR;
import fc.LocationQR;

import java.sql.Connection;
import java.sql.SQLException;

public class FacadeBD {
		
	private DAO<Abonne> aboDAO;
	private EnfantDAO enfDAO;
	private DAO<Film> filmDAO;
	private DAO<LocationBR> locBRDAO;
	private DAO<LocationQR> locQRDAO;
	
	private Session ses;
	private Connection conn;
	
	FacadeBD(){
		ses = new Session();
		conn = ses.open();
		
		aboDAO = new AbonneDAO(conn);
		enfDAO = new EnfantDAO(conn);
		filmDAO = new FilmDAO(conn);
		locBRDAO = new LocationBRDAO(conn);
		locQRDAO = new LocationQRDAO(conn);
	}
	
	//AbonneDAO
	
	//enregistre l'abonne, renvoi faux si il a deja trop de compte(trigger)
	public boolean newAbonne(Abonne a) {
		try {
			return aboDAO.create(a);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean newEnfant(Enfant a) {
		try {
			return enfDAO.create(a);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//recupére l'abonne avec le numero de carte num 
	public Abonne getAbonne(int num) {
		try {
			return aboDAO.read(num);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//met à c le crédit de l'abonne a;
	public boolean updCredit(Abonne a,int c) {
		a.setCredit(c);
		try {
			return aboDAO.update(a);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//FilmDAO
	
	public HashSet<Film> getCatalogueGlobal(){
		return null;
		//Pas le read de FilmDAO mais une autre fonction qui renvois un HashSet<Film>
	}
	
	// LocationDAO
	
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
