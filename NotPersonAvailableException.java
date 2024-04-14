/**
 * Dedicating a package
 */
package aua.am.restaurant_management.core;

/**
 * NotPersonAvailableException class
 */
public class NotPersonAvailableException extends Exception{
    /**
     * Constructor that includes a call to its super class's construction (Exception)
     */
    public NotPersonAvailableException(){
        super("Sorry, The person is not available that day");
    }

    /**
     * Constructor that takes an argument of type String
     * And, includes a call to its super class's construction (Exception)
     * @param message of type String
     */
    public NotPersonAvailableException(String message){
        super(message);
    }
}
