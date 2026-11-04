package cinema.model.dao;

import cinema.exception.DAOException;
import cinema.model.domain.AffluenzaSala;
import cinema.model.domain.ListAffluenzaSala;


import java.sql.*;

public class ReportAffluenzaSaleDAO implements GenericProcedureDAO<ListAffluenzaSala> {

    @Override
    public ListAffluenzaSala execute(Object... params) throws DAOException {
        ListAffluenzaSala listaAffluenze = new ListAffluenzaSala();
        int anno = (int)params[0];
        int mese = (int)params[1];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call ReportAffluenzaSale(?,?)}");
            cs.setInt(1, anno);
            cs.setInt(2, mese);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                AffluenzaSala as = new AffluenzaSala(rs.getInt("numSala"),
                        rs.getInt("numeroConferme"),
                        rs.getInt("numeroAnnullamenti"));
                listaAffluenze.addAffluenzaSala(as);
            }

        } catch(SQLException e) {
            String state = e.getSQLState();
            switch (state) {
                case "45004", "45005", "45006" -> {
                    throw new DAOException(state, e.getMessage());
                }
                default -> {throw new DAOException("ReportAffluenzaSale error: " + e.getMessage());}
            }
        }

        return listaAffluenze;

    }
}
