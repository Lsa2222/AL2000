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
    
    public boolean create(LocationBR obj) throws SQLException {
        PreparedStatement statmLocation = conn.prepareStatement(
        		"INSERT INTO LesLocationsBR "+
        		"VALUES (?,?,?,?)");
        statmLocation.setInt(1,obj.getPersonneId());
        statmLocation.setInt(2,obj.getBRId());//Changer model
        statmLocation.setDate(3,new java.sql.Date(obj.getDate()));//TODO : date ?
        return statmLocation.execute();
    }

    public LocationBR read (Object obj) throws SQLException {
        return null;
    }
    
    
    
    public HashSet<LocationBR> readAll (Object obj) throws SQLException {
    	Personne per = (Personne) obj;
    	PreparedStatement querryLocationBR = conn.prepareStatement(""
        		+ "SELECT "
        		+ "f.noFilm, f.titre, f.realisateur, f.resumer, f.genre, b.idBR, b.etat"
        		+ "FROM LesLocationsQR l, LesFilms f, LesBlueRay b"//JOIN
        		+ "WHERE l.id = ? AND "
        		+ "f.noFilm = l.noFilm AND"
        		+ "f.noFilm = b.noFilm");
    	querryLocationBR.setInt(1, per.getId());
        ResultSet res3 = querryLocationBR.executeQuery();
        
        HashSet<LocationBR> liste = new HashSet<>();
        
        while(res3.next()) {
        	liste.add(new LocationBR(
        			new BluRay(
        					res3.getInt(6),
        					new Film(res3.getInt(1),
                					res3.getString(2),
                					res3.getString(3),
                					res3.getString(4),
                					tagDAO.readAll(res3.getInt(1)),
                					res3.getString(5)),//?
        					res3.getBoolean(7)
        					),
        			per
        			));
        }       
       
		return liste;
    }

    public boolean update (LocationBR obj) throws SQLException {
        return false;
    }

    public boolean delete(LocationBR obj) throws SQLException {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO LesLocationsBR "+
                "WHERE id=? AND idBR=?");
            statm1.setInt(1,obj.getPersonneId());
            statm1.setInt(2,obj.getBRId());
            statm1.execute();
            return true;
    }

}