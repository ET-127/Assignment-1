package Jack;

import java.util.*;
import java.util.concurrent.TimeUnit;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import javax.swing.JOptionPane;


public class Main_Class {

	static Finch tFinch = new Finch();

	public static void mainJac(){		
	//Main program		
		
		boolean PlayAgain = true;
		
		while (PlayAgain == true) {
			
			try {
				MoveSeqExecution(TiltRecord(GetTime()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int response = JOptionPane.showConfirmDialog(null, "Play again? ");
			
			if (response == JOptionPane.NO_OPTION) {
				
				PlayAgain = false;
			}
		}
		
	}
		
	
	static double GetTime() {
	//This method returns a double value that will be used for the amount of time that a finch records for
		
		boolean passed = false;
		double TimeCount = 0;
		
		while (passed == false) {
			
			System.out.println("Please enter an int between 1 and 20");		
			
			String UserIn = JOptionPane.showInputDialog("Enter integer between 1 and 20: ");
			try {
				
				int uTime = Integer.parseInt(UserIn);                    
				
				if((uTime >=1 && uTime <= 20) && (uTime % 1 == 0)) {
					
					System.out.println("Sucsessful input!");			
					TimeCount = uTime;
					passed = true;
				}
				else {		
					JOptionPane.showMessageDialog(null, "Error: Number must be within the correct range.");
				}
			} 
			catch (Exception e) {
				
				System.out.println("Error: Number must be interger");
				JOptionPane.showMessageDialog(null, "Error: Value must be a number.");
				
			}	
		}		
		
		return TimeCount;
	}

	static int[] TiltRecord (double x) throws InterruptedException {
	//Tilt recording method, returns an array which contains the tilt directions in the form of numbers from 0-4			
		
		tFinch.setLED(255, 000, 000);  //LED set to red to indicate that it is recording
		tFinch.buzz(500, 500);	//buzzer added to indicate recording period
		
		
		int FinchSq[] = new int[(int) (x*2)]; 
		//Initiates array, the size of it depends on the passed on variable from when the method is called
		//For example: a TimeCount value of 5 would be equivalent to 10 recorded inputs due to an input being recorded
		//every 0.5 seconds
		
		for (int i=0; x!=0; i++) {
			
			if (tFinch.isBeakUp() == true) {		//Beak up = Move forward
				
				FinchSq[i] = 0;						//Add 0 to current array index
				x -= 0.5;							//Deducts 0.5 seconds
				System.out.println(FinchSq[i]); 	//Print current position
				System.out.println("[" + i + "]");
				TimeUnit.MILLISECONDS.sleep(500);	//Wait 500 milliseconds 
			}
			
			else if (tFinch.isBeakDown() == true) {		//Beak down = Move backward
				
				FinchSq[i] = 1;
				x -= 0.5;
				System.out.println(FinchSq[i]);
				System.out.println("[" + i + "]");
				TimeUnit.MILLISECONDS.sleep(500);
			}
			
			else if (tFinch.isLeftWingDown() == true) {		//Left wing down = Turn left
				
				FinchSq[i] = 2;
				x -= 0.5;
				System.out.println(FinchSq[i]);
				System.out.println("[" + i + "]");
				TimeUnit.MILLISECONDS.sleep(500);
			}
			
			else if (tFinch.isRightWingDown() == true) {	//Right wing down = Turn Right
				
				FinchSq[i] = 3;
				x -= 0.5;
				System.out.println(FinchSq[i]);
				System.out.println("[" + i + "]");
				TimeUnit.MILLISECONDS.sleep(500);
			}
			
			else if (tFinch.isFinchLevel() == true) {	//Finch level = No movement
				
				FinchSq[i] = 4;
				x -= 0.5;
				System.out.println(FinchSq[i]);
				System.out.println("[" + i + "]");
				TimeUnit.MILLISECONDS.sleep(500);
			}
			
			else {	
				
				FinchSq[i] = 4;
				x -= 0.5;							//This 'if' statement is to assign a tilt position if the finch cannot detect any tilts
				System.out.println(FinchSq[i]);     //If no other tilts are found, 4 is assigned to indicate that the finch is level
				System.out.println("[" + i + "]");
				TimeUnit.MILLISECONDS.sleep(500);
			}
		}
		
		tFinch.setLED(000, 000, 000);
		tFinch.buzz(700, 500);
		
		TimeUnit.MILLISECONDS.sleep(200);
		
		System.out.println(Arrays.toString(FinchSq));
		
		return FinchSq;
	}
	
	static void MoveSeqExecution(int x[]) throws InterruptedException {
		
		tFinch.setLED(255, 255, 0);  //Added a yellow LED indicator to show that the finch is about to execute a tilt sequence
		TimeUnit.MILLISECONDS.sleep(2000);  //Wait for 2 seconds
		tFinch.setLED(0, 255, 0);
		
		for (int i=0; i<x.length; i++) {
			
			if (x[i] == 0) {	//if statement for moving forward
				int randNo = RandoNum();
				tFinch.setWheelVelocities( randNo, randNo, 500);  //Duration is set to 500 milliseconds
				System.out.println("Moving forward!");
			}
			
			if (x[i] == 1) {
				int randNo = RandoNum();
				tFinch.setWheelVelocities( (randNo * -1), (randNo * -1), 500);
				System.out.println("Moving backward!");
			}
			
			if (x[i] == 2) {
				tFinch.setWheelVelocities( 0, RandoNum(), 500);
				System.out.println("Turning left!");
			}
			
			if (x[i] == 3) {
				tFinch.setWheelVelocities( RandoNum(), 0, 500);
				System.out.println("Turning right!");
			}
			
			if (x[i] == 4) {
				tFinch.setWheelVelocities( 0, 0, 500);
				System.out.println("Not moving!");
			}
		}
		
		tFinch.setLED(0,0,0);
		
		tFinch.buzz(700, 500);
		TimeUnit.MILLISECONDS.sleep(500);
		tFinch.buzz(900, 500);
	}
		
	static int RandoNum() {  //Random number generator 
		
		Random rnd = new Random(); 

		int n = rnd.nextInt(256);	//Generates a number between 0 and 255, these are the minimum and maximum values for wheel velocity
		
		return n;
	}

}
