package BDD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;

public abstract class DAO<T> {
    protected Connection conn;
    
    protected DAO(Connection conn) {
        this.conn = conn;
    }
    
    public abstract boolean create(T obj) throws SQLException;

    public abstract T read (Object obj) throws SQLException;

    public abstract boolean update (T obj) throws SQLException;

    public abstract boolean delete(T obj) throws SQLException;
    
    public abstract HashSet<T> readAll (Object obj) throws SQLException;

}