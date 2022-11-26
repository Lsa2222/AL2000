package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fc.LocationBR;

public class LocationBRDAO extends DAO<LocationBR> {    
    protected LocationBRDAO(Connection conn) {
        super(conn);
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

    public boolean update (LocationBR obj) throws SQLException {
        return false;
    }

    public boolean delete(LocationBR obj) throws SQLException {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO LesLocationsBR "+
                "WHERE id=? AND idBR=?");
            statm1.setInt(1,obj.getPersonneId());
            statm1.setInt(2,obj.getBRId());
            return statm1.execute();
    }
}