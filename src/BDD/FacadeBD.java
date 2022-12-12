package BDD;

import java.util.HashSet;
import java.util.Iterator;

import fc.Abonne;
import fc.Enfant;
import fc.Film;
import fc.Guest;
import fc.LocationBR;
import fc.LocationQR;
import fc.Tag;

import java.sql.Connection;
import java.sql.SQLException;

public class FacadeBD {
	
	private DAO<Guest> guestDAO;
	private DAO<Abonne> aboDAO;
	private DAO<Enfant> enfDAO;
	private DAO<Film> filmDAO;
	private DAO<LocationBR> locBRDAO;
	private DAO<LocationQR> locQRDAO;
	private DAO<Tag> tagDAO;
	
	private Session ses;
	protected Connection conn;
	
	private FacadeBD(){
		ses = new Session();
		conn = ses.open();
		
		tagDAO = new TagDAO(conn);
		filmDAO = new FilmDAO(conn,tagDAO);
		locBRDAO = new LocationBRDAO(conn,tagDAO);
		locQRDAO = new LocationQRDAO(conn,tagDAO);
		
		aboDAO = new AbonneDAO(conn);
		guestDAO = new GuestDAO(conn);
		enfDAO = new EnfantDAO(conn,aboDAO);
		
		try {
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static FacadeBD instance = null;
	
	public static FacadeBD creer() {
		if(instance==null) {
			instance = new FacadeBD();
		}
		return instance;
	}
	
	public void reset() {
		DataBase db = new DataBase(conn);
		db.resetBD();
	}
	
	static int autoIncrDebug = 8;
	
	//PersonneDAO & AbonneDAO & EnfantDAO
	
	public boolean newGuest(Guest a) {
		try {
			a.setId(autoIncrDebug);
			boolean retour = guestDAO.create(a);
			if(retour) {
				conn.commit();
				autoIncrDebug++;
			}else {
				conn.rollback();
			}
			return retour;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean newAbonne(Abonne a) {
		try {
			a.setId(autoIncrDebug);
			boolean retour = aboDAO.create(a);
			if(retour) {
				conn.commit();
				autoIncrDebug++;
			}else {
				conn.rollback();
			}
			return retour;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean newEnfant(Enfant a) {
		try {
			a.setId(autoIncrDebug);
			boolean retour = enfDAO.create(a);
			if(retour) {
				conn.commit();
				autoIncrDebug++;
			}else {
				conn.rollback();
			}
			return retour;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//recupére l'abonne avec le numero de carte num 
	public Abonne getAbonne(int num) {
		try {
			Abonne jean = aboDAO.read(num);
			Iterator<LocationBR> locBR = locBRDAO.readAll(jean).iterator();
			Iterator<LocationQR> locQR = locQRDAO.readAll(jean).iterator();
			Iterator<Enfant> enfs = enfDAO.readAll(jean.getId()).iterator();
			
            while(locQR.hasNext()) {
            	jean.addLocationAdmin(locQR.next());
            }
            while(locBR.hasNext()) {
            	jean.addLocationAdmin(locBR.next());
            }
            while(enfs.hasNext()) {
            	jean.addEnfant(enfs.next());
            }
            
			return jean;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public Enfant getEnfant(int num) {
		try {
			Enfant jean = enfDAO.read(num);
			
			Iterator<LocationBR> locBR = locBRDAO.readAll(jean).iterator();
			Iterator<LocationQR> locQR = locQRDAO.readAll(jean).iterator();
            while(locQR.hasNext()) {
            	jean.addLocationAdmin(locQR.next());
            }
            while(locBR.hasNext()) {
            	jean.addLocationAdmin(locBR.next());
            }
			return jean;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//met à c le crédit de l'abonne a;
	public boolean updCredit(Abonne a,int c) {
		int ancienCredit = a.getCredit();
		a.setCredit(c);
		try {
			if(!aboDAO.update(a)) {
				conn.rollback();
				a.setCredit(ancienCredit);
				return false;
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//FilmDAO
	
	public HashSet<Film> getCatalogueGlobal(){
		HashSet<Film> ret = null;
		try {
			ret = filmDAO.readAll(null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	// LocationDAO
	
	public boolean newLocation(LocationBR br) {
			try {
				if(locBRDAO.create(br)) {
					conn.commit();
					return true;
				} else {
					conn.rollback();
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		
		
	}
	public boolean newLocation(LocationQR qr) {
		
		try {
			if(locQRDAO.create(qr)) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean delLocation(LocationBR br) {
		
		try {
			if(locBRDAO.delete(br)) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean delLocation(LocationQR qr) {
		try {
			if(locQRDAO.delete(qr)) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
