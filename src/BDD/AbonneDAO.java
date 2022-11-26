package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import fc.Abonne;
import fc.Film;
import fc.LocationQR;
import fc.Tag;

public class AbonneDAO extends DAO<Abonne> {
	
	protected AbonneDAO(Connection conn) {
		super(conn);
		System.err.println("NON IMPLEMENTEES !!!!");
    }
	
    public boolean create(Abonne obj) throws SQLException {//ATTENTION SYNCHRO !!!!!!
            PreparedStatement queryPeronne = conn.prepareStatement(
                    "SELECT id FROM LesPersonnes WHERE cb = ?");
            queryPeronne.setObject(1,obj.getCb());//obj.getCb() doit renvoyer un type compatible avec la taille d'une CB
            ResultSet res = queryPeronne.executeQuery();
            
            if(!res.next()) {
            	PreparedStatement statmPeronne = conn.prepareStatement(
                        "INSERT INTO LesPersonnes"+
                        "VALUES (?,?)");
            	statmPeronne.setInt(1,0);//TODO : Increment automatique
            	statmPeronne.setObject(2,obj.getCb());//obj.getCb() doit renvoyer un type compatible avec la taille d'une CB
            	statmPeronne.execute();
            	res = queryPeronne.executeQuery();
            	if(!res.next()) {
            		return false;
            	}
            }
            int idPersonne=res.getInt(1);//suffisant en taille ?
            
            obj.setId(idPersonne);
            
            PreparedStatement statmAbonne = conn.prepareStatement(
                    "INSERT INTO LesAbonnes"+
                    "VALUES (?,?,?,?,?,?)");
            statmAbonne.setInt(1, idPersonne);
            statmAbonne.setString(2, obj.getPrenom());
            statmAbonne.setString(3, obj.getNom());
            statmAbonne.setInt(4,obj.getCredit());
            statmAbonne.setString(5, obj.getAdrMail());
            statmAbonne.setString(6, obj.getAdrPhys());
            
            return statmAbonne.execute();
    }
    
    private HashSet<Tag> listTagFilm(int idFilm) throws SQLException{
    	HashSet<Tag> tab= new HashSet();
    	PreparedStatement queryFilm = conn.prepareStatement(""
    			+ "SELECT tag"
    			+ "FROM TagsFilm"
    			+ "WHERE noFilm = ?");
    	queryFilm.setInt(1,idFilm);
    	ResultSet res = queryFilm.executeQuery();
    	while(res.next()) {
    		tab.add(Tag.strToTag(res.getString(1)));
    	}
    	return tab;
    }

    public Abonne read (Object obj) {
    	
        int idPersonne = (int) obj;
        
    	try {//TODO
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
            
            PreparedStatement querryLocationQR = conn.prepareStatement(""
            		+ "SELECT "
            		+ "f.noFilm, f.titre, f.realisateur, f.genre, f.resumer "
            		+ "FROM LesLocationsQR l, LesFilms f "//JOIN
            		+ "WHERE l.id = ? AND "
            		+ "f.noFilm = l.noFilm");
            ResultSet res3 = querryLocationQR.executeQuery();
            while(res3.next()) {
            	jean.addLocation(new LocationQR(
            			new Film(res3.getInt(1),
            					res3.getString(2),
            					res3.getString(3),
            					res3.getString(4),
            					listTagFilm(res3.getInt(1)),
            					res3.getString(6))
            			,jean));
            }
            
            //TODO BR
            
            return jean;
    		
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return null;
        }
    }

    public boolean update (Abonne obj) {
    	try {//TODO
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
            
            return statmAbonne.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }

    public boolean delete(Abonne obj) {
        return false;
    }
}

