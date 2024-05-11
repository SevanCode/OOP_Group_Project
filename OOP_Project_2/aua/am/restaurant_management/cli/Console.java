package aua.am.restaurant_management.cli;
import aua.am.restaurant_management.core.*;
import java.util.Scanner;

/**
 * Console class
 */
public class Console {
    /**
     * instance variables
     */
    private RestaurantManagement management;

    /**
     * play is a method that throws exception and is invoked in the Main class for initializing the project.
     * It mainly relies on the RestaurantManagement class and its methods
     * @throws Exception of type Exception
     */
    public void play() throws Exception {
        Scanner inputFromUser = new Scanner(System.in);
        management = new RestaurantManagement();
        management.load("SayingWelcome.txt", 0);
        while (!management.getQuitPressed()) {
            int line = 0;
            management.load("Restaurant.txt", line);
            int optionSelectedByUser = inputFromUser.nextInt();
            switch (optionSelectedByUser) {
                case 1:
                    management.ifUserWantsToReserveTables();
                    break;
                case 2:
                    management.ifUserWantToReserveStaff();
                    break;
                case 3:
                    management.ifUserWantToSeeReport();
                    break;
                case 4:
                    management.ifUserWantToRemoveSomething(optionSelectedByUser);
                    break;
                case 5:
                    management.ifUserWantToCompleteReservation();
                    break;
                case 6:
                    management.ifUserReservedOrDoesNotReserveForSomethingWhileQuitIsPressed();
                    break;
                default:
                    management.ifUserInputsInvalidNumber();
                    break;
            }
        }
        management.afterQuitIsPressed_DoesUserReservedForSomethingOrNot();
    }
}