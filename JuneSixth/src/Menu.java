import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame implements ActionListener {
	
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 400;
	private JButton start;
	private JButton exit;
	private JButton showRec;

	private JPanel buttonPan;

	public Menu() {
		super();
		setTitle("JuneSixth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(new BorderLayout());

		JTextField Title = new JTextField("  Enjoy JuneSixth");//ingame title
		Title.setFont(new Font("Serif", Font.PLAIN, 100));
		Title.setEditable(false);
		Title.setBackground(Color.white);

		add(Title, BorderLayout.NORTH);

		JPanel empty1 = new JPanel();//blank
		add(empty1, BorderLayout.EAST);

		JPanel empty2 = new JPanel();
		add(empty2, BorderLayout.WEST);//blank

		buttonPan = new JPanel(new GridLayout(3, 1));//sellect mode button

		start = new JButton("start");//start button
		start.addActionListener(this);
		buttonPan.add(start);
		showRec = new JButton("record");//show record button
		showRec.addActionListener(this);
		buttonPan.add(showRec);
		exit = new JButton("exit");//exit game button
		exit.addActionListener(this);
		buttonPan.add(exit);
		add(buttonPan, BorderLayout.CENTER);

	}

	//button action
	public void actionPerformed(ActionEvent e) {

		String actionCommand = e.getActionCommand();

		//open game window
		if (actionCommand.equals("start")) {
			GameScene Game = new GameScene();
			Game.setVisible(true);
			dispose();
		} 
		//open record window
		else if (actionCommand.equals("record")) {
			Record record = new Record();
			record.setVisible(true);
		}

		//close menu window
		else if (actionCommand.equals("exit")) {
			System.exit(0);
		} else {
			System.err.println("Error");
		}
	}

	

}
