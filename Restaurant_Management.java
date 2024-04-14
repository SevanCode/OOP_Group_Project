/**
 * Dedicating a package
 */
package aua.am.restaurant_management.core;

/**
 * The class Restaurant_Management
 */
public class Restaurant_Management {
    /**
     * Declaring instance variables
     */
    private int whichEventIsSelected;
    private boolean quitPressed;
    private int numberOfGuests;
    /**
     * Two arrays: tables for storing tables available and people for storing chiefs and waiters available
     */
    private Table[] tables;
    private Person[] people; // waiter 6 chef 3

    /**
     * Constructor that includes a call to its own constructor with two String arguments
     */
    public Restaurant_Management(){
        this("1_12.4.2025,2_13.4.2025,3_1.1.2020,4_21.3.2024,5_1.5.2024,6_2.5.2024,7_10.6.2024,8_10.6.2024,9_11.8.2024,10_12.8.2024",
                "w_Alex_2.2.2025,w_Jake_2.2.2025,w_Bruce_8.7.2024,w_Clara_8.7.2020,w_Taylor_8.7.2024,w_Emilia_2.2.205,c_Conor_2.2.2025,c_Kevin_8.7.2024,c_Mike_8.7.2024");
    }

    /**
     * Constructor arranging tables and people arrays
     * @param tables_available of type String
     * @param people_available of type String
     */
    public Restaurant_Management(String tables_available, String people_available){
        setQuitPressed(false);
        String[] splitDate;
        String[] splitArrayForTables = tables_available.split(",");
        Table[] numberOfTablesAvailable = new Table[splitArrayForTables.length];
        for (int i = 0; i < splitArrayForTables.length; i++){
            String onlyDate = splitArrayForTables[i].substring(splitArrayForTables[i].indexOf("_") + 1);
            splitDate = onlyDate.split("\\.");
            numberOfTablesAvailable[i] = new Table(Integer.parseInt(splitArrayForTables[i].substring(0,1)),
                    Integer.parseInt(splitDate[0]),
                    Integer.parseInt(splitDate[1]),
                    Integer.parseInt(splitDate[2]),true);
        }
        setTables(numberOfTablesAvailable);
        String[] splitArrayForPeople = people_available.split(",");
        Person[] numberOfPeopleAvailable = new Person[splitArrayForPeople.length];
        for (int i = 0; i < numberOfPeopleAvailable.length; i++){
            String[] informationOfEachWorker = splitArrayForPeople[i].split("_");
            splitDate = informationOfEachWorker[2].split("\\.");
            if (informationOfEachWorker[0].equals("w")){
                numberOfPeopleAvailable[i] = new Waiter(informationOfEachWorker[1],
                        Integer.parseInt(splitDate[0]),
                        Integer.parseInt(splitDate[1]),
                        Integer.parseInt(splitDate[2]));
            } else if (informationOfEachWorker[0].equals("c")){
                numberOfPeopleAvailable[i] = new Chief(informationOfEachWorker[1],
                        Integer.parseInt(splitDate[0]),
                        Integer.parseInt(splitDate[1]),
                        Integer.parseInt(splitDate[2]));
            }
        }
        setPeople(numberOfPeopleAvailable);
    }

    /**
     * Accessor and mutator method for whichEventIsSelected
     * @param eventIsSelected of type int
     */
    public void setWhichEventIsSelected(int eventIsSelected){
        this.whichEventIsSelected = eventIsSelected;
    }
    public int getWhichEventIsSelected(){
        return this.whichEventIsSelected;
    }

    /**
     * Accessor and mutator method for quitPressed
     * @param value of type boolean
     */
    public void setQuitPressed(boolean value){
        this.quitPressed = value;
    }
    public boolean getQuitPressed(){
        return this.quitPressed;
    }

    /**
     * Accessor and mutator method for numberOfGuests
     * @param numberOfGuests of type int
     */
    public void setNumberOfGuests(int numberOfGuests){
        this.numberOfGuests = numberOfGuests;
    }
    public int getNumberOfGuests(){
        return this.numberOfGuests;
    }

    /**
     * Accessor and mutator method for tables
     * @param tables of type Table
     */
    public void setTables(Table[] tables) {
        this.tables = tables;
    }
    public Table[] getTables(){
        return this.tables;
    }

    /**
     * Accessor and mutator method for people
     * @param people of type Person
     */
    public void setPeople(Person[] people){
        this.people = people;
    }
    public Person[] getPeople(){
        return this.people;
    }

    /**
     * determineNumberOfBalloon method checks if it is a wedding ceremony returns 0 (does not need balloon)
     * Otherwise, it returns 1, it is neccessary
     * @return of type int
     */
    public int determineNumberOfBalloon(){
        if (getWhichEventIsSelected() == 1)
            return 0;
        else
            return 1;
    }


}
