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
	
    public boolean create(Personne obj) throws SQLException {
    	PreparedStatement queryPersonne = conn.prepareStatement(
                "SELECT id FROM LesPersonnes WHERE cb = ?");
	    queryPersonne.setObject(1,obj.getCb());
	    ResultSet res = queryPersonne.executeQuery();
	    if(!res.next()) {
	    	PreparedStatement statmPeronne;
			statmPeronne = conn.prepareStatement(
			        "INSERT INTO LesPersonnes "+
			        "VALUES (?,?)");
			statmPeronne.setInt(1,0);
		    statmPeronne.setObject(2,obj.getCb());//obj.getCb() doit renvoyer un type compatible avec la taille d'une CB
		    statmPeronne.execute();
		    
		    res = queryPersonne.executeQuery();
	    }
    	
	    if(!res.next()) {
			return false;
	    }
	    int id = res.getInt(1);	    
	    obj.setId(id);
	    return true;
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

