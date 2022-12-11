package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

import fc.BluRay;

public class BluRayDAO extends DAO<BluRay> {    
    protected BluRayDAO(Connection conn) {
        super(conn);
    }
    
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

    public BluRay read (Object obj) {
        return null;
    }

    public boolean update (BluRay obj) {
        return false;
    }

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

	@Override
	public HashSet<BluRay> readAll(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}