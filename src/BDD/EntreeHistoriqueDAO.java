package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.HashSet;

import fc.EntreeHistorique;

public class EntreeHistoriqueDAO extends DAO<EntreeHistorique> {    
    protected EntreeHistoriqueDAO(Connection conn) {
        super(conn);
    }
    
    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean create(EntreeHistorique obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "INSERT INTO HistoriqueLocation "+
                "VALUES (?,?,?,?,?)");
            //statm1.setInt(1,/*generer id personne*/ );
            statm1.setInt(2,obj.getFilmId());//Changer model
			statm1.setInt(3,obj.getBRId());
            //statm1.setDate(4,obj.getDate());//TODO : Convertion date
			//statm1.setDate(5,/*récup date actuelle*/);
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public EntreeHistorique read (Object obj) {
        return null;
    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean update (EntreeHistorique obj) {
        return false;
    }

    /**
     * Non utilisee
	@deprecated
	 **/
    public boolean delete(EntreeHistorique obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO HistoriqueLocation "+
                "WHERE id=? AND noFilm=? AND idBR=?");
            statm1.setInt(1,obj.getPersonneId());//Changer model
			statm1.setInt(2, obj.getFilmId());
            statm1.setInt(3,obj.getBRId());
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }

    /**
     * Non utilisee
	@deprecated
	 **/
	@Override
	public HashSet<EntreeHistorique> readAll(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}