package cinema.view;

import cinema.model.domain.Attore;
import cinema.model.domain.Film;
import cinema.model.domain.ListaAttori;
import cinema.model.domain.Proiezione;
import cinema.model.domain.erroriAddProiezione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;


public class ProprietarioView {

    public static int showMenu() throws IOException {
        System.out.println("*************************************");
        System.out.println("*       PROPRIETARIO DASHBOARD      *");
        System.out.println("*************************************");
        System.out.println("*** Che operazione vuoi svolgere? ***\n");
        System.out.println("1) Aggiungi film");
        System.out.println("2) Programma nuova proiezione");
        System.out.println("3) Quit");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Inserisci la tua scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 3) {
                break;
            }
            System.out.println("Opzione Invalida");
        }

        return choice;
    }

    public static Film filmDaInserire() throws IOException {
        String titoloFilm, casaCinematograficaFilm;
        int durata;

        System.out.print("Inserisci il film che desideri aggiungere: ");
        Scanner inputFilm = new Scanner(System.in);
        titoloFilm = inputFilm.nextLine();

        System.out.print("Inserisci la durata: ");
        Scanner inputDurata = new Scanner(System.in);
        durata = inputDurata.nextInt();

        System.out.print("Inserisci la casa Cinematografica: ");
        Scanner inputCasa = new Scanner(System.in);
        casaCinematograficaFilm = inputCasa.nextLine();

        return new Film(titoloFilm,durata, casaCinematograficaFilm);
    }

    public static ListaAttori inserisciAttori() throws IOException {
        ListaAttori attori = new ListaAttori();

        String nomeAttore, cognomeAttore;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Inserisci il cast!");

        while (true) {
            System.out.print("Inserisci il nome dell'attore (oppure 'fine' per terminare): ");
            nomeAttore = reader.readLine();
            if (nomeAttore.equalsIgnoreCase("fine")) {
                break;
            }

            System.out.print("Inserisci il cognome dell'attore: ");
            cognomeAttore = reader.readLine();


            attori.addActor(new Attore(nomeAttore, cognomeAttore));
        }

        if(attori.isEmpty()){
            System.out.println("Occorre specificare almeno un attore!");
            return null;
        }
        return attori;
    }

    public static void completaAggiuntaFilm(Boolean ris){
        if (ris){
            System.out.println("Film aggiunto correttamente!");
        }
        else{
            System.out.println("C'è stato un errore: Film non aggiunto!");
        }
    }

    public static Proiezione inserisciProiezione() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String titoloFilm;
        int sala;
        Date data;
        Time ora;
        BigDecimal costoBiglietto;

        try{
            System.out.print("Inserisci il titolo del film: ");
            titoloFilm = reader.readLine().trim();

            System.out.print("Inserisci il numero della sala: ");
            sala = Integer.parseInt(reader.readLine().trim());

            System.out.print("Inserisci la data della proiezione (yyyy-mm-dd): ");
            data = Date.valueOf(reader.readLine().trim());

            System.out.print("Inserisci l'ora della proiezione (hh:mm:ss): ");
            ora = Time.valueOf(reader.readLine().trim());

            System.out.print("Inserisci il costo del biglietto (€): ");
            costoBiglietto = new BigDecimal(reader.readLine().trim());
        }
        catch (IllegalArgumentException e){
            System.out.println("Non è stato rispettato il formato specificato: ritenta!\n");
            return null;
        }

        return new Proiezione(titoloFilm, sala, data, ora, costoBiglietto);
    }

    public static void completaAggiuntaProiezione(erroriAddProiezione check){
        switch (check){
            case erroriAddProiezione.OK -> System.out.println("Proiezione aggiunta correttamente!\n");
            case erroriAddProiezione.OVERLAP -> System.out.println("Insuccesso aggiunta nuova proiezione: l' intervallo di proiezione si sovrappone!\n");
            case erroriAddProiezione.OLDDAY -> System.out.println("Tentativo di programmazione di una proiezione per un giorno già trascorso\n");
            case erroriAddProiezione.OLDTIME -> System.out.println("Tentativo di programmazione di una proiezione per un'ora già trascorsa\n");
        }
    }
}
