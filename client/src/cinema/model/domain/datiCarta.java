package cinema.model.domain;

public class datiCarta {
    private String numeroCarta;
    private String nomeIntestatario;
    private String dataScadenza;
    private String cvv;

    public datiCarta(String numeroCarta, String nomeIntestatario, String dataScadenza, String cvv) {
        this.numeroCarta = numeroCarta;
        this.nomeIntestatario = nomeIntestatario;
        this.dataScadenza = dataScadenza;
        this.cvv = cvv;
    }
    public String getNumeroCarta() {
        return numeroCarta;
    }
    public String getNomeIntestatario() {
        return nomeIntestatario;
    }
    public String getDataScadenza() {
        return dataScadenza;
    }
    public String getCvv() {
        return cvv;
    }

    public boolean isValid(){
        return  (this.numeroCarta.length() == 16 && this.numeroCarta.matches("\\d+")) &&
                (this.nomeIntestatario!=null) && (this.dataScadenza.matches("^(0[1-9]|1[0-2])/\\d{2}$")) &&
                (this.cvv.length() == 3 && this.cvv.matches("\\d+"));
    }
}
