package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import fc.Personne;

public class PersonneDAO extends DAO<Personne> {
	
	protected PersonneDAO(Connection conn) {
		super(conn);
    }
	
    public boolean create(Personne obj) {
    	PreparedStatement queryPersonne = null;
    	ResultSet res = null;
    	try {
    		queryPersonne = conn.prepareStatement(
                    "SELECT id FROM LesPersonnes WHERE cb = ?");
    	    queryPersonne.setObject(1,obj.getCb());
    	    res = queryPersonne.executeQuery();
    	    if(!res.next()) {
    	    	PreparedStatement statmPeronne;
    			statmPeronne = conn.prepareStatement(
    			        "INSERT INTO LesPersonnes "+
    			        "VALUES (?,?)");
    			statmPeronne.setInt(1,0);
    		    statmPeronne.setObject(2,obj.getCb());//obj.getCb() doit renvoyer un type compatible avec la taille d'une CB
    		    statmPeronne.execute();
    		    
    		    res.close();
    		    res = queryPersonne.executeQuery();
    	    } else {
    	    	obj.setId(res.getInt(1));
    	    }
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

