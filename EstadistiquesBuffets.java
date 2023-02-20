

public class EstadistiquesBuffets {

    // Atributos
    public int[] platsPerAreaBuffet;
    public int[] platsEnColaPerAreaBuiffet;

    // Construcotr
    public EstadistiquesBuffets() {
        this.platsPerAreaBuffet = new int[3];
        this.platsEnColaPerAreaBuiffet = new int[3];
    }
    
    public EstadistiquesBuffets(int[] platsPerAreaBuffet, int[] platsEnColaPerAreaBuiffet) {
        this.platsPerAreaBuffet = platsPerAreaBuffet;
        this.platsEnColaPerAreaBuiffet = platsEnColaPerAreaBuiffet;
    }

    // Getter y setter
    public int[] getPlatsPerAreaBuffet() {
        return platsPerAreaBuffet;
    }

    public void setPlatsPerAreaBuffet(int[] platsPerAreaBuffet) {
        this.platsPerAreaBuffet = platsPerAreaBuffet;
    }

    public int[] getPlatsEnColaPerAreaBuiffet() {
        return platsEnColaPerAreaBuiffet;
    }

    public void setPlatsEnColaPerAreaBuiffet(int[] platsEnColaPerAreaBuiffet) {
        this.platsEnColaPerAreaBuiffet = platsEnColaPerAreaBuiffet;
    }
    

}
