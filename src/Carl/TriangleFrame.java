package Carl;

import java.awt.BorderLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TriangleFrame extends JFrame {
	private tri_input triinput;
	
	public TriangleFrame(String title) {
		super(title);
		
		//Set Layout
		setLayout(new BorderLayout());
		triinput = new tri_input();
		
		
		
		//Create Components
		
			//Image 
			URL url = getClass().getResource("/Carl/Triangle.jpg");
			ImageIcon img = new ImageIcon(url);
			JLabel tri_img = new JLabel(img);
			
			//
		
		JLabel trititle = new JLabel("<html>Draw A Triangle<br> Instructions: Enter a value for the sides A, B, and C of a Triangle");
		trititle.setHorizontalAlignment(SwingConstants.CENTER);
		trititle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		JLabel closetomain = new JLabel("Close this window to go back to Main Menu");

		
		//Display components in content pane.
		Container triframe = getContentPane();
		
		triframe.add(trititle, BorderLayout.NORTH);
		triframe.add(triinput, BorderLayout.CENTER);
		triframe.add(tri_img, BorderLayout.WEST);
		triframe.add(closetomain, BorderLayout.SOUTH);		

		
		
		
		
		
		
		
}
}

