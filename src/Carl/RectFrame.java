package Carl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RectFrame extends JFrame {
	private rect_input recttinput;
	
	public RectFrame(String title) {
		super(title);
		
		setLayout(new BorderLayout());
		recttinput = new rect_input();
		
		
		//Create Components
		
			//Image 
		URL url = getClass().getResource("/Carl/Rectangle.jpg");
		ImageIcon img = new ImageIcon(url);
		JLabel rect_img = new JLabel(img);
		
			//
		
		JLabel recttitle = new JLabel("<html>Draw A Rectangle<br> Instructions: Enter a value for the height(H) and width(W) of the ");
		recttitle.setHorizontalAlignment(SwingConstants.CENTER);
		recttitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		JLabel closetomain = new JLabel("Close this window to go back to Main Menu");
		
		//Display components in content pane.
		Container rectframe = getContentPane();
		
		rectframe.add(recttitle, BorderLayout.NORTH);
		rectframe.add(recttinput, BorderLayout.CENTER);
		rectframe.add(rect_img, BorderLayout.WEST);
		rectframe.add(closetomain, BorderLayout.SOUTH);
		
		
		
	}
}

