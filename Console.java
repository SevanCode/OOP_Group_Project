package aua.am.restaurant_management.cli;
import aua.am.restaurant_management.core.*;

import java.io.*;
import java.util.Scanner;

/**
 * Console class
 */
public class Console {
    /**
     * instance variables
     */
    private int numberOfWaitersUserWants;
    private int numberOfChiefsUserWants;
    private Table[] reservedTables = new Table[0];
    private Person[] reservedStaff = new Person[0];
    private Restaurant_Management management;

    /**
     * play is a method that throws exception and is invoked in the Main class for initializing the project
     * @throws Exception of type Exception
     */
    public void play() throws Exception {
        Scanner inputFromUser = new Scanner(System.in);
        management = new Restaurant_Management(addTablesFromFile(), addStaffFromFile());
        while (!management.getQuitPressed()) {
            int line = 0;
            load("Restaurant.txt", line);
            int optionSelectedByUser = inputFromUser.nextInt();
            int dayFromUser;
            int monthFromUser;
            int yearFromUser;
            int numberOfTheTable;
            int numberOfWaitersUserWants;
            int numberOfChiefsUserWants = 0;
            int numberOfGuests = 0;
            int numberOfWaitersInProcess = 0;
            int numberOfChiefsInProcess = 0;
            int whichEventUserWants = 0;
            if (optionSelectedByUser == 1) {
                load("QuestionsForTableReservation.txt", line);
                dayFromUser = inputFromUser.nextInt();
                monthFromUser = inputFromUser.nextInt();
                yearFromUser = inputFromUser.nextInt();
                line++;
                load("QuestionsForTableReservation.txt", line);
                numberOfTheTable = inputFromUser.nextInt();
                line++;
                Table table = new Table(numberOfTheTable, dayFromUser, monthFromUser, yearFromUser,true);
                if (table.isAvailable(table.getDayAvailable(), table.getMonthAvailable(), table.getYearAvailable(), management)) {
                    load("QuestionsForTableReservation.txt", line);
                    line = 0;
                    reservedTables = appendingToTableArray(reservedTables,table);
                } else {
                    System.out.println("Please try again");
                    line = 0;
                }
            } else if (optionSelectedByUser == 2) {
                load("QuestionsForStaffReservation.txt",line);
                dayFromUser = inputFromUser.nextInt();
                monthFromUser = inputFromUser.nextInt();
                yearFromUser = inputFromUser.nextInt();
                line++;
                load("QuestionsForStaffReservation.txt",line);
                numberOfWaitersUserWants = inputFromUser.nextInt();
                this.numberOfWaitersUserWants = numberOfWaitersUserWants;
                line++;
                load("QuestionsForStaffReservation.txt",line);
                numberOfChiefsUserWants = inputFromUser.nextInt();
                this.numberOfChiefsUserWants = numberOfChiefsUserWants;
                line++;
                load("QuestionsForStaffReservation.txt",line);
                numberOfGuests = inputFromUser.nextInt();
                line++;
                management.setNumberOfGuests(numberOfGuests);
                Waiter waiter = new Waiter(numberOfWaitersUserWants,dayFromUser,monthFromUser,yearFromUser);
                Chief chief = new Chief(numberOfWaitersUserWants,dayFromUser,monthFromUser,yearFromUser);
                if (waiter.isEnough(management) && chief.isEnough(management)){
                    Person[] people = management.getPeople();
                    for (int i = 0; i < people.length; i++){
                        if (people[i].isReservable(dayFromUser,monthFromUser,yearFromUser,management)){
                            if (people[i] instanceof Waiter && people[i].isAvailable(dayFromUser,monthFromUser,yearFromUser,management)) {
                                reservedStaff = appendingToPersonArray(reservedStaff,people[i]);
                                numberOfWaitersInProcess++;
                            }
                            else if (people[i] instanceof Chief && people[i].isAvailable(dayFromUser,monthFromUser,yearFromUser,management)) {
                                reservedStaff = appendingToPersonArray(reservedStaff,people[i]);
                                numberOfChiefsInProcess++;
                            }
                        }
                    }
                    try {
                        if (waiter.getNumberOfWaitersNeeded() > numberOfWaitersInProcess)
                            throw new NotPersonAvailableException("Number of waiters that are available are less than the number that you want");
                        else if (chief.getNumberOfChiefsNeeded() > numberOfWaitersInProcess)
                            throw new NotPersonAvailableException("Number of chiefs that are available are less than the number that you want");
                    }catch (NotPersonAvailableException e){
                        System.out.println(e.getMessage());
                        System.out.println("Please try again");
                        continue;
                    }
                    load("QuestionsForStaffReservation.txt",line);
                    line = 0;
                } else {
                    System.out.println("Please try again");
                    line = 0;
                }
            } else if (optionSelectedByUser == 3) {
                load("EventQuestions.txt", line);
                whichEventUserWants = inputFromUser.nextInt();
                if (whichEventUserWants == 1 || whichEventUserWants == 2){
                    load("AskingTheDate.txt",line);
                    dayFromUser = inputFromUser.nextInt();
                    monthFromUser = inputFromUser.nextInt();
                    yearFromUser = inputFromUser.nextInt();
                    management.setWhichEventIsSelected(whichEventUserWants);
                    int determineNumberOfBalloons = management.determineNumberOfBalloon();
                    Events events = new Events(whichEventUserWants ,dayFromUser, determineNumberOfBalloons);
                    if (!events.isFresh()){
                        System.out.println("Please try again");
                    }
                }
                else {
                    System.out.println("Please try again");
                }
            } else if (optionSelectedByUser == 4) {
                printTables(reservedTables);
                System.out.println();
                printStaff(reservedStaff);
                System.out.println();
            } else {
                management.setQuitPressed(true);
            }
        }
        System.out.println("Thanks For Your Reservation!");
    }


