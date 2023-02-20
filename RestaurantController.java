
public class RestaurantController implements Runnable {

    // Atributos
    private RestaurantView restaurantView;
    private RestaurantModel restaurantModel;

    // Constructor
    public RestaurantController() {

    }

    public RestaurantController(RestaurantView restaurantView, RestaurantModel restaurantModel) {
        this.restaurantView = restaurantView;
        this.restaurantModel = restaurantModel;

    }

    // Getter y Setter
    public RestaurantView getRestaurantView() {
        return restaurantView;
    }

    public void setRestaurantView(RestaurantView restaurantView) {
        this.restaurantView = restaurantView;
    }

    public RestaurantModel getRestaurantModel() {
        return restaurantModel;
    }

    public void setRestaurantModel(RestaurantModel restaurantModel) {
        this.restaurantModel = restaurantModel;
    }

    // Metodos
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    public void play() {
        restaurantModel.play();   
        
    }

    public void pause() {
        restaurantModel.pause();
    }

    public void stop() {
        restaurantModel.stop();
    }

    public void getStatistics() {

    }

    public void canviStatusComensal() {

    }

    public void canviStatusAreaBuffet() {

    }

    public void canviStatusGrill() {

    }

}
