package scr;

public class Aviao {
    private int numero;
    private int assentos;


    public Aviao(int numero, int assentos) {
        this.numero = numero;
        this.assentos = assentos;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getAssentos() {
        return assentos;
    }
    public void setAssentos(int assentos) {
        this.assentos = assentos;
    }

}
