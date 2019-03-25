package Henry;

import java.util.Scanner;

import java.lang.Math;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Assignment_2_v01_1
{
	public static int getLength(Scanner userInpt)
	{
		boolean valInpt = false;
		String inptLen;
		int lnLen = 20; //Sets default value for input as 20
		
		while (valInpt == false)
		{
			System.out.println("Please input an integer value for the length of the lines (In cm, between 20 and 80cm) to be drawn by the Finch: ");//Input for length of lines
			try
			{
				inptLen = userInpt.nextLine(); //Attempts to read user input, but as a String
				lnLen = Integer.parseInt(inptLen); //Converts to Int if possible
				
				if (lnLen >= 20 && lnLen <= 80) //Checks to see if the valid input is within the range allowed
				{
					valInpt = true;
				}
				else
				{
					System.out.println("The value must be between 20 and 80.");
				}
			}
			catch (NumberFormatException exception) //Catches the error if the user inputs an invalid format (Not an Int)
			{
				System.out.println("The value entered must be an Integer value.");
				continue;
			}
		}
		return lnLen;
	}
	
	public static int getLines(Scanner userInpt)
	{
		boolean valInpt = false;
		String inptNum;
		int lnNum = 2; //Sets default value for input as 2
		
		while (valInpt == false)
		{
			System.out.println("Please input an integer value for the number of lines (Even numbers between 2 and 10) to be drawn by the Finch: "); //Input for number of lines
			try
			{
				inptNum = userInpt.nextLine(); //Attempts to read user input, but as a String
				lnNum = Integer.parseInt(inptNum); //Converts to Int if possible
				
				if (lnNum >= 2 && lnNum <= 10 && lnNum % 2 == 0) //Checks to see if the valid input is within the range allowed
				{
					valInpt = true;
				}
				else
				{
					System.out.println("The value must be even and between 2 and 10.");
				}
			}
			catch (NumberFormatException exception) //Catches the error if the user inputs an invalid format (Not an Int)
			{
				System.out.println("The value entered must be an Integer value.");
				continue;
			}
		}
		return lnNum;
	}
	
	public static void mainHen()
	{
		Scanner userInpt = new Scanner(System.in); //Creates scanner to read inputs
		int lnLen, lnNum, lenTrav, lenStr, travTime, movSpd;
		double rndSpd, travDbl, hlfLen, disDbl, valSpd, rt2 = Math.sqrt(2);
		long stTime, edTime;
		String tTaken;
		
		lnLen = getLength(userInpt);
		lnNum = getLines(userInpt);
		System.out.println("The length of lines to be drawn is " + lnLen + "cm.");
		System.out.println("The number of lines to be drawn is " + lnNum+ ".");
		
		Finch myFinch = new Finch();
		stTime = timer.start(); //Starts timer using the class "timer"
		rndSpd = 1 - Math.random();
		valSpd = Math.floor(rndSpd * 255); //Using rndSpeed creates the value for the random speed at which the Finch will travel
		movSpd = (int) Math.round(valSpd); //Converts the speed into an int to be usable by the Finch
		travDbl = 1000 * (lnLen / 20) * (255 / valSpd); //Calculates time it will take to complete distance based on known time to travel 20 cm (1sec), selected distance compared to 20cm and the randomised speed
		travTime = (int) (travDbl); //Converts travDbl to an int to be usable by the Finch
		
		fMove.drawOut(lnLen, lnNum, movSpd, travTime, myFinch); //Runs the code moving the Finch along the path chosen
		fMove.drawReturn(lnLen, lnNum, movSpd, travTime, myFinch); //Runs the code moving the Finch back to the start
		
		lenTrav = lnLen * lnNum; //Calcs length of path travelled
		hlfLen = lenTrav / 2;
		disDbl = rt2 * hlfLen; //Calcs distance between start and end of zig zag pattern
		lenStr = (int) (Math.round(disDbl)); //Converts to int for easier display
		edTime = timer.end(); //Stops timer
		tTaken = timer.timeTaken(stTime, edTime); //Tells the timer to calculate the time taken and return it as a String
		System.out.println("Time taken to draw zig zag: " + tTaken); //Prints time taken
		System.out.println("Distance travelled along zig zag: " + lenTrav + "cm."); //Prints length of zig zag
		System.out.println("Distance between ends of zig zag: " + lenStr + "cm."); //Prints distance from one end to the other of the zig zag
		
		myFinch.quit();
	}
}
