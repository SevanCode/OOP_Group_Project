package aua.am.restaurant_management.core;

/**
 * Chief class that extends Person and implements Reservable and Enoughable
 */
public class Chief extends Person implements Reservable,Enoughable{
    /**
     * instance variables
     */
    private String nameOfTheChief;
    private static final int NUMBER_OF_DISHES_HANDLE = 6;
    private int numberOfChiefsNeeded;

    /**
     * a constructor for initializing instance variables
     * @param name of type String
     * @param dayAvailable of type int
     * @param monthAvailable of type int
     * @param yearAvailable of type int
     */
    public Chief(String name, int dayAvailable, int monthAvailable, int yearAvailable){
        setNameOfTheChief(name);
        setDayAvailable(dayAvailable);
        setMonthAvailable(monthAvailable);
        setYearAvailable(yearAvailable);
    }

    /**
     * a constructor for initializing the instance variables
     * @param numberOfChiefsNeeded of type int
     * @param dayAvailable of type int
     * @param monthAvailable of type int
     * @param yearAvailable of type int
     */
    public Chief(int numberOfChiefsNeeded, int dayAvailable, int monthAvailable, int yearAvailable) {
        setNumberOfChiefsNeeded(numberOfChiefsNeeded);
        setYearAvailable(yearAvailable);
        setMonthAvailable(monthAvailable);
        setDayAvailable(dayAvailable);
    }

    /**
     * a mutator for name of the chief
     * @param name of type String
     */
    public void setNameOfTheChief(String name){
        this.nameOfTheChief = name;
    }

    /**
     * a setter for name of the chief
     * @return this.nameOfTheChief
     */
    public String getNameOfTheChief(){
        return this.nameOfTheChief;
    }

    /**
     * a mutator for name of the chief
     * @param numberOfChiefs of type int
     */
    public void setNumberOfChiefsNeeded(int numberOfChiefs) {
        this.numberOfChiefsNeeded = numberOfChiefs;
    }

    /**
     * a setter fot number of chiefs needed
     * @return numberOfChiefsNeeded
     */
    public int getNumberOfChiefsNeeded() {
        return numberOfChiefsNeeded;
    }

    /**
     * isAvailable a method that is override by Reservable interface
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @param restaurant_management of type Restaurant_Management
     * @return boolean
     * @throws NotPersonAvailableException Exception
     */
    public boolean isAvailable(int day, int month, int year, Restaurant_Management restaurant_management) throws NotPersonAvailableException{
        Person[] peopleThatAreAvailable = restaurant_management.getPeople();
        boolean isValid = false;
        try{
            for (int i = 0; i < peopleThatAreAvailable.length; i++){
                if (getNumberOfChiefsNeeded() <= NUMBER_OF_DISHES_HANDLE &&
                        peopleThatAreAvailable[i] instanceof Chief &&
                        peopleThatAreAvailable[i].getYearAvailable() == year &&
                        peopleThatAreAvailable[i].getMonthAvailable() == month &&
                        peopleThatAreAvailable[i].getDayAvailable() == day) {
                    isValid = true;
                }
            }
            if (isValid){
                return true;
            }else {
                throw new NotPersonAvailableException();
            }
        }catch (NotPersonAvailableException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * isEnough is method that overdrives from Enoughable interface
     * @param restaurant_management of type Restaurant_Management
     * @return boolean
     * @throws NotPersonAvailableException Exception
     */
    public boolean isEnough(Restaurant_Management restaurant_management)throws NotPersonAvailableException{
        try {
            if (restaurant_management.getNumberOfGuests() > getNumberOfChiefsNeeded() * NUMBER_OF_DISHES_HANDLE)
                throw new NotPersonAvailableException("There is not enough Chiefs for the " + restaurant_management.getNumberOfGuests() + " number of guests.");
        }catch (NotPersonAvailableException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean isReservable(int day, int month, int year, Restaurant_Management restaurant_management){
        Person[] peopleThatAreAvailable = restaurant_management.getPeople();
        boolean isValid = false;
        for (int i = 0; i < peopleThatAreAvailable.length; i++) {
            if (peopleThatAreAvailable[i] instanceof Chief &&
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
