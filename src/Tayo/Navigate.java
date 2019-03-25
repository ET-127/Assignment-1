package Tayo;
import javax.swing.*;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Navigate {
	
	static Finch finch = new Finch();
	
	static boolean run = true;
	
	static int timeToTurn = 1000;
	
	public static Command CommandControl;
	public static String input = "";
	
	public static void mainTay() {
		
		print("Starting");
		
		
		if(finch != null) {
			
			print("Finch online");
			
		}
		
		JFrame frame = new MainFrame("Navigate");	
		frame.setSize(1250, 500);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		return;
		
		
	}
	
	//Quit Function
	public static void quit() {
		
		System.exit(0);
		
	}
	
	//Print to console and UI
	public static void print(Object o) {
		
		System.out.println(o);
		MainFrame.UpdateOuput(o);
		return;
		
	}
	
	//Send a movement command to the finch
	static void command(char cmd,int dur,int speed) {
			
			if(cmd == 'f' || cmd == 'F'){
				
				print("Moving forwards at speed " + speed + " for " + dur + " milliseconds");
				finch.setWheelVelocities(speed, speed, dur);
				return;
				
			} else if (cmd == 'b' || cmd ==  'B'){
				
				print("Moving backwards at speed " + speed + " for " + dur + " milliseconds");
				finch.setWheelVelocities(-speed, -speed,dur);
				return;
				
			} else if (cmd == 'l' || cmd ==  'L'){
				
				print("Turning left");
				finch.setWheelVelocities(0,133,1000);
				command('f',dur,speed);
				return;
				
			} else if (cmd == 'r' || cmd == 'R'){
				
				print("Turning right");
				finch.setWheelVelocities(133,0,1000);
				command('f',dur,speed);
				return;

			} else if (cmd == 'i'){
				
				command('b',dur,speed);
				print("Turning right");
				finch.setWheelVelocities(133,0,1000);
				return;

			} else if (cmd == 'n'){
				
				command('b',dur,speed);
				print("Turning left");
				finch.setWheelVelocities(-133,0,1000);
				return;

			} else {
				
				print("Error");
				
			}
		
		
	}

}
