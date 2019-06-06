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

public class Menu extends JFrame implements ActionListener{
	

	private JButton start;
	private JButton exit;
	private JButton showRec;
	
	private JPanel buttonPan;
	
	
	public Menu()
	{
		super();
		setTitle("뭐라고 하지");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 400);
		 setLayout(new BorderLayout( ));
		 
		 JTextField Title = new JTextField("Game Title");
		 Title.setFont(new Font("Serif", Font.PLAIN, 100));
		 Title.setEditable(false);
		 Title.setBackground(Color.white);
		 
		 add(Title,BorderLayout.NORTH);
		 
		 JPanel empty1 = new JPanel();
		 add(empty1,BorderLayout.EAST);
		 
		 JPanel empty2 = new JPanel();
		 add(empty2,BorderLayout.WEST);
		 
		 buttonPan = new JPanel(new GridLayout(3,1));
		 
		 start = new JButton("start");
		 start.addActionListener(this);
		 buttonPan.add(start);
		 showRec = new JButton("record");
		 showRec.addActionListener(this);
		 buttonPan.add(showRec);
		 exit = new JButton("exit");
		 exit.addActionListener(this);
		 buttonPan.add(exit);
		 add(buttonPan,BorderLayout.CENTER);

		
		
	}
	
	public void actionPerformed(ActionEvent e)
    {
       
		  String actionCommand = e.getActionCommand( );
		  
		  if(actionCommand.equals("start"))
		  {
			  
		  }
		  else if(actionCommand.equals("record"))
		  {
			  
		  }
		  
		  else if(actionCommand.equals("exit"))
		  {
			  System.exit(0);
		  }
		  else
		  {
			  System.err.println("Error");
		  }
    }
	
	public static void main(String args[])
	{
		Menu m = new Menu();
		m.setVisible(true);
	}

	
}
