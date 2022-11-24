package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import fc.LocationBR;

public class LocationBRDAO extends DAO<LocationBR> {    
    protected LocationBRDAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(LocationBR obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "INSERT INTO LesLocationsBR "+
                "VALUES (?,?,?,?)");
            statm1.setInt(1,/*generer id personne*/ );
            statm1.setInt(2,obj.getBRId());//Changer model
            statm1.setDate(3,);//TODO : date
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }

    public LocationBR read (Object obj) {
        return null;
    }

    public boolean update (LocationBR obj) {
        return false;
    }

    public boolean delete(LocationBR obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO LesLocationsBR "+
                "WHERE id=? AND idBR=?");
            statm1.setInt(1,obj.getPersonneId());//Changer model
            statm1.setInt(2,obj.getBRId());
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }
}