

public class Estadistiques {

    // Atributos
    public EstadistiquesChefs chefs;
    public EstadistiquesComensals comensals;
    public EstadistiquesBuffets areaBuffet;

    // Construcotr
    public Estadistiques() {
        this.chefs = new EstadistiquesChefs(0, 0, 0);
        this.comensals = new EstadistiquesComensals(0, 0, 0);
        this.areaBuffet = new EstadistiquesBuffets();
    }

    // Getter y setter
    public EstadistiquesChefs getChefs() {
        return chefs;
    }

    public void setChefs(EstadistiquesChefs chefs) {
        this.chefs = chefs;
    }

    public EstadistiquesComensals getComensals() {
        return comensals;
    }

    public void setComensals(EstadistiquesComensals comensals) {
        this.comensals = comensals;
    }

    public EstadistiquesBuffets getAreaBuffet() {
        return areaBuffet;
    }

    public void setAreaBuffet(EstadistiquesBuffets areaBuffet) {
        this.areaBuffet = areaBuffet;
    }

}
