package cinema.model.domain;

public class codicePrenotazione {
    private int codicePrenotazione;
    public codicePrenotazione(int codicePrenotazione) {
        this.codicePrenotazione = codicePrenotazione;
    }
    public int ottieniCodice() {
        return codicePrenotazione;
    }

    @Override
    public String toString() {
        return String.valueOf(this.ottieniCodice());
    }
}
