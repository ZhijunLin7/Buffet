
public class ParametresSimulacio {

    // Atributos
    private int maxPlatAreaBuffet;
    private int maxPlatGrill;
    private int limitPlatsEnCoa;

    private Rango tempsConsumir;
    private Rango tempsTertulia;
    private Rango tempsCuinat;
    private Rango tempsDescans;
    private Rango numComensal;
    private Rango numChefPerGrill;
    private Rango tiempoTrabajando;

    // Constuctor
    public ParametresSimulacio() {

    }

    public ParametresSimulacio(int maxPlatAreaBuffet, int maxPlatGrill, int limitPlatsEnCoa, Rango tempsConsumir,
            Rango tempsTertulia, Rango tempsCuinat, Rango tempsDescans, Rango numComensal, Rango numChefPerGrill,
            Rango tiempoTrabajando) {
        this.maxPlatAreaBuffet = maxPlatAreaBuffet;
        this.maxPlatGrill = maxPlatGrill;
        this.limitPlatsEnCoa = limitPlatsEnCoa;
        this.tempsConsumir = tempsConsumir;
        this.tempsTertulia = tempsTertulia;
        this.tempsCuinat = tempsCuinat;
        this.tempsDescans = tempsDescans;
        this.numComensal = numComensal;
        this.numChefPerGrill = numChefPerGrill;
        this.tiempoTrabajando = tiempoTrabajando;
    }

    // Getters y setters
    public Rango getTiempoTrabajando() {
        return tiempoTrabajando;
    }

    public void setTiempoTrabajando(Rango tiempoTrabajando) {
        this.tiempoTrabajando = tiempoTrabajando;
    }

    public int getMaxPlatAreaBuffet() {
        return maxPlatAreaBuffet;
    }

    public void setMaxPlatAreaBuffet(int maxPlatAreaBuffet) {
        this.maxPlatAreaBuffet = maxPlatAreaBuffet;
    }

    public int getMaxPlatGrill() {
        return maxPlatGrill;
    }

    public void setMaxPlatGrill(int maxPlatGrill) {
        this.maxPlatGrill = maxPlatGrill;
    }

    public int getLimitPlatsEnCoa() {
        return limitPlatsEnCoa;
    }

    public void setLimitPlatsEnCoa(int limitPlatsEnCoa) {
        this.limitPlatsEnCoa = limitPlatsEnCoa;
    }

    public Rango getTempsConsumir() {
        return tempsConsumir;
    }

    public void setTempsConsumir(Rango tempsConsumir) {
        this.tempsConsumir = tempsConsumir;
    }

    public Rango getTempsTertulia() {
        return tempsTertulia;
    }

    public void setTempsTertulia(Rango tempsTertulia) {
        this.tempsTertulia = tempsTertulia;
    }

    public Rango getTempsCuinat() {
        return tempsCuinat;
    }

    public void setTempsCuinat(Rango tempsCuinat) {
        this.tempsCuinat = tempsCuinat;
    }

    public Rango getTempsDescans() {
        return tempsDescans;
    }

    public void setTempsDescans(Rango tempsDescans) {
        this.tempsDescans = tempsDescans;
    }

    public Rango getNumComensal() {
        return numComensal;
    }

    public void setNumComensal(Rango numComensal) {
        this.numComensal = numComensal;
    }

    public Rango getNumChefPerGrill() {
        return numChefPerGrill;
    }

    public void setNumChefPerGrill(Rango numChefPerGrill) {
        this.numChefPerGrill = numChefPerGrill;
    }

}
