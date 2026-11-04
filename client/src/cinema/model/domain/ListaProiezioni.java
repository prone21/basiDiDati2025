package cinema.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaProiezioni {
    List<Proiezione> listaProiezioni = new ArrayList<>();

    public void addProiezione(Proiezione proiezione) {
        this.listaProiezioni.add(proiezione);
    }

    public Proiezione getProiezione(int i) {
        return listaProiezioni.get(i);
    }

    public boolean isEmpty() {
        return listaProiezioni.isEmpty();
    }

    public int size() {
        return listaProiezioni.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 1; // Partenza da 1

        for (Proiezione proiezione : listaProiezioni) {
            sb.append(index).append(") ")  // Solo il numero con parentesi
                    .append(proiezione);
            index++; // Incrementa il contatore
        }

        return sb.toString();
    }
}
