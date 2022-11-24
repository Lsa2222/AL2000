package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fc.BluRay;

public class BluRayDAO extends DAO<BluRay> {    
    protected BluRayDAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(BluRay obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "INSERT INTO LesBlueRay "+
                "VALUES (?,?,?)");
            statm1.setInt(1,obj.getId());
            statm1.setInt(2,obj.getFilmId());
			statm1.setBoolean(3,obj.getEtat());
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }

    public BluRay read (Object obj) {
        return null;
    }

    public boolean update (BluRay obj) {
        return false;
    }

    public boolean delete(BluRay obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO LesBlueRay "+
                "WHERE idBR=?");
            statm1.setInt(1,obj.getId());//Changer model
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }
}