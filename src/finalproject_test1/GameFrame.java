//Darshan Shah

//This class creates the frame for the actual gameplay, then implements the player panel
//It only sets the name of the frame

package finalproject_test1;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public void setupFrame(int enemySpeed) {
        //Creates a new JFrame object with title
        JFrame frame = new JFrame("Graceful Slasher");

        frame.getContentPane().add(new PlayerPanel(frame, enemySpeed));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        
        //sets the JFrame object so you cannot resize while it is running
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
