package cinema.model.domain;

public class AffluenzaSala {
    private int num_sala;
    private int num_prenotazioni_confermate;
    private int num_prenotazioni_annullate;

    public AffluenzaSala(int num_sala, int num_prenotazioni_confermate, int num_prenotazioni_annullate) {
        this.num_sala = num_sala;
        this.num_prenotazioni_confermate = num_prenotazioni_confermate;
        this.num_prenotazioni_annullate = num_prenotazioni_annullate;
    }

    @Override
    public String toString() {
        return String.format("Sala: %-5d Prenotazioni Confermate: %-5d Prenotazioni Annullate: %-5d",
                num_sala, num_prenotazioni_confermate,
                num_prenotazioni_annullate);
    }

}
