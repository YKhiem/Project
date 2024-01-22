package WM;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
	private JFrame frame;

	public Main (String title) {
		super();
		JButton logbutton = new JButton("Login");
		JButton regbutton = new JButton("Register"); 
		frame = new JFrame("Electric tax");
		frame.add(logbutton);
		frame.add(regbutton);
		frame.setLocation(300,200);
		frame.setSize(400, 300);
		frame.setLayout(new GridLayout(3, 3));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
//Add listener
	
	logbutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            logActionPerformed(e);
        }
    });

	regbutton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            regActionPerformed(e);
        }
    });
	}
 //Add performed
	 private void regActionPerformed(ActionEvent evt) {
		 new RegisterForm("");
		 frame.dispose();
	 }
	 
	 
		 private void logActionPerformed(ActionEvent evt) {
			 new LoginForm("");
			 frame.dispose();
		 } 
	public static void main(String[] args) {
    new Main("Main");
	}
}
//Login Perform


