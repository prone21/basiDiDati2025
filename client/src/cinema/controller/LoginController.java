package cinema.controller;

import cinema.exception.DAOException;
import cinema.model.dao.LoginProcedureDAO;
import cinema.model.domain.Credentials;
import cinema.view.LoginView;

import java.io.IOException;

public class LoginController implements Controller {
    Credentials cred = null;

    @Override
    public void start() {
        try {
            cred = LoginView.authenticate();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        try {
            cred = new LoginProcedureDAO().execute(cred.getUsername(), cred.getPassword());
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }
    }

    public Credentials getCred() {
        return cred;
    }
}

