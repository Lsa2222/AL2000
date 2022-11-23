package dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

public class TagDAO extends DAO<Tag> {    
    protected DAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(Tag obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "INSERT INTO Tags"+
                "VALUES (?)");
            statm1.setVarchar2(1,obj);
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }

    public Location read (Object obj) {
        return null;
    }

    public boolean update (Tag obj) {
        return false;
    }

    public boolean delete(Tag obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "DELETE INTO Tags "+
                "WHERE tag=?");
            statm1.setVarchar2(1,obj);//Changer model
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }
}