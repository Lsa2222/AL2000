package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import fc.Personne;

public abstract class PersonneDAO extends DAO<Personne> {
	
	protected PersonneDAO(Connection conn) {
		super(conn);
    }
	
    public boolean create(Personne obj) {
    	PreparedStatement queryPersonne = null;
    	ResultSet res = null;
    	try {
    	    PreparedStatement statmPeronne;
    		statmPeronne = conn.prepareStatement(
    			        "INSERT INTO LesPersonnes "+
    			        "VALUES (?,?)");
    		statmPeronne.setInt(1,obj.getId());
    		statmPeronne.setObject(2,obj.getCb());
    		statmPeronne.execute();
    		
    	    return true;
    	} catch(SQLException e) {
    		return false;
    	} finally {
			try {
            	if(res!=null) {
            		res.close();}
            	if(queryPersonne!=null) {
            		queryPersonne.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
    }

    public Personne read (Object obj) {
        return null;
    }

    public boolean update (Personne obj) {
        return false;
    }

    public boolean delete(Personne obj) {
    	return false;

    }

	@Override
	public HashSet<Personne> readAll(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}

