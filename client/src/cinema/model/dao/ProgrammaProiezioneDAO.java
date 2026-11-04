package cinema.model.dao;

import cinema.exception.DAOException;
import cinema.model.domain.erroriAddProiezione;

import java.sql.*;

public class ProgrammaProiezioneDAO implements GenericProcedureDAO<erroriAddProiezione> {
    @Override
    public erroriAddProiezione execute(Object... params) throws DAOException {
        int sala = (int) params[0];
        java.sql.Date data = (java.sql.Date) params[1];
        java.sql.Time ora =  (java.sql.Time) params[2];
        String titolo = (String) params[3];
        java.math.BigDecimal costoBiglietto =  (java.math.BigDecimal) params[4];


        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call ProgrammaProiezione(?,?,?,?,?)}");
            cs.setInt(1, sala);
            cs.setDate(2, data);
            cs.setTime(3, ora);
            cs.setString(4, titolo);
            cs.setBigDecimal(5, costoBiglietto);
            cs.execute();
        }
        catch(SQLException e){
            String state = e.getSQLState();
            switch (state) {
                case "45003" -> {
                    // c'è stato un overlap
                    return erroriAddProiezione.OVERLAP;
                }
                case "45007" -> {
                    return erroriAddProiezione.OLDDAY;
                }
                case "45008" -> {
                    return erroriAddProiezione.OLDTIME;
                }
                default -> throw new DAOException("ProgrammaProiezione errore: " + e.getMessage());
            }
        }

        return erroriAddProiezione.OK;
    }


}