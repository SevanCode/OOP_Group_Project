package aua.am.restaurant_management.ui;

import aua.am.restaurant_management.core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * RestaurantUI calss that extends JFrame
 */
public class RestaurantUI extends JFrame {
    /**
     * instance variables
     */
    private RestaurantManagement management;
    private JPanel[] panels;
    private RestaurantButton[] restaurantButtons;
    private JLabel[] labels;
    private JTextField[] textField;
    private ArrayList<String> contentsFromFiles = new ArrayList<>();

    /**
     * a constructor that shows the main menu of the program by invoking additional method
     * which its name is initialBoard()
     */
    public RestaurantUI() {
        load("SayingWelcome.txt",0);
        JLabel sayingWelcome = new JLabel(contentsFromFiles.get(0));
        sayingWelcome.setLayout(new BorderLayout());
        JPanel panelForWelcome = new JPanel();
        panelForWelcome.setPreferredSize(new Dimension(this.getWidth(),50));
        panelForWelcome.add(sayingWelcome,BorderLayout.CENTER);
        sayingWelcome.setSize(200,200);
        this.add(panelForWelcome);
        management = new RestaurantManagement();
        initialBoard();
        contentsFromFiles.clear();
        load("tableAndStaffAndEventInformation.txt",0);
        listsOfTablesAndStaffAvailableWindow();
    }

