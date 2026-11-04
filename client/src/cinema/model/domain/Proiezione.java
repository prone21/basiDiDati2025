package cinema.model.domain;

public class Proiezione {
    private String titoloFilm;
    private int sala;
    private java.sql.Date data;
    private java.sql.Time ora;
    private java.math.BigDecimal costoBiglietto;

    public Proiezione(String titoloFilm, int sala, java.sql.Date data, 	java.sql.Time ora, java.math.BigDecimal costoBiglietto) {
        this.titoloFilm = titoloFilm;
        this.sala = sala;
        this.data = data;
        this.ora = ora;
        this.costoBiglietto = costoBiglietto;
    }

    public String TitoloFilmProiezione() {
        return titoloFilm;
    }

    public int salaProiezione() {
        return sala;
    }
    public java.sql.Date dataProiezione() {
        return data;
    }
    public java.sql.Time OraProiezione() {
        return ora;
    }
    public java.math.BigDecimal costoBigliettoProiezione() {
        return costoBiglietto;
    }



    @Override
    public String toString() {
        return String.format("Sala: %-5d Data: %-13s Ora: %-11s Costo Biglietto (€): %.2f\n",
                sala, data, ora, costoBiglietto);

    }
}
