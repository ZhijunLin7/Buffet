
public class EstadistiquesComensals {

    // Atributos
    public int tempsMenjantComensals;
    public int tempsTertuliaComensals;
    public int tempsEsperantComensals;
    public int[] comensalsPerEstat;

    // Construcotr
    public EstadistiquesComensals(int tempsMenjantComensals, int tempsTertuliaComensals, int tempsEsperantComensals) {
        this.tempsMenjantComensals = tempsMenjantComensals;
        this.tempsTertuliaComensals = tempsTertuliaComensals;
        this.tempsEsperantComensals = tempsEsperantComensals;
        this.comensalsPerEstat = new int[3];
    }

    public EstadistiquesComensals(int tempsMenjantComensals, int tempsTertuliaComensals, int tempsEsperantComensals,
            int estado1, int estado2,
            int estado3) {
                this.tempsMenjantComensals = tempsMenjantComensals;
                this.tempsTertuliaComensals = tempsTertuliaComensals;
                this.tempsEsperantComensals = tempsEsperantComensals;
        int[] estado = { estado1, estado2, estado3 };
        this.comensalsPerEstat = estado;

    }

    // Getter y setter
    public int getTempsMenjantComensals() {
        return tempsMenjantComensals;
    }

    public void setTempsMenjantComensals(int tempsMenjantComensals) {
        this.tempsMenjantComensals = tempsMenjantComensals;
    }

    public int getTempsTertuliaComensals() {
        return tempsTertuliaComensals;
    }

    public void setTempsTertuliaComensals(int tempsTertuliaComensals) {
        this.tempsTertuliaComensals = tempsTertuliaComensals;
    }

    public int getTempsEsperantComensals() {
        return tempsEsperantComensals;
    }

    public void setTempsEsperantComensals(int tempsEsperantComensals) {
        this.tempsEsperantComensals = tempsEsperantComensals;
    }

    public int[] getComensalsPerEstat() {
        return comensalsPerEstat;
    }

    public void setComensalsPerEstat(int[] comensalsPerEstat) {
        this.comensalsPerEstat = comensalsPerEstat;
    }

}
