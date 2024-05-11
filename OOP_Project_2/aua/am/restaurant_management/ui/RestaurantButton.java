package aua.am.restaurant_management.ui;

import javax.swing.*;
import java.awt.*;

/**
 * RestaurantButton class that extends JButton
 */
public class RestaurantButton extends JButton {
    /**
     * instance variable
     */
    private int numberAssociatedToEachButton;

    /**
     * a constructor that initializes numberAssociatedToEachButton
     * @param numberAssociatedToEachButton of type int
     */
    public RestaurantButton(int numberAssociatedToEachButton){
        this.setPreferredSize(new Dimension(50,20));
        this.setText("Select");
        setNumberAssociatedToEachButton(numberAssociatedToEachButton);
    }

    /**
     * a mutator for numberAssociatedToEachButton
     * @param numberAssociatedToEachButton of type int
     */
    public void setNumberAssociatedToEachButton(int numberAssociatedToEachButton){
        this.numberAssociatedToEachButton = numberAssociatedToEachButton;
    }

    /**
     * an accessor for numberAssociatedToEachButton
     * @return of type int
     */
    public int getNumberAssociatedToEachButton(){
        return this.numberAssociatedToEachButton;
    }
}
