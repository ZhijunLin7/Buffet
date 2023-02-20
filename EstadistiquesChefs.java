import java.util.Arrays;

public class EstadistiquesChefs {

    // Atributos
    public int tempsDescansChef;
    public int tempsCuinatsChef;
    public int platsCuinats;
    public int[] chefsPerEstat;

    // Construcotr
    public EstadistiquesChefs(int tempsDescansChef, int tempsCuinatsChef, int platsCuinats) {
        this.tempsDescansChef = tempsDescansChef;
        this.tempsCuinatsChef = tempsCuinatsChef;
        this.platsCuinats = platsCuinats;
        this.chefsPerEstat = new int[3];
    }

    public EstadistiquesChefs(int tempsDescansChef, int tempsCuinatsChef, int platsCuinats, int estado1, int estado2,
            int estado3) {
        this.tempsDescansChef = tempsDescansChef;
        this.tempsCuinatsChef = tempsCuinatsChef;
        this.platsCuinats = platsCuinats;
        int[] estado = { estado1, estado2, estado3 };
        this.chefsPerEstat = estado;

    }

    // Getter y setter
    public int getTempsDescansChef() {
        return tempsDescansChef;
    }

    public void setTempsDescansChef(int tempsDescansChef) {
        this.tempsDescansChef = tempsDescansChef;
    }

    public int getTempsCuinatsChef() {
        return tempsCuinatsChef;
    }

    public void setTempsCuinatsChef(int tempsCuinatsChef) {
        this.tempsCuinatsChef = tempsCuinatsChef;
    }

    public int getPlatsCuinats() {
        return platsCuinats;
    }

    public void setPlatsCuinats(int platsCuinats) {
        this.platsCuinats = platsCuinats;
    }

    public int[] getChefsPerEstat() {
        return chefsPerEstat;
    }

    public void setChefsPerEstat(int[] chefsPerEstat) {
        this.chefsPerEstat = chefsPerEstat;
    }

    @Override
    public String toString() {
        return "EstadistiquesChefs [tempsDescansChef=" + tempsDescansChef + ", tempsCuinatsChef=" + tempsCuinatsChef
                + ", platsCuinats=" + platsCuinats + ", chefsPerEstat=" + Arrays.toString(chefsPerEstat) + "]";
    }

}
