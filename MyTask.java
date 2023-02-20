public class MyTask {
    public static void main(String[] args) {

        RestaurantController restaurantController = new RestaurantController();
        RestaurantModel restaurantModel = new RestaurantModel("running");
        restaurantController.setRestaurantModel(restaurantModel);
        restaurantModel.setRestaurantController(restaurantController);
        RestaurantView restaurantView = new RestaurantView(restaurantController);
        restaurantController.setRestaurantView(restaurantView);
        
        Thread view= new Thread(restaurantView);
        view.start();
    }
}
