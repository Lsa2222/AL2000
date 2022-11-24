package BDD;

import java.sql.Connection;

import fc.Personne;

public class PersonneDAO extends DAO<Personne> {
	
	protected PersonneDAO(Connection conn) {
		super(conn);
		System.err.println("NON IMPLEMENTEES !!!!");
        
    }
	
    public boolean create(Personne obj) {
    	return false;
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
}

