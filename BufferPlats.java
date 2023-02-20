

public class BufferPlats {

    // Atributos
    private int capacitatMaxima;
    private int quantitatActual;

    // Constructor
    public BufferPlats() {

    }

    public BufferPlats(int capacitatMaxima, int quantitatActual) {
        this.capacitatMaxima = capacitatMaxima;
        this.quantitatActual = quantitatActual;
    }

    // Getters y setters
    public int getCapacitatMaxima() {
        return capacitatMaxima;
    }

    public void setCapacitatMaxima(int capacitatMaxima) {
        this.capacitatMaxima = capacitatMaxima;
    }

    public int getQuantitatActual() {
        return quantitatActual;
    }

    public void setQuantitatActual(int quantitatActual) {
        this.quantitatActual = quantitatActual;
    }

    // Metodos
    public synchronized void afagirPlat() {
        this.quantitatActual=quantitatActual+1;

    }

    public synchronized void retirarPlat() {
        this.quantitatActual=quantitatActual-1;

    }
}
