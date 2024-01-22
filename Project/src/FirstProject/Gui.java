package FirstProject;

import java.awt.*;

import javax.swing.WindowConstants;
public class Gui extends Main{
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	public Gui(String title) {
		super(title);
		b1=new Button("North");
		b2=new Button("South");
		b3=new Button("East");
		b4=new Button("West");
		b5=new Button("Center");
		setLayout(new BorderLayout(50,10));
		add(b1,"North");
		add(b2,"South");
		add(b3,"East");
		add(b4,"West");
		add(b5,"Center");
		setVisible(true);
		setLocation(300,200);
		setSize(300,200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void setDefaultCloseOperation(int exitOnClose) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		new Gui("EP");
	}

}
