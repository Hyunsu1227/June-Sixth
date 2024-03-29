import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;

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
	private int my_score;

	private JLabel Time;
	private JTextField timefield;
	private int my_time;//time class value
	private JTextField input;
	
	private JLabel[] Word;
	private ArrayList<Integer> rand_int = new ArrayList<Integer>();	
	private Iterator<Integer> rand = rand_int.iterator();
	private StrArr arr = new StrArr();
	private int game_speed;
	
	public GameScene() {
		super("JuneSixth GameScene");
	
		setVisible(true);
		setSize(Menu.WINDOW_WIDTH,Menu.WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowListen());
		
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
			Word[i] = new JLabel("");
			WordPanel.add(Word[i]);
		}
		
		add(WordPanel,BorderLayout.CENTER);
		
	}
	
	//get score - when GameOver print score and score to 0
	public int getscore() {
		return my_score;
	}
	
	public void setscore() {
		my_score = 0;
	}
	
	//enter event
	public void keyPressed(KeyEvent e) {
		//if press Enter key
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			// input text equals?
			int i;
			for(i =0; i<25; i++) {
				if(Word[i].isShowing() && Word[i].getText()!="" &&input.getText().equals(Word[i].getText())){
					Word[i].setText("");
					input.setText("");
					//score, level update
					my_score +=10; //score+10
					if(my_level != my_score/100+1) { // level+1 per score 100
						my_level = my_score/100+1;
						//level up!
						my_time+=20;
						game_speed--;
					}
					
					rand = rand_int.iterator();
					while(rand.hasNext()) {
						if(rand.next() == i) {
							rand.remove();
						}
					}
					
					scorefield.setText(Integer.toString(my_score));
					levelfield.setText(Integer.toString(my_level));
					timefield.setText(Integer.toString(my_time));
					
					break;
				}
			}
			if(i==25) {
				input.setText("");
				//if no text, given time penalty
				my_time-=5;
				timefield.setText(Integer.toString(my_time));
			}
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public class GameStart extends Thread { // game start
		
		public GameStart() {// after 3 second, game start
			my_time = 3;
			my_level = 1;
			my_score = 0;
			levelfield.setText(Integer.toString(my_level)); //initiate level
			scorefield.setText(Integer.toString(my_score)); //initiate score
		}
		
		public void run() {
			while(my_time >= 0) {
				if(my_time>0) {
					input.setEditable(false);//can't input 3,2,1
					timefield.setText(Integer.toString(my_time)); // 3,2,1
				}
				else {
					timefield.setText("Start!");
				}
				
				try {
					Thread.sleep(1000);// 1 second 
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				my_time--;
			}
			
			this.interrupt();// count end
			GameRun gamerun = new GameRun(); // game run
			gamerun.start();
		}
	}
	
	private class GameRun extends Thread {// game run
		
		public GameRun() { // initiate time 60 seconds
			my_time = 60;
			rand_int = new ArrayList<Integer>();
			input.setEditable(true);
			game_speed = 15;
		}
		public void run() {
			int cnt =0;
			while(my_time >= 0) {
				timefield.setText(Integer.toString(my_time)); // show time
				try {
					for(int i=0;i<10;i++) {
						Thread.sleep(100);
						cnt++;
						
						if(game_speed == cnt) { // per 1.5 second create word;
							
							if(rand_int.size() == 25) { //if label is full of word, the game is end
								my_time = 0;
								break;
							}
							int temp = random_index();
							Word[temp].setText(arr.GetWord());
							cnt = 0;
						}
						
					}
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				my_time--;
			}
			//game over
			GameOver gui = new GameOver(my_score);
			//gui.setEndScore(my_score);//send my score to GameOver class// why no change???
			gui.setVisible(true);
			dispose();
			this.interrupt();// game end
		}
		
		private int random_index() { // create 0~25 random index 
			int random = (int)(Math.random()*25);
			rand = rand_int.iterator();
			
			while(rand.hasNext() && rand_int.size() <= 24) {
				if(rand.next() == random) {
					random = (int)(Math.random()*25);
					rand = rand_int.iterator();
				}
			}
			rand_int.add(random); // add random on rand_int
			
			return random;
		}
	}
	
	private class WindowListen implements WindowListener{
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			GameStart gamestart = new GameStart();
			gamestart.start();
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
