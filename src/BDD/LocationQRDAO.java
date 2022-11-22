package dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

public class LocationQRDAO<LocationBR> extends DAO {    
    protected DAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(Location obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "INSERT INTO LesLocationsQR "+
                "VALUES (?,?,?,?)");
            statm1.setInt(1,/*generer id personne*/ );
            statm1.setInt(2,obj.getFilm());//Changer model
			statm1.setDate(3,obj.getActivation());
            statm1.setDate(4,obj.getDate());
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }

    public Location read (Object obj) {
        return null;
    }

    public boolean update (Location obj) {
        return false;
    }

    public boolean delete(Location obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "DELETE INTO LesLocationsQR "+
                "WHERE id=? AND noFilm=? AND dateActivation=?");
            statm1.setInt(1,obj.getPersonne().getId());//Changer model
            statm1.setInt(2,obj.getBR().getId());
			statm1.setDate(3, obj.getActivation());
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }
}