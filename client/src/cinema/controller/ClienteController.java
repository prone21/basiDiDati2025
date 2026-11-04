package cinema.controller;

import cinema.model.dao.ConnectionFactory;
import cinema.model.dao.EffettuaPrenotazioneDAO;
import cinema.model.dao.ListaPostiDisponibiliDAO;
import cinema.model.dao.ListaProiezioniDAO;
import cinema.model.dao.AnnullaPrenotazioneDAO;


import cinema.model.domain.*;
import cinema.view.ClienteView;
import cinema.exception.DAOException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class ClienteController implements Controller{
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.CLIENTE);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            int choice;
            try {
                choice = ClienteView.showMenu();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }

            switch (choice) {
                case 1 -> PrenotaBiglietto();
                case 2 -> AnnullaPrenotazione();
                case 3 -> System.exit(0);
                default -> throw new RuntimeException("Scelta non valida");
            }
        }
    }

    public void PrenotaBiglietto(){
        Film filmDesiderato = null;
        try{
            filmDesiderato = ClienteView.inserisciFilm();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        Mostriamo tutte le proiezioni disponibili per quel film col relativo prezzo del biglietto
         */

        ListaProiezioni listaProiezioni;
        try {
            listaProiezioni = new ListaProiezioniDAO().execute(filmDesiderato.TitoloFilm());
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

        if(listaProiezioni.isEmpty()){
            ClienteView.completaPrenotazione(null, risultatoPrenotazione.NOPROIEZIONIDISP);
            return;
        }

        // inizia la procedura di prenotazione e da questo momento in poi  il cliente ha a disposizione 10 minuti per
        // perfezionare la prenotazione

        Instant inizio = Instant.now(); // in questo momento incomincia la procedura di prenotazione.
        // inizio è l'istante in cui parte la procedura

        // Il cliente deve scegliere la proiezione

        Proiezione proiezioneSelezionata = null;
        try {
            proiezioneSelezionata = ClienteView.selezionaProiezioneFilm(listaProiezioni);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        if(proiezioneSelezionata == null) {
            // se non viene selezionata alcuna proieizone non è possibile procedere con la prenotazione
            return;
        }

        // controllo se sono trascorsi 10 minuti
        Instant adesso = Instant.now();
        long minutiTrascorsi = Duration.between(inizio, adesso).toMinutes();

        if (minutiTrascorsi >= 10) {
            ClienteView.completaPrenotazione(null, risultatoPrenotazione.TIMEOUT);
            return;
        }

        // ora bisogna consentire al cliente di selezionare il posto per tale proiezione

        ListaPosti listaposti;
        try {
            listaposti = new ListaPostiDisponibiliDAO().execute(proiezioneSelezionata.salaProiezione(), proiezioneSelezionata.dataProiezione(),
                                            proiezioneSelezionata.OraProiezione());
        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

        if(listaposti.isEmpty()){
            ClienteView.completaPrenotazione(null, risultatoPrenotazione.NOPOSTIDISP);
            return;
        }

        // Il cliente deve scegliere il posto
        Posto postoSelezionato = null;
        try {
            postoSelezionato = ClienteView.selezionaPosto(listaposti,proiezioneSelezionata.salaProiezione());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        if(postoSelezionato == null) {
            // se non viene selezionato alcun posto non è possibile procedere con la prenotazione
            return;
        }

        // controllo se sono trascorsi 10 minuti
        adesso = Instant.now();
        minutiTrascorsi = Duration.between(inizio, adesso).toMinutes();

        if (minutiTrascorsi >= 10) {
            ClienteView.completaPrenotazione(null, risultatoPrenotazione.TIMEOUT);
            return;
        }

        // simulo il pagamento del cliente
        datiCarta pagamentoCliente = null;
        try {
            pagamentoCliente = ClienteView.EffettuaPagamento();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        if(pagamentoCliente == null) {
            return;
        }

        // controllo se sono trascorsi 10 minuti
        adesso = Instant.now();
        minutiTrascorsi = Duration.between(inizio, adesso).toMinutes();

        if (minutiTrascorsi >= 10) {
            ClienteView.completaPrenotazione(null, risultatoPrenotazione.TIMEOUT);
            return;
        }

        // registriamo la prenotazione

        codicePrenotazione cP;
        try {
            cP = new EffettuaPrenotazioneDAO().execute(proiezioneSelezionata.salaProiezione(),
                    proiezioneSelezionata.dataProiezione(), proiezioneSelezionata.OraProiezione(),
                    postoSelezionato.filaPosto(), postoSelezionato.numeroPosto());

        } catch(DAOException e) {
            throw new RuntimeException(e);
        }

        ClienteView.completaPrenotazione(cP, risultatoPrenotazione.OK);


    }

    public void AnnullaPrenotazione(){
        codicePrenotazione cp = null;
        try{
            cp = ClienteView.inserisciCodicePrenotazione();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        risultatoAnnullamento check;
        try{
            check = new AnnullaPrenotazioneDAO().execute(cp.ottieniCodice());
        }
        catch (DAOException e){
            throw new RuntimeException(e);
        }

        ClienteView.comunicaAnnullamento(check);

    }

}

