package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

import fc.Film;
import fc.Tag;

public class TagDAO extends DAO<Tag> {    
    protected TagDAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(Tag obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "INSERT INTO Tags"+
                "VALUES (?)");
            statm1.setString(1,obj.toString());
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }

    public Tag read (Object obj) {
        return null;
    }

    public boolean update (Tag obj) {
        return false;
    }

    public boolean delete(Tag obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO Tags "+
                "WHERE tag=?");
            statm1.setString(1,obj.toString());//Changer model
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }
    
    public HashSet<Tag> listTagFilm(int idFilm) throws SQLException{
    	HashSet<Tag> tab= new HashSet();
    	PreparedStatement queryFilm = conn.prepareStatement(""
    			+ "SELECT tag"
    			+ "FROM TagsFilm"
    			+ "WHERE noFilm = ?");
    	queryFilm.setInt(1,idFilm);
    	ResultSet res = queryFilm.executeQuery();
    	while(res.next()) {
    		tab.add(Tag.strToTag(res.getString(1)));
    	}
    	return tab;
    }
}