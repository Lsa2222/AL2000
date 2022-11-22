package dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashSet;
import java.util.Set;

public class FilmDAO<LocationBR> extends DAO {    
    protected DAO(Connection conn) {
        super(conn);
    }
    
    public boolean create(Film obj) {
        try {
            PreparedStatement statm1 = conn.PreparedStatement(
                "INSERT INTO LesFilms "+
                "VALUES (?,?,?,?,?)");
            statm1.setInt(1,obj.getFilm() );
            statm1.setVarchar2(2,obj.getTitre());//Changer model
			statm1.setVarchar2(3,obj.getBR.getRealisateur());
            statm1.setVarchar2(4,obj.getGenre());
			statm1.setVarchar2(5,obj.getResumer());
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
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
            PreparedStatement statm1 = conn.PreparedStatement(
                "DELETE INTO LesFilms "+
                "WHERE noFilm=?");
            statm1.setInt(1,obj.getFilm());//Changer model
            statm1.execute();
        } catch (SQLException e) {
            System.out.println("Hu,Ho...");
        }
    }
}