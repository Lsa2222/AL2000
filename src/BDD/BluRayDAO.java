package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

import fc.BluRay;

public abstract class BluRayDAO extends DAO<BluRay> {    
    protected BluRayDAO(Connection conn) {
        super(conn);
    }
    
    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean create(BluRay obj) {
    	PreparedStatement statm1 = null;
    	try {
            statm1 = conn.prepareStatement(
                "INSERT INTO LesBlueRay "+
                "VALUES (?,?,?)");
            statm1.setInt(1,obj.getId());
            statm1.setInt(2,obj.getFilmId());
            
            String etatStr;
            if(obj.getEtat()) {
            	etatStr = "good";
            } else {
            	etatStr = "raye";
            }
            
			statm1.setString(3,etatStr);
            statm1.execute();
            return true;
        } catch (SQLException e) {
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
    
    /**
     * Non utilisee
	@deprecated
	 **/
    public BluRay read (Object obj) {
        return null;
    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean update (BluRay obj) {
        return false;
    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean delete(BluRay obj) {
    	PreparedStatement statm1 = null;
    	try {
            statm1 = conn.prepareStatement(
                "DELETE INTO LesBlueRay "+
                "WHERE idBR=?");
            statm1.setInt(1,obj.getId());//Changer model
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

    /**
     * Non utilisee
	@deprecated
	 **/
	@Override
	public HashSet<BluRay> readAll(Object obj) throws SQLException {
		return null;
	}
}