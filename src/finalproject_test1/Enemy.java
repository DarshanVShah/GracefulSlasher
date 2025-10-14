
//This is the class to create the enemy object and make all its movements and collison

package finalproject_test1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Enemy extends JFrame{
    
    //Create all variables for the player itself
    private int x, y;
    private int size;
    private int speed;
    private int quad;
    
    Random rand = new Random(); //create random object
    
    Image image1 = (new ImageIcon("images/enemyShip.v3.png")).getImage(); //set the image 
    
    //constructor
    public Enemy(int speedRec) {
        //generate random quadrant
        quad = rand.nextInt(4) + 1;
        
        //generate random coordinate for the enemy to start in from one of the four quadrants in the corners
        switch (quad) {
            case 1: 
                x = rand.nextInt(120) + 1;
                y = rand.nextInt(120) + 1;
                break;
            case 2:
                x = rand.nextInt(480 - 360 + 1) + 360;
                y = rand.nextInt(120) + 1;
                break;
            case 3:
                x = rand.nextInt(120) + 1;
                y = rand.nextInt(480 - 360 + 1) + 360;
                break;
            case 4:
                x = rand.nextInt(480 - 360 + 1) + 360;
                y = rand.nextInt(480 - 360 + 1) + 360;
        }
        
        
        //set size to 20 for now
        size = 20; //again the size is the box around the object that collides with the other stuff
        
        //the speed will be dependent on the difficult chosen
        speed = speedRec;
    }
    
    //draw method to draw the enemy as a square right now as well
    public void draw(Graphics gc, int screenwidth) {
        gc.setColor(Color.blue);
        //gc.fillRect(x - 3, y, size, size);
        
        gc.drawImage(image1, x, y, null);  //draws enemy image with x y coordinate
    }
    
    //Acessor Methods
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public int getEnemySize() {
        return this.size;
    }
    
    //have a move method that takes in the x and y pos then chases it in a way by comparing it to where its own pos is.
    public void move(int playerX, int playerY) {
        
        if (x < playerX + 5) { //if player to the right, increase x 
            x += speed;
        }
        else if (x > playerX + 5) { //if player to the left, decrease x
            x -= speed;
        }
        
        if (y < playerY + 5) { //if player below, increase y
            y += speed;
        }
        else if (y > playerY + 5) { //if player above, decrease y
            y -= speed;
        }
            
    }
    
    //create a method to see if the enemy is touch someone
    public boolean isTouching(int playerX, int playerY, int playerSize) {
        //this takes the players location and size to basically create a box around each item and if these boxes intersect in any way the method returns true
        
        if ((x + size >= playerX) && (x < playerX + playerSize) && (y <= playerY + size) && (y + size >= playerY)) {
            return true;
        }
        else if ((y + size >= playerY) && (y < playerY + playerSize) && (x <= playerX + size) && (x + size >= playerX)) {
            return true;
        }

        return false;
    }
}
