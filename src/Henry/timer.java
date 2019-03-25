package Henry;

public class timer
{
	public static long start()
	{
		long startTime = System.nanoTime(); //Gets time at start of movement
		return startTime;
	}
	
	public static long end()
	{
		long endTime = System.nanoTime(); //Gets time at end of movement
		return endTime;
	}
	
	public static String timeTaken(long sT, long eT)
	{
		String otp = "";
		long timeDiff = eT - sT; //Calculates number of nanoseconds between start and end
		double secsDbl = timeDiff / 1000000000; //Converts nano seconds into seconds
		int secs = 0, mins = 0, hrs = 0, totSecs, totMins; //Sets default value for seconds, minutes and hours to 0
		
		totSecs = (int) Math.floor(secsDbl);
		
		if (totSecs / 60 <= 1) //Calculates if more than one minute passed between start and finish
		{
			totMins = totSecs / 60;
			secs = totSecs % 60;
			mins = totMins;
			
			if (totMins / 60 <= 1) //Calculates if more than one hour passed between start and finish
			{
				hrs = totMins / 60;
				mins = totMins % 60;
			}
		}
		else
		{
			secs = totSecs;
		}
		
		otp = hrs + " hours, " + mins + " minutes, " + secs + " seconds";
		
		return otp;
	}
}
