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
	
	
    public boolean create(Abonne obj) {
    		ResultSet res = null;
    		PreparedStatement statmAbonne = null;
    		PreparedStatement queryPersonne = null;
    		try {
                queryPersonne = conn.prepareStatement(
                        "SELECT id FROM LesPersonnes WHERE cb = ?");
        	    queryPersonne.setObject(1,obj.getCb());
        	    res = queryPersonne.executeQuery();
        	    if(!res.next()) {
        	    	return false;
        	    }
        	    
        	    obj.setId(res.getInt(1));
        	    
    			statmAbonne = conn.prepareStatement(
    				        "INSERT INTO LesAbonnes "+
    				        "VALUES (?,?,?,?,?,?)");
    			statmAbonne.setInt(1, obj.getId());
    	        statmAbonne.setString(2, obj.getPrenom());
    	        statmAbonne.setString(3, obj.getNom());
    	        statmAbonne.setInt(4,obj.getCredit());
    	        statmAbonne.setString(5, obj.getAdrMail());
    	        statmAbonne.setString(6, obj.getAdrPhys());
    	        statmAbonne.execute();
    	        
    	        res.close();//TODO try catch
    	        return true;
    		} catch(SQLException e) {
    			if(res!=null) {
    				try {
						res.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
    			}
    			return false;
    		}
            
    }
    
    public Abonne read (Object obj) {
    	ResultSet res1 = null;
    	ResultSet res2 = null;
    	try {
    		int idPersonne = (int) obj;
            
        	PreparedStatement querryAbonne = conn.prepareStatement(
                       "SELECT prenom, nom, credit, adrMail, AdrPhy "
                       + "FROM LesAbonnes "
                       + "WHERE id = ?");
        	
            querryAbonne.setInt(1, idPersonne);
            res1 = querryAbonne.executeQuery();
            if(!res1.next()) {
            	return null;
            }
                
            PreparedStatement querryPersonne = conn.prepareStatement(
                        "SELECT cb "
                        + "FROM LesPersonnes "
                        + "WHERE id = ?");
            querryPersonne.setInt(1, idPersonne);
            res2 = querryPersonne.executeQuery();
            if(!res2.next()) {
               	return null;
            }
                    
            Abonne jean = new Abonne(res1.getString(1), res1.getString(2) , res1.getString(4), res1.getString(5), res1.getInt(3), res2.getBigDecimal(1).toBigInteger());
            jean.setId(idPersonne);
            res1.close();
            res2.close();
            return jean;
    	} catch(SQLException e) {
            try {
            	if(res1!=null) {
            		res1.close();}
            	if(res2!=null) {
            		res2.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		return null;
    	}
    }

    public boolean update (Abonne obj) {
    		try {
    			PreparedStatement statmAbonne = conn.prepareStatement(
                        "UPDATE LesAbonnes "+
                        "SET prenom = ?, "
                        + 	"nom = ?, "
                        + 	"credit = ?, "
                        + 	"adrMail = ?, "
                        + 	"AdrPhy = ? "
                        + "WHERE id = ? ");
                statmAbonne.setString(1, obj.getPrenom());
                statmAbonne.setString(2, obj.getNom());
                statmAbonne.setInt(3,obj.getCredit());
                
                statmAbonne.setString(4, obj.getAdrMail());
                statmAbonne.setString(5, obj.getAdrPhys());
                statmAbonne.setInt(6, obj.getId());
                
                statmAbonne.execute();
                return true;
    		} catch(SQLException e) {
    			return false;
    		}
    		
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