    /**
     * addStaffFromFile is a method that adds the contents written in the StaffThatAreAvailable.txt file
     * @return StaffThatAreAvailable.txt file content in String
     */
    public String addStaffFromFile(){
        Scanner readingFromFile = null;
        try {
            readingFromFile = new Scanner(new FileInputStream("StaffThatAreAvailable.txt"));
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return readingFromFile.nextLine();
    }

    /**
     * addTablesFromFile is a method that adds the contents written in the TablesThatAreAvailable.txt
     * @return TablesThatAreAvailable.txt file content in String
     */
    public String addTablesFromFile(){
        Scanner readingFromFile = null;
        try {
            readingFromFile = new Scanner(new FileInputStream("TablesThatAreAvailable.txt"));
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return readingFromFile.nextLine();
    }

    /**
     * load is a method that loads and reads the content of the files given as an argument during declaration
     * @param file of type String
     * @param line of type int
     */
    public void load(String file,int line){
        Scanner readingFromFile = null;
        try {
            readingFromFile = new Scanner(new FileInputStream(file));
            if (file.equals("Restaurant.txt")) {
                save(file,6,line);
            } else if (file.equals("QuestionsForTableReservation.txt")){
                save(file,line + 1,line);
            } else if (file.equals("QuestionsForStaffReservation.txt")){
                save(file,line + 1,line);
            } else if (file.equals("EventQuestions.txt")){
                save(file,3,line);
            } else if (file.equals("AskingTheDate.txt")){
                save(file,1,line);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        readingFromFile.close();
    }

    /**
     * save is a method that reads and writes to SavedProcess.txt file to save the game.
     * @param file of type String
     * @param count of type int
     * @param line of type int
     */
    public void save(String file,int count,int line){
        Scanner readingFile = null;
        PrintWriter outputStream = null;

        try {
            readingFile = new Scanner(new FileInputStream(file));
            outputStream = new PrintWriter(new FileOutputStream("SavedProcess.txt", true));
            if (file.equals("Restaurant.txt")) {
                for (int i = line; i <= count; i++) {
                    String content = readingFile.nextLine();
                    System.out.println(content);
                    outputStream.println(content);
                    line++;
                }
            } else {
                if (line == 0){
                    System.out.println(readingFile.nextLine());
                } else {
                    for (int i = 0; i < line; i++) {
                        readingFile.nextLine();
                    }
                    System.out.println(readingFile.nextLine());
                }
            }
            }catch(FileNotFoundException e){
                System.out.println(e.getMessage());
                System.exit(0);
            }
            readingFile.close();
            outputStream.close();
    }

    /**
     * appendingToTableArray is a method to append new Tables selected by user to reservedTables
     * @param reservedTables of type Table
     * @param wantedTable of type Table
     * @return Table[]
     */
    public Table[] appendingToTableArray(Table[] reservedTables, Table...wantedTable){
        Table[] result = new Table[reservedTables.length + wantedTable.length];
        int i = 0;
        for (; i < reservedTables.length; i++) {
            result[i] = reservedTables[i];
        }
        for (int j = 0; j < wantedTable.length; i++, j++){
            result[i] = wantedTable[j];
        }
        result[0].setIsNotReserved(true);
        return result;
    }

    /**
     * appendingToPersonArray is a method to append new Person selected by user to reservedStaff
     * @param people of type Person
     * @param wantedStaff of type Person
     * @return Person[]
     */
    public Person[] appendingToPersonArray(Person[] people, Person...wantedStaff){
        Person[] result = new Person[people.length + wantedStaff.length];
        int i = 0;
        for (; i < people.length; i++) {
            result[i] = people[i];
        }
        for (int j = 0; j < wantedStaff.length; i++, j++){
            result[i] = wantedStaff[j];
        }
        return result;
    }

    /**
     * printTables that prints reservedTables
     * @param tables of type Table
     */
    public void printTables(Table[] tables){
        if (tables.length == 0){
            System.out.println("Tables : None");
            return;
        }else {
            for (int i = 0; i < tables.length; i++){
                System.out.print("Table number " + tables[i].getNumberOfTable() + " reserved for " + tables[i].getDayAvailable() + " " + tables[i].getMonthAvailable() + " " + tables[i].getYearAvailable());
            }
        }
    }

    /**
     * printTables that prints reservedStaff
     * @param people of type Person
     */
    public void printStaff(Person[] people){
        if (people.length == 0){
            System.out.println(" Staff : None");
            return;
        }else {
            for (int i = 0 , j = 0; i < this.numberOfChiefsUserWants; j++) {
                if (people[j] instanceof Waiter) {
                    System.out.println("Waiter :  " + ((Waiter) people[j]).getNameOfTheWaiter() + "reserved for " + people[j].getDayAvailable() + " " + people[j].getMonthAvailable() + " " + people[j].getYearAvailable() + " ");
                    i++;
                }
            }
            for (int i = 0 , j = 0; i < this.numberOfChiefsUserWants; j++){
                if (people[j] instanceof Chief) {
                    System.out.println("Chief :  " + people[i].getNameOfTheWorker() + "reserved for " + people[j].getDayAvailable() + " " + people[j].getMonthAvailable() + " " + people[j].getYearAvailable() + " ");
                    i++;
                }
            }
        }
    }
}
