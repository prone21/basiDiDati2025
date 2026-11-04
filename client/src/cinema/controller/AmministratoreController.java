package cinema.controller;

import cinema.model.dao.ConnectionFactory;
import cinema.model.dao.ReportAffluenzaSaleDAO;
import cinema.model.domain.Periodo;
import cinema.model.domain.Role;
import cinema.view.AmministratoreView;
import cinema.exception.DAOException;
import cinema.model.domain.ListAffluenzaSala;

import java.io.IOException;
import java.sql.SQLException;

public class AmministratoreController implements Controller {

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.AMMINISTRATORE);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            int choice;
            try {
                choice = AmministratoreView.showMenu();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice) {
                case 1 -> ReportAffulenzaSale();
                case 2 -> System.exit(0);
                default -> throw new RuntimeException("Scelta non valida");
            }
        }
    }

    public void ReportAffulenzaSale(){
        Periodo p = null;

        try{
            p = AmministratoreView.inserisciPeriodoReport();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


        ListAffluenzaSala affluenze = null;

        try {
            affluenze = new ReportAffluenzaSaleDAO().execute(p.getAnno(), p.getMese());
        } catch(DAOException e) {
            switch (e.getState()){
                case "45004", "45005", "45006" -> {
                    AmministratoreView.showError(e.getMessage());
                    return;
                }
                default -> throw new RuntimeException(e);
            }

        }




        AmministratoreView.showReport(affluenze);
    }

}
