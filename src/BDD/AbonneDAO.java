package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import fc.Abonne;

public class AbonneDAO extends DAO<Abonne> {
		
	protected AbonneDAO(Connection conn) {
		super(conn);
    }
	
	
    public boolean create(Abonne obj) {
    		ResultSet res = null;
    		PreparedStatement statmAbonne = null;
    		PreparedStatement queryPersonne = null;
    		PreparedStatement statmPeronne = null;
    		try {
    			statmPeronne = conn.prepareStatement(
    			        "INSERT INTO LesPersonnes "+
    			        "VALUES (?,?)");
    			statmPeronne.setInt(1,obj.getId());
    			statmPeronne.setObject(2,obj.getCb());
    			statmPeronne.execute();
                queryPersonne = conn.prepareStatement(
                        "SELECT id FROM LesPersonnes "
                        + "WHERE cb = ? "
                        + "ORDER BY id DESC");
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
    	        
    	        return true;
    		} catch(SQLException e) {
    			e.printStackTrace();
    			return false;
    		} finally {
    			try {
    				if(res!=null) {
    					res.close();
        			}if(statmAbonne!=null) {
        				statmAbonne.close();
        			}if(queryPersonne!=null) {
        				queryPersonne.close();
        			}
        			if(statmPeronne!=null) {
        				statmPeronne.close();
        			}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
    			
    		}
            
    }
    
    public Abonne read (Object obj) {
    	ResultSet res1 = null;
    	ResultSet res2 = null;
    	PreparedStatement querryAbonne = null;
    	PreparedStatement querryPersonne = null;
    	try {
    		int idPersonne = (int) obj;
            
        	querryAbonne = conn.prepareStatement(
                       "SELECT prenom, nom, credit, adrMail, AdrPhy "
                       + "FROM LesAbonnes "
                       + "WHERE id = ?");
        	
            querryAbonne.setInt(1, idPersonne);
            res1 = querryAbonne.executeQuery();
            if(!res1.next()) {
            	return null;
            }
                
            querryPersonne = conn.prepareStatement(
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
            return jean;
    	} catch(SQLException e) {
            
    		return null;
    	} finally {
    		try {
            	if(res1!=null) {
            		res1.close();}
            	if(res2!=null) {
            		res2.close();}
            	if(querryAbonne!=null) {
            		querryAbonne.close();}
            	if(querryPersonne!=null) {
            		querryPersonne.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	}
    }

    public boolean update (Abonne obj) {
    	PreparedStatement statmAbonne = null;
    		try {
    			statmAbonne = conn.prepareStatement(
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
    		} finally {
    			try {
                	if(statmAbonne!=null) {
                		statmAbonne.close();}
    			} catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    		}
    		
    }

    public boolean delete(Abonne obj) {
        return false;
    }


	@Override
	public HashSet<Abonne> readAll(Object obj) throws SQLException {
		return null;
	}
}

