package aua.am.restaurant_management.core;

public abstract class Person implements Reservable{
    private boolean isAvailable;
    private String nameOfTheWorker;
    private int yearAvailable;
    private int monthAvailable;
    private int dayAvailable;

    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setNameOfTheWorker(String nameOfTheWaiter){
        this.nameOfTheWorker = nameOfTheWaiter;
    }
    public String getNameOfTheWorker(){
        return this.nameOfTheWorker;
    }

    public void setYearAvailable(int yearAvailable) {
        this.yearAvailable = yearAvailable;
    }

    public int getYearAvailable() {
        return yearAvailable;
    }

    public void setMonthAvailable(int monthAvailable) {
        this.monthAvailable = monthAvailable;
    }

    public int getMonthAvailable() {
        return monthAvailable;
    }

    public void setDayAvailable(int dayAvailable) {
        this.dayAvailable = dayAvailable;
    }

    public int getDayAvailable() {
        return dayAvailable;
    }

    public abstract boolean isAvailable(int day, int month, int year,Restaurant_Management restaurant_management)throws Exception;

}
