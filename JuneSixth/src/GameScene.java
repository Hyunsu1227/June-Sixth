import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameScene extends JFrame implements KeyListener{
	public static final int NUMBER_OF_CHAR=7;//FIELD SIZE
	
	private JLabel Level;
	private JTextField levelfield;
	private int my_level=1;
	
	private JLabel Score;
	private JTextField scorefield;
	private static int my_score;

	private JLabel Time;
	private JTextField timefield;
	private int my_time=5;//time class value
	private JTextField input;
	
	private JLabel[] Word;
	private int[] rand_int;	//random number save

	public GameScene() {
		super("JuneSixth GameScene");
		setSize(Menu.WINDOW_WIDTH,Menu.WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new CheckOnExit());
		
		JPanel StatePanel = new JPanel(new GridLayout(1,5));//Panel of (level,score,time)
		Level = new JLabel("level");//label of stage level
		Level.setFont(new Font("Serif", Font.BOLD, 20));
		levelfield = new JTextField(NUMBER_OF_CHAR);
		levelfield.setText(Integer.toString(my_level));
		levelfield.setEditable(false);
		JLabel blank1 = new JLabel();
		Score = new JLabel("Score");//label of score
		Score.setFont(new Font("Serif", Font.BOLD, 20));
		scorefield = new JTextField(NUMBER_OF_CHAR);
		scorefield.setText(Integer.toString(my_score));
		scorefield.setEditable(false);
		JLabel blank2 = new JLabel();
		Time = new JLabel("Time");//label of time right now
		Time.setFont(new Font("Serif", Font.BOLD, 20));
		timefield = new JTextField(NUMBER_OF_CHAR);
		timefield.setText(Integer.toString(my_time));
		timefield.setEditable(false);
		StatePanel.add(Score);StatePanel.add(scorefield);StatePanel.add(blank1);
		StatePanel.add(Level);StatePanel.add(levelfield);StatePanel.add(blank2);
		StatePanel.add(Time);StatePanel.add(timefield);
		add(StatePanel,BorderLayout.NORTH);
		
		JPanel InputPanel = new JPanel(new FlowLayout());//panel of input text
		input = new JTextField("",10);
		input.addKeyListener(this);
		InputPanel.add(input);
		add(InputPanel,BorderLayout.SOUTH);
		
		JPanel WordPanel = new JPanel(new GridLayout(5,5));//panel of random text
		Word = new JLabel[25];//array of random text
		for(int i=0;i<25;i++) {
			Word[i] = new JLabel("aaa");
			WordPanel.add(Word[i]);
		}
		Word[20].setText("aab");
		
		add(WordPanel,BorderLayout.CENTER);
		
		
		/*JPanel NickName = new JPanel();
		add(NickName,BorderLayout.CENTER);*/
		
	}
	
	//get score - when GameOver print score and score to 0
	public static int getscore() {
		return my_score;
	}
	
	public static void setscore() {
		my_score = 0;
	}
	
	//enter event
	public void keyPressed(KeyEvent e) {
		//if press Enter key
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			// input text equals?
			int i;
			for(i =0; i<25; i++) {
				if(Word[i].isShowing() && input.getText().equals(Word[i].getText())){
					Word[i].setVisible(false); 
					Word[i].setText("");
					
					//score, level update
					my_score +=10; //score+10
					if(my_level != my_score/100+1) { // level+1 per score 100
						my_level = my_score/100+1;
						//level up!
						my_time+=1;
					}
					scorefield.setText(Integer.toString(my_score));
					levelfield.setText(Integer.toString(my_level));
					timefield.setText(Integer.toString(my_time));
					
					return;
				}
			}
			if(i==25) {
				//if no text, given time penalty
				my_time-=1;
				//game over
				if(my_time==0) {
					GameOver gui = new GameOver();
					gui.setVisible(true);
					dispose();
				}
				timefield.setText(Integer.toString(my_time));
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private class CheckOnExit implements WindowListener{
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			Menu gui = new Menu();
			gui.setVisible(true);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
