package cinema.model.dao;

import cinema.exception.DAOException;
import cinema.model.domain.codicePrenotazione;

import java.sql.*;

public class EffettuaPrenotazioneDAO implements GenericProcedureDAO<codicePrenotazione>{
    @Override
    public codicePrenotazione execute (Object... params) throws DAOException{
        int salaPrenotazione = (int)params[0];
        java.sql.Date dataPrenotazione = (java.sql.Date)params[1];
        java.sql.Time oraPrenotazione = (java.sql.Time)params[2];
        char filaPostoPrenotazione = (char)params[3];
        int numeroPostoPrenotazione = (int)params[4];

        int codicePrenotazioneEffettuata;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call EffettuaPrenotazione(?,?,?,?,?,?)}");
            cs.setInt(1, salaPrenotazione);
            cs.setDate(2, dataPrenotazione);
            cs.setTime(3, oraPrenotazione);
            cs.setString(4,String.valueOf(filaPostoPrenotazione));
            cs.setInt(5, numeroPostoPrenotazione);
            cs.registerOutParameter(6, Types.NUMERIC);

            boolean hadResults = cs.execute();
            // Processa tutti gli eventuali result set ritornati
            while (hadResults) {
                ResultSet rs = cs.getResultSet();
                hadResults = cs.getMoreResults();
            }
            // recupera tutti i parametri di output
            codicePrenotazioneEffettuata = cs.getInt(6);

        } catch(SQLException e) {
            throw new DAOException("Errore completamento Prenotazione: " + e.getMessage());
        }

        return new codicePrenotazione(codicePrenotazioneEffettuata);
    }
}
