package dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

public class EntreeHistoriqueDAO<LocationBR> extends DAO {    
    protected DAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(EntreeHistorique obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "INSERT INTO HistoriqueLocation "+
                "VALUES (?,?,?,?,?)");
            statm1.setInt(1,/*generer id personne*/ );
            statm1.setInt(2,obj.getFilm());//Changer model
			statm1.setInt(3,obj.getBR.getId());
            statm1.setDate(4,obj.getDate());
			statm1.setDate(5,/*récup date actuelle*/)
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }

    public EntreeHistorique read (Object obj) {
        return null;
    }

    public boolean update (EntreeHistorique obj) {
        return false;
    }

    public boolean delete(EntreeHistorique obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "DELETE INTO HistoriqueLocation "+
                "WHERE id=? AND noFilm=? AND idBR=?");
            statm1.setInt(1,obj.getPersonne().getId());//Changer model
			statm1.setInt(2, obj.getFilm.getFilm());
            statm1.setInt(3,obj.getBR().getId());
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }
}