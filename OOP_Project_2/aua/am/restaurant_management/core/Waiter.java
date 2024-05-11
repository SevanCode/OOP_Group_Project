/**
 * Dedicating a package and import statement
 */
package aua.am.restaurant_management.core;

/**
 * The class Waiter that inherits the class Person and implements two interface classes
 */
public class Waiter extends Person implements Reservable , Enoughable{
    /**
     * Declaring instance variables and a final constant
     */
    private static final int NUMBER_OF_GUESTS_HANDLE = 6;

    /**
     * Constructor Waiter that takes four arguments, one of them is of type String and other int
     * @param name of type String
     * @param dayAvailable of type int
     * @param monthAvailable of type int
     * @param yearAvailable of type int
     */
    public Waiter(String name, int dayAvailable, int monthAvailable, int yearAvailable, int cost) {
        setNameOfTheWorker(name);
        setYearAvailable(yearAvailable);
        setMonthAvailable(monthAvailable);
        setDayAvailable(dayAvailable);
        setCost(cost);
    }

    /**
     * no-argument constructor that initializes instance variables
     */
    public Waiter(){
        setDayAvailable(0);
        setMonthAvailable(0);
        setYearAvailable(0);
    }
    /**
     * mutator and mutator methods for nameOfTheWaiter
     * @param name of type String
     */
    public void setNameOfTheWorker(String name){
        this.nameOfTheWorker = name;
    }

    /**
     * an accessor for name of the worker
     * @return of type String
     */
    public String getNameOfTheWorker(){
        return this.nameOfTheWorker;
    }

    /**
     * a mutator for cost
     * @param cost of type int
     */
    public void setCost(double cost){
        this.cost = cost;
    }

    /**
     * an accessor for cost
     * @return of type int
     */
    public double getCost(){
        return this.cost;
    }

    /**
     * an accessor static for number of guests handle
     * @return of type in
     */
    public static int getNumberOfGuestsHandle(){
        return NUMBER_OF_GUESTS_HANDLE;
    }

    /**
     * isEnough method extends an interface and throws a NotPersonAvailableException exception
     * @param restaurant_management of type Restaurant_Management
     * @return of type boolean
     * @throws NotPersonAvailableException of type Exception
     */
    public boolean isEnough(RestaurantManagement restaurant_management) throws NotPersonAvailableException {
        try {
            if (restaurant_management.getNumberOfGuests() > restaurant_management.getNumberOfWaitersNeeded() * NUMBER_OF_GUESTS_HANDLE)
                throw new NotPersonAvailableException("There is not enough waiters for the " + restaurant_management.getNumberOfGuests() + " number of guests.");
        }catch (NotPersonAvailableException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * isReservable is a method the checks whether the waiter is available in the given date or not
     * @param day of type int
     * @param month of type int
     * @param year of type int
    //     * @param restaurant_management of type Restaurant_Management
     * @return boolean
     */
    public boolean isReservable(int day, int month, int year){
        if (getDayAvailable() == day &&
                getMonthAvailable() == month &&
                getYearAvailable() == year){
            return true;
        }else
            return false;
    }

    /**
     * equals is a method that checks whether two Waiters have the same aspects or not for removal activities.
     * @param other of type Object
     * @return boolean
     */
    public boolean equals(Object other){
        if (other == null){
            return false;
        } else if (getClass() != other.getClass()) {
            return false;
        }
        Waiter object = (Waiter) other;
        return getNameOfTheWorker().equals(object.getNameOfTheWorker()) &&
                getDayAvailable() == object.getDayAvailable() &&
                getMonthAvailable() == object.getMonthAvailable() &&
                getYearAvailable() == object.getYearAvailable();
    }
}
