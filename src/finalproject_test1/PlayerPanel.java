//Darshan Shah
//This is the main game panel where majority of the code takes place. It allows for everything to work together
//With the different timers, objects, and events.

package finalproject_test1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

public class PlayerPanel extends JPanel implements ActionListener {

    //list all variables that will be needed
    private final Player player; //declare object of type Player
    private final Line line; //declare object of type Line
    private final InvisibleBox iBox; //declare object of type invisible box

    public int score = 0; //use to track score and time
    private double time = 0;
    
    private boolean enemySpawnRate; //this is used to increase number of enemies that spawn

    private Font font; //for displaying of score

    private ArrayList<Enemy> enemyList; //use this for spawning of enemy objects

    private JFrame gameFrame; //this is made so the jframe can be closed from within this class
    private int chosenSpeed; //this is so the enemy speed can be sent to the enemy class

    boolean drawLine = false;
    Timer timer1, timer2, timer3, timer4;

    //variables for sound
    Clip clip;
    AudioInputStream sound;

    //constructor - the frame and sound are brought from the other classes that call them
    public PlayerPanel(JFrame frame, int enemySpeedRec) {
        
        //create objects
        player = new Player(); 
        line = new Line(); 
        iBox = new InvisibleBox(); 

        enemyList = new ArrayList<>();

        font = new Font("Arial", Font.PLAIN, 36);

        // create an object of type playerListener
        playerListener monitor = new playerListener();

        // add the addMouseMotionListener to the monitor object
        addMouseMotionListener(monitor);

        // add the addMouseListener to the monitor object
        addMouseListener(monitor);

        //setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setBackground(Color.LIGHT_GRAY);

        //set the size of this panel
        setPreferredSize(new Dimension(480, 480));

        //set the game frame so it can be altered
        gameFrame = frame;

        //set the enemy speed that was chosen
        chosenSpeed = enemySpeedRec;

        //try to find the sound file and open the audiosystem
        try {
            File file = new File("sounds/DRIVE.wav");
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
        }
        
        //make the spawn rate normal to start
        enemySpawnRate = false;

        timer1 = new Timer(10, this);//1 hundreth of a second
        timer2 = new Timer(100, this);// 1 tenth of second
        timer3 = new Timer(2000, this);//2 seconds
        timer4 = new Timer(1000, this);//1 second

        //start all timers
        timer1.start();
        timer2.start();
        timer3.start();
        timer4.start();

    }

