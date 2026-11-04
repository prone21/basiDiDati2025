package cinema.controller;

import cinema.model.domain.Credentials;
import cinema.view.StartView;
import java.io.IOException;

public class ApplicationController implements Controller {
    Credentials cred;

    @Override
    public void start() {
        int result;
        try {
            result = StartView.identificazione();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (result == 2) {
            // è un dipendente e quindi si vuole loggare
            LoginController loginController = new LoginController();
            loginController.start();
            cred = loginController.getCred();

            if (cred.getRole() == null) {
                throw new RuntimeException("Credenziali non valide");
            }

            switch (cred.getRole()) {
                case PROPRIETARIO -> new cinema.controller.ProprietarioController().start();
                case AMMINISTRATORE -> new cinema.controller.AmministratoreController().start();
                default -> throw new RuntimeException("Credenziali non valide");
            }
        }
        else if (result == 1) {
            // è un cliente e dunque non ha bisogno di autenticarsi
            // procederà col fare le operazioni che può
            ClienteController clienteController = new ClienteController();
            clienteController.start();
        }
    }
}