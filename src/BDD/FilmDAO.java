package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import fc.Film;
import fc.Tag;

public class FilmDAO extends DAO<Film> {
	
	DAO<Tag> tagDAO;
	
    protected FilmDAO(Connection conn, DAO<Tag> tagDAO) {
        super(conn);
        this.tagDAO = tagDAO;
    }
    
    public boolean create(Film obj) {
    	PreparedStatement statm1 = null;
    	try {
            statm1 = conn.prepareStatement(
                "INSERT INTO LesFilms "+
                "VALUES (?,?,?,?,?)");
            statm1.setInt(1,obj.getId() );
            statm1.setString(2,obj.getTitre());//Changer model
			statm1.setString(3,obj.getRealisateur());
            statm1.setString(4,"PAS DE GENRE DANS LE FC ");
			statm1.setString(5,obj.getResumer());
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

    public Film read (Object obj) {
        return null;
    }

    public boolean update (Film obj) {
        return false;
    }

    public boolean delete(Film obj) {
    	PreparedStatement statm1 = null;
    	try {
            statm1 = conn.prepareStatement(
                "DELETE INTO LesFilms "+
                "WHERE noFilm=?");
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
	public HashSet<Film> readAll(Object obj) {
		ResultSet res = null;
		PreparedStatement queryFilm  = null;
		try {
			queryFilm = conn.prepareStatement(""
					+ "SELECT "
	        		+ "noFilm, titre, realisateur, resumer, genre "
	        		+ "FROM LesFilms ");
			HashSet<Film> liste = new HashSet<>();
			res = queryFilm.executeQuery();
			while(res.next()) {
				liste.add(new Film(res.getInt(1),
						res.getString(2),
						res.getString(3),
						res.getString(4),
						tagDAO.readAll(res.getInt(1)),
						res.getString(5)));
			}
			return liste;
		} catch(SQLException e){
			return null;
		} finally {
			try {
            	if(res!=null) {
            		res.close();}
            	if(queryFilm!=null) {
            		queryFilm.close();}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
}