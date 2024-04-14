/**
 * Dedicating a package and an import statement
 */
package aua.am.restaurant_management.core;

import java.io.FileNotFoundException;

/**
 * The Event class that implements the interface Decoratable that has two methods of type boolean
 */

public class Events implements Decoratable {
    /**
     * Declaring instance variables
     */
    private int day;
    private int digitForTypesOfEvents;
    private int digitForBalloonColor;
    private TypesOfMusic finalChoice;

    /**
     * Declaring an enum of the name TypesOfMusic that has two constants
     */
    public enum TypesOfMusic {
        Pop, Jazz
    }
    /**
     * Declaring an enum of the name ColorOfBalloons that has two constants
     */
    public enum ColorOfBalloons{
        RED, BLUE
    }

    /**
     * Constructor that takes three arguments and throws a FlowerNotFreshException exception
     * @param digitForTypesOfEvents of type int
     * @param day of type int
     * @param digitForBalloonColor of type int
     * @throws FlowerNotFreshException of type Exception
     */
    public Events (int digitForTypesOfEvents, int day, int digitForBalloonColor) throws FlowerNotFreshException{
        setDay(day);
        setDigitForTypesOfEvents(digitForTypesOfEvents);
        if (digitForTypesOfEvents == 1) {
            try {
                if (!isFresh()) {
                    throw new FlowerNotFreshException();
                } else{
                    System.out.println("Your flowers will be ready for your wedding ceremony.");
                }
            } catch (FlowerNotFreshException e) {
                System.out.println(e.getMessage());
            } finally {
                setFinalChoice(choosingTheMusicType(getDigitForTypesOfEvents()));
            }
        } else {
            setDigitForBalloonColor(digitForBalloonColor);
            if (whichColor() == ColorOfBalloons.RED){
                System.out.println("Red balloons will be ready for your daughter's birthday party");
            } else {
                System.out.println("Blue balloons will be ready for your son's birthday party");
            }
            setFinalChoice(choosingTheMusicType(getDigitForTypesOfEvents()));
        }
    }

    /**
     * setter and getter for BalloonColor
     * @param digitForBalloonColor of type ColorOfBalloons
     */
    public void setDigitForBalloonColor(int digitForBalloonColor) {
        this.digitForBalloonColor = digitForBalloonColor;
    }

    public int getDigitForBalloonColor() {
        return digitForBalloonColor;
    }

    /**
     * accessor and mutator methods for day
     * @param day of type int
     */
    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    /**
     * accessor and mutator methods for digit
     * @param digitForTypesOfMusics of type int
     */
    public void setDigitForTypesOfEvents(int digitForTypesOfMusics) {
        this.digitForTypesOfEvents = digitForTypesOfMusics;
    }

    public int getDigitForTypesOfEvents() {
        return digitForTypesOfEvents;
    }

    /**
     * accessor and mutator methods for finalChoice
     * @param finalChoice of type TypesOfMusic
     */
    public void setFinalChoice(TypesOfMusic finalChoice) {
        this.finalChoice = finalChoice;
    }

    public TypesOfMusic getFinalChoice() {
        return finalChoice;
    }

    /**
     * Evaluates which type of music is appropriate for the event
     * @param number of type int
     * @return of type TypesOfMusic
     */
    public TypesOfMusic choosingTheMusicType(int number) {

        if (number == 1) {
            return TypesOfMusic.Jazz;
        } else {
            return TypesOfMusic.Pop;
        }

    }

    /**
     * isFresh method chcks if the flowers are fresh or not based on the day
     * Decoratable interface
     * @return of type boolean
     */
    public boolean isFresh(){
        if (getDay() % 2 != 0){ // we have fresh flowers only on odd days
            return true;
        } else {
            return false;
        }
    }

    /**
     * whichColor method checks if the gender is girl then the color of balloons is red
     * Otherwise it is blue
     * Decoratable interface
     * @return of type ColorOfBalloons
     */
    public ColorOfBalloons whichColor(){
        if (getDigitForBalloonColor() == 1){
            return ColorOfBalloons.RED;
        } else {
            return ColorOfBalloons.BLUE;
        }
    }
}

