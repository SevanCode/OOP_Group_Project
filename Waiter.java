/**
 * Dedicating a package and import statement
 */
package aua.am.restaurant_management.core;

import com.sun.source.tree.Tree;

/**
 * The class Waiter that inherits the class Person and implements two interface classes
 */
public class Waiter extends Person implements Reservable , Enoughable{
    /**
     * Declaring instance variables and a final constant
     */
    private static final int NUMBER_OF_GUESTS_HANDLE = 6;
    private int numberOfWaitersNeeded;
    private String nameOfTheWaiter;

    /**
     * Constructor Waiter that takes four arguments, one of them is of type String and other int
     * @param name of type String
     * @param dayAvailable of type int
     * @param monthAvailable of type int
     * @param yearAvailable of type int
     */
    public Waiter(String name, int dayAvailable, int monthAvailable, int yearAvailable) {
        setNameOfTheWaiter(name);
        setYearAvailable(yearAvailable);
        setMonthAvailable(monthAvailable);
        setDayAvailable(dayAvailable);
    }

    /**
     * Constructor that takes four arguments but all of them is of type int
     * @param numberOfWaitersNeeded of type int
     * @param dayAvailable of type int
     * @param monthAvailable of type int
     * @param yearAvailable of type int
     */
    public Waiter(int numberOfWaitersNeeded,int dayAvailable, int monthAvailable, int yearAvailable){
        setNumberOfWaitersNeeded(numberOfWaitersNeeded);
        setDayAvailable(yearAvailable);
        setMonthAvailable(monthAvailable);
        setYearAvailable(dayAvailable);
    }

    /**
     * accessor and mutator methods for nameOfTheWaiter
     * @param name of type String
     */
    public void setNameOfTheWaiter(String name){
        this.nameOfTheWaiter = name;
    }

    public String getNameOfTheWaiter(){
        return this.nameOfTheWaiter;
    }

    /**
     * accessor and mutator methods for numberOfWaitersNeeded
     * @param numberOfWaiters of type int
     */
    public void setNumberOfWaitersNeeded(int numberOfWaiters) {
        this.numberOfWaitersNeeded = numberOfWaiters;
    }

    public int getNumberOfWaitersNeeded() {
        return numberOfWaitersNeeded;
    }

    /**
     * The isAvailable method extends an interface and throws a NotPersonAvailableException exception
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @param restaurant_management of type Restaurant_Management
     * @return of type boolean
     * @throws NotPersonAvailableException of type Exception
     */
    public boolean isAvailable(int day, int month, int year, Restaurant_Management restaurant_management) throws NotPersonAvailableException {
        Person[] peopleThatAreAvailable = restaurant_management.getPeople();
        boolean isValid = false;
        try {
            for (int i = 0; i < peopleThatAreAvailable.length; i++) {
                if (peopleThatAreAvailable[i] instanceof Waiter &&
                        peopleThatAreAvailable[i].getYearAvailable() == year &&
                        peopleThatAreAvailable[i].getMonthAvailable() == month &&
                        peopleThatAreAvailable[i].getDayAvailable() == day) {
                    isValid = true;
                }
            }
            if (isValid) {
                return true;
            } else {
                throw new NotPersonAvailableException();
            }
        } catch (NotPersonAvailableException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * isEnough method extends an interface and throws a NotPersonAvailableException exception
     * @param restaurant_management of type Restaurant_Management
     * @return of type boolean
     * @throws NotPersonAvailableException of type Exception
     */
    public boolean isEnough(Restaurant_Management restaurant_management) throws NotPersonAvailableException {
        try {
            if (restaurant_management.getNumberOfGuests() > getNumberOfWaitersNeeded() * NUMBER_OF_GUESTS_HANDLE)
                throw new NotPersonAvailableException("There is not enough waiters for the" + restaurant_management.getNumberOfGuests() + " number of guests.");
        }catch (NotPersonAvailableException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * isReservable method
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @param restaurant_management of type Restaurant_Management
     * @return
     */
    public boolean isReservable(int day, int month, int year, Restaurant_Management restaurant_management) {
        Person[] peopleThatAreAvailable = restaurant_management.getPeople();
        boolean isValid = false;
        for (int i = 0; i < peopleThatAreAvailable.length; i++) {
            if (peopleThatAreAvailable[i] instanceof Waiter &&
                    peopleThatAreAvailable[i].getYearAvailable() == year &&
                    peopleThatAreAvailable[i].getMonthAvailable() == month &&
                    peopleThatAreAvailable[i].getDayAvailable() == day) {
                isValid = true;
            }
        }
        if (isValid)
            return true;
        else
            return false;
    }
}
