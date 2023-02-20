
import java.util.ArrayList;
import java.util.Random;

public class RestaurantModel {

    // Atributos
    private static Estadistiques estadistiques;
    private ArrayList<Comensal> comensals;
    private ArrayList<Chef> chefs;
    private ParametresSimulacio parametresSimulacio;
    private Rellotge rellotge;
    private ArrayList<AreaBuffet> areaBuffets;
    private ArrayList<Grill> grills;
    private RestaurantController restaurantController;
    private String status;


    private ArrayList<Thread> comensalsThreads;
    private ArrayList<Thread> chefsThreads;
    // Constructor
    public RestaurantModel(String status) {
        this.status=status;
        crearObjetos();
        
    }

    public RestaurantModel(ArrayList<Comensal> comensals, ArrayList<Chef> chefs,
            ParametresSimulacio parametresSimulacio, Rellotge rellotge, ArrayList<AreaBuffet> areaBuffets,
            ArrayList<Grill> grills) {
        this.comensals = comensals;
        this.chefs = chefs;
        this.parametresSimulacio = parametresSimulacio;
        this.rellotge = rellotge;
        this.areaBuffets = areaBuffets;
        this.grills = grills;
    }

    // Getters y setters
    public ArrayList<Comensal> getComensals() {
        return comensals;
    }

    public void setComensals(ArrayList<Comensal> comensals) {
        this.comensals = comensals;
    }

    public ArrayList<Chef> getChefs() {
        return chefs;
    }

    public void setChefs(ArrayList<Chef> chefs) {
        this.chefs = chefs;
    }

    public ParametresSimulacio getParametresSimulacio() {
        return parametresSimulacio;
    }

    public void setParametresSimulacio(ParametresSimulacio parametresSimulacio) {
        this.parametresSimulacio = parametresSimulacio;
    }

    public Rellotge getRellotge() {
        return rellotge;
    }

    public void setRellotge(Rellotge rellotge) {
        this.rellotge = rellotge;
    }

    public ArrayList<AreaBuffet> getAreaBuffets() {
        return areaBuffets;
    }

    public void setAreaBuffets(ArrayList<AreaBuffet> areaBuffets) {
        this.areaBuffets = areaBuffets;
    }

    public ArrayList<Grill> getGrills() {
        return grills;
    }

    public void setGrills(ArrayList<Grill> grills) {
        this.grills = grills;
    }

    public RestaurantController getRestaurantController() {
        return restaurantController;
    }

