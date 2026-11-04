package cinema.controller;
import cinema.exception.DAOException;

import cinema.model.dao.AggiungiFilmDAO;
import cinema.model.dao.ConnectionFactory;
import cinema.model.dao.ProgrammaProiezioneDAO;


import cinema.model.domain.Film;
import cinema.model.domain.Proiezione;
import cinema.model.domain.Role;
import cinema.model.domain.ListaAttori;
import cinema.model.domain.erroriAddProiezione;


import cinema.view.ProprietarioView;

import java.io.IOException;
import java.sql.SQLException;

public class ProprietarioController  implements Controller{

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.PROPRIETARIO);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            int choice;
            try {
                choice = ProprietarioView.showMenu();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }

            switch(choice) {
                case 1 -> AggiungiFilm();
                case 2 -> ProgrammaProiezione();
                case 3 -> System.exit(0);
                default -> throw new RuntimeException("Scelta non valida");
            }
        }
    }

    public void AggiungiFilm(){
        // acquisiamo i dettagli sul film da aggiungere
        Film film;
        try{
            film = ProprietarioView.filmDaInserire();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ci servono gli attori
        ListaAttori cast = null;
        try{
            cast = ProprietarioView.inserisciAttori();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(cast == null){
            return;
        }

        // abbiamo il film da aggiungere ed il cast
        Boolean ris;
        try{
            ris = new AggiungiFilmDAO().execute(film.TitoloFilm(), film.DurataFilm(),
                    film.CasaCinematograficaFIlm(),
                    cast.toString());
        }
        catch (DAOException e){
            throw new RuntimeException(e);
        }


        ProprietarioView.completaAggiuntaFilm(ris);


    }


    public void ProgrammaProiezione(){
        // acquisiamo la proiezione da aggiungere
        Proiezione p;
        try {
            p = ProprietarioView.inserisciProiezione();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(p == null){
            // hai inserito male i dati: ritenta
            return;
        }

        // abbiamo finalmente la proiezione da inserire: inseriamola!
        erroriAddProiezione ris;
        try{
            ris = new ProgrammaProiezioneDAO().execute(p.salaProiezione(), p.dataProiezione(), p.OraProiezione(),
                    p.TitoloFilmProiezione(), p.costoBigliettoProiezione());
        }
        catch (DAOException e){
            throw new RuntimeException(e);
        }

        ProprietarioView.completaAggiuntaProiezione(ris);
    }
}