    //draws the player on the screen
    @Override
    public void paintComponent(Graphics page) {
        super.paintComponent(page);

        //draws all the enemies found in the arraylist
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).draw(page, getWidth());
        }

        //checks if the line should be drawn
        if (drawLine == true) {
            line.draw(page, getWidth());
        }

        //draws the player
        player.draw(page, getWidth());
        //iBox.draw(page, getWidth());

        //draw the score in the top right corner and time in the left
        page.setFont(font);
        page.setColor(Color.white);
        page.drawString(Integer.toString(score), 425, 37);

        page.setFont(font);
        page.setColor(Color.white);
        page.drawString(Double.toString(time), 10, 37);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //all inside these are called whenever the time indicated passes
        
        if (e.getSource() == timer1) {

            //increase the time variable
            time += 0.01;

            //round the time to 2 decimals
            time = (double) Math.round(time * 100) / 100;
            
            //this is done to increase the difficulty somewhat if the player has survived over 20 seconds
            if (time >= 20) {
                enemySpawnRate = true;
            }

            //this calls the move function for all the enemies the arraylist
            for (int i = 0; i < enemyList.size(); i++) {
                enemyList.get(i).move(player.getX(), player.getY());
            }

            //this checks if the enemy has collided with the player for all the enemies in the arraylist
            for (int i = 0; i < enemyList.size(); i++) {
                //uses the method created in the enemy class
                if (enemyList.get(i).isTouching(player.getX(), player.getY(), player.getPlayerSize()) == true) {
                    //call game over screen
                    DeathClass deathObject;
                    deathObject = new DeathClass();
                    deathObject.setupFrame(score, time); //send the score and time over to be dislpayed
                    this.hide(); //hide this frame and panel
                    gameFrame.hide();
                    
                    clip.stop(); //stop the music

                    //stop all the timers so they don't continue running what is inside them
                    timer1.stop();
                    timer2.stop();
                    timer3.stop();
                    timer4.stop();
                }

            }

            //this is used to check if the players trail collides with the enemies to destroy them
            
            //this makes the box move until it reaches the player, otherwise it would continue its movement and kill any enemies that touched the player
            //so its checking if it collides with the player like the enemy does
            if (iBox.collision(player.getX(), player.getY(), player.getPlayerSize()) == false) {
                //if ((iBox.getX() != player.getX()) && (iBox.getY() != player.getY())) {

                //as long as it isn't colliding the box moves towards the player, in somewhat similar fashion to the players line
                iBox.move(player.getX(), player.getY());

                //now it checks for all the enemies in the arraylist if it has collided with them and if so remove them from the arraylist
                for (int i = 0; i < enemyList.size(); i++) {
                    if ((iBox.collision(enemyList.get(i).getX(), enemyList.get(i).getY(), enemyList.get(i).getEnemySize())) == true) {
                        enemyList.remove(i);
                        score++; //increase the score for enemy killed
                    }
                }
            } 
        }

        //use timer to undraw the line every time this timer is called
        //(one issue: if line drawn at same time its called, the line does not get drawn-no easy fix)
        if (e.getSource() == timer2) {
            drawLine = false;
        }

        //these spawns a enemy every two seconds to start the game, but will stop once 20 seconds is reached
        if ((e.getSource() == timer3) && (enemySpawnRate == false)) {
            enemyList.add(new Enemy(chosenSpeed)); // adds enemy to array list
        }
        
        //then this timer will spawn them every one second
        if ((e.getSource() == timer4) && (enemySpawnRate == true)) {
            enemyList.add(new Enemy(chosenSpeed));
        }

        repaint(); //call draw method
    }


    private class playerListener implements MouseMotionListener, MouseListener {

        //  This event is triggered when the mouse is moved onto a component
        //  but no buttons have been pushed
        @Override
        public void mouseMoved(MouseEvent event) {
            repaint();  //will manually re-call the paintComponent method
        }

        //  This event is triggered when the mouse is moved on a component
        //  while a mouse button is pressed
        @Override
        public void mouseDragged(MouseEvent event) {
            repaint();  //will manually re-call the paintComponent method
        }

        //  Called just after the user presses a mouse button while the 
        //  cursor is over the listened-to component.
        @Override
        public void mousePressed(MouseEvent event) {
            //check if left trigger on mouse has been clicked
            if (event.getButton() == 1) {
                //retrieve the coordinates for the mouse and player and draw the line between the coordinates
                line.setCoordinates(player.getX(), player.getY(), event.getX(), event.getY());
                //line.calcLineEq();

                drawLine = true;

                //iBox.setCoordinates(player.getX(), player.getY());
                player.move(event.getX(), event.getY()); //move the player to clicked spot
                
                clip.start(); //start the music once the first click has happened

                repaint();  //will manually re-call the paintComponent method
            }

        }

        //------------------------------------------------------------------------
        //  Empty definitions for unused events. Must Include even if not using
        //------------------------------------------------------------------------
        @Override
        public void mouseReleased(MouseEvent event) {

        } //invoked when mouse button is released

        @Override
        public void mouseClicked(MouseEvent event) {
        } //Invoked when the mouse button has been clicked (pressed and released) on a component.

        @Override
        public void mouseExited(MouseEvent event) {
        }  //Invoked when the mouse exits a component.

        @Override
        public void mouseEntered(MouseEvent event) {
        } //Invoked when the mouse enters a component.
    }

}
