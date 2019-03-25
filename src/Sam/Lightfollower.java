package Sam;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Lightfollower {
	
	Finch myFinch;
	int LIM;
	int[] sensors;
	int[] initialReadings;
	int maxReading;
	int minReading;
	long startTime;
	
	//Constructor
	public Lightfollower(Finch myFinch, int LIM){
		this.LIM = LIM;
		this.myFinch = myFinch;
		this.startTime = System.nanoTime(); //Starts recording runtime
		this.myFinch.setLED(Color.green); 
		this.myFinch.setWheelVelocities(50, 50, 2000);
	}
	
	//Function that starts the loop
	public void Start(){
		this.initialReadings = this.myFinch.getLightSensors();
		//Get initial minimum value because by default it is already 0
		this.minReading = Math.min(this.initialReadings[0], this.initialReadings[1]);
		
		//Check if the finch is initially on the floor, if not, buzz until it is
		if (!this.myFinch.isFinchLevel()) 
		{
			do {
				this.myFinch.buzz(100, 100);
			} while(!this.myFinch.isFinchLevel());
			
			this.myFinch.sleep(5000); // sleep for 3 seconds to allow the user to place on the floor
		}
		
		while(true)
		{
			this.sensors = this.myFinch.getLightSensors();
			if (!this.myFinch.isFinchLevel())
			{
				log_exe();
				break;
			}
			
			CompareAndMove();
			
		}
	}
	
	public void CompareAndMove(){
		int sensorsavg = (this.sensors[0] + this.sensors[1]) / 2; //Average, used as a variable for the intensity of the red colour
		
		//Sensor 0, Left
		if (this.sensors[0] > this.sensors[1] + this.LIM)
		{
			this.myFinch.setLED(sensorsavg, 0, 0); //Different red tone depending on the intensity.
			FinchUI.console.append("Going left\n\n");
			this.myFinch.setWheelVelocities(-150, 100, 1000);
			
		}
		//Sensor 1, Right
		else if (this.sensors[1] > this.sensors[0] + this.LIM)
		{
			this.myFinch.setLED(sensorsavg, 0, 0);
			FinchUI.console.append("Going right\n\n");
			this.myFinch.setWheelVelocities(150, -100, 1000);
			
		}
		//Otherwise just go straight
		else
		{
			this.myFinch.setLED(sensorsavg, 0, 0);
			FinchUI.console.append("Going straight\n\n");
		}
		
		//Check if the highest reading recorded in this iteration is bigger than the current maximum value
		if (Math.max(this.sensors[0], this.sensors[1]) > this.maxReading) {
			this.maxReading = Math.max(this.sensors[0], this.sensors[1]);
		}
		
		//Check if the lowest reading recorded in this iteration is less than the current minimum value
		if (Math.min(this.sensors[0], this.sensors[1]) < this.minReading) {
			this.minReading = Math.min(this.sensors[0], this.sensors[1]);
		}
		
		this.myFinch.setWheelVelocities(175, 175, 1000); //Going forward for all conditions.
	}
	
	
	//Function to print out the log of execution if the user wants it.
	public void log_exe(){
		int dialogResultLog = JOptionPane.showConfirmDialog(null, "Would you like the log of execution?","Information",JOptionPane.YES_NO_OPTION);
		
        if (dialogResultLog == JOptionPane.YES_OPTION)
        {
        	//Variable of the time elapsed
        	long timeElapsed = System.nanoTime() - startTime;
        	
        	//Create a big string in order to just print it out to the console and later, to a file.
        	String consoleOutput = "";
        	String newLine = System.getProperty("line.separator") + System.getProperty("line.separator");
        	        	
        	consoleOutput += "Showing the log of execution..." + newLine;
        	consoleOutput += "Initial readings: Left: "+ this.initialReadings[0] + " | Right: "+ this.initialReadings[1] + newLine;
        	consoleOutput += "Highest value recorded: " + this.maxReading +" (out of 255)" + newLine;
        	consoleOutput += "Lowest value recorded: "+ this.minReading +" (out of 255)" + newLine;
        	consoleOutput += "Length of the execution: "+ timeElapsed / 1000000000 +" seconds.";
        	
			
        	//Command that prints all the string into the console.
			FinchUI.console.setText(consoleOutput);
			
			//Dialog that asks if the user wants to export the log of execution.
			int dialogResultFile = JOptionPane.showConfirmDialog(null, "Would you like to export the log of execution into a file?","Information",JOptionPane.YES_NO_OPTION);
			if (dialogResultFile == JOptionPane.YES_OPTION) 
			{
				//Opens the file chooser where the user decides where to store the file.
				FileNameExtensionFilter ft = new FileNameExtensionFilter("Text files", "txt");
				JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				chooser.addChoosableFileFilter(ft);
				
				int returnVal = chooser.showSaveDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = chooser.getSelectedFile();
					String absPath = selectedFile.getAbsolutePath();
					
					//Makes sure the file ends with .txt, if not, it will add ".txt" into the end of the filename.
					if (!absPath.endsWith(".txt"))
					{
						absPath += ".txt";
					}
					
					//Tries to write to file. (If there are any errors it won't crash).
					try {
						PrintStream out = new PrintStream(new File(absPath));
						out.print(consoleOutput);
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
        } 
        //Else, just close program.
        else 
        {
            FinchUI.console.append("End of program");
        }
		this.myFinch.quit();
	}
}