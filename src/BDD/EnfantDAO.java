package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import fc.Abonne;
import fc.Enfant;
import fc.Tag;

public class EnfantDAO extends AbonneDAO{

	protected EnfantDAO(Connection conn) {
		super(conn);
	}
	
	@Override
	public boolean create(Abonne obj) throws SQLException {
		//TODO : erreur
		return false;
	}

	public boolean create(Enfant obj) throws SQLException {
		if(!super.create(obj)) {
			return false;
		}
		
		PreparedStatement statmRestriction = conn.prepareStatement(
                "INSERT INTO LesRestrictions"+
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
                "INSERT INTO LesEnfants"+
                "VALUES (?,?)");
		statmEnfant.setInt(1, obj.getId());
		statmEnfant.setInt(2, obj.getParentId());
		
		return statmEnfant.execute();
	}

}
