/**
 * Dedicating a package
 */
package aua.am.restaurant_management.core;

/**
 * OutOfTablesException class
 */
public class OutOfTablesException extends Exception{
    /**
     * Constructor that includes a call to its super class's construction (Exception)
     */
    public OutOfTablesException(){
        super("No such table is available corresponding to your information");
    }

    /**
     * Constructor that takes an argument of type String
     * And, includes a call to its super class's construction (Exception)
     * @param message of type String
     */
    public OutOfTablesException(String message){
        super(message);
    }
}
