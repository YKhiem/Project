package FirstProject;
 
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
 
public class Test {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
 
    public Test(){
       prepareGUI();
    }
 
    public static void main(String[] args) {
        Test demo = new Test();
        demo.showActionListenerDemo();
    }
 
    private void prepareGUI() {
        mainFrame = new JFrame("Vi du Java Swing");
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
        mainFrame.setTitle("Ví dụ ActionListener trong Java Swing");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
 
    private void showActionListenerDemo() {
        headerLabel.setText("Listener in action: ActionListener");
        JPanel panel = new JPanel();
        panel.setBackground(Color.magenta);
 
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new CustomActionListener());
        panel.add(okButton);
        controlPanel.add(panel);
        mainFrame.setVisible(true);
    }
 
    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            statusLabel.setText("This button does nothing.");
        }
    }
}