package aua.am.restaurant_management.core;

/**
 * Chief class that extends Person and implements Reservable and Enoughable
 */
public class Chef extends Person implements Reservable,Enoughable{
    /**
     * constants
     */
    private static final int NUMBER_OF_DISHES_HANDLE = 6;

    /**
     * a constructor for initializing instance variables
     * @param name of type String
     * @param dayAvailable of type int
     * @param monthAvailable of type int
     * @param yearAvailable of type int
     */
    public Chef(String name, int dayAvailable, int monthAvailable, int yearAvailable, int cost){
        setNameOfTheWorker(name);
        setDayAvailable(dayAvailable);
        setMonthAvailable(monthAvailable);
        setYearAvailable(yearAvailable);
        setCost(cost);
    }

    /**
     * no-argument constructor for initializing instance variables
     */
    public Chef(){
        setDayAvailable(0);
        setMonthAvailable(0);
        setYearAvailable(0);
    }
    /**
     * a mutator for name of the chief
     * @param name of type String
     */
    public void setNameOfTheWorker(String name){
        this.nameOfTheWorker = name;
    }

    /**
     * a setter for name of the chief
     * @return this.nameOfTheChief
     */
    public String getNameOfTheWorker(){
        return this.nameOfTheWorker;
    }

    /**
     * a mutator for cost
     * @param cost of type cost
     */
    public void setCost(double cost){
        this.cost = cost;
    }

    /**
     * an accessor for cost
     * @return of type cost
     */
    public double getCost(){
        return this.cost;
    }

    public static int getNumberOfGuestsHandle(){
        return NUMBER_OF_DISHES_HANDLE;
    }

    /**
     * isEnough is method that overdrives from Enoughable interface
     * @param restaurant_management of type Restaurant_Management
     * @return boolean
     * @throws NotPersonAvailableException Exception
     */
    public boolean isEnough(RestaurantManagement restaurant_management)throws NotPersonAvailableException{
        try {
            if (restaurant_management.getNumberOfGuests() > restaurant_management.getNumberOfChiefsNeeded() * NUMBER_OF_DISHES_HANDLE)
                throw new NotPersonAvailableException("There is not enough Chiefs for the " + restaurant_management.getNumberOfGuests() + " number of guests.");
        }catch (NotPersonAvailableException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * isReservable is a method that checks whether the chief that user wants is available in the given date or not
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
     * equals is method that checks whether two chiefs are similar or not
     * because of removal activities
     * @param other of type Object
     * @return boolean
     */
    public boolean equals(Object other){
        if (other == null){
            return false;
        } else if (getClass() != other.getClass()) {
            return false;
        }
        Chef object = (Chef) other;
        return getNameOfTheWorker().equals(object.getNameOfTheWorker()) &&
                getDayAvailable() == object.getDayAvailable() &&
                getMonthAvailable() == object.getMonthAvailable() &&
                getYearAvailable() == object.getYearAvailable();
    }
}
