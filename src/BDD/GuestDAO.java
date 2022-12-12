package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import fc.Guest;

public class GuestDAO extends DAO<Guest> {
	
	protected GuestDAO(Connection conn) {
		super(conn);
    }
	
	/**
	Ajoute le guest a la BD.
	Ne le rajoute pas si il est deja present. 
	Met a jour son identifiant.
	@param obj	le guest a ajouter a la BD
	@return vrais si l'ajout est reussi
	 **/
    public boolean create(Guest obj) {
    	PreparedStatement queryPersonne = null;
    	PreparedStatement statmPeronne = null;
    	ResultSet res = null;
    	try {
    		queryPersonne = conn.prepareStatement(
                    "SELECT id FROM LesPersonnes WHERE cb = ?");
    	    queryPersonne.setObject(1,obj.getCb());
    	    res = queryPersonne.executeQuery();
    	    if(!res.next()) {
    			statmPeronne = conn.prepareStatement(
    			        "INSERT INTO LesPersonnes "+
    			        "VALUES (?,?)");
    			statmPeronne.setInt(1,obj.getId());
    		    statmPeronne.setObject(2,obj.getCb());//obj.getCb() doit renvoyer un type compatible avec la taille d'une CB
    		    statmPeronne.execute();
    		    
    		    res.close();
    		    res = queryPersonne.executeQuery();
    	    }
    	    obj.setId(res.getInt(1));

    	    return true;
    	} catch(SQLException e) {
    		return false;
    	} finally {
			try {
            	if(res!=null) {
            		res.close();}
            	if(queryPersonne!=null) {
            		queryPersonne.close();}
            	if(statmPeronne!=null) {
            		statmPeronne.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public Guest read (Object obj) {
        return null;
    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean update (Guest obj) {
        return false;
    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean delete(Guest obj) {
        return false;

    }

    /**
     * Non utilisee
	@deprecated
	 **/
	@Override
	public HashSet<Guest> readAll(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}

