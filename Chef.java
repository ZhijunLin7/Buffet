import java.util.Random;

public class Chef implements Runnable {

    // Atributos
    private static EstadistiquesChefs estadistiquesChefs;
    private int tempsTotalCuinant;
    private int tempsNoDescans;
    private int nombrePlatsCuinats;
    private int horariIniciDescans;
    private int tempsTotalDescans;
    private int tempsEspera;
    private String status;
    private Rellotge rellotge;
    private AreaBuffet areaBuffet;
    private Grill grill;
    private RestaurantModel restaurantModel;

    // Constuctor
    public Chef() {

    }

    public Chef(int tempsTotalCuinant, int tempsNoDescans, int nombrePlatsCuinats, int horariIniciDescans,
            int tempsTotalDescans, int tempsEspera, String status, Rellotge rellotge, AreaBuffet areaBuffet,
            Grill grill, RestaurantModel restaurantModel) {
        this.tempsTotalCuinant = tempsTotalCuinant;
        this.tempsNoDescans = tempsNoDescans;
        this.nombrePlatsCuinats = nombrePlatsCuinats;
        this.horariIniciDescans = horariIniciDescans;
        this.tempsTotalDescans = tempsTotalDescans;
        this.tempsEspera = tempsEspera;
        this.status = status;
        this.rellotge = rellotge;
        this.areaBuffet = areaBuffet;
        this.grill = grill;
        this.restaurantModel = restaurantModel;
    }

    // Getters y setters
    public static EstadistiquesChefs getEstadistiquesChefs() {
        return estadistiquesChefs;
    }

    public static void setEstadistiquesChefs(EstadistiquesChefs estadistiquesChefs) {
        Chef.estadistiquesChefs = estadistiquesChefs;
    }

    public Rellotge getRellotge() {
        return rellotge;
    }

    public void setRellotge(Rellotge rellotge) {
        this.rellotge = rellotge;
    }

    public int getTempsTotalCuinant() {
        return tempsTotalCuinant;
    }

    public void setTempsTotalCuinant(int tempsTotalCuinant) {
        this.tempsTotalCuinant = tempsTotalCuinant;
    }

    public int getTempsNoDescans() {
        return tempsNoDescans;
    }

    public void setTempsNoDescans(int tempsNoDescans) {
        this.tempsNoDescans = tempsNoDescans;
    }

    public int getNombrePlatsCuinats() {
        return nombrePlatsCuinats;
    }

    public void setNombrePlatsCuinats(int nombrePlatsCuinats) {
        this.nombrePlatsCuinats = nombrePlatsCuinats;
    }

    public int getHorariIniciDescans() {
        return horariIniciDescans;
    }

    public void setHorariIniciDescans(int horariIniciDescans) {
        this.horariIniciDescans = horariIniciDescans;
    }

    public int getTempsTotalDescans() {
        return tempsTotalDescans;
    }

