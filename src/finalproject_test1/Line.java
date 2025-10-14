
//This is the class that allows for the trail of the character to be drawn 

package finalproject_test1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line {

    //variables for point of a line
    private int x1, x2, y1, y2;
    private double slope, yInt;

    //constructor 
    public Line() {
        //set everything to zero as it is not needed unless called upon
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
    }
    
    //set the coordinates to the current moment
    public void setCoordinates(int x1Rec,int y1Rec,int x2Rec,int y2Rec) {
        //takes in the inputted values and sets the variables to them
        x1 = x1Rec;
        y1 = y1Rec;
        x2 = x2Rec;
        y2 = y2Rec;
    }
    
    //call a method to draw the line
    public void draw(Graphics gc, int screenWidth) {
        Graphics2D g2 = (Graphics2D) gc;
        g2.setStroke(new BasicStroke(10)); //allows a thicker line to be drawn over a normal graphics card would
        g2.setColor(Color.cyan);
        g2.drawLine(x1, y1, x2, y2); //draws a line between the two
        
    }
}
