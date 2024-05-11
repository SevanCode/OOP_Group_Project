/**
 * Dedicating a package
 */
package aua.am.restaurant_management.core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class Restaurant_Management
 */
public class RestaurantManagement {
    /**
     * Declaring instance variables
     */
    private boolean quitPressed;
    private boolean isDonePressedWhenRemoving;
    private int numberOfGuests;
    private int numberOfWaitersInProcess;
    private int numberOfWaitersNeeded;
    private int numberOfChiefsNeeded;
    private int numberOfChiefsInProcess;
    private int dayFormUser;
    private int monthFromUser;
    private int yearFromUser;
    private double totalCost;
    private ArrayList<Table> tables;
    private ArrayList<Table> reservedTables;
    private ArrayList<Person> people;
    private ArrayList<Person> reservedStaff;
    private Scanner inputFromUser;

    /**
     * Constructor that includes a call to its own constructor with two String arguments
     */
    public RestaurantManagement(){
        setQuitPressed(false);
        setDonePressedWhenRemoving(false);
        setNumberOfGuests(0);
        setNumberOfWaitersInProcess(0);
        setNumberOfChiefsInProcess(0);
        this.tables = new ArrayList<>();
        this.reservedTables = new ArrayList<>();
        this.people = new ArrayList<>();
        this.reservedStaff = new ArrayList<>();
        addTablesAndStaffAndEventsFromFile();
        this.inputFromUser = new Scanner(System.in);
    }

    /**
     * Accessor and mutator method for quitPressed
     * @param value of type boolean
     */
    public void setQuitPressed(boolean value){
        this.quitPressed = value;
    }

    /**
     * an accessor for isQuitPressed instance variable
     * @return boolean
     */
    public boolean getQuitPressed(){
        return this.quitPressed;
    }

    /**
     * a mutator for donePressedWhenRemoving
     * @param donePressedWhenRemoving of type boolean
     */
    public void setDonePressedWhenRemoving(boolean donePressedWhenRemoving) {
        isDonePressedWhenRemoving = donePressedWhenRemoving;
    }

    /**
     * an accessor for donePressedWhenRemoving
     * @return of type boolean
     */
    public boolean isDonePressedWhenRemoving() {
        return isDonePressedWhenRemoving;
    }

