package Tayo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;


public class Command {
	
	public static char command = ' ';
	static String cmdLog = "";
	static boolean retracing = false;
	static boolean reversing = false;
	
	public static long currentTime;
	public static long end;
	
	public static void RunCommand(char cmd,String[] parts,boolean run) throws IOException {
		
			//Check which of the non movement commands has been input else send it to Navigate
			if(cmd == 'q' || cmd ==  'Q'){
				
				//If there is a space after q are there any other strings? If not then input is valid
				if(parts[1] != null) {
					
					Navigate.print("Invalid Input");
					return;
					
				}
				
				Navigate.print("Quiting");
				Navigate.quit();
				
			} else if(cmd == 't' || cmd ==  'T'){
				
				if(parts[1] == null || Integer.parseInt(parts[1]) > StringUtils.countMatches(cmdLog, "\n") ) {
					
					Navigate.print("Invalid Input");
					return;
					
				}
				
				Navigate.print("Retracing " +  parts[1] + " steps");
				
				retracing = true;
				
				cmdLog += "t " + parts[1] + "\n"; 

				
				String[] commands = cmdLog.split("\n");
				
				//play the instructions back to front
				for(int i = 1; i <= Integer.parseInt(parts[1]);i++) {
					
					String[] partsT = commands[Integer.parseInt(parts[1]) - i].split(" ");
					
					char cmdT = partsT[0].toCharArray()[0];
					int durT = Integer.parseInt(partsT[1]);
					int speedT = Integer.parseInt(partsT[2]);
					CommandInt(cmdT,durT,speedT);
					
				}
	
			} else if(cmd == 'v' || cmd ==  'V'){
				
				
				if(parts[1] == null || Integer.parseInt(parts[1]) > StringUtils.countMatches(cmdLog, "\n") ) {
					
					Navigate.print("Invalid Input");
					return;
					
				}
				
				char charToInverse = ' ';
				
				Navigate.print("Reversing " +  parts[1] + " steps");
				
				reversing = true;
				
				cmdLog += "v " + parts[1] + "\n"; 

				
				String[] commands = cmdLog.split("\n");
				
				//play the instructions back to front
				for(int i = 1; i <= Integer.parseInt(parts[1]);i++) {
					
					String[] partsV = commands[Integer.parseInt(parts[1]) - i].split(" ");
					
					//invert all the directions
					charToInverse = partsV[0].toCharArray()[0];
					
					//Forwards converst to backwards
					if(charToInverse == 'f' || charToInverse == 'F'){
						
						charToInverse = 'b';
						
					//Backwards converts to forwards
					} else if (charToInverse == 'b' || charToInverse ==  'B'){
						
						charToInverse = 'f';
						
					//Left converts to (I)nverse Left
					} else if (charToInverse == 'l' || charToInverse ==  'L'){
							
						charToInverse = 'i';
							
					//Right converts to i(N)verse
					} else if (charToInverse == 'r' || charToInverse == 'R'){
							
						charToInverse = 'n';

					//(I)nverse Left converts to left
					} else if (charToInverse == 'i'){
						
						charToInverse = 'l';
					//i(N)verse Right converts to right
					} else if (charToInverse == 'n'){
						
						charToInverse = 'r';
						
					}
					
					char cmdV = charToInverse;
					int durV = Integer.parseInt(partsV[1]);
					System.out.println(parts[1]);
					System.out.println(i);
					int speedV = Integer.parseInt(partsV[2]);
					CommandInt(cmdV,durV,speedV);
						
				}
					
					
			} else if(cmd == 'w' || cmd ==  'W'){
				
				Navigate.print("Writing to file 'Command Log.txt' ");
				PrintWriter writer = null;
				try {
					writer = new PrintWriter("Command Log.txt", "UTF-8");
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				writer.println(cmdLog);
				writer.close();
				
			} else if(cmd == 'p' || cmd == 'P'){
				
				Navigate.print(cmdLog);
				
			} else if(cmd == 'o' || cmd == 'O'){
				
				Navigate.print("Opening Command Log.txt");
				
				File file = new File("Command Log.txt");
				
				if (Desktop.isDesktopSupported()) {
				    Desktop.getDesktop().edit(file);
				} else {
				    Navigate.print("Opening file failed");
				}
		
			} else if(parts.length > 2){ 
				
				retracing = false;
				reversing = false;
				
				CommandInt(cmd,Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
		
			} else {
				
				Navigate.print("Invalid Input");
				
			}
		
	}
	
	static void CommandInt(char cmd,int dur,int speed) {

		boolean validInput = false;
		
		if(dur < 6001 && speed < 201) {
			
			validInput = true;
			
			if(!retracing || !reversing) {

				cmdLog += (cmd + " " + dur + " " + speed + "\n");
				
			}
			
		} else {
			
			validInput = false;
			Navigate.print("Invalid Input");
			
		}
		
		
		if(cmd == 'f' || cmd == 'b' || cmd == 'F' || cmd == 'B') {
			
			currentTime = System.currentTimeMillis();
			end = dur + currentTime;
			
		} else {
		
			currentTime = System.currentTimeMillis();
			end = Navigate.timeToTurn + dur + currentTime;
			
		}
		
		while(currentTime < end && validInput){
			
			Navigate.command(cmd,dur,speed);

			currentTime = System.currentTimeMillis();

		}
		
		Navigate.input = "";
		Navigate.finch.stopWheels();
		return;
		
	}
	

}
