/**
 * Dedicating a package
 */
package aua.am.restaurant_management.core;

/**
 * The class FlowerNotFreshException
 */
public class FlowerNotFreshException extends Exception{
    /**
     * Constructor that includes a call to its super class's construction (Exception)
     */
    public FlowerNotFreshException(){
        super("Sorry, we do not have any fresh flowers on this day");
    }

    /**
     * Constructor that takes an argument of type String
     * And, includes a call to its super class's construction (Exception)
     * @param message of type String
     */
    public FlowerNotFreshException(String message){
        super(message);
    }
}

