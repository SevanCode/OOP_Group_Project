package aua.am.restaurant_management.core;

/**
 * Person class
 */
public abstract class Person implements Reservable{
    /**
     * instance variables
     */
    private boolean isAvailable;
    private String nameOfTheWorker;
    private int yearAvailable;
    private int monthAvailable;
    private int dayAvailable;

    /**
     * mutator for is available
     * @param isAvailable of type boolean
     */

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    /**
     * setter for is available
     * @return isAvailable
     */
    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    /**
     * mutator for nameOfTheWorker
     * @param nameOfTheWaiter of type String
     */
    public void setNameOfTheWorker(String nameOfTheWaiter){
        this.nameOfTheWorker = nameOfTheWaiter;
    }

    /**
     * setter for name of the worker
     * @return nameOfTheWorker
     */
    public String getNameOfTheWorker(){
        return this.nameOfTheWorker;
    }

    /**
     * mutator for year available
     * @param yearAvailable of type int
     */
    public void setYearAvailable(int yearAvailable) {
        this.yearAvailable = yearAvailable;
    }

    /**
     * setter for year available
     * @return yearAvailable
     */
    public int getYearAvailable() {
        return yearAvailable;
    }

    /**
     * setter for month available
     * @param monthAvailable of type int
     */
    public void setMonthAvailable(int monthAvailable) {
        this.monthAvailable = monthAvailable;
    }

    /**
     * setter for month available
     * @return yearAvailable
     */
    public int getMonthAvailable() {
        return monthAvailable;
    }

    /**
     * setter for day available
     * @param dayAvailable of type int
     */
    public void setDayAvailable(int dayAvailable) {
        this.dayAvailable = dayAvailable;
    }

    /**
     * setter for day available
     * @return yearAvailable
     */
    public int getDayAvailable() {
        return dayAvailable;
    }

    /**
     * isAvailable is an abstract method
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @param restaurant_management of type Restaurant_Management
     * @return nothing because it is abstract
     * @throws Exception of type Exception
     */
    public abstract boolean isAvailable(int day, int month, int year,Restaurant_Management restaurant_management)throws Exception;

}
