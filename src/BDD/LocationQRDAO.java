package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import fc.LocationQR;

public class LocationQRDAO extends DAO<LocationQR> {    
    protected LocationQRDAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(LocationQR obj) throws SQLException {
            PreparedStatement statm1 = conn.prepareStatement(
                "INSERT INTO LesLocationsQR "+
                "VALUES (?,?,?,?)");
            statm1.setInt(1,obj.getPersonneId());
            statm1.setInt(2,obj.getFilmId());//Changer model
			statm1.setDate(3,new java.sql.Date(obj.getActivationDate()));//TODO
            statm1.setDate(4,new java.sql.Date(obj.getDate()));//TODO
            return statm1.execute();

    }

    public LocationQR read (Object obj) {
        return null;
    }

    public boolean update (LocationQR obj) {
        return false;
    }

    public boolean delete(LocationQR obj) throws SQLException {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO LesLocationsQR "+
                "WHERE id=? AND noFilm=?");
            statm1.setInt(1,obj.getPersonneId());//Changer model
            statm1.setInt(2,obj.getFilmId());
            return statm1.execute();

    }
    
}