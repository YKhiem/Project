package FirstProject;
import java.awt.*;
import javax.swing.*;

public class Action extends Main{
       JFrame frame;
       JLabel Account, PassWord;
       JTextField acctf, passtf;
       JPanel  logpanel, procpanel;
       JButton Done, Cancel;
	public Action(String title) {
		super(title);
		frame = new JFrame("Login");
		Account = new JLabel("User");
		PassWord = new JLabel("PassWord");
		acctf = new JTextField(" ");
		passtf = new JTextField(" ");
		Done = new JButton("Confrim Login");
		Cancel = new JButton("Cancel Login");
		    logpanel = new JPanel();
		    procpanel = new JPanel();
		    logpanel.setLayout(new GridLayout(3,3));
		    logpanel.add(Account);
		    logpanel.add(PassWord);
		    logpanel.add(acctf);
		    logpanel.add(passtf);
		    procpanel.add(Done);
		    procpanel.add(Cancel);
		frame.add(logpanel,"North");
		frame.add(procpanel,"South");
		frame.setVisible(true);
		frame.setLocation(300,200);
		frame.setSize(300,150);
	}

	public static void main(String[] args) {
		new Action("Gui");

	}
}
