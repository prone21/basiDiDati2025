package cinema.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaAttori {
    List<Attore> listaAttori = new ArrayList<>();

    public void addActor(Attore attore) {
        this.listaAttori.add(attore);
    }

    public boolean isEmpty() {
        return listaAttori.isEmpty();
    }

    @Override
    public String toString() {
        String listaAttoriStringa = "";

        for(Attore attore : listaAttori) {
            listaAttoriStringa += attore.toString() + "\n";
        }

        // devo eliminare l'ultimo a capo, in quanto sovrabbondante
        if (!listaAttoriStringa.isEmpty()) {
            listaAttoriStringa = listaAttoriStringa.substring(0, listaAttoriStringa.length() - 1);
        }


        return listaAttoriStringa;
    }
}
