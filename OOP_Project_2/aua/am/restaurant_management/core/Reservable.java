package aua.am.restaurant_management.core;

/**
 * Reservable interface
 */
public interface Reservable {
    /**
     * isReservable is a method that checks whether it is available or not without throwing exception
     * @param day of type int
     * @param month of type int
     * @param year of type int
//     * @param restaurant_management of type Restaurant_Management
     * @return boolean
     */
    boolean isReservable(int day, int month, int year);
}
