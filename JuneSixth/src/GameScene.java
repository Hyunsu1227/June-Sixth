import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
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
	private int my_level;
	
	private JLabel Score;
	private JTextField scorefield;
	private int my_score;

	private JLabel Time;
	private JTextField timefield;
	private int my_time;//time class value
	private JTextField input;
	
	private JLabel[] Word;
	private int[] rand_int;	//random number save
	
	public GameScene() {
		super("JuneSixth GameScene");
	
		setVisible(true);
		setSize(Menu.WINDOW_WIDTH,Menu.WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				GameStart gamestart = new GameStart();
				gamestart.start();
			}
		});
		
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
	
	//enter event
	public void keyPressed(KeyEvent e) {
		//if press Enter key
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			// input text equals?
			int i;
			for(i =0; i<25; i++) {
				if(Word[i].isShowing() && input.getText().equals(Word[i].getText())){
					Word[i].setVisible(false); return;
				}
			}
			if(i==25) {
				//if no text, given time penalty
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
		private int last_index = 0; // rand_int of last index
		
		public GameRun() { // initiate time 60 seconds
			my_time = 60;
			rand_int = new int[25]; 
		}
		public void run() {
			int game_speed = 15, cnt =0;
			while(my_time > 0) {
				timefield.setText(Integer.toString(my_time)); // show time
				try {
					for(int i=0;i<10;i++) {
						Thread.sleep(100);
						cnt++;
						if(game_speed == cnt) { // per 1.5 second create word;
							System.out.println(random_index());
							//Word[random_index()].setText(getWord());
							cnt = 0;
						}
					}
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				my_time--;
			}
			this.interrupt();// game end
		}
		
		private int random_index() { // create 0~25 random index 
			int random = (int)(Math.random()*25) + 1;
			
			for(int i=0 ; i<last_index ; i++) { // until random is not on rand_int[], run
				if(rand_int[i] == random) {
					random = (int)(Math.random()*25);
					i = 0;
				}
			}
			rand_int[last_index] = random; // random put on rand_int
			last_index++;
			
			return random;
		}
	}

}
