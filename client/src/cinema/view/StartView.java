package cinema.view;
import java.io.IOException;
import java.util.Scanner;

public class StartView {
    public static int identificazione () throws IOException {
        System.out.println("*************************************");
        System.out.println("*       BENVENUTO AL CINEMA         *");
        System.out.println("*************************************");
        System.out.println("*** Perchè sei venuto? ***");
        System.out.println("1) Sono un cliente");
        System.out.println("2) Sono un dipendente");


        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("Inserisci la tua scelta: ");
        choice = input.nextInt();
        if (choice != 1 && choice != 2) {
            throw new RuntimeException("Opzione Invalida");
        }

        System.out.println("");

        return choice;
    }
}
