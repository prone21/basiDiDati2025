package cinema.view;

import cinema.model.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ClienteView {

    public static int showMenu() throws IOException {
        System.out.println("*************************************");
        System.out.println("*         CLIENTE DASHBOARD         *");
        System.out.println("*************************************");
        System.out.println("*** Che operazione vuoi svolgere? ***\n");
        System.out.println("1) Prenota un biglietto");
        System.out.println("2) Annulla una prenotazione");
        System.out.println("3) Quit");


        Scanner input = new Scanner(System.in);
        int choice;
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

    public static Film inserisciFilm() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserisci il film che desideri vedere: ");
        String titoloFilm = reader.readLine();

        return new Film(titoloFilm);
    }

    public static Proiezione selezionaProiezioneFilm(ListaProiezioni lp) throws IOException {
        System.out.println("Hai a disposizione 10 minuti per completare la prenotazione!");
        System.out.println("Scegli Proiezione");
        System.out.print(lp);

        Scanner input = new Scanner(System.in);
        int last_proiezione = lp.size();
        int choice;
        while (true) {
            System.out.print("Inserisci la tua scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= last_proiezione) {
                break;
            }
            System.out.println("Opzione Invalida");
        }

        return lp.getProiezione(choice-1);
    }

    public static Posto selezionaPosto(ListaPosti listaPosti, int sala) throws IOException{
        System.out.println("Scegli un posto tra i disponibili per la sala "+sala);
        System.out.println(listaPosti+"\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Posto postoSelezionato = null;
        char fila;
        int numeroPosto;
        String posto;
        String[] tokens;


        while(true){
            System.out.print("Scegli un posto (fila,numero) o digita 'No' per non proseguire: ");
            posto = reader.readLine();


            if (posto.equalsIgnoreCase("no")){
                System.out.println("Procedura di prenotazione non conclusa correttamente");
                break;
            }
            else if(!posto.matches("^[A-Za-z],(1[0-3]|[1-9])$")){
                System.out.println("Formato del posto non valido");
                continue;
            }


            tokens = posto.split(",");
            fila = tokens[0].charAt(0);
            numeroPosto = Integer.parseInt(tokens[1]);

            postoSelezionato = listaPosti.getPosto(sala, fila, numeroPosto);

            if(postoSelezionato == null){
                System.out.println("Posto non disponibile");
            }
            else{
                break;
            }
        }

        return postoSelezionato;
    }


    public static datiCarta EffettuaPagamento() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String numeroCarta;
        String nomeIntestatario;
        String dataScadenza;
        String cvv;

        System.out.println("\nInserisci i dati della carta di credito:");

        System.out.print("Numero della carta (16 cifre): ");
        numeroCarta = reader.readLine();

        System.out.print("Nome intestatario: ");
        nomeIntestatario = reader.readLine();

        System.out.print("Data di scadenza (MM/AA): ");
        dataScadenza = reader.readLine();

        System.out.print("CVV (3 cifre): ");
        cvv = reader.readLine();

        datiCarta dc = new datiCarta(numeroCarta, nomeIntestatario, dataScadenza, cvv);
        if(!dc.isValid()){
            System.out.println("Errore inserimento dati della carta di credito!");
            System.out.println("La prenotazione non è avvenuta!");
            return null;
        }
        return dc;
    }

    public static void completaPrenotazione(codicePrenotazione cP, risultatoPrenotazione ris){
        switch (ris){
            case ris.OK -> {
                System.out.println("Buona visione! Questo è il tuo codice di prenotazione: "+ cP);
                System.out.println("Potrai annullare la tua prenotazione fino a 30 minuti dall'inizio della proiezione");
            }
            case ris.TIMEOUT -> {
                System.out.println("Il tempo è scaduto! Ritenta");
            }
            case ris.NOPROIEZIONIDISP -> {
                System.out.println("Non sono disponibili proiezioni per il film desiderato");
            }
            case ris.NOPOSTIDISP -> {
                System.out.println("Non sono disponibili posti per la proiezione selezionata: ritenta");
            }
        }

    }

    public static codicePrenotazione inserisciCodicePrenotazione() throws IOException{
        Scanner input = new Scanner(System.in);
        int codicePrenotazione;
        System.out.print("Inserisci il codice della prenotazione da annullare: ");
        codicePrenotazione = input.nextInt();
        return new codicePrenotazione(codicePrenotazione);
    }

    public static void comunicaAnnullamento(risultatoAnnullamento check){
        switch (check){
            case risultatoAnnullamento.OK -> System.out.println("Prenotazione annullata con successo");
            case risultatoAnnullamento.CODICEINESISTENTE -> System.out.println("Il codice specificato non è valido");
            case risultatoAnnullamento.TEMPOSCADUTO -> System.out.println("Scaduto tempo massimo di annullamento prenotazione");
        }
    }


}