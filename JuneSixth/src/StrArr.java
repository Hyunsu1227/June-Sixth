import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class StrArr {
	
	public String arr[] = new String[10];
	int i=0;
	public StrArr()
	{
		try
		{
			BufferedReader Reading = new BufferedReader(new FileReader("word.txt"));
	
			for(i = 0;i<10;i++)
				{
		
					arr[i] = Reading.readLine();
				}
	
				Reading.close();
		} 
		 catch(FileNotFoundException e)
	       {
	           System.out.println("I can't find the file 'word.txt'");
	           
	       }
	       catch(IOException e)
	       {
	           System.out.println("Error reading from word.txt.");
	       }

	}
	
	public String GetWord()
	{
		Random randomNumbers = new Random();
		int k= randomNumbers.nextInt(10);
		
		return arr[k];


	}
	
	public static void main(String args[])
	{
		
		StrArr a = new StrArr();
		String print = a.GetWord();
		System.out.println(print);
		
	}

}
