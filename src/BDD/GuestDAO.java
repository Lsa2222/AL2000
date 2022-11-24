package BDD;

import java.sql.Connection;

import fc.Guest;

public class GuestDAO extends DAO<Guest> {
	
	protected GuestDAO(Connection conn) {
		super(conn);
		System.err.println("NON IMPLEMENTEES !!!!");
    }
	
    public boolean create(Guest obj) {
        return false;

    }

    public Guest read (Object obj) {
        return null;
    }

    public boolean update (Guest obj) {
        return false;
    }

    public boolean delete(Guest obj) {
        return false;

    }
}

