package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import fc.BluRay;
import fc.Film;
import fc.LocationBR;
import fc.Personne;
import fc.Tag;

public class LocationBRDAO extends DAO<LocationBR> {
	
	DAO<Tag> tagDAO;
    protected LocationBRDAO(Connection conn,DAO<Tag> tagDAO) {
        super(conn);
        this.tagDAO=tagDAO;
    }
    
    public boolean create(LocationBR obj) {
    	try {
    		PreparedStatement statmLocation = conn.prepareStatement(
            		"INSERT INTO LesLocationsBR "+
            		"VALUES (?,?,?)");
            statmLocation.setInt(1,obj.getPersonneId());
            statmLocation.setInt(2,obj.getBRId());
            statmLocation.setDate(3,new java.sql.Date(obj.getDate()));//TODO : date ?
            statmLocation.execute();
            return true;
    	} catch(SQLException e) {
    		e.printStackTrace();
    		return false;
    	}
        
    }

    public LocationBR read (Object obj) {
        return null;
    }
    
    
    
    public HashSet<LocationBR> readAll (Object obj) {
    	
    	ResultSet res3 = null;
    	
    	try {
    		Personne per = (Personne) obj;
        	
        	PreparedStatement querryLocationBR = conn.prepareStatement(""
            		+ "SELECT "
            		+ "f.noFilm, f.titre, f.realisateur, f.resumer, f.genre, b.idBR, b.etat "
            		+ "FROM LesLocationsBR l, LesFilms f, LesBlueRay b "//JOIN
            		+ "WHERE l.id = ? AND "
            		+ "b.idBr = l.idBr AND "
            		+ "f.noFilm = b.noFilm");
        	querryLocationBR.setInt(1, per.getId());
            res3 = querryLocationBR.executeQuery();
            
            HashSet<LocationBR> liste = new HashSet<>();
            
            while(res3.next()) {
            	Boolean etatBool = false;
            	if(res3.getString(7).equals("good")) {
            		etatBool = true;
    			}
            	liste.add(new LocationBR(
            			new BluRay(
            					res3.getInt(6),
            					new Film(res3.getInt(1),
                    					res3.getString(2),
                    					res3.getString(3),
                    					res3.getString(4),
                    					tagDAO.readAll(res3.getInt(1)),
                    					res3.getString(5)),//?
            					etatBool
            					),
            			per
            			));
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

    public boolean update (LocationBR obj) {
        return false;
    }

    public boolean delete(LocationBR obj) throws SQLException {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE FROM LesLocationsBR "+
                "WHERE id=? AND idBR=?");
            statm1.setInt(1,obj.getPersonneId());
            statm1.setInt(2,obj.getBRId());
            statm1.execute();
            return true;
    }

}