package cinema.view;

import cinema.model.domain.ListAffluenzaSala;
import cinema.model.domain.Periodo;

import java.io.IOException;
import java.util.Scanner;

public class AmministratoreView {

    public static int showMenu() throws IOException {
        System.out.println("*************************************");
        System.out.println("*      AMMINISTRATORE DASHBOARD     *");
        System.out.println("*************************************");
        System.out.println("*** Che operazione vuoi svolgere? ***\n");
        System.out.println("1) Report affluenza delle sale");
        System.out.println("2) Quit");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Inserisci la tua scelta: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 2) {
                break;
            }
            System.out.println("Opzione Invalida");
        }

        return choice;
    }

    public static Periodo inserisciPeriodoReport() throws IOException{
        Scanner inputAnno = new Scanner(System.in);
        Scanner inputMese = new Scanner(System.in);
        int anno, mese;
        System.out.print("Inserisci anno per il report: ");
        anno = inputAnno.nextInt();
        System.out.print("Inserisci mese per il report: ");
        mese = inputMese.nextInt();

        return new Periodo(anno, mese);
    }

    public static void showReport(ListAffluenzaSala ls){
        System.out.println("Report dell'affluenza alle sale:");
        System.out.println(ls);
    }

    public static void showError(String err){
        System.out.println(err);
    }

}
