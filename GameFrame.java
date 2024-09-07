package Snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{
    // GameFrame inherits the properties of JFrame + the properties/constants/constraints we are about to set.
    GamePanel panel = new GamePanel();
    // create the variable panel which is of type gamePanel

        GameFrame(){
        //constructor for this class
            // we will add the panel to the game frame ( the window where our game's gonna appear )
            this.add(panel);
            this.setTitle("Snake"); // title that's gonna appear on there
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the code terminate when the user exits the page (clicks x and closes the game window)
            this.setResizable(false); // the user does not have the ability to resize
            this.pack(); // packs everything up in the desired sizes
            this.setVisible(true); // the window will be visible
            this.setLocationRelativeTo(null); // the location of the window isn't specified


        }










    }

