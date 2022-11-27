package BDD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

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

	@Override
	public HashSet<Guest> readAll(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}

