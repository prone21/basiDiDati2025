package cinema.model.dao;
import cinema.exception.DAOException;

import java.sql.*;

public class AggiungiFilmDAO implements GenericProcedureDAO<Boolean> {
    @Override
    public Boolean execute(Object... params) throws DAOException{
        String titoloFilm = (String) params[0];
        int durataFilm = (Integer) params[1];
        String casaCinematograficaFilm = (String) params[2];
        String cast = (String) params[3];

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call AggiungiFilm(?,?,?,?)}");
            cs.setString(1, titoloFilm);
            cs.setInt(2, durataFilm);
            cs.setString(3, casaCinematograficaFilm);
            cs.setString(4, cast);
            cs.execute();
        }
        catch(SQLException e){
            throw new DAOException("Aggiungifilm errore: " + e.getMessage());
        }

        return true;
    }


}
