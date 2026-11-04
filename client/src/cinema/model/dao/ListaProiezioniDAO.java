package cinema.model.dao;

import cinema.exception.DAOException;
import cinema.model.domain.ListaProiezioni;
import cinema.model.domain.Proiezione;

import java.sql.*;

public class ListaProiezioniDAO implements GenericProcedureDAO<ListaProiezioni>{
    @Override
    public ListaProiezioni execute (Object... params) throws DAOException{
        ListaProiezioni listaProiezioni = new ListaProiezioni();
        String titoloFilmDesiderato = (String) params[0];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call OttieniProiezioniDisponibili(?)}");
            cs.setString(1, titoloFilmDesiderato);

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Proiezione t = new Proiezione(titoloFilmDesiderato,
                        rs.getInt("numeroSala"),
                        rs.getDate("data"),
                        rs.getTime("oradiInizio"),
                        rs.getBigDecimal("costoBiglietto"));
                listaProiezioni.addProiezione(t);
            }

        } catch(SQLException e) {
            throw new DAOException("ListaProiezioni errore: " + e.getMessage());
        }

        return listaProiezioni;
    }
}
