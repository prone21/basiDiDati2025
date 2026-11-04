package cinema.model.domain;

public class Film {
    private String titolo;
    private int durata;
    private String casaCinematografica;

    public Film(String titolo, int durata, String casaCinematografica) {
        this.titolo = titolo;
        this.durata = durata;
        this.casaCinematografica = casaCinematografica;
    }

    public Film(String titolo) {
        this.titolo = titolo;
        this.durata = 0;
        this.casaCinematografica = "";
    }

    public String TitoloFilm() {
        return this.titolo;
    }

    public int DurataFilm() {
        return this.durata;
    }

    public String CasaCinematograficaFIlm() {
        return this.casaCinematografica;
    }
}
