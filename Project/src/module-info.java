package FirstProject;

import java.awt.*;
public class Gui extends Frame{
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
		add(b1,"North");
		add(b2,"South");
		add(b3,"East");
		add(b4,"West");
		add(b5,"Center");
		setVisible(true);
		setLocation(300,200);
		setSize(300,200);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       new Gui("BorderLayout");
	}

}