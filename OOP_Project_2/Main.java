import aua.am.restaurant_management.cli.Console;
import aua.am.restaurant_management.ui.RestaurantUI;

/**
 * Main class
 */
public class Main {
    /**
     * main method that invokes play method in Console class or initializes a graphical user interface for user
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0){
            RestaurantUI UI = new RestaurantUI();
        } else if (args.length == 1 && args[0].equals("-console")){
            Console console = new Console();
            console.play();
        }
    }
}
