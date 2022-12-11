package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashSet;

import fc.Tag;

public class TagDAO extends DAO<Tag> {    
    protected TagDAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(Tag obj) {
    	PreparedStatement statm1 = null;
    	try {
            statm1 = conn.prepareStatement(
                "INSERT INTO Tags "+
                "VALUES (?)");
            statm1.setString(1,obj.toString());
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        } finally {
			try {
            	if(statm1!=null) {
            		statm1.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
    }

    public Tag read (Object obj) {
        return null;
    }

    public boolean update (Tag obj) {
        return false;
    }

    public boolean delete(Tag obj) {
    	PreparedStatement statm1 = null;
    	try {
            statm1 = conn.prepareStatement(
                "DELETE INTO Tags "+
                "WHERE tag=?");
            statm1.setString(1,obj.toString());//Changer model
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        } finally {
			try {
            	if(statm1!=null) {
            		statm1.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
    }

	@Override
	public HashSet<Tag> readAll(Object obj) {
		ResultSet res = null;
		PreparedStatement queryFilm = null;
		try {
			int idFilm = (int) obj;
			
			HashSet<Tag> tab= new HashSet<>();
	    	queryFilm = conn.prepareStatement(""
	    			+ "SELECT tag "
	    			+ "FROM TagsFilm "
	    			+ "WHERE noFilm = ?");
	    	queryFilm.setInt(1,idFilm);
	    	res = queryFilm.executeQuery();
	    	while(res.next()) {
	    		tab.add(Tag.valueOf(res.getString(1)));
	    	}
	    	res.close();
	    	return tab;
		} catch(SQLException e) {
			if(res!=null) {
				try {
					res.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
            	if(res!=null) {
            		res.close();}
            	if(queryFilm!=null) {
            		queryFilm.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}