package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fc.Film;

public class FilmDAO extends DAO<Film> {    
    protected FilmDAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(Film obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "INSERT INTO LesFilms "+
                "VALUES (?,?,?,?,?)");
            statm1.setInt(1,obj.getId() );
            statm1.setString(2,obj.getTitre());//Changer model
			statm1.setString(3,obj.getRealisateur());
            statm1.setString(4,obj.getGenre());
			statm1.setString(5,obj.getResumer());
            statm1.execute();
            return true;
        } catch (SQLException e) {
        	System.out.println("Hu,Ho...");
        	return false;
            
        }
    }

    public Film read (Object obj) {
        return null;
    }

    public boolean update (Film obj) {
        return false;
    }

    public boolean delete(Film obj) {
        try {
            PreparedStatement statm1 = conn.prepareStatement(
                "DELETE INTO LesFilms "+
                "WHERE noFilm=?");
            statm1.setInt(1,obj.getId());//Changer model
            statm1.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
            return false;
        }
    }
}