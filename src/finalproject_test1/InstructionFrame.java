
//This class just brings forth the panel and frame for the instructions and gives the option to go back to the main menu

package finalproject_test1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionFrame extends JFrame implements ActionListener {
    
    //declare all variables
    JPanel panel = new JPanel();
    JLabel bg;
    JButton backBtn;
    
    public InstructionFrame() {
        super("Graceful Slasher - Help"); //set frame name 
    }
    
    public void setupFrame() {
        // is required to make sure that the program actually ends when the 'X' button is pressed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets the frame to be 300 pixels wide by 445 pixels high
        this.setBounds(0, 0, 300, 445);
        this.setResizable(false);

        // The background is set to white
        panel.setBackground(Color.white);

        //Setting the layout of the panel to null
        panel.setLayout(null);
        
        //place the back button the screen
        backBtn = new JButton(" ");
        backBtn.setBounds(83, 350, 110, 40);
        
        //found some code on how to make a button transparent
        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        panel.add(backBtn); //add to panel
        
        //add background
        ImageIcon icon = new ImageIcon("images/helpScreen2.png");
        bg = new JLabel(icon);
        bg.setBounds(-10, -20, 300, 445); //set spot where it looks right
        panel.add(bg);
        
        
        //implement action listener
        backBtn.addActionListener(this);
        
        // show the contents
        this.setContentPane(panel);
        // sets the frame to be visible
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //if the button is clicked:
        if (e.getSource() == backBtn) {
            //go back to menu panel
            
            
            this.hide();
            MenuFrame menu = new MenuFrame();
            menu.setupFrame();
            
        }
    }
}