    public void setRestaurantController(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    public synchronized String getStatus() {
        if (this.status.equals("pause")) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Metodos
    public synchronized void play() {
        if (this.status.equals("pause")) {
            notifyAll();
        } else if (this.status.equals("stop")) {
            this.start();
        }
        this.status="running";
    }

    public void start() {
        this.status="running";
        crearObjetos();
        
        Thread vewThread = new Thread(this.restaurantController.getRestaurantView());
        vewThread.start();
    }

    public void stop() {
        this.status="stop";
        for (int i = 0; i < chefsThreads.size(); i++) {
            chefsThreads.get(i).stop();
        }
        for (int i = 0; i < comensalsThreads.size(); i++) {
            comensalsThreads.get(i).stop();
        }
    }

    public void pause() {
        if (this.status.equals("running")) {
            this.status="pause";
        }
    }

    public Estadistiques getEstadistiques() {
        return null;

    }

    public static void setEstadistiques(Estadistiques estadistiques) {
        RestaurantModel.estadistiques = estadistiques;
    }

    public void setParametres(ParametresSimulacio parametresSimulacio) {
        this.setParametresSimulacio(parametresSimulacio);
    }

    public ParametresSimulacio getParametres() {
        return getParametresSimulacio();
    }

    public AreaBuffet getRandomBuffete() {
        Random ran = new Random();
        return areaBuffets.get(ran.nextInt(areaBuffets.size()));
    }

    public int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void crearObjetos() {
        RestaurantModel.estadistiques = new Estadistiques();

        Rango tempsConsumir = new Rango(8, 16);
        Rango tempsTertulia = new Rango(5, 15);
        Rango tempsCuinat = new Rango(4, 8);
        Rango tempsDescans = new Rango(2, 12);
        Rango numComensal = new Rango(12, 36);
        Rango numChefPerGrill = new Rango(1, 3);
        Rango tiempoTrabajando = new Rango(60, 120);

        System.out.println("Crear parametro de simulacion");
        this.parametresSimulacio = new ParametresSimulacio(6, 1, 3, tempsConsumir, tempsTertulia, tempsCuinat,
                tempsDescans, numComensal, numChefPerGrill, tiempoTrabajando);

        System.out.println("Crear Rellotge");
        this.rellotge = Rellotge.getInstance();
        this.rellotge.setRestaurantModel(this);
        this.rellotge.setMinActual(0);
        
        System.out.println("Crear grills");
        ArrayList<Grill> newGrills = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Grill grill = new Grill(this.parametresSimulacio.getMaxPlatGrill(), 0, true);
            newGrills.add(grill);
        }
        this.grills = newGrills;

        System.out.println("Crear areaBuffets");
        ArrayList<AreaBuffet> newAreaBuffets = new ArrayList<>();
        String[] comida = { "Hamburquesa", "Patata Frita", "Coca-Cola" };
        for (int i = 0; i < 3; i++) {
            AreaBuffet areaBuffet = new AreaBuffet(this.parametresSimulacio.getMaxPlatAreaBuffet(), 0, comida[i],
                    this.grills.get(i), new ColaPlatsCuinats(this.parametresSimulacio.getLimitPlatsEnCoa(), 0));
            AreaBuffet.setEstadistiquesBuffet(estadistiques.getAreaBuffet());
            newAreaBuffets.add(areaBuffet);
        }
        this.areaBuffets = newAreaBuffets;

        System.out.println("Crear comensals");
        ArrayList<Comensal> newComensals = new ArrayList<>();
        int randomComensal = randomInt(this.parametresSimulacio.getNumComensal().getMin(),
                this.parametresSimulacio.getNumComensal().getMax());
        for (int i = 0; i < randomComensal; i++) {
            Comensal comensal = new Comensal(0, 0, 0, 0, "agafantPlat", this.rellotge, this);
            Comensal.setEstadistiques(estadistiques.getComensals());
            newComensals.add(comensal);
        }
        this.comensals = newComensals;

        System.out.println("Crear chefs");
        ArrayList<Chef> newChefs = new ArrayList<>();
        int randomChef = randomInt(this.parametresSimulacio.getNumChefPerGrill().getMin(),
                this.parametresSimulacio.getNumChefPerGrill().getMax());
        for (int i = 0; i < randomChef * 3; i++) {
            int horariIniciDescans = randomInt(this.parametresSimulacio.getTiempoTrabajando().getMin(),
                    this.parametresSimulacio.getTiempoTrabajando().getMax());
            Chef chef = new Chef(0, 0, 0, horariIniciDescans, 0, 0, "Cuinant", this.rellotge,
                    this.areaBuffets.get(i % 3),
                    this.grills.get(i % 3), this);
            Chef.setEstadistiquesChefs(estadistiques.getChefs());
            newChefs.add(chef);
        }
        this.chefs = newChefs;

        System.out.println("Comenzar Thread");
        Thread relogThread = new Thread(rellotge);
        relogThread.start();

        chefsThreads=new ArrayList<>();
        comensalsThreads=new ArrayList<>();

        for (Chef fChef : chefs) {
            Thread tchef = new Thread(fChef);
            chefsThreads.add(tchef);
            tchef.start();

        }
        for (Comensal fComensal : comensals) {
            Thread tComensal = new Thread(fComensal);
            comensalsThreads.add(tComensal);
            tComensal.start();

        }
        System.out.println(chefs.size() + "--" + comensals.size());
    }

}