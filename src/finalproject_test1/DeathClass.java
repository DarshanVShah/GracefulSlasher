//Darshan Shah
//This class displays the death screen that occurs after the game is over. 
//It shows the players score and time and a option to go back home

package finalproject_test1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeathClass extends JFrame implements ActionListener {
    
    //create all variables that are needed
    JPanel panel = new JPanel();
    JLabel bgLbl, scoreLbl, timeLbl;
    JButton homeBtn;
    
    private Font font;

    public DeathClass() {
        super("Graceful Slasher - Death"); //title the frame name as is
    }

    public void setupFrame(int scoreRec, double timeRec) {
        // is required to make sure that the program actually ends when the 'X' button is pressed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets the frame to be 300 pixels wide by 445 pixels high
        this.setBounds(0, 0, 300, 445);
        this.setResizable(false);

        // The panel colour is set to white
        panel.setBackground(Color.white);

        //Setting the layout of the panel to null
        panel.setLayout(null);

        //place the home button the screen
        homeBtn = new JButton(" ");
        homeBtn.setBounds(83, 370, 135, 44);
        
        //found some code on how to make a button transparent
        homeBtn.setOpaque(false);
        homeBtn.setContentAreaFilled(false);
        homeBtn.setBorderPainted(false);
        panel.add(homeBtn); //add it to the panel

        //set the font
        font = new Font("Arial", Font.PLAIN, 30);

        //create the text to place the score on the screen
        scoreLbl = new JLabel(Integer.toString(scoreRec));
        scoreLbl.setFont(font);
        scoreLbl.setBounds(180, 130, 50, 50);
        panel.add(scoreLbl);
        
        //create the text to place the score on the screen
        timeLbl = new JLabel(Double.toString(timeRec));
        timeLbl.setFont(font);
        timeLbl.setBounds(160, 180, 100, 50);
        panel.add(timeLbl);
        

        //add background
        ImageIcon icon = new ImageIcon("images/deathScreen.png");
        bgLbl = new JLabel(icon);
        bgLbl.setBounds(0, -25, 300, 445);
        panel.add(bgLbl);

        //implement action listener
        homeBtn.addActionListener(this);

        // show the contents
        this.setContentPane(panel);
        // sets the frame to be visible
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeBtn) {
            //go back to menu panel
            MenuFrame menu = new MenuFrame();
            menu.setupFrame();

            this.setVisible(false); //close this frame

        }
    }

}
