package BDD;

import java.sql.Connection;

import fc.Abonne;

public class AbonneDAO extends DAO<Abonne> {
	
	protected AbonneDAO(Connection conn) {
		super(conn);
		System.err.println("NON IMPLEMENTEES !!!!");
    }
	
    public boolean create(Abonne obj) {
        return false;
    }

    public Abonne read (Object obj) {
        return null;
    }

    public boolean update (Abonne obj) {
        return false;
    }

    public boolean delete(Abonne obj) {
        return false;
    }
}

