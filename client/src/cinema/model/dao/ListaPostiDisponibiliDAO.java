package cinema.model.dao;
import cinema.exception.DAOException;
import cinema.model.domain.ListaPosti;
import cinema.model.domain.Posto;

import java.sql.*;

public class ListaPostiDisponibiliDAO implements GenericProcedureDAO<ListaPosti> {
    @Override
    public ListaPosti execute (Object... params) throws DAOException {
        ListaPosti listaposti = new ListaPosti();
        int numeroSala = (int) params[0];
        java.sql.Date dataProiezione = (java.sql.Date) params[1];
        java.sql.Time oraProiezione = (java.sql.Time) params[2];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call OttieniPostiDisponibili(?,?,?)}");
            cs.setInt(1, numeroSala);
            cs.setDate(2, dataProiezione);
            cs.setTime(3, oraProiezione);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Posto p = new Posto(numeroSala,
                                    rs.getString("fila").charAt(0),
                                    rs.getInt("numero"));

                listaposti.addPosto(p);
            }

        } catch(SQLException e) {
            throw new DAOException("ListaPosti errore: " + e.getMessage());
        }

        return listaposti;
    }
}
