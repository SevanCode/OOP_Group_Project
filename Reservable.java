package aua.am.restaurant_management.core;

/**
 * Reservable interface
 */
public interface Reservable {
    /**
     * isAvailable is a method that checks whether it is available or not( the thing that we want)
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @param restaurant_management of type Restaurant_Management
     * @return boolean
     * @throws Exception of type Exception
     */
    boolean isAvailable(int day, int month, int year,Restaurant_Management restaurant_management) throws Exception;

    /**
     * isReservable is a method that checks whether it is available or not without throwing exception
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @param restaurant_management of type Restaurant_Management
     * @return boolean
     */
    boolean isReservable(int day, int month, int year,Restaurant_Management restaurant_management);
}
