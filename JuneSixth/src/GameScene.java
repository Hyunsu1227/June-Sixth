import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameScene extends JFrame {
		
	public static void main(String args[]) {
		GameScene aaa = new GameScene();
		aaa.setVisible(true);
	}
	
	public GameScene() {
		super("JuneSixth GameScene");
		setSize(500,500);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel qqq = new JPanel(new GridLayout(1,5));
		JLabel Level = new JLabel("level");
		JLabel blank1 = new JLabel();
		JLabel Score = new JLabel("Score");
		JLabel blank2 = new JLabel();
		JLabel Time = new JLabel("Time");
		
		qqq.add(Score);qqq.add(blank1);qqq.add(Level);qqq.add(blank2);qqq.add(Time);
		add(qqq,BorderLayout.NORTH);
		
		
		JPanel kkk = new JPanel(new FlowLayout());
		JTextField input = new JTextField("",10);
		kkk.add(input);
		add(kkk,BorderLayout.SOUTH);
		
		
		JPanel ttt = new JPanel(new GridLayout(5,5));
		
		
		
		add(ttt,BorderLayout.CENTER);
		
		
		/*JPanel NickName = new JPanel();
		add(NickName,BorderLayout.CENTER);*/
		
	}
}
