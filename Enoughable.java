/**
 * Dedicating a package
 */
package aua.am.restaurant_management.core;

/**
 * Interface Enoughable
 */
public interface Enoughable {
    /**
     * isEnough method
     * @param restaurant_management of type Restaurant_Management
     * @return of type boolean
     * @throws NotPersonAvailableException of type Exception
     */
    boolean isEnough(Restaurant_Management restaurant_management)throws NotPersonAvailableException;
}
