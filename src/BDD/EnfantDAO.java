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
	
	@Override
	public boolean create(Enfant obj) throws SQLException {
		
		if(!aboDAO.create(obj)) {
			return false;
		}
		
		
		
		PreparedStatement statmRestriction = conn.prepareStatement(
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
		
		PreparedStatement statmEnfant = conn.prepareStatement(
                "INSERT INTO LesEnfants "+
                "VALUES (?,?)");
		statmEnfant.setInt(1, obj.getId());
		statmEnfant.setInt(2, obj.getParentId());
		
		statmEnfant.execute();
		return true;
	}

	@Override
	public Enfant read(Object obj) {
		
		ResultSet res = null;
		
		
		try {
			Enfant ret;
			ret = Enfant.aboToEnfant(aboDAO.read(obj));
			int idEnf = (int) obj;
			PreparedStatement querryEnfant = conn.prepareStatement(""
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
			res.close();
			return ret;
		} catch (SQLException e) {
			if(res!=null) {
				try {
					res.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		}
		
	}

	@Override
	public boolean update(Enfant obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Enfant obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashSet<Enfant> readAll(Object obj) {
		
		ResultSet res = null;
		
		try {
			int idParent = (int) obj;
			PreparedStatement querryParent = conn.prepareStatement(""
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
		}
		
		
	}

}
