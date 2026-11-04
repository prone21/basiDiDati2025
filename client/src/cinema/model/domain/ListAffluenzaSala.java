package cinema.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ListAffluenzaSala {
    List<AffluenzaSala> affluenze = new ArrayList<>();

    public void addAffluenzaSala(AffluenzaSala affluenza) {
        this.affluenze.add(affluenza);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean firstEntry = true;
        for(AffluenzaSala affluenza : affluenze) {
            if(!firstEntry) {
                sb.append("\n");
            }
            firstEntry = false;
            sb.append(affluenza);
        }
        return sb.toString();
    }
}
