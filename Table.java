/**
 * Dedicating a package and import statement
 */
package aua.am.restaurant_management.core;

import java.time.Month;

/**
 * The class Table that implements an interface of the name Reservable
 */

public class Table implements Reservable{
    /**
     * Declaring instance variables
     */
    private boolean getIsNotReserved;
    private int yearAvailable;
    private int monthAvailable;
    private int dayAvailable;
    private int numberOfTheTable;

    /**
     * Constructor
     * @param numberOfTable of type int
     * @param dayAvailable of type int
     * @param monthAvailable of type int
     * @param yearAvailable of type int
     * @param isNotReserved of type boolean
     */
    public Table(int numberOfTable,int dayAvailable, int monthAvailable, int yearAvailable , boolean isNotReserved){
        setNumberOfTables(numberOfTable);
        setDayAvailable(dayAvailable);
        setMonthAvailable(monthAvailable);
        setYearAvailable(yearAvailable);
        setIsNotReserved(isNotReserved);
    }

    /**
     * Accessor and mutator method for IsNotReserved
     * @param isAvailable of type boolean
     */
    public void setIsNotReserved(boolean isAvailable){
        this.getIsNotReserved = isAvailable;
    }
    public boolean getIsNotReserved(){
        return this.getIsNotReserved;
    }

    /**
     * Accessor and mutator method for yearAvailable
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
     * @param numberOfTables of type int
     */
    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTheTable = numberOfTables;
    }

    public int getNumberOfTable() {
        return numberOfTheTable;
    }

    /**
     * The isAvailable method extends an interface and throws a OutOfTablesException exception
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @param restaurant_management of type Restaurant_Management
     * @return of type boolean
     * @throws OutOfTablesException of type exception
     */
    public boolean isAvailable(int day, int month, int year ,Restaurant_Management restaurant_management) throws OutOfTablesException{
        Table[] tablesThatAreAvailable = restaurant_management.getTables();
        boolean isValid = false;
        try {
            for (int i = 0; i < tablesThatAreAvailable.length; i++) {
                if (tablesThatAreAvailable[i].getDayAvailable() == day &&
                        tablesThatAreAvailable[i].getMonthAvailable() == month &&
                        tablesThatAreAvailable[i].getYearAvailable() == year &&
                        tablesThatAreAvailable[i].getNumberOfTable() == getNumberOfTable() &&
                        getIsNotReserved()) {
                    isValid = true;
                    tablesThatAreAvailable[i].setIsNotReserved(false);
                }
            }
            if (isValid == true){
                return true;
            } else {
                throw new OutOfTablesException();
            }
        }catch (OutOfTablesException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * isReservable method
     * @param day of type int
     * @param month of type int
     * @param year of type int
     * @param restaurant_management of type Restaurant_Management
     * @return
     */
    public boolean isReservable(int day, int month, int year, Restaurant_Management restaurant_management){
        return true;
    }
}
