package cinema.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ListaPosti {
    List<Posto> listaPosti = new ArrayList<>();

    public void addPosto(Posto posto) {
        this.listaPosti.add(posto);
    }

    public Posto getPosto(int i) {
        return listaPosti.get(i);
    }


    public Posto getPosto(int numeroSala, char fila, int numeroPosto) {
        for (Posto posto : listaPosti) {
            if (posto.numeroSalaPosto() == numeroSala &&

                    (Character.toLowerCase(posto.filaPosto()) == fila ||
                    Character.toUpperCase(posto.filaPosto()) == fila)

                     &&
                    posto.numeroPosto() == numeroPosto) {
                return posto; // Posto trovato
            }
        }
        return null; // Nessun posto trovato con quei parametri
    }


    public boolean isEmpty() {
        return listaPosti.isEmpty();
    }

    public int size() {
        return listaPosti.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
// Mappa per raggruppare i posti per fila (es: 'A' -> [1, 2, 3])
        Map<Character, List<Integer>> mappaFile = new TreeMap<>(); // TreeMap ordina le chiavi alfabeticamente

// Raggruppamento dei posti per fila
        for (Posto posto : listaPosti) {
            char fila = posto.filaPosto();
            int numero = posto.numeroPosto();

            // Se la fila non esiste nella mappa, la inizializzo
            mappaFile.computeIfAbsent(fila, k -> new ArrayList<>()).add(numero);
        }

// Costruzione della stringa finale
        boolean firstEntry = true;
        for (Map.Entry<Character, List<Integer>> entry : mappaFile.entrySet()) {
            if (!firstEntry) {
                sb.append("\n"); // Aggiungo una nuova riga solo tra le righe
            }
            firstEntry = false;

            sb.append("Fila ").append(entry.getKey()).append(": ");

            // Aggiungo i numeri dei posti separati da virgole
            List<Integer> numeriPosti = entry.getValue();
            for (int i = 0; i < numeriPosti.size(); i++) {
                sb.append(numeriPosti.get(i));
                if (i < numeriPosti.size() - 1) {
                    sb.append(", ");
                }
            }
        }

        return sb.toString();
    }

}