    public void setTempsTotalDescans(int tempsTotalDescans) {
        this.tempsTotalDescans = tempsTotalDescans;
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

    public AreaBuffet getAreaBuffet() {
        return areaBuffet;
    }

    public void setAreaBuffet(AreaBuffet areaBuffet) {
        this.areaBuffet = areaBuffet;
    }

    public Grill getGrill() {
        return grill;
    }

    public void setGrill(Grill grill) {
        this.grill = grill;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }

    // Metodos
    public int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void cocinar() {
        restaurantModel.getStatus();
        this.status = "cuinant";
        try {
            int tiempoMinCocinar = restaurantModel.getParametresSimulacio().getTempsCuinat().getMin();
            int tiempoMaxCocinar = restaurantModel.getParametresSimulacio().getTempsCuinat().getMax();
            int tiempoRamCocinar = randomInt(tiempoMinCocinar, tiempoMaxCocinar);
            Thread.sleep(tiempoRamCocinar * 1000 / Rellotge.getInstance().getMultiplicadorTemps());
            this.tempsTotalCuinant = tempsTotalCuinant + tiempoRamCocinar;
            this.nombrePlatsCuinats = nombrePlatsCuinats + 1;
            this.tempsNoDescans = tempsNoDescans + tiempoRamCocinar;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void descansar() {
        restaurantModel.getStatus();
        int tempactual = Rellotge.getInstance().getMinActual();
        if (tempactual > this.horariIniciDescans) {
            this.status = "descansant";
            System.out.println("Chef escansando");
            try {
                int tiempoMinDescansant = restaurantModel.getParametresSimulacio().getTempsDescans().getMin();
                int tiempoMaxDescansant = restaurantModel.getParametresSimulacio().getTempsDescans().getMax();
                int tiempoRamDescansant = randomInt(tiempoMinDescansant, tiempoMaxDescansant);
                Thread.sleep(tiempoRamDescansant * 1000 / Rellotge.getInstance().getMultiplicadorTemps());
                this.tempsTotalDescans = tempsTotalDescans + tiempoRamDescansant;
            } catch (Exception e) {
                // TODO: handle exception
            }
            int tempMinsiguienteDescando = this.restaurantModel.getParametres().getTiempoTrabajando().getMin();
            int tempMaxsiguienteDescando = this.restaurantModel.getParametres().getTiempoTrabajando().getMax();
            this.horariIniciDescans = Rellotge.getInstance().getMinActual()
                    + randomInt(tempMinsiguienteDescando, tempMaxsiguienteDescando);
        }
    }

    public void entregarPlat() {
        restaurantModel.getStatus();
        this.status = "entregant";
        int tempEntrar = Rellotge.getInstance().getMinActual();
        areaBuffet.afagirPlat();
        int tempSalir = Rellotge.getInstance().getMinActual();
        int tempEsperado = tempSalir - tempEntrar;
        this.tempsEspera = tempsEspera + tempEsperado;
        this.tempsNoDescans = tempsNoDescans + tempEsperado;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        while (!this.restaurantModel.getStatus().equals("stop")) {
            restaurantModel.getStatus();
            cocinar();
            entregarPlat();
            actualizarbufferEstadistica();
            descansar();
            actualizarChefEstadistica();
            System.out.println(toString());
        }
    }

    @Override
    public String toString() {
        return "Chef [tempsTotalCuinant=" + tempsTotalCuinant + ", tempsNoDescans=" + tempsNoDescans
                + ", nombrePlatsCuinats=" + nombrePlatsCuinats + ", horariIniciDescans=" + horariIniciDescans
                + ", tempsTotalDescans=" + tempsTotalDescans + ", tempsEspera=" + tempsEspera + ", status=" + status
                + "]";
    }

    public void actualizarChefEstadistica() {
        restaurantModel.getStatus();
        int tempsDescansChef = 0;
        int tempsCuinantsChef = 0;
        int platsCuinats = 0;
        int cuinant = 0;
        int descansant = 0;
        int entregant = 0;
        for (int i = 0; i < restaurantModel.getChefs().size(); i++) {
            tempsDescansChef += restaurantModel.getChefs().get(i).getTempsTotalDescans();
            tempsCuinantsChef += restaurantModel.getChefs().get(i).getTempsTotalCuinant();
            platsCuinats += restaurantModel.getChefs().get(i).getNombrePlatsCuinats();

            switch (restaurantModel.getChefs().get(i).getStatus()) {
                case "cuinant":
                    cuinant += 1;
                    break;

                case "descansant":
                    descansant += 1;
                    break;
                case "entregant":
                    entregant += 1;
                    break;
            }
        }
        Chef.estadistiquesChefs = new EstadistiquesChefs(tempsDescansChef, tempsCuinantsChef, platsCuinats, cuinant,
                descansant, entregant);
    }

    public void actualizarbufferEstadistica() {
        restaurantModel.getStatus();
        int buffet1=restaurantModel.getAreaBuffets().get(0).getQuantitatActual();
        int buffet2=restaurantModel.getAreaBuffets().get(1).getQuantitatActual();
        int buffet3=restaurantModel.getAreaBuffets().get(2).getQuantitatActual();
        int cola1=restaurantModel.getAreaBuffets().get(0).getColaPlatsCuinats().getQuantitatActual();
        int cola2=restaurantModel.getAreaBuffets().get(1).getColaPlatsCuinats().getQuantitatActual();
        int cola3=restaurantModel.getAreaBuffets().get(2).getColaPlatsCuinats().getQuantitatActual();
        int [] buffetquantitat={buffet1,buffet2,buffet3};
        int [] buffetquantitatcola={cola1,cola2,cola3};
        AreaBuffet.setEstadistiquesBuffet(new EstadistiquesBuffets(buffetquantitat,buffetquantitatcola));
    }
}
