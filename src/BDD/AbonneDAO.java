package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import fc.Abonne;
import fc.Personne;


public class AbonneDAO extends DAO<Abonne> {
	
	DAO<Personne> perDAO;
	
	protected AbonneDAO(Connection conn, DAO<Personne> perDAO) {
		super(conn);
		this.perDAO = perDAO; 
    }
	
	
    public boolean create(Abonne obj) throws SQLException {
            PreparedStatement statmAbonne;
			statmAbonne = conn.prepareStatement(
				        "INSERT INTO LesAbonnes"+
				        "VALUES (?,?,?,?,?,?)");
			statmAbonne.setInt(1, obj.getId());
	        statmAbonne.setString(2, obj.getPrenom());
	        statmAbonne.setString(3, obj.getNom());
	        statmAbonne.setInt(4,obj.getCredit());
	        statmAbonne.setString(5, obj.getAdrMail());
	        statmAbonne.setString(6, obj.getAdrPhys());
	        statmAbonne.execute();
	        return true;
    }
    
    public Abonne read (Object obj) throws SQLException {
    	
        int idPersonne = (int) obj;
        
    	PreparedStatement querryAbonne = conn.prepareStatement(
                   "SELECT prenom, nom, credit, adrMail, AdrPhy "
                   + "FROM LesAbonnes"
                   + "WHERE id = ?");
        querryAbonne.setInt(1, idPersonne);
        ResultSet res1 = querryAbonne.executeQuery();
        if(!res1.next()) {
        	return null;
        }
            
        PreparedStatement querryPersonne = conn.prepareStatement(
                    "SELECT cb"
                    + "FROM LesPersonnes"
                    + "WHERE id = ?");
        querryPersonne.setInt(1, idPersonne);
        ResultSet res2 = querryPersonne.executeQuery();
        if(!res2.next()) {
           	return null;
        }
            
        Abonne jean = new Abonne(idPersonne, res1.getString(1), res1.getString(2) , res1.getString(4), res1.getString(5), res1.getInt(3), res2.getInt(1));
                        
        return jean;
    	
    }

    public boolean update (Abonne obj) throws SQLException {
    		PreparedStatement statmAbonne = conn.prepareStatement(
                    "UPDATE LesAbonnes"+
                    "SET prenom = '?', "
                    + 	"nom = '?', "
                    + 	"credit = '?' ,"
                    + 	"adrMail = '?' ,"
                    + 	"AdrPhy = '?' "
                    + "WHERE id = obj.id");
            statmAbonne.setInt(1, obj.getId());
            statmAbonne.setString(2, obj.getPrenom());
            statmAbonne.setInt(3,obj.getCredit());
            statmAbonne.setString(4, obj.getNom());
            statmAbonne.setString(5, obj.getAdrMail());
            statmAbonne.setString(6, obj.getAdrPhys());
            
            statmAbonne.execute();
            return true;
    }

    public boolean delete(Abonne obj) {
        return false;
    }


	@Override
	public HashSet<Abonne> readAll(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}

