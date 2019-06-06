import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameScene extends JFrame {
		
	private JLabel Level;
	private JLabel blank1;
	private JLabel Score;
	private JLabel blank2;
	private JLabel Time;
	private JTextField input;
	private JLabel[] Word;
	
	public static void main(String args[]) {
		GameScene aaa = new GameScene();
		aaa.setVisible(true);
	}
	
	public GameScene() {
		super("JuneSixth GameScene");
		setSize(500,500);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel StatePanel = new JPanel(new GridLayout(1,5));
		Level = new JLabel("level");
		blank1 = new JLabel();
		Score = new JLabel("Score");
		blank2 = new JLabel();
		Time = new JLabel("Time");
		
		StatePanel.add(Score);StatePanel.add(blank1);StatePanel.add(Level);StatePanel.add(blank2);StatePanel.add(Time);
		add(StatePanel,BorderLayout.NORTH);
		
		
		JPanel InputPanel = new JPanel(new FlowLayout());
		input = new JTextField("",10);
		InputPanel.add(input);
		add(InputPanel,BorderLayout.SOUTH);
		
		
		JPanel WordPanel = new JPanel(new GridLayout(5,5));
		Word = new JLabel[25];
		for(int i=0;i<25;i++) {
			Word[i] = new JLabel("aaa");
			WordPanel.add(Word[i]);
		}
		
		
		add(WordPanel,BorderLayout.CENTER);
		
		
		/*JPanel NickName = new JPanel();
		add(NickName,BorderLayout.CENTER);*/
		
	}
}
