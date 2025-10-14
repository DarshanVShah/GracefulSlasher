//Darshan Shah
//This class displays the home screen (the first screen shown) and gives the options to users
//to either play or get help, after death the game will return here. 

package finalproject_test1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuFrame extends JFrame implements ActionListener {

    //create all the variables that are needed
    JButton playBtn, helpBtn, scoreBoardBtn;
    JLabel bgLbl, characterLbl;
    
    JPanel panel = new JPanel();

    //constructor
    public MenuFrame() {
        //names the frame
        super("Graceful Slasher - Home");
    }

    public void setupFrame() {
        // is required to make sure that the program actually ends when the 'X' button is pressed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets the frame to be 400 pixels wide by 445 pixels high
        this.setBounds(0, 0, 400, 445);
        this.setResizable(false);

        //The panel is set to a colour
        panel.setBackground(Color.black);

        //Setting the layout of the panel to null
        panel.setLayout(null);

        //place the play button on the screen
        playBtn = new JButton(" ");
        playBtn.setBounds(145, 128, 110, 40);
        
        //found some code on how to make a button transparent
        playBtn.setOpaque(false);
        playBtn.setContentAreaFilled(false);
        playBtn.setBorderPainted(false);
        panel.add(playBtn); //add to panel
        
        //place the help button on screen
        helpBtn = new JButton(" ");
        helpBtn.setBounds(145, 165, 110, 40); //sets location
        
        //found some code on how to make a button transparent
        helpBtn.setOpaque(false);
        helpBtn.setContentAreaFilled(false);
        helpBtn.setBorderPainted(false);
        panel.add(helpBtn);
        
        //place the scoreboard button
        scoreBoardBtn = new JButton(" ");
        scoreBoardBtn.setBounds(95, 210, 220, 40);
        
        //found some code on how to make a button transparent
        scoreBoardBtn.setOpaque(false);
        scoreBoardBtn.setContentAreaFilled(false);
        scoreBoardBtn.setBorderPainted(false);
        panel.add(scoreBoardBtn);
        
        //add character (just small addition)
        ImageIcon icon2 = new ImageIcon("images/character.gif");
        characterLbl = new JLabel(icon2);
        characterLbl.setBounds(130, 250, 150, 150);
        panel.add(characterLbl);
        
        //add background - self created with text already implemented on it
        ImageIcon icon = new ImageIcon("images/menuScreen2.png");
        bgLbl = new JLabel(icon);
        bgLbl.setBounds(0, 0, 400, 413);
        panel.add(bgLbl);
        
        

        //implement action listener
        playBtn.addActionListener(this);
        helpBtn.addActionListener(this);
        scoreBoardBtn.addActionListener(this);

        // show the contents
        this.setContentPane(panel);
        // sets the frame to be visible
        this.setVisible(true);
        this.setResizable(false);
        //set the size of the frame
        //this.setSize(300, 4);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //checks to see what object triggered the actionPerformed event
        if (e.getSource() == playBtn) {
            //if the this button is clicked show the mode screen by creating an object of said class
            DifficultyFrame modeObject;
            modeObject = new DifficultyFrame();
            modeObject.setupFrame();
            
            this.setVisible(false);
        }
        else if (e.getSource() == helpBtn) {
            //if this button is clicked it launches the instruction panel and hides this frame
            InstructionFrame helpObject;
            helpObject = new InstructionFrame();
            helpObject.setupFrame();
            this.hide();
        }
        //this was going to be implemented, but I ended up not doing it--left the button because text is part of image I created that
        //I can't alter now
        else if (e.getSource() == scoreBoardBtn) {
            
        }
    }

}
