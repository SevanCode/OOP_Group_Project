package aua.am.restaurant_management.core;

/**
 * Person class
 */
public abstract class Person implements Reservable{
    /**
     * instance variables
     */
    protected String nameOfTheWorker;
    private int yearAvailable;
    private int monthAvailable;
    private int dayAvailable;
    protected double cost;

    /**
     * mutator for nameOfTheWorker
     * @param nameOfTheWaiter of type String
     */
    public abstract void setNameOfTheWorker(String nameOfTheWaiter);

    /**
     * setter for name of the worker
     * @return nameOfTheWorker
     */
    public abstract String getNameOfTheWorker();

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
     * isReservable is an abstract method that checks whether a chief or a waiter is reservable in the given date or not.
     * @param day of type int
     * @param month of type int
     * @param year of type int
    //     * @param restaurant_management of type Restaurant_Management
     * @return boolean
     */
    public abstract boolean isReservable(int day, int month, int year);

    /**
     * equals is an abstract method that checks whether a waiter has equal aspects, comparing other waiter.
     * As well as for chiefs.
     * @param other of type Object
     * @return boolean
     */
    public abstract boolean equals(Object other);

    /**
     * getCost is an abstract method that returns the cost of each employee
     * @return int
     */
    public abstract double getCost();

}
