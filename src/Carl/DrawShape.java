package Carl;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane; 

public class DrawShape {
	//Initialize Finch
	private static Finch Drawer = null;

	public static void mainCar() {
		//Create a main menu frame.
				// Declare Finch
				Drawer = new Finch();
				Drawer.setLED(Color.red);
				
				// Create the Main Menu Frame
				if (Drawer.isFinchLevel() == true) {
					Drawer.setLED(Color.blue);
					JFrame frame = new MainFrame("Draw Shape");
					frame.setSize(540, 400);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else {
					DrawShape.infoBox("Please restart the program and lay the finch flat", "error");
				}
				
	 }
	
	// Draw Triangle Function
   public static void DrawTriangle(int a, int b, int c) {
		checkTriangleFormed(a, b, c);
			
		if (checkTriangleFormed(a,b,c) == true){	
			//Draw A Triangle
				Drawer.setLED(Color.YELLOW);
				//Draw side a
				Drawer.setWheelVelocities(50, 50, TimeToDrawSide(a));
				//Draw side b
				
				//Draw side c
			
		}
   }
   
   // Draw Rectangle Function
   public static void DrawRectangle(int h, int w) {
	   infoBox("Creating a rectangle using " + h + " as height, and" + w + "as width.", "Draw A Rectangle");
	   Drawer.setLED(Color.YELLOW);	   
	   //Draw A Rectangle
	   
	   	//Draw h1
	   	Drawer.setWheelVelocities(50, 50, TimeToDrawSide(h));
	   	
	   	//Draw w1
	   	//Draw w2
	   	//Draw w3
   }
   public static boolean CheckInput(int a) {
	   if (a >= 20 && a <=80) {
		   return true;
	   }
	   else {
		   return false;
	   }
	   
	   
   }

   //Check Triangle Formed
   public static boolean checkTriangleFormed(int a, int b, int c){

	   //check all combinations,
	   //sum of any 2 sides must be greater than the third side to form a triangle
	   if(check(a, b, c) && check(a, c, b) && check(b, c, a)) {
		   infoBox(("Creating a triangle using side " + a + ", " + b + ", " +c ), "Draw A Triangle");
   		return true;
   		
		   
	   }

    	else {
    		infoBox("Triangle cannot be formed using side " + a + ", " + b + ", " +c, "Error");
    		return false;
    	}
    	   
}
   public static double calcA(int a, int b, int c) {
	   double angleA = Math.acos(((b * b) + (c * c) - (a * a))/(-2*b*c)); 
	   return angleA;

   }
   public static double calcB(int a, int b, int c) {
	   double angleB = Math.acos(((c*c) + (a*a) - (b*b))/(-2*c*a));
	   return angleB;
   }
   
   public static double calcC(int a, int b, int c) {
	   double angleC = Math.acos(((b*b) + (a*a) - (c*c))/(-2*b*a));
	   return angleC;
   }
   

   public static boolean check(int a, int c, int b){
	   //check if sum of a  and c is greater than b
	   if((a+c)>b) {
        return true;
	   }
    
	   System.out.println("Sum of " + a + " and "+ c + " is not > " + b);
	   return false;
}
   public static void infoBox(String infoMessage, String titleBar)
   {
       JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
   }
	public static int TimeToDrawSide(int a) {
		//Works for both shapes, but only on a straight line.
		// Calculate the time needed to draw the length of one side of a triangle
		// At the speed of 50 on both wheels, it can draw 3cm/s
		double t = (a/(double)2.9) * 1000;
		int int_t = (int) t;
		return int_t;
	}

	// attempt to log on files
	public static void writeUsingFileWriter(String data) {
		File file = new File("/Users/krlth/eclipse-workspace/Finch_DrawShape/Record.txt");
		FileWriter fr = null;
	    try {
	        fr = new FileWriter(file);
	        fr.append(data);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        //close resources
	        try {
	            fr.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

	}






