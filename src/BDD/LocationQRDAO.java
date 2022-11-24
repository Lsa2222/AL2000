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
    
    public boolean create(LocationQR obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "INSERT INTO LesLocationsQR "+
                "VALUES (?,?,?,?)");
            statm1.setInt(1,/*generer id personne*/ );
            statm1.setInt(2,obj.getFilmId());//Changer model
			statm1.setDate(3,obj.getActivationDate());//TODO
            statm1.setDate(4,obj.getDate());//TODO
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }

    public LocationQR read (Object obj) {
        return null;
    }

    public boolean update (LocationQR obj) {
        return false;
    }

    public boolean delete(LocationQR obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO LesLocationsQR "+
                "WHERE id=? AND noFilm=? AND dateActivation=?");
            statm1.setInt(1,obj.getPersonneId());//Changer model
            statm1.setInt(2,obj.getQRId());//???????
			statm1.setDate(3, obj.getActivationDate());//TODO
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }
}