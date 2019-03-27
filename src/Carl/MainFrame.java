package Carl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;



public class MainFrame extends JFrame {
	
	//Create instance variable for a jpanel
	private main_input optioninput;
	
	
	public MainFrame(String title) {
		super(title);

		//Set layout
		setLayout(new BorderLayout());
		optioninput = new main_input();
		
		//Create Swing components
		
		JLabel maintitle = new JLabel("<html>Draw A Shape<br>  Menu:<br> T = Draw A Triangle<br> R = Draw A Rectangle  <br> Q = Quit");
		//modify maintitle
		maintitle.setHorizontalAlignment(SwingConstants.LEFT);
		maintitle.setFont(new Font("Tahoma", Font.PLAIN, 24));


		//Add swing components to content pane.
		Container mainmenu =  getContentPane();
		 
		mainmenu.add(maintitle, BorderLayout.NORTH);
		//make the center of the border layout a gridbag layout.
		mainmenu.add(optioninput, BorderLayout.CENTER);
		
	}

}

    