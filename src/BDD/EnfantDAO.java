package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import fc.Abonne;
import fc.Enfant;
import fc.Tag;

public class EnfantDAO extends DAO<Enfant>{

	DAO<Abonne> aboDAO;
	
	protected EnfantDAO(Connection conn, DAO<Abonne> aboDAO) {
		super(conn);
		this.aboDAO = aboDAO;
	}
	
	/**
	Ajoute l'enfant obj a la BD.
	N'ajoute pas les location de obj.
	Mes a jour son identifiant.
	@param obj	l'enfant a ajouter a la BD. Ne doit pas dejat etre dans la BD
	@return vrais si l'ajout est reussi
	 **/
	@Override
	public boolean create(Enfant obj) {
		PreparedStatement statmRestriction = null;
		PreparedStatement statmEnfant = null;
		try {
			if(!aboDAO.create(obj)) {
				return false;
			}
			
			statmRestriction = conn.prepareStatement(
	                "INSERT INTO LesRestrictions "+
	                "VALUES (?,?)");
			Iterator<Tag> it = obj.getRestrIterator();
			statmRestriction.setInt(1, obj.getId());
			
			while(it.hasNext()) {
				statmRestriction.setString(2, it.next().toString());
				if(!statmRestriction.execute()) {
					return false;
				}
			}
			
			statmEnfant = conn.prepareStatement(
	                "INSERT INTO LesEnfants "+
	                "VALUES (?,?)");
			statmEnfant.setInt(1, obj.getId());
			statmEnfant.setInt(2, obj.getParentId());
			
			statmEnfant.execute();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
			try {
            	if(statmRestriction!=null) {
            		statmRestriction.close();}
            	if(statmEnfant!=null) {
            		statmEnfant.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

	/**
	Creer l'enfant obj a partir de son id.
	Ne remplie pas les listes contenue dans obj
	@param obj	(integer) l'id de l'enfant
	@return l'enfant ou null en cas d'echec
	 **/
	@Override
	public Enfant read(Object obj) {
		
		ResultSet res = null;
		PreparedStatement querryEnfant = null;
		try {
			Enfant ret;
			ret = Enfant.aboToEnfant(aboDAO.read(obj));
			int idEnf = (int) obj;
			querryEnfant = conn.prepareStatement(""
					+ "SELECT idParent "
					+ "FROM LesEnfants "
					+ "WHERE idEnfant = ?");
			querryEnfant.setInt(1, idEnf);
			res = querryEnfant.executeQuery();
			if(!res.next()) {
				ret.setIdParent(-1);// ?????
				return ret;
			}
			ret.setIdParent(res.getInt(1));// ?????
			return ret;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
            	if(res!=null) {
            		res.close();}
            	if(querryEnfant!=null) {
            		querryEnfant.close();}
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
	public boolean update(Enfant obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
     * Non utilisee
	@deprecated
	 **/
	@Override
	public boolean delete(Enfant obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashSet<Enfant> readAll(Object obj) {
		
		ResultSet res = null;
		PreparedStatement querryParent = null;
		try {
			int idParent = (int) obj;
			querryParent = conn.prepareStatement(""
					+ "SELECT idEnfant "
					+ "FROM LesEnfants "
					+ "WHERE idParent = ?");
			querryParent.setInt(1, idParent);
			res = querryParent.executeQuery();
			HashSet<Enfant> enfants = new HashSet<Enfant>();
			while(res.next()) {
				enfants.add(read(res.getInt(1)));
			}
			res.close();
			return enfants;
		} catch(SQLException e) {
			if(res!=null) {
				try {
					res.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
            	if(res!=null) {
            		res.close();}
            	if(querryParent!=null) {
            		querryParent.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

}
