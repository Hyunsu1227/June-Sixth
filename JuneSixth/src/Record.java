import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Record extends JFrame {
	public static final int WIDTH = 300;
	public static final int WEIGHT = 200;
	public static final int RECORD_NUM = 10;
	int[] record = new int[10]; // read 10 record

	public int i = 0;
	public int j = 0;

	public Record() {
		super("Record");
		setSize(WIDTH, WEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		// read record file
		try {
			Scanner Reading = new Scanner(new FileInputStream("src/record.txt"));

			for (i = 0; i < 10; i++) {
				record[i] = Reading.nextInt();
			}

			Reading.close();
		} catch (FileNotFoundException e) {
			System.exit(0);
		} catch (IOException e) {
			System.exit(0);
		}

		// sorting
		sorting(record);

		// title of record
		JLabel TextLabel = new JLabel("      RECORD");
		TextLabel.setFont(new Font("Serif", Font.BOLD, 19));
		add(TextLabel, BorderLayout.NORTH);

		// print record
		JPanel RecordPanel = new JPanel();
		RecordPanel.setLayout(new GridLayout(RECORD_NUM / 2, 2));
		JLabel[] scoreL = new JLabel[RECORD_NUM];
		for (i = 0; i < scoreL.length; i++)
			scoreL[i] = new JLabel(Integer.toString(i + 1) + ". " + Integer.toString(record[i]));
		for (i = 0; i < scoreL.length; i++)
			RecordPanel.add(scoreL[i]);
		add(RecordPanel, BorderLayout.CENTER);
	}

	// Sorting Method
	public void sorting(int arr[]) {
		int temp;
		for (i = 1; i < RECORD_NUM; i++) {
			for (j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public void writing() {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream("src/record.txt", false));
		} catch (FileNotFoundException e) {
			System.out.println("i can't find the file 'record.txt'");
			System.exit(0);
		}
		for (i = 0; i < RECORD_NUM; i++) {
			outputStream.println(record[i]);
		}
		outputStream.close();
	}
}
