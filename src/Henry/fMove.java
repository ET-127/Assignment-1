package Henry;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class fMove
{
	public static void drawOut(int lnLen, int lnNum, int valSpd, int travTime, Finch myFinch)
	{
		int i = 0, rtTime = 750;
		
		for (i = 0;i<lnNum;i++)
		{
			if (i % 2 == 0) //Decides which colour the Finch's nose should be based on line number
			{
				myFinch.setLED(0, 255, 0); //Sets LED on Finch to Green
				//myFinch.playtone
			}
			else if (i % 2 == 1)
			{
				myFinch.setLED(255, 0, 0); //Sets LED on Finch to Red
				//myFinch.playtone
			}
			
			myFinch.setWheelVelocities(valSpd, valSpd, (travTime)); //Moves the Finch forwards the desired amount
			
			if (i % 2 == 0) //Decides which direction the Finch should turn based on line number
			{
				myFinch.setWheelVelocities(100, -100, rtTime);
			}
			else if (i % 2 == 1)
			{
				myFinch.setWheelVelocities(-100, 100, rtTime);
			}
		}
		
		myFinch.setWheelVelocities(-100, 100, rtTime);
	}
	
	public static void drawReturn(int lnLen, int lnNum, int valSpd, int travTime, Finch myFinch)
	{
		int i = 0, rtTime = 750;
		
		for (i = 0;i<lnNum;i++)
		{
			if (i % 2 == 0) //Decides which colour the Finch's nose should be based on line number
			{
				myFinch.setLED(0, 255, 0); //Sets LED on Finch to Green
				//myFinch.playtone
			}
			else if (i % 2 == 1)
			{
				myFinch.setLED(255, 0, 0); //Sets LED on Finch to Red
				//myFinch.playtone
			}
			
			myFinch.setWheelVelocities(valSpd, valSpd, (travTime)); //Moves the Finch forwards the desired amount
			
			if (i % 2 == 1) //Decides which direction the Finch should turn based on line number
			{
				myFinch.setWheelVelocities(100, -100, rtTime);
			}
			else if (i % 2 == 0)
			{
				myFinch.setWheelVelocities(-100, 100, rtTime);
			}
		}
		myFinch.setWheelVelocities(100, -100, rtTime);
	}
}
