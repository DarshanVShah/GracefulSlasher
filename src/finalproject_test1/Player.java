
//This is the class for the main player-it has all the methods relating to the main player

package finalproject_test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Player extends JFrame{

    //Create all variables for the player itself
    private int x, y;
    private int size;
    
    Image image1 = (new ImageIcon("images/slasher2.v4.png")).getImage(); //use created image as the player

    //Constructor
    public Player() {
        //set starting position to middle
        x = 220;
        y = 220;
     
        
        //set size for now (was used as the square length first but now it acts as the collider around the player)
        size = 20;
    }
    
    //draw method(for now it will be a square, but later an image)
    public void draw(Graphics gc, int screenWidth) {
        //just draw simple square
        gc.setColor(Color.red);
        //gc.fillRect(x, y, size, size);
        
        gc.drawImage(image1, x, y, null);  //draws player image with x y coordinate
        //panel.add(imgLabel);
    }

    //Acessor Methods
    public int getX() {
        return this.x + 15; //add 15 because the image is not centred without a little altering
    }

    public int getY() {
        return this.y + 15;
    }
    
    public int getPlayerSize() {
        return this.size;
    }
    
    //create a movement method to change the x and y of the player
    public void move(int xRec, int yRec) {
        x = xRec - 15; //same thing as above, alter a little
        y = yRec - 15;
    }

}
