package dao;

public class LocationDAO extends DAO<Location> {
	
	protected DAO(Connection conn) {
		System.err.println("NON IMPLEMENTEES !!!!");
        super(conn);
    }
	
    public boolean create(Film obj) {
        try {
            return false;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }

    public Film read (Object obj) {
        return null;
    }

    public boolean update (Film obj) {
        return false;
    }

    public boolean delete(Film obj) {
        try {
            return false;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }
}

