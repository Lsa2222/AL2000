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
    
    public boolean create(LocationQR obj) {
    	try {
    		PreparedStatement statm1 = conn.prepareStatement(
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
    	}
            

    }

    public LocationQR read (Object obj) {
        return null;
    }
    
    public HashSet<LocationQR> readAll (Object obj) {
    	ResultSet res3=null;
    	try {
    		Personne per = (Personne) obj;
        	
        	PreparedStatement querryLocationQR = conn.prepareStatement(""
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
            res3.close();
    		return liste;
    	}catch(SQLException e) {
    		if(res3!=null) {
    			try {
					res3.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
    		}
    		return null;
    	}
    	
    	
    }

    public boolean update (LocationQR obj) {
        return false;
    }

    public boolean delete(LocationQR obj) {
           
			try {
				PreparedStatement statm1;
				statm1 = conn.prepareStatement(
				    "DELETE FROM LesLocationsQR "+
				    "WHERE id=? AND noFilm=?");
				statm1.setInt(1,obj.getPersonneId());//Changer model
	            statm1.setInt(2,obj.getFilmId());
	            statm1.execute();
	            return true;
			} catch (SQLException e) {
				return false;
			}
            

    }
    
}