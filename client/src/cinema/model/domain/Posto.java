package cinema.model.domain;

public class Posto {
    private int numerSala;
    private char fila;
    private int numero;

    public Posto(int numerSala, char fila, int numero) {
        this.numerSala = numerSala;
        this.fila = fila;
        this.numero = numero;
    }

    public int numeroSalaPosto() {
        return numerSala;
    }

    public char filaPosto() {
        return fila;
    }

    public int numeroPosto() {
        return numero;
    }
}
