
//This class is called before the game is player for the user to choose the difficulty they would like
//a different button will send a different argument as the speed

package finalproject_test1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DifficultyFrame extends JFrame implements ActionListener{

    //define all variables
    JPanel panel = new JPanel();
    JLabel bgLbl;
    JButton easyBtn, mediumBtn, hardBtn;
    int enemySpeed;
    
    public DifficultyFrame() {
        super("Graceful Slasher - Mode"); //set the frame name
    }
    
    public void setupFrame() {
        // is required to make sure that the program actually ends when the 'X' button is pressed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Sets the frame to be 300 pixels wide by 445 pixels high
        this.setBounds(0, 0, 300, 445);
        this.setResizable(false);

        // The colour set to white
        panel.setBackground(Color.white);

        //Setting the layout of the panel to null
        panel.setLayout(null);
        
        //place the easy button pn the screen
        easyBtn = new JButton("");
        easyBtn.setBounds(85, 125, 133, 40);
        
        //found some code on how to make a button transparent
        easyBtn.setOpaque(false);
        easyBtn.setContentAreaFilled(false);
        easyBtn.setBorderPainted(false);
        panel.add(easyBtn); //add to panel
        
        //place the medium button on the screen
        mediumBtn = new JButton("");
        mediumBtn.setBounds(65, 225, 185, 40);
        
        //found some code on how to make a button transparent
        mediumBtn.setOpaque(false);
        mediumBtn.setContentAreaFilled(false);
        mediumBtn.setBorderPainted(false);
        panel.add(mediumBtn);
        
        //place the hard button on the screen
        hardBtn = new JButton("");
        hardBtn.setBounds(85, 330, 133, 40);
        
        //found some code on how to make a button transparent
        hardBtn.setOpaque(false);
        hardBtn.setContentAreaFilled(false);
        hardBtn.setBorderPainted(false);
        panel.add(hardBtn);
        
        
        //add background
        ImageIcon icon = new ImageIcon("images/modeScreen.png");
        bgLbl = new JLabel(icon);
        bgLbl.setBounds(0, 0, 300, 445);
        panel.add(bgLbl);
        
        
        //implement action listener
        easyBtn.addActionListener(this);
        mediumBtn.addActionListener(this);
        hardBtn.addActionListener(this);
        
        // show the contents
        this.setContentPane(panel);
        // sets the frame to be visible
        this.setVisible(true);
        this.setResizable(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //if the easy button is clicked
        if (e.getSource() == easyBtn) {
            //set the enemy speed to a low number
            enemySpeed = 1;
            
            //open the game object to start the game
            GameFrame gameObject;
            gameObject = new GameFrame();
            gameObject.setupFrame(enemySpeed);
            this.setVisible(false);
            
            
        }
        //if the medium button is clicked
        else if (e.getSource() == mediumBtn) {
            //set the enemy speed to a decent difficulty
            enemySpeed = 2;
            
            //open the game object to start the game
            GameFrame gameObject;
            gameObject = new GameFrame();
            gameObject.setupFrame(enemySpeed);
            this.setVisible(false);
            
        }
        //if the hard button is clicked
        else if (e.getSource() == hardBtn) {
            //set the enemy speed to very difficult
            enemySpeed = 3;
            
            //open the game object to start the game
            GameFrame gameObject;
            gameObject = new GameFrame();
            gameObject.setupFrame(enemySpeed);
            this.setVisible(false);
            
        }
    }
        
    
}
