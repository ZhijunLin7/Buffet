
public class Rellotge implements Runnable {

    // Atributos
    private int minActual;
    private int multiplicadorTemps;
    private static Rellotge rellotge;
    private RestaurantModel restaurantModel;

    // Constuctor
    private Rellotge(int minActual, int multiplicadorTemps) {
        this.minActual = minActual;
        this.multiplicadorTemps = multiplicadorTemps;
    }

    // Getters y setters
    public int getMinActual() {
        return minActual;
    }

    public void setMinActual(int minActual) {
        this.minActual = minActual;
    }

    public int getMultiplicadorTemps() {
        return multiplicadorTemps;
    }

    public void setMultiplicadorTemps(int multiplicadorTemps) {
        this.multiplicadorTemps = multiplicadorTemps;
    }

    public static Rellotge getRellotge() {
        return rellotge;
    }

    public static void setRellotge(Rellotge rellotge) {
        Rellotge.rellotge = rellotge;
    }

    public static synchronized Rellotge getInstance() {
        if (rellotge == null) {
            rellotge = new Rellotge(0, 1);
        }
        return rellotge;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }

    // Metodos
    public int minutsEnMilisegons(int minuto) {
        return minuto * 1000;
    }

    public int getMinutoActual() {
        return minActual;
    }

    public void getIntervalEnMinuts() {

    }

    @Override
    public void run() {

        while (!this.restaurantModel.getStatus().equals("stop")) {
            restaurantModel.getStatus();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.setMinActual(minActual + 1 * multiplicadorTemps);
            System.out.println(getMinActual());
        }

    }

}
