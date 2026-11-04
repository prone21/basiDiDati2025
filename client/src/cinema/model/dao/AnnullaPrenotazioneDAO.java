package cinema.model.dao;

import cinema.exception.DAOException;
import cinema.model.domain.risultatoAnnullamento;

import java.sql.*;;

public class AnnullaPrenotazioneDAO implements GenericProcedureDAO<risultatoAnnullamento>{
    @Override
    public risultatoAnnullamento execute (Object... params) throws DAOException{
        int codicePrenotazione = (int)params[0];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call AnnullaPrenotazione(?)}");
            cs.setInt(1, codicePrenotazione);
            cs.execute();

        } catch(SQLException e) {
            String state = e.getSQLState();
            switch (state) {
                case "45000" -> {
                    return risultatoAnnullamento.CODICEINESISTENTE;
                }
                case "45001" -> {
                    return risultatoAnnullamento.TEMPOSCADUTO ;
                }
                default -> throw new DAOException("Errore completamento dell'annullamento: " + e.getMessage());
            }
        }

        return risultatoAnnullamento.OK; // la procedura di annullamento ha avuto successo
    }
}