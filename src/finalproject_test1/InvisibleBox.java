
//This class checks for if the player collided with any enemies in its movement path
//It does this by running a invisible box at a high speed towards the player and destroying 
//anything that it hits in its path

package finalproject_test1;

import java.awt.Color;
import java.awt.Graphics;

//deosn't actually implement anything, could've used collision from enemy or coordinates from line
public class InvisibleBox extends Line { 
    //this class will run up the line and see if it collides with an enemy
    
    //variables
    private int x, y;
    private int speed, size;
    
    public InvisibleBox() {
        //set the coordinates to zero since they arent needed until when the player starts moving
        x = 0;
        y = 0;
        
        size = 15; //this is side of the box that collides with everything
        
        speed = 20; //the speed it travels to reach the player
    }
    
    //set the squares location to where the player originally is
    public void setCoordinates(int xRec, int yRec) {
        x = xRec - 5;
        y = yRec + 5;
    }
    
    //Acessor Methods
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    //make a move function to follow where the player goes, similar in how the enemy moves
    public void move(int destX, int destY) {
        
        
        if (x < destX) { //if player to the right increase x 
            x += speed; 
        }
        else if (x > destX) { //if player to left decrease x
            x -= speed;
        }
        
        if (y < destY + 2) { //if player below increase y
            y += speed;
        } 
        else if (y > destY + 2) { //of the player above decrease y
            y -= speed;
        }
        
       
    }
    
    //check if it collides with any enemies
    public boolean collision(int enemyX, int enemyY, int enemySize) {
        //this takes the enemies location and size to basically create a box around each item and if these boxes intersect in any way the method returns true
        
        if ((enemyX + enemySize >= x) && (enemyX < x + size) && (enemyY <= y + enemySize) && (enemyY + enemySize >= y)) {
            return true;
        }
        else if ((enemyY + enemySize >= y) && (enemyY < y + size) && (enemyX <= x + enemySize) && (enemyX + enemySize >= x)) {
            return true;
        }

        return false;
    }
}