    /**
     * Accessor and mutator method for numberOfGuests
     * @param numberOfGuests of type int
     */
    public void setNumberOfGuests(int numberOfGuests){
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * an accessor for number of guests
     * @return of type int
     */
    public int getNumberOfGuests(){
        return this.numberOfGuests;
    }

    /**
     * a mutator for numberOfWaitersInProcess
     * @param numberOfWaitersInProcess of type int
     */
    public void setNumberOfWaitersInProcess(int numberOfWaitersInProcess){
        this.numberOfWaitersInProcess = numberOfWaitersInProcess;
    }

    /**
     * an accessor for number of waiters in process
     * @return of type int
     */
    public int getNumberOfWaitersInProcess(){
        return this.numberOfWaitersInProcess;
    }

    /**
     * a mutator for number of chefs
     * @param numberOfChiefsInProcess of type int
     */
    public void setNumberOfChiefsInProcess(int numberOfChiefsInProcess){
        this.numberOfChiefsInProcess = numberOfChiefsInProcess;
    }

    /**
     * an accessor for number of chefs in process
     * @return of type int
     */
    public int getNumberOfChiefsInProcess(){
        return this.numberOfChiefsInProcess;
    }

    /**
     * a mutator for number of waiters needed
     * @param numberOfWaitersNeeded of type int
     */
    public void setNumberOfWaitersNeeded(int numberOfWaitersNeeded){
        this.numberOfWaitersNeeded = numberOfWaitersNeeded;
    }

    /**
     * an accessor for number of waiters needed
     * @return of type int
     */
    public int getNumberOfWaitersNeeded(){
        return this.numberOfWaitersNeeded;
    }

    /**
     * a mutator for number of chefs needed
     * @param numberOfChiefsNeeded of type int
     */
    public void setNumberOfChiefsNeeded(int numberOfChiefsNeeded){
        this.numberOfChiefsNeeded = numberOfChiefsNeeded;
    }

    /**
     * an accessor for number of chefs
     * @return of type int
     */
    public int getNumberOfChiefsNeeded(){
        return this.numberOfChiefsNeeded;
    }

    /**
     * an accessor for day from user
     * @return of type int
     */
    public int getDayFormUser() {
        return dayFormUser;
    }

    /**
     * an accessor for day from user
     * @param dayFormUser of type int
     */
    public void setDayFormUser(int dayFormUser) {
        this.dayFormUser = dayFormUser;
    }

    /**
     * an accessor for month from user
     * @return of type int
     */
    public int getMonthFromUser() {
        return monthFromUser;
    }

    /**
     * a mutator for month from user
     * @param monthFromUser of type int
     */
    public void setMonthFromUser(int monthFromUser) {
        this.monthFromUser = monthFromUser;
    }

    /**
     * an accessor for year from user
     * @return of type int
     */
    public int getYearFromUser() {
        return yearFromUser;
    }

    /**
     * a mutator for number of year from user
     * @param yearFromUser of type int
     */
    public void setYearFromUser(int yearFromUser) {
        this.yearFromUser = yearFromUser;
    }

    /**
     * a mutator for total cost
     * @param cost of type double
     */
    public void setTotalCost(double cost){
        this.totalCost = this.totalCost + cost;
    }

    /**
     * an accessor for total cost
     * @return of type double
     */
    public double getTotalCost(){
        return this.totalCost;
    }

    /**
     * Accessor and mutator method for tables
     * @param tables of type Table
     */
    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    /**
     * an accessor for tables
     * @return of type ArrayList<Table>
     */
    public ArrayList<Table> getTables(){
        return this.tables;
    }

    /**
     * Accessor and mutator method for people
     * @param people of type Person
     */
    public void setPeople(ArrayList<Person> people){
        this.people = people;
    }

    /**
     * an accessor for people
     * @return of type ArrayList<Person>
     */
    public ArrayList<Person> getPeople(){
        return this.people;
    }

    /**
     * a mutator for reserved tables
     * @param tables of type ArrayList<Table>
     */
    public void setReservedTables(ArrayList<Table> tables){
        this.reservedTables = tables;
    }

    /**
     * an accessor for reserved tables
     * @return of type ArrayList<Table>
     */
    public ArrayList<Table> getReservedTables(){
        return this.reservedTables;
    }

    /**
     * a mutator for reserved staff
     * @param people of type ArrayList<Person>
     */
    public void setReservedStaff(ArrayList<Person> people){
        this.reservedStaff = people;
    }

    /**
     * an accessor for reserved staff
     * @return of type ArrayList<Person>
     */
    public ArrayList<Person> getReservedStaff(){
        return this.reservedStaff;
    }

    /**
     * setReversible is a method that takes anything that is a type Reservable and
     * if it is of type Table, it will add to tables list
     * if it is of type Person, it will add to people list
     * @param reservable of type Reservable
     */
    public void setReversible(Reservable reservable){
        if (reservable instanceof Table)
            this.tables.add((Table) reservable);
        else if (reservable instanceof  Person)
            this.people.add((Person) reservable);
    }

    /**
     * createNewTableOrStaffObject that creates a Reversabel type objects.
     * @param str of type String
     * @return Reversable
     */
    public static Reservable createNewTableOrStaffObject(String str){
        char firstCharacter = str.charAt(0);
        str = str.substring(str.indexOf("_") + 1);
        String[] split = str.split("_");
        String[] onlyDate = split[1].split("\\.");
        String costInString = split[split.length - 1].substring(split[split.length - 1].indexOf("$") + 1);
        switch (firstCharacter){
            case 'T':
                return new Table(Integer.parseInt(split[0]),
                        Integer.parseInt(onlyDate[0]),
                        Integer.parseInt(onlyDate[1]),
                        Integer.parseInt(onlyDate[2]),
                        true,Integer.parseInt(costInString));
            case 'W':
                return new Waiter(split[0],
                        Integer.parseInt(onlyDate[0]),
                        Integer.parseInt(onlyDate[1]),
                        Integer.parseInt(onlyDate[2]),
                        Integer.parseInt(costInString));
            default:
                return new Chef(split[0],
                        Integer.parseInt(onlyDate[0]),
                        Integer.parseInt(onlyDate[1]),
                        Integer.parseInt(onlyDate[2]),
                        Integer.parseInt(costInString));
        }
    }

    /**
     * ifUserWantsToReserveTables is a method that invoked when user wants to reserve tables
     */
    public void ifUserWantsToReserveTables(){
        int line = 0;
        Table tableThatUserWants = new Table();
        load("QuestionsForTableReservation.txt", 0);
        tableThatUserWants.setDayAvailable(inputFromUser.nextInt());
        tableThatUserWants.setMonthAvailable(inputFromUser.nextInt());
        tableThatUserWants.setYearAvailable(inputFromUser.nextInt());
        line++;
        load("QuestionsForTableReservation.txt", line);
        tableThatUserWants.setNumberOfTables(inputFromUser.nextInt());
        line++;
        boolean checkIfTableIsAvailable = false;
        try {
            for (int i = 0; i < getTables().size(); i++) {
                if (getTables().get(i).equals(tableThatUserWants)){
                    if (getTables().get(i).isReservable(tableThatUserWants.getDayAvailable(),
                            tableThatUserWants.getMonthAvailable(),
                            tableThatUserWants.getYearAvailable())){
                        load("QuestionsForTableReservation.txt", line);
                        tableThatUserWants.setCost(getTables().get(i).getCost());
                        setTotalCost(getTables().get(i).getCost());
                        reservedTables = appendingToTableList(reservedTables, tableThatUserWants);
                        checkIfTableIsAvailable = true;
                        break;
                    }
                }
            }
            if (!checkIfTableIsAvailable) {
                throw new OutOfTablesException();
            }
        } catch (OutOfTablesException e) {
            System.out.println(e.getMessage());
            load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt",0);
        }
    }

    /**
     * ifUserWantToReserveStaff is a method that invoked when user wants to reserve for staff
     * @throws NotPersonAvailableException of type Exception
     */
    public void ifUserWantToReserveStaff() throws NotPersonAvailableException {
        int line = 0;
        boolean check = true;
        Waiter waiter = new Waiter();
        Chef chef = new Chef();
        load("QuestionsForStaffReservation.txt", line);
        setDayFormUser(inputFromUser.nextInt());
        setMonthFromUser(inputFromUser.nextInt());
        setYearFromUser(inputFromUser.nextInt());
        line++;
        load("QuestionsForStaffReservation.txt", line);
        setNumberOfWaitersNeeded(inputFromUser.nextInt());
        line++;
        load("QuestionsForStaffReservation.txt", line);
        setNumberOfChiefsNeeded(inputFromUser.nextInt());
        line++;
        load("QuestionsForStaffReservation.txt", line);
        setNumberOfGuests(inputFromUser.nextInt());
        line++;
        Person[] peopleModificationArray = new Person[getNumberOfChiefsNeeded() + getNumberOfWaitersNeeded()];
        int index = 0;
        if (waiter.isEnough(this) && chef.isEnough(this)) {
            ArrayList<Person> people = getPeople();
            for (int i = 0; i < people.size(); i++) {
                if (people.get(i).isReservable(getDayFormUser(),
                        getMonthFromUser(),
                        getYearFromUser())) {
                    if (people.get(i) instanceof Waiter) {
                        if (getNumberOfWaitersInProcess() < getNumberOfWaitersNeeded()) {
                            peopleModificationArray[index] = people.get(i);
                            index++;
                        }
                        setNumberOfWaitersInProcess(getNumberOfWaitersInProcess() + 1);
                    } else if (people.get(i) instanceof Chef) {
                        if (getNumberOfChiefsInProcess() < getNumberOfChiefsNeeded()) {
                            peopleModificationArray[index] = people.get(i);
                            index++;
                        }
                        setNumberOfChiefsInProcess(getNumberOfChiefsInProcess() + 1);
                    }
                }
            }
            try {
                if (getNumberOfWaitersNeeded() > getNumberOfWaitersInProcess() &&
                        getNumberOfChiefsNeeded() > getNumberOfChiefsInProcess()) {
                    throw new NotPersonAvailableException("Number of both waiters and chiefs that are available is less than the number that you want.");
                } else if (getNumberOfWaitersNeeded() > getNumberOfWaitersInProcess())
                    throw new NotPersonAvailableException("Number of waiters that are available is less than the number that you want");
                else if (getNumberOfChiefsNeeded() > getNumberOfChiefsInProcess())
                    throw new NotPersonAvailableException("Number of chiefs that are available is less than the number that you want");
            } catch (NotPersonAvailableException e) {
                int numberOfWaiters = 0;
                int numberOfChiefs = 0;
                for (int i = 0; i < reservedStaff.size(); i++) {
                    if (reservedStaff.get(i) instanceof Waiter) {
                        numberOfWaiters++;
                    } else
                        numberOfChiefs++;
                }
                setNumberOfWaitersNeeded(numberOfWaiters);
                setNumberOfChiefsNeeded(numberOfChiefs);
                setNumberOfWaitersInProcess(0);
                setNumberOfChiefsInProcess(0);
                System.out.println(e.getMessage());
                load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt",0);
                check = false;
            }
            if (check) {
                reservedStaff = appendingToPersonArray(reservedStaff, peopleModificationArray);
                for (Person person :
                        reservedStaff) {
                    setTotalCost(person.getCost());
                }
                load("QuestionsForStaffReservation.txt", line);
            }
        } else {
            load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt",0);
        }
    }

    /**
     * ifUserWantToSeeReport is a method that invokes when the user wants to see the report.
     */
    public void ifUserWantToSeeReport(){
        printTables(reservedTables);
        printStaff(reservedStaff);
        System.out.println();
        load("TotalCost.txt",0);
        System.out.print(" ");
        System.out.printf("%6.2f" ,getTotalCost());
        System.out.println("$");
    }

    /**
     * ifUserWantToRemoveSomething is a method that invokes when user wants to remove something
     * @param optionSelectedByUser of type int
     */
    public void ifUserWantToRemoveSomething(int optionSelectedByUser){
        int line = 0;
        while (!isDonePressedWhenRemoving()){
            load("QuestionsForRemoval.txt", line);
            optionSelectedByUser = inputFromUser.nextInt();
            switch (optionSelectedByUser) {
                case 1:
                    if (reservedTables.isEmpty()){
                        load("StatementIfNoTablesAreSelected.txt",line);
                        continue;
                    }
                    load("QuestionsForTableAndStaffAndEventRemoval.txt", line);
                    printTables(reservedTables);
                    if(removingTables(inputFromUser.nextInt()))
                        load("IfRemovalWasSuccessful.txt",0);
                    else
                        load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt",0);
                    break;
                case 2:
                    if (reservedStaff.isEmpty()){
                        load("StatementIfNoStaffIsSelected.txt",line);
                        continue;
                    }
                    load("QuestionsForTableAndStaffAndEventRemoval.txt", line);
                    printStaff(reservedStaff);
                    if(removingStaff(inputFromUser.nextInt()))
                        load("IfRemovalWasSuccessful.txt",0);
                    else
                        load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt",0);
                    break;
                case 3:
                    setDonePressedWhenRemoving(true);
                    break;
            }
        }
        setDonePressedWhenRemoving(false);
    }

    /**
     * ifUserWantToCompleteReservation is a method that invokes when user wants to complete the reservation
     */
    public void ifUserWantToCompleteReservation(){
        printTables(reservedTables);
        printStaff(reservedStaff);
        System.out.println();
        load("TotalCost.txt",0);
        System.out.println(" " + getTotalCost());
        System.out.println();
        load("CompleteReservation.txt",0);
        int fromUser = inputFromUser.nextInt();
        if (fromUser == 1){
            load("SubmitReservation.txt",0);
            double change = inputFromUser.nextDouble();
            if (change < getTotalCost()) {
                load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt", 0);
            } else {
                change = change - getTotalCost();
                load("YourChangeIs.txt",0);
                System.out.print(" ");
                System.out.printf("%6.2f", change);
                setQuitPressed(true);
                System.out.println();
            }
        }
    }

    /**
     * ifUserInputsInvalidNumber is a method that is invoked if user inputs invalid number
     */
    public void ifUserInputsInvalidNumber(){
        load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt",0);
    }

    /**
     * ifUserReservedOrDoesNotReserveForSomethingWhileQuitIsPressed is a method that is invoked when user pressed quit
     * and ordered or has not ordered yet for something
     */
    public void ifUserReservedOrDoesNotReserveForSomethingWhileQuitIsPressed(){
        if (!reservedTables.isEmpty() || !reservedStaff.isEmpty()){
            load("QuitIsPressedButUserHasOrderedSomething.txt",0);
        } else {
            setQuitPressed(true);
        }
    }

    /**
     * afterQuitIsPressed_DoesUserReservedForSomethingOrNot is a method that checks whether user reserves for something
     * or not while quiting
     */
    public void afterQuitIsPressed_DoesUserReservedForSomethingOrNot(){
        if (reservedTables.isEmpty() && reservedStaff.isEmpty())
            load("IfUserDoesNotReserveAnything.txt",0);
        else
            load("IfUserReservedForSomething.txt",0);
    }

    /**
     * addTablesAndStaffFromFile is a method that reads the tableAndStaffAndEventInformation.txt file and
     * creates objects of Tables and Waiter and Chief by and Events invoking createNewTableOrStaffObject method from
     * RestaurantManagement class and setReversable to append tables and Waiters and Chiefs and Events.
     */
    public void addTablesAndStaffAndEventsFromFile(){
        Scanner readingFromFile = null;
        try {
            readingFromFile = new Scanner(new FileInputStream("tableAndStaffAndEventInformation.txt"));
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        while(readingFromFile.hasNextLine()){
            Reservable reservable = RestaurantManagement.createNewTableOrStaffObject(readingFromFile.nextLine());
            setReversible(reservable);
        }
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
            switch (file) {
                case "Restaurant.txt" :
                    save(file, 7, line);
                    break;
                case "QuestionsForRemoval.txt" :
                    save(file, 4, line);
                    break;
                case "StatementIfNoTablesAreSelected.txt",
                        "StatementIfNoStaffIsSelected.txt",
                        "IfUserDoesNotReserveAnything.txt",
                        "IfUserReservedForSomething.txt",
                        "IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt",
                        "AskingTheDate.txt",
                        "IfRemovalWasSuccessful.txt",
                        "SayingWelcome.txt",
                        "TotalCost.txt",
                        "QuitIsPressedButUserHasOrderedSomething.txt",
                        "SubmitReservation.txt",
                        "TablesNone.txt",
                        "StaffNone.txt",
                        "EventsNone.txt",
                        "YourChangeIs.txt":
                    save(file, 1, line);
                    break;
                case "QuestionsForTableReservation.txt",
                        "EventQuestions.txt",
                        "IfUserWantToBringCake.txt",
                        "IfUserWantsFlowers.txt",
                        "IfUserWantBalloons.txt":
                    save(file,2,line);
                    break;
                case "QuestionsForStaffReservation.txt":
                    save(file,3,line);
                    break;
                case "QuestionsForTableAndStaffAndEventRemoval.txt",
                        "NumberOfFlowersUserWants.txt",
                        "NumberOfBalloonsUserWants.txt":
                    save(file, line + 1, line);
                    break;
                case "CompleteReservation.txt":
                    save(file,1,line);
                    break;
                case "TypesOfFlowers.txt",
                        "TypesOfBalloons.txt":
                    save(file,5,line);
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
        String contents = "";
        try {
            readingFile = new Scanner(new FileInputStream(file));
            outputStream = new PrintWriter(new FileOutputStream("SavedProcess.txt", true));
            if (file.equals("Restaurant.txt") ||
                    file.equals("EventQuestions.txt") ||
                    file.equals("QuestionsForRemoval.txt") ||
                    file.equals("CompleteReservation.txt") ||
                    file.equals("IfUserWantToBringCake.txt")||
                    file.equals("IfUserWantBalloons.txt") ||
                    file.equals("IfUserWantsFlowers.txt") ||
                    file.equals("TypesOfFlowers.txt") ||
                    file.equals("TypesOfBalloons.txt")) {
                for (int i = line; i <= count; i++) {
                    if (readingFile.hasNextLine()) {
                        contents = readingFile.nextLine();
                        System.out.println(contents);
                        outputStream.println(contents);
                    }
                }
            } else {
                if (line == 0){
                    if (file.equals("TotalCost.txt") || file.equals("YourChangeIs.txt")){
                        contents = readingFile.nextLine();
                        System.out.print(contents);
                    } else {
                        contents = readingFile.nextLine();
                        System.out.println(contents);
                    }
                } else {
                    for (int i = 0; i < line; i++) {
                        readingFile.nextLine();
                    }
                    contents = readingFile.nextLine();
                    System.out.println(contents);
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
    public ArrayList<Table> appendingToTableList(ArrayList<Table> reservedTables, Table...wantedTable){
        ArrayList<Table> result = new ArrayList<>(reservedTables.size() + wantedTable.length);
        int i = 0;
        for (; i < reservedTables.size(); i++) {
            result.add(reservedTables.get(i));
        }
        for (int j = 0; j < wantedTable.length; i++, j++){
            result.add(wantedTable[j]);
        }
        result.get(0).setIsNotReserved(false);
        return result;
    }



    /**
     * appendingToPersonArray is a method to append new Person selected by user to reservedStaff
     * @param people of type Person
     * @param wantedStaff of type Person
     * @return Person[]
     */
    public ArrayList<Person> appendingToPersonArray(ArrayList<Person> people, Person[] wantedStaff){
        ArrayList<Person> result = new ArrayList<>(people.size() + wantedStaff.length);
        int i = 0;
        for (; i < people.size(); i++) {
            result.add(people.get(i));
        }
        for (int j = 0; j < wantedStaff.length; i++, j++){
            result.add(wantedStaff[j]);
        }
        ArrayList<Person> managementPeopleList = getPeople();
        for (int r = 0 ; r < wantedStaff.length; r++){
            for (int p = 0; p < managementPeopleList.size(); p++){
                if (managementPeopleList.get(p).equals(result.get(r))){
                    managementPeopleList.remove(p);
                }
            }
        }
        setPeople(managementPeopleList);
        int numberOfWaiters = 0;
        int numberOfChiefs = 0;
        for (int w = 0 ; w < result.size(); w++){
            if (result.get(w) instanceof Waiter){
                numberOfWaiters++;
            } else
                numberOfChiefs++;
        }
        setNumberOfWaitersNeeded(numberOfWaiters);
        setNumberOfChiefsNeeded(numberOfChiefs);
        setNumberOfWaitersInProcess(0);
        setNumberOfChiefsInProcess(0);
        return result;
    }



    /**
     * printTables that prints reservedTables
     * @param tables of type Table
     */
    public void printTables(ArrayList<Table> tables){
        if (tables.isEmpty()){
            load("TablesNone.txt",0);
        }else {
            for (int i = 0; i < tables.size(); i++){
                System.out.print(i + 1 + ": " + "Table number " + tables.get(i).getNumberOfTable() + " reserved for " + tables.get(i).getDayAvailable() + "." + tables.get(i).getMonthAvailable() + "." + tables.get(i).getYearAvailable() + ": ");
                System.out.println(tables.get(i).getCost() + "$");

            }
        }
    }

    /**
     * printTables that prints reservedStaff
     * @param people of type Person
     */
    public void printStaff(ArrayList<Person> people){
        if (people.isEmpty()){
            load("StaffNone.txt",0);
        }else {
            int index = 0;
            for (int i = 0 , j = 0; i < getNumberOfWaitersNeeded(); j++) {
                if (people.get(j) instanceof Waiter) {
                    System.out.print(index + 1 + ": " +"Waiter :  " + people.get(j).getNameOfTheWorker() + " reserved for " +  people.get(j).getDayAvailable() + "." +  people.get(j).getMonthAvailable() + "." +  people.get(j).getYearAvailable() + ": ");
                    System.out.println(( people.get(j)).getCost() + "$");
                    i++;
                    index++;
                }
            }
            for (int i = 0 , j = 0; i < getNumberOfChiefsNeeded(); j++){
                if ( people.get(j) instanceof Chef) {
                    System.out.print(index + 1 + ": " + "Chief :  " + people.get(j).getNameOfTheWorker() + " reserved for " +  people.get(j).getDayAvailable() + "." +  people.get(j).getMonthAvailable() + "." +  people.get(j).getYearAvailable() + ": ");
                    System.out.println(( people.get(j)).getCost() + "$");
                    i++;
                    index++;
                }
            }
        }
    }

    /**
     * removingTables is a method for removing tables from reserved list
     * @param optionFromUser of type int
     * @return boolean
     */
    public boolean removingTables(int optionFromUser){
        ArrayList<Table> tablesThatAreAvailable = getTables();
        for (int i = 0; i < reservedTables.size(); i++){
            if (optionFromUser == i + 1){
                reservedTables.get(i).setIsNotReserved(true);
                tablesThatAreAvailable.remove(reservedTables.get(i));
                tablesThatAreAvailable.add(reservedTables.get(i));
                setTables(tablesThatAreAvailable);
                setTotalCost(-(reservedTables.get(i).getCost()));
                reservedTables.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * removingStaff is a method for removing reserved staff from reserved staff list
     * @param optionFromUser of type int
     *
     */
    public boolean removingStaff(int optionFromUser){
        boolean check = false;
        ArrayList<Person> peopleThatAreAvailable = getPeople();
        for (int i = 0; i < reservedStaff.size(); i++){
            if (optionFromUser == i + 1){
                peopleThatAreAvailable.add(reservedStaff.get(i));
                setPeople(peopleThatAreAvailable);
                setTotalCost(-(reservedStaff.get(i).getCost()));
                reservedStaff.remove(i);
                check = true;
                break;
            }
        }
        int numberOfWaiters = 0;
        int numberOfChiefs = 0;
        for (int i = 0; i < reservedStaff.size(); i++){
            if (reservedStaff.get(i) instanceof Waiter)
                numberOfWaiters++;
            else
                numberOfChiefs++;
        }
        setNumberOfWaitersNeeded(numberOfWaiters);
        setNumberOfChiefsNeeded(numberOfChiefs);
        return check;
    }
}
