

public class Grill extends BufferPlats {

    // Atributos
    private Boolean enServei;

    // Constructor
    public Grill(Boolean enServei) {
        this.enServei = enServei;
    }

    public Grill(int capacitatMaxima, int quantitatActual, Boolean enServei) {
        super(capacitatMaxima, quantitatActual);
        this.enServei = enServei;
    }

    // Getters y setters
    public Boolean getEnServei() {
        return enServei;
    }

    public void setEnServei(Boolean enServei) {
        this.enServei = enServei;
    }

    // Metodos
    @Override
    public synchronized void afagirPlat() {
        // TODO Auto-generated method stub
        
    }

    public void posarEnServei() {

    }

    public void treuraDeServei() {

    }
}
