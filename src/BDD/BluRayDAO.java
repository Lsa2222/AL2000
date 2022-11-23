package dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

public class BluRayDAO extends DAO<BluRay> {    
    protected DAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(BluRay obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "INSERT INTO LesBlueRay "+
                "VALUES (?,?,?)");
            statm1.setInt(1,obj.getId());
            statm1.setInt(2,obj.getFilm().getFilm());//Changer model
			statm1.setVarchar2(3,obj.getEtat());
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
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
            PreparedStatement statm1 = conn.PreparedStatement(
                "DELETE INTO LesBlueRay "+
                "WHERE idBR=?");
            statm1.setInt(1,obj.getId());//Changer model
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }
}