import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameOver extends JFrame implements ActionListener {
	private boolean over;// state gaming or over
	private int EndScore;

	public GameOver(int SCORE) {
		super("JuneSixth GameScene");// test area
		setSize(500, 500);

		JPanel BackGroundPanel = new JPanel();// include all

		JPanel OptionPanel = new JPanel();// Show score, select restart, select stop
		OptionPanel.setLayout(new GridLayout(3, 1));

		JTextField OverText = new JTextField("Game Over"); // Show game over
		OverText.setFont(new Font("Serif", Font.PLAIN, 60));
		OverText.setEditable(false);
		OverText.setBackground(Color.red);
		BackGroundPanel.add(OverText, BorderLayout.NORTH);

		JTextField ScoreText = new JTextField();// show my score and max score
		String MaxScore = "no score";

		Record BeforePlay = new Record();
		if (BeforePlay.record[9] < SCORE) {// get max record
			BeforePlay.record[9] = SCORE;
			BeforePlay.sorting(BeforePlay.record);
			BeforePlay.writing();// replace record.txt
		}
		ScoreText.setText("   max record :  " + BeforePlay.record[0] + " my record : " + SCORE + "  ");
		// GameScene.setscore();//score set 0
		ScoreText.setFont(new Font("Serif", Font.PLAIN, 30));
		ScoreText.setBackground(Color.orange);
		OptionPanel.add(ScoreText);

		JButton RestartButton = new JButton("restart");// game restart
		RestartButton.setFont(new Font("Serif", Font.PLAIN, 60));
		RestartButton.addActionListener(this);
		OptionPanel.add(RestartButton);

		JButton dddd = new JButton("stop game");// go to menu
		dddd.setFont(new Font("Serif", Font.PLAIN, 60));
		dddd.addActionListener(this);
		OptionPanel.add(dddd);

		BackGroundPanel.add(OptionPanel, BorderLayout.CENTER);
		add(BackGroundPanel, BorderLayout.CENTER);
		over = true;
		OptionPanel.setVisible(over);
	}

	public void setOver(boolean other) {
		over = other;
	}

	public void setEndScore(int score) {
		EndScore = score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("restart")) {// restart game
			GameScene Game = new GameScene();
			Game.setVisible(true);
			dispose();
		} else if (e.getActionCommand().equals("stop game")) {// go to menu
			Menu m = new Menu();
			m.setVisible(true);
			dispose();
		}
	}

}
