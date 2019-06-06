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
	
	
	
	public GameScene() {
		super("JuneSixth GameScene");
		setSize(500,500);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel StatePanel = new JPanel(new GridLayout(1,5));//Panel of (level,score,time)
		Level = new JLabel("level");//label of stage level
		blank1 = new JLabel();
		Score = new JLabel("Score");//label of score
		blank2 = new JLabel();
		Time = new JLabel("Time");//label of time right now
		
		StatePanel.add(Score);StatePanel.add(blank1);StatePanel.add(Level);StatePanel.add(blank2);StatePanel.add(Time);
		add(StatePanel,BorderLayout.NORTH);
		
		
		JPanel InputPanel = new JPanel(new FlowLayout());//panel of input text
		input = new JTextField("",10);
		InputPanel.add(input);
		add(InputPanel,BorderLayout.SOUTH);
		
		
		JPanel WordPanel = new JPanel(new GridLayout(5,5));//panel of random text
		Word = new JLabel[25];//array of random text
		for(int i=0;i<25;i++) {
			Word[i] = new JLabel("aaa");
			WordPanel.add(Word[i]);
		}
		
		
		add(WordPanel,BorderLayout.CENTER);
		
		
		/*JPanel NickName = new JPanel();
		add(NickName,BorderLayout.CENTER);*/
		
	}
}
