package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;

import fc.Film;
import fc.LocationQR;
import fc.Personne;
import fc.Tag;

public class LocationQRDAO extends DAO<LocationQR> {
	
	DAO<Tag> tagDAO;
	
    protected LocationQRDAO(Connection conn,DAO<Tag> tagDAO) {
        super(conn);
        this.tagDAO=tagDAO;
    }
    
    /**
	Ajoute la location QR obj a la BD.
	Supose que l'abonne et le blueRay existe dans la bd
	@param obj	la location a ajouter a la BD. Ne doit pas dejat etre dans la BD
	@return vrais si l'ajout est reussi
	 **/
    public boolean create(LocationQR obj) {
    	PreparedStatement statm1 = null;
    	try {
    		statm1 = conn.prepareStatement(
                    "INSERT INTO LesLocationsQR "+
                    "VALUES (?,?,?,?)");
                statm1.setInt(1,obj.getPersonneId());
                statm1.setInt(2,obj.getFilmId());//Changer model
    			statm1.setDate(3,new java.sql.Date(obj.getActivationDate()));
                statm1.setDate(4,new java.sql.Date(obj.getDate()));
                statm1.execute();
            return true;
    	} catch (SQLException e) {
    		return false;
    	} finally {
			try {
            	if(statm1!=null) {
            		statm1.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
            

    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public LocationQR read (Object obj) {
        return null;
    }
    
    /**
     * Lit toutes les locations QR d'une personne
     * @param obj	(Personne)
     * @return La liste des location QR de obj
	 **/
    public HashSet<LocationQR> readAll (Object obj) {
    	ResultSet res3=null;
    	PreparedStatement querryLocationQR = null;
    	try {
    		Personne per = (Personne) obj;
        	
        	querryLocationQR = conn.prepareStatement(""
            		+ "SELECT "
            		+ "f.noFilm, f.titre, f.realisateur, f.resumer, f.genre "
            		+ "FROM LesLocationsQR l, LesFilms f "//JOIN
            		+ "WHERE l.id = ? AND "
            		+ "f.noFilm = l.noFilm");
        	
        	querryLocationQR.setInt(1, per.getId());
            res3 = querryLocationQR.executeQuery();
            
            HashSet<LocationQR> liste = new HashSet<>();
                    
            while(res3.next()) {
            	liste.add(new LocationQR(
            			new Film(res3.getInt(1),
            					res3.getString(2),
            					res3.getString(3),
            					res3.getString(4),
            					tagDAO.readAll(res3.getInt(1)),
            					res3.getString(5))
            			,per));
            }
    		return liste;
    	}catch(SQLException e) {
    		return null;
    	} finally {
			try {
				if(res3!=null) {
            		res3.close();}
            	if(querryLocationQR!=null) {
            		querryLocationQR.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
    	
    	
    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean update (LocationQR obj) {
        return false;
    }

    /**
     * supprime la location obj de la bd
     * @param obj	la location a supprimer
	@return vrais si la suppresion est reussi
	 **/
    public boolean delete(LocationQR obj) {
    		PreparedStatement statm1 = null;
			try {
				statm1 = conn.prepareStatement(
				    "DELETE FROM LesLocationsQR "+
				    "WHERE id=? AND noFilm=?");
				statm1.setInt(1,obj.getPersonneId());//Changer model
	            statm1.setInt(2,obj.getFilmId());
	            statm1.execute();
	            return true;
			} catch (SQLException e) {
				return false;
			} finally {
				try {
					if(statm1!=null) {
						statm1.close();}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
            

    }
    
}