import Tayo.*;
import Sam.*;
import Henry.*;

import java.util.Scanner;

public class MainClass {
	
	public static void main(String args[]) {
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.print("Tayo = 0" + '\n' +
		"Henry = 1" + '\n' +
		"Sam = 2" + '\n');
		
		int input = reader.nextInt();
		
		reader.close();
		
		switch(input) {
			
			case 0:
				
				Navigate.mainTay();
				break;
			case 1:
				
				Assignment_2_v01_1.mainHen();
				break;
				
			case 2:
				
				FinchUI.mainSam();
				break;
				
		}
		
	}

}
