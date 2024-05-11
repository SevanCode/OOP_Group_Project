/**
 * Dedicating a package and import statement
 */
package aua.am.restaurant_management.core;

/**
 * The class Table that implements an interface of the name Reservable
 */

public class Table implements Reservable {
    /**
     * Declaring instance variables
     */
    private boolean IsNotReserved;
    private int yearAvailable;
    private int monthAvailable;
    private int dayAvailable;
    private int numberOfTheTable;
    private double cost;

    /**
     * Constructor
     *
     * @param numberOfTable  of type int
     * @param dayAvailable   of type int
     * @param monthAvailable of type int
     * @param yearAvailable  of type int
     * @param isNotReserved  of type boolean
     */
    public Table(int numberOfTable, int dayAvailable, int monthAvailable, int yearAvailable, boolean isNotReserved, int cost) {
        setNumberOfTables(numberOfTable);
        setDayAvailable(dayAvailable);
        setMonthAvailable(monthAvailable);
        setYearAvailable(yearAvailable);
        setIsNotReserved(isNotReserved);
        setCost(cost);
    }

    /**
     * no-argument constructor for initializing for creating
     */
    public Table() {
        setNumberOfTables(0);
        setDayAvailable(0);
        setMonthAvailable(0);
        setYearAvailable(0);
        setIsNotReserved(true);
    }

    /**
     * Accessor and mutator method for IsNotReserved
     * @param isAvailable of type boolean
     */
    public void setIsNotReserved(boolean isAvailable) {
        this.IsNotReserved = isAvailable;
    }

    public boolean getIsNotReserved() {
        return this.IsNotReserved;
    }

    /**
     * Accessor and mutator method for yearAvailable
     *
     * @param yearAvailable of type int
     */
    public void setYearAvailable(int yearAvailable) {
        this.yearAvailable = yearAvailable;
    }

    public int getYearAvailable() {
        return yearAvailable;
    }

    /**
     * Accessor and mutator method for monthAvailable
     *
     * @param monthAvailable of type int
     */
    public void setMonthAvailable(int monthAvailable) {
        this.monthAvailable = monthAvailable;
    }

    public int getMonthAvailable() {
        return monthAvailable;
    }

    /**
     * Accessor and mutator method for dayAvailable
     *
     * @param dayAvailable of type int
     */
    public void setDayAvailable(int dayAvailable) {
        this.dayAvailable = dayAvailable;
    }

    public int getDayAvailable() {
        return dayAvailable;
    }

    /**
     * Accessor and mutator method for numberOfTheTable
     *
     * @param numberOfTables of type int
     */
    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTheTable = numberOfTables;
    }

    public int getNumberOfTable() {
        return numberOfTheTable;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return this.cost;
    }

    /**
     * isReservable method checks whether the table is reservable with given dates or not
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @return boolean
     */
    public boolean isReservable(int day, int month, int year) {
        if (getDayAvailable() == day &&
                getMonthAvailable() == month &&
                getYearAvailable() == year &&
                getIsNotReserved()) {
            setIsNotReserved(false);
            return true;
        } else
            return false;
    }

    /**
     * equals is a method that checks whether the given table Object has the same number of the table as the calling
     * object or not
     * @param other og type Object
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        Table otherTable = (Table) other;
        return getNumberOfTable() == otherTable.getNumberOfTable();
    }
}
