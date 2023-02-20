import java.util.Random;

public class Comensal implements Runnable {

    // Atributos
    private static EstadistiquesComensals estadistiques;
    private int platsMenjats;
    private int tempsMenjant;
    private int tempsTertulia;
    private int tempsEspera;
    private String status;
    private Rellotge rellotge;
    private RestaurantModel restaurantModel;

    // Constuctor
    public Comensal() {

    }

    public Comensal(int platsMenjats, int tempsMenjant, int tempsTertulia, int tempsEspera, String status,
            Rellotge rellotge, RestaurantModel restaurantModel) {
        this.platsMenjats = platsMenjats;
        this.tempsMenjant = tempsMenjant;
        this.tempsTertulia = tempsTertulia;
        this.tempsEspera = tempsEspera;
        this.status = status;
        this.rellotge = rellotge;
        this.restaurantModel = restaurantModel;

    }

    // Getters y setters
    public Rellotge getRellotge() {
        return rellotge;
    }

    public void setRellotge(Rellotge rellotge) {
        this.rellotge = rellotge;
    }

    public int getPlatsMenjats() {
        return platsMenjats;
    }

    public void setPlatsMenjats(int platsMenjats) {
        this.platsMenjats = platsMenjats;
    }

    public int getTempsMenjant() {
        return tempsMenjant;
    }

    public void setTempsMenjant(int tempsMenjant) {
        this.tempsMenjant = tempsMenjant;
    }

    public int getTempsTertulia() {
        return tempsTertulia;
    }

    public void setTempsTertulia(int tempsTertulia) {
        this.tempsTertulia = tempsTertulia;
    }

    public int getTempsEspera() {
        return tempsEspera;
    }

    public void setTempsEspera(int tempsEspera) {
        this.tempsEspera = tempsEspera;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }

    public static void setEstadistiques(EstadistiquesComensals estadistiques) {
        Comensal.estadistiques = estadistiques;
    }

    // Metodos
    public int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static EstadistiquesComensals getEstadistiques() {
        return estadistiques;
    }

    public static void resetEstadistiques() {
        Comensal.estadistiques = new EstadistiquesComensals(0, 0, 0);
    }

    public void menjar() {
        restaurantModel.getStatus();
        this.status = "menjant";
        try {
            int tiempoMinComer = restaurantModel.getParametresSimulacio().getTempsConsumir().getMin();
            int tiempoMaxComer = restaurantModel.getParametresSimulacio().getTempsConsumir().getMax();
            int tiempoRamComer = randomInt(tiempoMinComer, tiempoMaxComer);
            Thread.sleep(tiempoRamComer * 1000 / Rellotge.getInstance().getMultiplicadorTemps());
            this.tempsMenjant = tempsMenjant + tiempoRamComer;
            this.platsMenjats = platsMenjats + 1;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void tertulia() {
        restaurantModel.getStatus();
        this.status = "xerrant";
        try {
            int tiempoMinXerrant = restaurantModel.getParametresSimulacio().getTempsTertulia().getMin();
            int tiempoMaxXerrant = restaurantModel.getParametresSimulacio().getTempsTertulia().getMax();
            int tiempoRamXerrant = randomInt(tiempoMinXerrant, tiempoMaxXerrant);
            Thread.sleep(tiempoRamXerrant * 1000 / Rellotge.getInstance().getMultiplicadorTemps());
            this.tempsTertulia = tempsTertulia + tiempoRamXerrant;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void agafarPlat() {
        restaurantModel.getStatus();
        this.status = "agafantPlat";
        AreaBuffet buffet = restaurantModel.getRandomBuffete();
        int tempEntrar = Rellotge.getInstance().getMinActual();
        buffet.retirarPlat();
        int tempSalir = Rellotge.getInstance().getMinActual();
        int tempEsperado = tempSalir - tempEntrar;
        this.tempsEspera = tempsEspera + tempEsperado;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (!this.restaurantModel.getStatus().equals("stop")) {
            agafarPlat();
            actualizarbufferEstadistica();
            menjar();
            tertulia();
            actualizarComensalEstadistica();
            System.out.println(toString());
        }
    }

    public void actualizarComensalEstadistica() {
        restaurantModel.getStatus();
        int tempsMenjantComensals = 0;
        int tempsTertuliaComensals = 0;
        int tempsEsperantComensals = 0;
        int menjant = 0;
        int xerrant = 0;
        int agafantPlat = 0;
        for (int i = 0; i < restaurantModel.getChefs().size(); i++) {
            tempsMenjantComensals += restaurantModel.getComensals().get(i).getTempsMenjant();
            tempsTertuliaComensals += restaurantModel.getComensals().get(i).getTempsTertulia();
            tempsEsperantComensals += restaurantModel.getComensals().get(i).getTempsEspera();

            switch (restaurantModel.getChefs().get(i).getStatus()) {
                case "menjant":
                    menjant += 1;
                    break;
                case "xerrant":
                    xerrant += 1;
                    break;
                case "agafantPlat":
                    agafantPlat += 1;
                    break;
            }
        }
        Comensal.estadistiques = new EstadistiquesComensals(tempsMenjantComensals, tempsTertuliaComensals,
                tempsEsperantComensals, menjant, xerrant, agafantPlat);
    }

    public void actualizarbufferEstadistica() {
        restaurantModel.getStatus();
        int buffet1 = restaurantModel.getAreaBuffets().get(0).getQuantitatActual();
        int buffet2 = restaurantModel.getAreaBuffets().get(1).getQuantitatActual();
        int buffet3 = restaurantModel.getAreaBuffets().get(2).getQuantitatActual();
        int cola1 = restaurantModel.getAreaBuffets().get(0).getColaPlatsCuinats().getQuantitatActual();
        int cola2 = restaurantModel.getAreaBuffets().get(1).getColaPlatsCuinats().getQuantitatActual();
        int cola3 = restaurantModel.getAreaBuffets().get(2).getColaPlatsCuinats().getQuantitatActual();
        int[] buffetquantitat = { buffet1, buffet2, buffet3 };
        int[] buffetquantitatcola = { cola1, cola2, cola3 };
        AreaBuffet.setEstadistiquesBuffet(new EstadistiquesBuffets(buffetquantitat, buffetquantitatcola));
    }

    @Override
    public String toString() {
        return "Comensal [platsMenjats=" + platsMenjats + ", tempsMenjant=" + tempsMenjant + ", tempsTertulia="
                + tempsTertulia + ", tempsEspera=" + tempsEspera + ", status=" + status + "]";
    }

}
