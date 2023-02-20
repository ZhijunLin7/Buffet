
public class AreaBuffet extends BufferPlats {

    // Atributos
    private static EstadistiquesBuffets estadistiquesBuffet;
    private String descripcio;
    private Grill grill;
    private ColaPlatsCuinats colaPlatsCuinats;

    // Constructor
    public AreaBuffet() {

    }

    public AreaBuffet(String descripcio, Grill grill, ColaPlatsCuinats colaPlatsCuinats) {
        this.descripcio = descripcio;
        this.grill = grill;
        this.colaPlatsCuinats = colaPlatsCuinats;
    }

    public AreaBuffet(int capacitatMaxima, int quantitatActual, String descripcio, Grill grill,
            ColaPlatsCuinats colaPlatsCuinats) {
        super(capacitatMaxima, quantitatActual);
        this.descripcio = descripcio;
        this.grill = grill;
        this.colaPlatsCuinats = colaPlatsCuinats;
    }

    // Getters y setters
    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public Grill getGrill() {
        return grill;
    }

    public void setGrill(Grill grill) {
        this.grill = grill;
    }

    public ColaPlatsCuinats getColaPlatsCuinats() {
        return colaPlatsCuinats;
    }

    public void setColaPlatsCuinats(ColaPlatsCuinats colaPlatsCuinats) {
        this.colaPlatsCuinats = colaPlatsCuinats;
    }

    public static EstadistiquesBuffets getEstadistiquesBuffet() {
        return estadistiquesBuffet;
    }

    public static void setEstadistiquesBuffet(EstadistiquesBuffets estadistiquesBuffet) {
        AreaBuffet.estadistiquesBuffet = estadistiquesBuffet;
    }

    // Metodos

    @Override
    public synchronized void afagirPlat() {
        // TODO Auto-generated method stub

        while (super.getQuantitatActual() >= super.getCapacitatMaxima()) {
            try {
                System.out.println("Chef dormido");
                this.colaPlatsCuinats.setQuantitatActual(this.colaPlatsCuinats.getQuantitatActual()+1);
                wait();
                this.colaPlatsCuinats.setQuantitatActual(this.colaPlatsCuinats.getQuantitatActual()-1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        super.afagirPlat();
        System.out.println(getDescripcio() + " :" + super.getQuantitatActual() + "/" + super.getCapacitatMaxima());
        if (super.getQuantitatActual() >= super.getCapacitatMaxima()) {
            notifyAll();
        }

    }

    @Override
    public synchronized void retirarPlat() {
        // TODO Auto-generated method stub

        while (super.getQuantitatActual() <= 0) {
            try {
                System.out.println("Comensal dormido");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        super.retirarPlat();
        System.out.println(
                "Consumido: " + descripcio + " " + super.getQuantitatActual() + "/" + super.getCapacitatMaxima());
        if (super.getQuantitatActual() <= 0) {
            notifyAll();
        }

    }

}
