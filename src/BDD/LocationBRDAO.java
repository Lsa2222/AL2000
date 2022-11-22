package dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

public class LocationBRDAO<LocationBR> extends DAO {    
    protected DAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(Location obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "INSERT INTO LesLocationsBR "+
                "VALUES (?,?,?,?)");
            statm1.setInt(1,/*generer id personne*/ );
            statm1.setInt(2,obj.getBR().getId());//Changer model
            statm1.setInt(3,obj.getDate());
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
                "DELETE INTO LesLocationsBR "+
                "WHERE id=? AND idBR=?");
            statm1.setInt(1,obj.getPersonne().getId());//Changer model
            statm1.setInt(2,obj.getBR().getId());
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }
}