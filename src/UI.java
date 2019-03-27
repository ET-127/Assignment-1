import Tayo.*;
import Sam.*;
import Henry.*;
import Jack.*;
import Carl.*;
import javax.swing.JOptionPane;

public class UI 
{

	public static void main(String args[]) 
	{
		String s = "";
		s = Menu();
		if (s.equals("Tayo")) Navigate.mainTay();;
		if (s.equals("Henry")) Assignment_2_v01_1.mainHen();
		if (s.equals("Sam")) FinchUI.mainSam();
		if (s.equals("Jack")) Main_Class.mainJac();
		if (s.equals("Carl")) DrawShape.mainCar();
		
	}
	
	private static String Menu()
	{
		Object[] possibilities = {"Tayo","Henry","Sam","Jack","Carl"};
		String s = (String)JOptionPane.showInputDialog(null,"Choose a project:\n\n","Red 10 Assignment 1",JOptionPane.PLAIN_MESSAGE, null,possibilities,"Tayo");
		return(s);
	}

	
}