    /**
     * theActionOfClickedButton is a method that invoked when a button is clicked
     * it handles all the possibilities if the user wants to reserve tables,
     * staff, see report, from the reservation list, completing reservation and quiting
     * @param numberOfButton of type int
     * @throws NotPersonAvailableException of type Exception
     */
    public void theActionOfClickedButton(int numberOfButton) throws NotPersonAvailableException {
        switch (numberOfButton) {
            case 1:
                Table tableThatUserWants = new Table();
                load("QuestionsForTableReservation.txt", 0);
                labels = new JLabel[contentsFromFiles.size()];
                textField = new JTextField[contentsFromFiles.size() - 1];
                panels = new JPanel[contentsFromFiles.size()];
                for (int i = 0; i < labels.length - 1; i++) {
                    labels[i] = new JLabel(contentsFromFiles.get(i));
                    textField[i] = new JTextField();
                    panels[i] = new JPanel();
                    panels[i].setPreferredSize(new Dimension(500, 100));
                    textField[i].setPreferredSize(new Dimension(300, 30));
                    panels[i].add(labels[i], BorderLayout.CENTER);
                    panels[i].add(textField[i], BorderLayout.CENTER);
                    this.add(panels[i]);
                    if (i == labels.length - 2) {
                        JButton continueButton = new RestaurantButton(restaurantButtons.length);
                        JButton goBackButton = new JButton();
                        continueButton.setText("Continue");
                        continueButton.setPreferredSize(new Dimension(80,50));
                        goBackButton.setText("Go Back To Main Menu");
                        goBackButton.setPreferredSize(new Dimension(200,50));
                        JPanel panelForButtons = new JPanel();
                        panelForButtons.setLayout(new FlowLayout());
                        panelForButtons.add(goBackButton,BorderLayout.WEST);
                        panelForButtons.add(continueButton,BorderLayout.EAST);
                        this.add(panelForButtons, BorderLayout.CENTER);
                        continueButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                boolean pass = true;
                                for (int i = 0; i < textField.length; i++) {
                                    if (ifTextFiledIsValidForDate(textField[i].getText().trim(),i)) {
                                        String[] split = textField[i].getText().trim().split("\\.");
                                        if (split.length == 3) {
                                            tableThatUserWants.setDayAvailable(Integer.parseInt(split[0]));
                                            tableThatUserWants.setMonthAvailable(Integer.parseInt(split[1]));
                                            tableThatUserWants.setYearAvailable(Integer.parseInt(split[2]));
                                        } else if (split.length == 1) {
                                            tableThatUserWants.setNumberOfTables(Integer.parseInt(split[0]));
                                        }
                                    } else {
                                        pass = false;
                                        break;
                                    }
                                }
                                if (pass) {
                                    removingTheComponents();
                                    ArrayList<Table> tablesThatAreAvailable = management.getTables();
                                    JLabel congratulationText = new JLabel();
                                    JPanel congratulationPanel = new JPanel();
                                    JPanel exceptionPanel = new JPanel();
                                    boolean checkIfTableIsAvailable = false;
                                    for (int i = 0; i < tablesThatAreAvailable.size(); i++) {
                                        if (tablesThatAreAvailable.get(i).equals(tableThatUserWants)) {
                                            if (tablesThatAreAvailable.get(i).isReservable(tableThatUserWants.getDayAvailable(),
                                                    tableThatUserWants.getMonthAvailable(),
                                                    tableThatUserWants.getYearAvailable())) {
                                                load("Congratulation.txt", 0);
                                                congratulationText.setText(contentsFromFiles.get(0));
                                                congratulationText.setPreferredSize(new Dimension(200, 200));
                                                congratulationPanel.setLayout(new BorderLayout());
                                                congratulationPanel.add(congratulationText, BorderLayout.CENTER);
                                                congratulationPanel.setPreferredSize(new Dimension(getWidth(), 50));
                                                tableThatUserWants.setCost(tablesThatAreAvailable.get(i).getCost());
                                                management.setTotalCost(tablesThatAreAvailable.get(i).getCost());
                                                management.setReservedTables(management.appendingToTableList(management.getReservedTables(), tableThatUserWants));
                                                checkIfTableIsAvailable = true;
                                                addingSomethingWhenEventIsInvoked(congratulationPanel);
                                                break;
                                            }
                                        }
                                    }
                                    JLabel exceptionText = new JLabel();
                                    exceptionPanel.setLayout(new BorderLayout());
                                    exceptionPanel.add(exceptionText, BorderLayout.CENTER);
                                    exceptionPanel.setPreferredSize(new Dimension(getWidth(), 50));
                                    try {
                                        if (!checkIfTableIsAvailable) {
                                            throw new OutOfTablesException();
                                        }
                                    } catch (OutOfTablesException exception) {
                                        exceptionText.setText(exception.getMessage());
                                        contentsFromFiles.clear();
                                        load(exceptionText.getText());
                                        openingNewWindow();
                                    }
                                    initialBoard();
                                }
                            }
                        });
                        goBackButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                removingTheComponents();
                                initialBoard();
                            }
                        });
                    }
                }
                this.setVisible(true);
                break;
            case 2:
                load("QuestionsForStaffReservation.txt", 0);
                labels = new JLabel[contentsFromFiles.size()];
                textField = new JTextField[contentsFromFiles.size()];
                panels = new JPanel[contentsFromFiles.size() + 2];
                int line = 0;
                boolean check = true;
                Waiter waiter = new Waiter();
                Chef chef = new Chef();
                int i = 0;
                for (; i < labels.length; i++) {
                    labels[i] = new JLabel(contentsFromFiles.get(i));
                    textField[i] = new JTextField();
                    panels[i] = new JPanel(new GridLayout(2, 1));
                    panels[i].setPreferredSize(new Dimension(500, getHeight()/5));
                    textField[i].setPreferredSize(new Dimension(200, 30));
                    panels[i].add(labels[i], BorderLayout.CENTER);
                    panels[i].add(textField[i], BorderLayout.CENTER);
                    this.add(panels[i], BorderLayout.CENTER);
                }
                JButton continueButton = new JButton();
                JButton goBackButton = new JButton();
                continueButton.setText("Continue");
                continueButton.setPreferredSize(new Dimension(120,30));
                goBackButton.setText("Go Back To Main Menu");
                goBackButton.setPreferredSize(new Dimension(200,30));
                panels[i] = new JPanel();
                panels[i].setPreferredSize(new Dimension(getWidth(),200));
                JPanel panelForButtons = new JPanel();
                panelForButtons.add(continueButton);
                panelForButtons.add(goBackButton);
                panelForButtons.setPreferredSize(new Dimension(getWidth(),100));
                panels[i + 1] = new JPanel(new GridLayout(2, 1));
                panels[i + 1].setPreferredSize(new Dimension(500, 200));
                panels[i + 1].add(panelForButtons);
                this.add(panels[i],BorderLayout.CENTER);
                this.add(panels[i + 1],BorderLayout.CENTER);
                continueButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean check = true;
                        boolean isAllTextFieldsFull = true;
                        for (int j = 0; j < textField.length; j++) {
                            if (ifTextFiledIsValidForDate(textField[j].getText().trim(),j)) {
                                String[] split = textField[j].getText().trim().split("\\.");
                                if (split[0] == null || split[0].isEmpty()) {
                                    isAllTextFieldsFull = false;
                                    break;
                                }
                                if (split.length == 3) {
                                    management.setDayFormUser(Integer.parseInt(split[0]));
                                    management.setMonthFromUser(Integer.parseInt(split[1]));
                                    management.setYearFromUser(Integer.parseInt(split[2]));
                                } else if (j == 1 && split.length == 1) {
                                    management.setNumberOfWaitersNeeded(Integer.parseInt(split[0]));
                                } else if (j == 2 && split.length == 1) {
                                    management.setNumberOfChiefsNeeded(Integer.parseInt(split[0]));
                                } else if (j == 3 && split.length == 1) {
                                    management.setNumberOfGuests(Integer.parseInt(split[0]));
                                }
                            }
                            else {
                                isAllTextFieldsFull = false;
                                break;
                            }
                        }
                        if (isAllTextFieldsFull) {
                            Person[] peopleModificationArray = new Person[management.getNumberOfChiefsNeeded() + management.getNumberOfWaitersNeeded()];
                            int index = 0;
                            JPanel exceptionPanel = new JPanel();
                            JLabel exceptionText = new JLabel();
                            exceptionPanel.setLayout(new BorderLayout());
                            exceptionPanel.add(exceptionText, BorderLayout.CENTER);
                            exceptionPanel.setPreferredSize(new Dimension(getWidth(), 50));
                            try {
                                if (waiter.isEnough(management) && chef.isEnough(management)) {
                                    ArrayList<Person> people = management.getPeople();
                                    for (int l = 0; l < people.size(); l++) {
                                        if (people.get(l).isReservable(management.getDayFormUser(),
                                                management.getMonthFromUser(),
                                                management.getYearFromUser())) {
                                            if (people.get(l) instanceof Waiter) {
                                                if (management.getNumberOfWaitersInProcess() < management.getNumberOfWaitersNeeded()) {
                                                    peopleModificationArray[index] = people.get(l);
                                                    index++;
                                                }
                                                management.setNumberOfWaitersInProcess(management.getNumberOfWaitersInProcess() + 1);
                                            } else if (people.get(l) instanceof Chef) {
                                                if (management.getNumberOfChiefsInProcess() < management.getNumberOfChiefsNeeded()) {
                                                    peopleModificationArray[index] = people.get(l);
                                                    index++;
                                                }
                                                management.setNumberOfChiefsInProcess(management.getNumberOfChiefsInProcess() + 1);
                                            }
                                        }
                                    }
                                    try {
                                        if (management.getNumberOfWaitersNeeded() > management.getNumberOfWaitersInProcess() &&
                                                management.getNumberOfChiefsNeeded() > management.getNumberOfChiefsInProcess()) {
                                            throw new NotPersonAvailableException("Number of both waiters and chiefs that are available is less than the number that you want.");
                                        } else if (management.getNumberOfWaitersNeeded() > management.getNumberOfWaitersInProcess())
                                            throw new NotPersonAvailableException("Number of waiters that are available is less than the number that you want");
                                        else if (management.getNumberOfChiefsNeeded() > management.getNumberOfChiefsInProcess())
                                            throw new NotPersonAvailableException("Number of chiefs that are available is less than the number that you want");
                                        else if (management.getNumberOfGuests() > (Waiter.getNumberOfGuestsHandle() * management.getNumberOfWaitersNeeded() +
                                                Chef.getNumberOfGuestsHandle() * management.getNumberOfChiefsNeeded()))
                                            throw new NotPersonAvailableException("The number of guests is to many required number of staff.");
                                    } catch (NotPersonAvailableException exception) {
                                        int numberOfWaiters = 0;
                                        int numberOfChiefs = 0;
                                        for (int m = 0; m < management.getReservedStaff().size(); m++) {
                                            if (management.getReservedStaff().get(m) instanceof Waiter) {
                                                numberOfWaiters++;
                                            } else
                                                numberOfChiefs++;
                                        }
                                        management.setNumberOfWaitersNeeded(numberOfWaiters);
                                        management.setNumberOfChiefsNeeded(numberOfChiefs);
                                        management.setNumberOfWaitersInProcess(0);
                                        management.setNumberOfChiefsInProcess(0);
                                        exceptionText.setText(exception.getMessage());
                                        contentsFromFiles.clear();
                                        load(exceptionText.getText());
                                        openingNewWindow();
                                        removingTheComponents();
                                        check = false;
                                    }
                                    if (check) {
                                        management.setReservedStaff(management.appendingToPersonArray(management.getReservedStaff(), peopleModificationArray));
                                        for (Person person :
                                                management.getReservedStaff()) {
                                            management.setTotalCost(person.getCost());
                                        }
                                        load("QuestionsForStaffReservation.txt", line);
                                        load("Congratulation.txt", 0);
                                        JLabel congratulationText = new JLabel();
                                        JPanel congratulationPanel = new JPanel();
                                        congratulationText.setText(contentsFromFiles.get(0));
                                        congratulationText.setPreferredSize(new Dimension(200, 200));
                                        congratulationPanel.setLayout(new BorderLayout());
                                        congratulationPanel.add(congratulationText, BorderLayout.CENTER);
                                        congratulationPanel.setPreferredSize(new Dimension(getWidth(), 50));
                                        removingTheComponents();
                                        addingSomethingWhenEventIsInvoked(congratulationPanel);
                                    }
                                } else {
                                    load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt", 0);
                                    exceptionText.setText(contentsFromFiles.get(0));
                                    openingNewWindow();
                                    removingTheComponents();
                                }
                            } catch (NotPersonAvailableException ex) {
                                throw new RuntimeException(ex);
                            }
                            initialBoard();
                        }
                    }
                });
                goBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removingTheComponents();
                        initialBoard();
                    }
                });
                    break;
            case 3:
                panels = new JPanel[5];

                labels = new JLabel[management.getReservedTables().size() + management.getReservedStaff().size()];
                for (int i1 = 0; i1 < panels.length; i1++){
                    panels[i1] = new JPanel();
                    this.add(panels[i1],BorderLayout.CENTER);
                    panels[i1].setPreferredSize(new Dimension(getWidth(),200));
                }
                printTables(management.getReservedTables(),panels[0]);
                printStaff(management.getReservedStaff(), panels[1]);
                load("TotalCost.txt",0);
                load(management.getTotalCost() + "$");
                JLabel totalCostLabel = new JLabel(contentsFromFiles.get(0) + contentsFromFiles.get(1));
                panels[3].add(totalCostLabel);
                this.add(panels[3],BorderLayout.CENTER);
                contentsFromFiles.clear();

                goBackButton = new JButton("Go Back To Main Menu");
                goBackButton.setPreferredSize(new Dimension(200,50));
                goBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removingTheComponents();
                        initialBoard();
                    }
                });
                panelForButtons = new JPanel();
                panelForButtons.add(goBackButton);
                panels[4].add(panelForButtons);
                this.add(panels[4],BorderLayout.CENTER);
                break;
            case 4:
                removingWindow();
                break;
            case 5:
                panels = new JPanel[4];
                labels = new JLabel[management.getReservedTables().size() + management.getReservedStaff().size()];
                for (int i1 = 0; i1 < panels.length; i1++){
                    panels[i1] = new JPanel();
                    this.add(panels[i1],BorderLayout.CENTER);
                }
                printTables(management.getReservedTables(),panels[0]);
                printStaff(management.getReservedStaff(), panels[1]);
                load("TotalCost.txt",0);
                load(management.getTotalCost() + "$");
                totalCostLabel = new JLabel(contentsFromFiles.get(0) + contentsFromFiles.get(1));
                panels[3].add(totalCostLabel);
                this.add(panels[3],BorderLayout.CENTER);
                contentsFromFiles.clear();
                goBackButton = new JButton("Go Back To Main Menu");
                goBackButton.setPreferredSize(new Dimension(200,50));
                goBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removingTheComponents();
                        initialBoard();
                    }
                });
                load("CompleteReservation.txt",0);
                panels = new JPanel[2];
                labels = new JLabel[2];
                restaurantButtons = new RestaurantButton[2];
                for (int j = 0; j < panels.length; j++){
                    panels[j] = new JPanel();
                    labels[j] = new JLabel(contentsFromFiles.get(j));
                    restaurantButtons[j] = new RestaurantButton(j + 1);
                    panels[j].add(labels[j]);
                    panels[j].add(restaurantButtons[j]);
                    addingSomethingWhenEventIsInvoked(panels[j]);
                    int finalJ = j;
                    restaurantButtons[j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (restaurantButtons[finalJ].getNumberAssociatedToEachButton() == 1){
                                if (management.getTotalCost() != 0) {
                                    removingTheComponents();
                                    load("TotalCost.txt", 0);
                                    load(contentsFromFiles.get(0) + management.getTotalCost());
                                    contentsFromFiles.remove(0);
                                    JLabel labelForTotalCost = new JLabel(contentsFromFiles.get(0));
                                    addingSomethingWhenEventIsInvoked(labelForTotalCost);
                                    contentsFromFiles.remove(0);
                                    load("SubmitReservation.txt", 0);
                                    JLabel submittingReservation = new JLabel(contentsFromFiles.get(0));
                                    addingSomethingWhenEventIsInvoked(submittingReservation);
                                    JTextField writingPrice = new JTextField();
                                    JPanel panelForSubmissionButton = new JPanel();
                                    JButton submissionButton = new JButton("Submit");
                                    panelForSubmissionButton.setPreferredSize(new Dimension(90,150));
                                    panelForSubmissionButton.add(submissionButton,BorderLayout.CENTER);
                                    addingSomethingWhenEventIsInvoked(writingPrice);
                                    addingSomethingWhenEventIsInvoked(panelForSubmissionButton);
                                    contentsFromFiles.clear();
                                    submissionButton.setPreferredSize(new Dimension(150,50));
                                    submissionButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (ifTextFiledForSingleNumber(writingPrice.getText().trim())) {
                                                double costFromUser = Double.parseDouble(writingPrice.getText());
                                                removingTheComponents();
                                                if (costFromUser < management.getTotalCost()) {
                                                    load("IfUserSelectsInvalidNumberOrProblemInStaffReservation.txt", 0);
                                                    JLabel label = new JLabel(contentsFromFiles.get(0));
                                                    addingSomethingWhenEventIsInvoked(label);
                                                    initialBoard();
                                                } else {
                                                    load("YourChangeIs.txt", 0);
                                                    load(contentsFromFiles.get(0) + (costFromUser - management.getTotalCost()) + "$");
                                                    contentsFromFiles.remove(0);
                                                    openingNewWindow();
                                                    contentsFromFiles.clear();
                                                    load("ThanksForYourReservation.txt",0);
                                                    openingNewWindow();
                                                    disposingMainWindow();
                                                }
                                            }
                                        }
                                    });
                                } else {
                                    contentsFromFiles.clear();
                                    load("YouHaveNotOrderedForSomethingYet.txt", 0);
                                    openingNewWindow();
                                    removingTheComponents();
                                    initialBoard();
                                }
                            } else {
                                removingTheComponents();
                                initialBoard();
                            }
                        }
                    });
                }
                break;
            case 6:
                if (!management.getReservedTables().isEmpty() || !management.getReservedStaff().isEmpty()){
                    load("QuitIsPressedButUserHasOrderedSomething.txt",0);
                    JLabel label = new JLabel(contentsFromFiles.get(0));
                    removingTheComponents();
                    addingSomethingWhenEventIsInvoked(label);
                    initialBoard();
                } else {
                    loadingOtherWindowForReservation();
                }
                break;
        }
    }

    /**
     * removingTheComponents is a method for removing all the components from the window
     */
    public void removingTheComponents() {
        Container container = getContentPane();
        container.removeAll();
        container.revalidate();
        this.repaint();
    }

    /**
     * a mutator for content from files list for adding single String statement
     * @param contents of type String
     */
    public void setContentsFromFiles(String contents) {
        this.contentsFromFiles.add(contents);
    }

    /**
     * an accessor for contents from file
     * @return of type ArrayList<String>
     */
    public ArrayList<String> getContentsFromFiles() {
        return this.contentsFromFiles;
    }

    /**
     * printTables is a method that prints the reserved tables that user has reserved
     * @param tables of type ArrayList<Table>
     * @param panel of type JPanel
     */
    public void printTables(ArrayList<Table> tables, JPanel panel){
        if (tables.isEmpty()){
            load("TablesNone.txt",0);
            JLabel label = new JLabel(contentsFromFiles.get(0));
            panel.add(label,BorderLayout.CENTER);
        }else {
            for (int i = 0; i < tables.size(); i++){
                labels = new JLabel[tables.size()];
                load(i + 1 + ": " + "Table number " + tables.get(i).getNumberOfTable() + " reserved for " + tables.get(i).getDayAvailable() + "." + tables.get(i).getMonthAvailable() + "." + tables.get(i).getYearAvailable() + ": " + tables.get(i).getCost() + "$");
                labels[i] = new JLabel(contentsFromFiles.get(0));
                panel.add(labels[i],BorderLayout.CENTER);
                contentsFromFiles.clear();
            }
        }
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        contentsFromFiles.clear();
        this.add(panel,BorderLayout.CENTER);
    }

    /**
     * printTablesWithButtons is a method that prints the reserved tables with additional buttons for removal
     * @param tables of type ArrayList<Table>
     * @param panel of type JPanel
     */
    public void printTablesWithButtons(ArrayList<Table> tables, JPanel panel){
        removingTheComponents();
        if (tables.isEmpty()){
            load("TablesNone.txt",0);
            JLabel label = new JLabel(contentsFromFiles.get(0));
            panel.add(label,BorderLayout.CENTER);
            panel.setPreferredSize(new Dimension(getWidth(),200));
        }else {
            JLabel whichOneLabel = new JLabel(contentsFromFiles.get(0));
            this.add(whichOneLabel);
            contentsFromFiles.remove(0);
            for (int i = 0; i < tables.size(); i++){
                labels = new JLabel[tables.size()];
                restaurantButtons = new RestaurantButton[tables.size()];
                load(i + 1 + ": " + "Table number " + tables.get(i).getNumberOfTable() + " reserved for " + tables.get(i).getDayAvailable() + "." + tables.get(i).getMonthAvailable() + "." + tables.get(i).getYearAvailable() + ": " + tables.get(i).getCost() + "$");
                labels[i] = new JLabel(contentsFromFiles.get(0));
                panel.add(labels[i]);
                restaurantButtons[i] = new RestaurantButton(i + 1);
                panel.add(restaurantButtons[i]);
                restaurantButtons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(int b = 0; b < tables.size(); b++){
                            restaurantButtons[b] = new RestaurantButton(b + 1);
                        }
                        for (int j = 0; j < tables.size(); j++){
                            if(j + 1 == restaurantButtons[j].getNumberAssociatedToEachButton()){
                                management.removingTables(j + 1);
                                break;
                            }
                        }
                        load("IfRemovalWasSuccessful.txt",0);
                        openingNewWindow();
                        removingTheComponents();
                        removingWindow();
                    }
                });
                contentsFromFiles.clear();
            }
        }
        contentsFromFiles.clear();
        this.add(panel,BorderLayout.CENTER);
    }

    /**
     * printStaff is a method that prints reserved staff
     * @param people of type ArrayList<Person>
     * @param panel of type JPanel
     */
    public void printStaff(ArrayList<Person> people, JPanel panel){
        if (people.isEmpty()){
            load("StaffNone.txt",0);
            JLabel label = new JLabel(contentsFromFiles.get(0));
            panel.add(label,BorderLayout.CENTER);
            panel.setPreferredSize(new Dimension(getWidth(),200));
        }else {
            int index = 0;
            labels = new JLabel[management.getNumberOfWaitersNeeded() + management.getNumberOfChiefsNeeded()];
            for (int i = 0 , j = 0; i < management.getNumberOfWaitersNeeded(); j++) {
                if (people.get(j) instanceof Waiter) {
                    load(index + 1 + ": " +"Waiter :  " + people.get(j).getNameOfTheWorker() + " reserved for " +  people.get(j).getDayAvailable() + "." +  people.get(j).getMonthAvailable() + "." +  people.get(j).getYearAvailable() + ": " + people.get(j).getCost() + "$");
                    labels[index] = new JLabel(contentsFromFiles.get(index));
                    panel.add(labels[index],BorderLayout.CENTER);
                    i++;
                    index++;
                }
            }
            for (int i = 0 , j = 0; i < management.getNumberOfChiefsNeeded(); j++){
                if ( people.get(j) instanceof Chef) {
                    load(index + 1 + ": " + "Chief :  " + people.get(j).getNameOfTheWorker() + " reserved for " +  people.get(j).getDayAvailable() + "." +  people.get(j).getMonthAvailable() + "." +  people.get(j).getYearAvailable() + ": " + people.get(j).getCost() + "$");
                    labels[index] = new JLabel(contentsFromFiles.get(index));
                    panel.add(labels[index],BorderLayout.CENTER);
                    i++;
                    index++;
                }
            }
        }
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        this.add(panel,BorderLayout.CENTER);
        contentsFromFiles.clear();
    }

    /**
     * printStaffWithButtons is a method that prints the reserved staff with additional buttons for removal
     * @param people of type ArrayList<Person>
     * @param panel of type JPanel
     */
    public void printStaffWithButtons(ArrayList<Person> people, JPanel panel){
        removingTheComponents();
        if (people.isEmpty()){
            load("StaffNone.txt",0);
            JLabel label = new JLabel(contentsFromFiles.get(0));
            panel.add(label,BorderLayout.CENTER);
            panel.setPreferredSize(new Dimension(getWidth(),200));
        }else {
            int index = 0;
            JLabel whichOneLabel = new JLabel(contentsFromFiles.get(0));
            this.add(whichOneLabel);
            contentsFromFiles.remove(0);
            labels = new JLabel[management.getNumberOfWaitersNeeded() + management.getNumberOfChiefsNeeded()];
            restaurantButtons = new RestaurantButton[management.getNumberOfWaitersNeeded() + management.getNumberOfChiefsNeeded()];
            for (int i = 0 , j = 0; i < management.getNumberOfWaitersNeeded(); j++) {
                if (people.get(j) instanceof Waiter) {
                    load(index + 1 + ": " +"Waiter :  " + people.get(j).getNameOfTheWorker() + " reserved for " +  people.get(j).getDayAvailable() + "." +  people.get(j).getMonthAvailable() + "." +  people.get(j).getYearAvailable() + ": " + people.get(j).getCost() + "$");
                    labels[index] = new JLabel(contentsFromFiles.get(index));
                    restaurantButtons[index] = new RestaurantButton(index + 1);
                    panel.add(labels[index],BorderLayout.CENTER);
                    panel.add(restaurantButtons[index],BorderLayout.CENTER);
                    restaurantButtons[index].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(int b = 0; b < management.getNumberOfWaitersNeeded(); b++){
                                restaurantButtons[b] = new RestaurantButton(b + 1);
                            }
                            for (int j = 0; j < people.size(); j++){
                                if(j + 1 == restaurantButtons[j].getNumberAssociatedToEachButton()){
                                    management.removingStaff(j + 1);
                                    break;
                                }
                            }
                            load("IfRemovalWasSuccessful.txt",0);
                            openingNewWindow();
                            removingTheComponents();
                            removingWindow();
                        }
                    });
                    i++;
                    index++;
                }
            }
            for (int i = 0 , j = 0; i < management.getNumberOfChiefsNeeded(); j++){
                if ( people.get(j) instanceof Chef) {
                    load(index + 1 + ": " + "Chief :  " + people.get(j).getNameOfTheWorker() + " reserved for " +  people.get(j).getDayAvailable() + "." +  people.get(j).getMonthAvailable() + "." +  people.get(j).getYearAvailable() + ": " + people.get(j).getCost() + "$");
                    labels[index] = new JLabel(contentsFromFiles.get(index));
                    restaurantButtons[index] = new RestaurantButton(index + 1);
                    panel.add(labels[index]);
                    panel.add(restaurantButtons[index]);
                    restaurantButtons[index].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for(int b = 0; b < management.getNumberOfChiefsNeeded(); b++){
                                restaurantButtons[b] = new RestaurantButton(b + 1);
                            }
                            for (int j = 0; j < people.size(); j++){
                                if(j + 1 == restaurantButtons[j].getNumberAssociatedToEachButton()){
                                    management.removingStaff(j + 1);
                                    break;
                                }
                            }
                            load("IfRemovalWasSuccessful.txt",0);
                            openingNewWindow();
                            removingTheComponents();
                            removingWindow();
                        }
                    });
                    i++;
                    index++;
                }
            }
        }
        this.add(panel,BorderLayout.CENTER);
        contentsFromFiles.clear();
    }

    /**
     * load is a method that takes a String sentence and adds it to contentFromFiles list
     * @param sentence of type String
     */
    public void load(String sentence){
        contentsFromFiles.add(sentence);
    }

    /**
     * load is a method that takes a file and a line and read from it
     * @param file of type String
     * @param line of type int
     */
    public void load(String file, int line) {
        Scanner readingFromFile = null;
        try {
            readingFromFile = new Scanner(new FileInputStream(file));
            switch (file) {
                case "Restaurant.txt":
                    save(file, 7, line);
                    break;
                case "QuestionsForRemoval.txt":
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
                        "Congratulation.txt",
                        "TablesNone.txt",
                        "StaffNone.txt",
                        "EventsNone.txt",
                        "TotalCost.txt",
                        "QuitIsPressedButUserHasOrderedSomething.txt",
                        "SubmitReservation.txt",
                        "YourChangeIs.txt",
                        "YouHaveNotOrderedForSomethingYet.txt",
                        "ThanksForYourReservation.txt":
                    save(file, 1, line);
                    break;
                case "QuestionsForTableReservation.txt":
                    save(file, 3, line);
                    break;
                case "QuestionsForStaffReservation.txt":
                    save(file, 3, line);
                    break;
                case "QuestionsForTableAndStaffAndEventRemoval.txt":
                    save(file, line + 1, line);
                    break;
                case "CompleteReservation.txt":
                    save(file,2,line);
                default:
                    save(file,38,line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        readingFromFile.close();
    }

    /**
     * save is a method that saves the process in the SavedProcess file
     * @param file of type String
     * @param count of type int
     * @param line of type int
     */
    public void save(String file, int count, int line) {
        Scanner readingFile = null;
        PrintWriter outputStream = null;
        String contents = "";
        if (!getContentsFromFiles().isEmpty())
            getContentsFromFiles().clear();
        try {
            readingFile = new Scanner(new FileInputStream(file));
            outputStream = new PrintWriter(new FileOutputStream("SavedProcess.txt", true));
            for (int i = line; i <= count; i++) {
                if (readingFile.hasNextLine()) {
                    contents = readingFile.nextLine();
                    outputStream.println(contents);
                    setContentsFromFiles(contents);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        readingFile.close();
        outputStream.close();
    }

    /**
     * initialBoard is a method that initializes and sets up the main board
     */
    public void initialBoard(){
        this.setSize(700, 700);
        this.setLayout(new GridLayout(10, 3));
        this.setTitle("Restaurant Management");
        load("Restaurant.txt",0);
        ArrayList<String> contents = getContentsFromFiles();
        panels = new JPanel[contents.size()];
        restaurantButtons = new RestaurantButton[contents.size()];
        labels = new JLabel[contents.size()];
        for (int i = 0; i < contents.size(); i++) {
            if (i == 0) {
                labels[i] = new JLabel(contents.get(i));
                panels[i] = new JPanel();
                panels[i].add(labels[i]);
                this.add(panels[i]);
            } else {
                panels[i] = new JPanel(new FlowLayout());
                restaurantButtons[i] = new RestaurantButton(i);
                labels[i] = new JLabel(contents.get(i));
                panels[i].add(labels[i]);
                panels[i].add(restaurantButtons[i]);
                this.add(panels[i]);
            }
        }
        for (int i = 1; i < restaurantButtons.length; i++) {
            restaurantButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RestaurantButton clickedButton = (RestaurantButton) e.getSource();
                    removingTheComponents();
                    try {
                        theActionOfClickedButton(clickedButton.getNumberAssociatedToEachButton());
                    } catch (NotPersonAvailableException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.contentsFromFiles.clear();
    }

    /**
     * addingSomethingWhenEventIsInvoked is a method that it adds something to the main window in an event
     * @param c of type Component
     */
    public void addingSomethingWhenEventIsInvoked(Component c){
        this.add(c);
    }

    /**
     * loadingOtherWindowForReservation is a method that checks if user reserved for something or not
     */
    public void loadingOtherWindowForReservation(){
        JFrame frame2 = new JFrame();
        frame2.setSize(500,100);
        if (management.getReservedTables().isEmpty() && management.getReservedStaff().isEmpty())
            load("IfUserDoesNotReserveAnything.txt", 0);
        else
            load("IfUserReservedForSomething.txt", 0);
        JLabel label = new JLabel(contentsFromFiles.get(0));
        label.setPreferredSize(new Dimension(200, 200));
        frame2.add(label, BorderLayout.CENTER);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.dispose();
    }

    /**
     * removingWindow is a window for reservation removal that appeared when user wants to remove a reservation
     */
    public void removingWindow(){
        removingTheComponents();
        load("QuestionsForRemoval.txt", 0);
        labels = new JLabel[contentsFromFiles.size()];
        restaurantButtons = new RestaurantButton[contentsFromFiles.size() - 1];
        panels = new JPanel[2];
        for (int j = 0, p = 0, b = 0; j < contentsFromFiles.size(); j++){
            labels[j] = new JLabel(contentsFromFiles.get(j));
            panels[p] = new JPanel();
            panels[p].setPreferredSize(new Dimension(getWidth(), 200));
            if (p == 0) {
                panels[p].add(labels[j]);
                panels[p].setPreferredSize(new Dimension(getWidth(), 200));
                this.add(panels[p]);
                p++;
            } else {
                panels[p].add(labels[j]);
                panels[p].setPreferredSize(new Dimension(getWidth(), 200));
                restaurantButtons[b] = new RestaurantButton(b + 1);
                panels[p].add(restaurantButtons[b]);
                this.add(panels[p]);
                b++;
            }
        }
        panels = new JPanel[3];
        for (int j = 0; j < panels.length; j++){
            panels[j] = new JPanel();
            int finalJ = j + 1;
            restaurantButtons[j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (finalJ){
                        case 1:
                            if (management.getReservedTables().isEmpty()){
                                load("StatementIfNoTablesAreSelected.txt",0);
                                openingNewWindow();
                            } else {
                                load("QuestionsForTableAndStaffAndEventRemoval.txt", 0);
                                printTablesWithButtons(management.getReservedTables(),panels[0]);
                            }
                            break;
                        case 2:
                            if (management.getReservedStaff().isEmpty()){
                                load("StatementIfNoStaffIsSelected.txt",0);
                                openingNewWindow();
                            } else {
                                load("QuestionsForTableAndStaffAndEventRemoval.txt", 0);
                                printStaffWithButtons(management.getReservedStaff(), panels[1]);
                            }
                            break;
                        case 3:
                            removingTheComponents();
                            initialBoard();
                            break;

                    }
                }
            });
        }
    }

    /**
     * openingNewWindow is a method that a new window for anu exception
     */
    public void openingNewWindow(){
        JFrame frame2 = new JFrame();
        frame2.setSize(700, 100);
        JLabel label = new JLabel(contentsFromFiles.get(0));
        label.setPreferredSize(new Dimension(200, 200));
        frame2.add(label, BorderLayout.CENTER);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * ifTextFiledIsValidForDate is a method that checks whether the content in the text field area is valid or not
     * only for dates
     * @param str of type String
     * @param index of type int
     * @return of type boolean
     */
    public boolean ifTextFiledIsValidForDate(String str, int index){
        if (index != 0){
             return ifTextFiledForSingleNumber(str);
        }
        boolean check = false;
        if (str.isEmpty())
            return check;
        else {
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '.') {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    /**
     * ifTextFiledForSingleNumber is a method that checks whether the content in the text field area is valid or not
     * for other than dates
     * @param str of type String
     * @return boolean
     */
    public boolean ifTextFiledForSingleNumber(String str){
        boolean check = false;
        if (str.isEmpty())
            return check;
        else {
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    /**
     * disposingMainWindow is a method for disposing the main window
     */
    public void disposingMainWindow(){
        this.dispose();
    }

    /**
     * listsOfTablesAndStaffAvailableWindow is a method that opens a new window to show the user which options
     * are available for the user
     */
    public void listsOfTablesAndStaffAvailableWindow(){
        JLabel tableLabel = new JLabel("Tables");
        tableLabel.setPreferredSize(new Dimension(200,80));
        JLabel staffLabel = new JLabel("Staffs");
        staffLabel.setPreferredSize(new Dimension(200,80));
        JFrame frame = new JFrame("List");
        frame.setLayout(new BorderLayout());
        JPanel panelForTables = new JPanel();
        JPanel panelForStaff = new JPanel();
        labels = new JLabel[contentsFromFiles.size()];
        panelForTables.add(tableLabel);
        int count = 0;
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(i + 1 + ") " + contentsFromFiles.get(i));
            labels[i].setPreferredSize(new Dimension(200,80));
            if (labels[i].getText().contains("T")) {
                panelForTables.add(labels[i],BorderLayout.CENTER);
            } else {
                if (count == 0){
                    panelForStaff.add(staffLabel,BorderLayout.CENTER);
                    count++;
                }
                panelForStaff.add(labels[i],BorderLayout.CENTER);
            }
        }
        panelForTables.setLayout(new BoxLayout(panelForTables,BoxLayout.Y_AXIS));
        panelForStaff.setLayout(new BoxLayout(panelForStaff,BoxLayout.Y_AXIS));
        frame.add(panelForTables, BorderLayout.WEST);
        frame.add(panelForStaff, BorderLayout.EAST);
        frame.setVisible(true);
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
