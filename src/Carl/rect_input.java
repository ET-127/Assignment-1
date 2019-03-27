package Carl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class rect_input extends JPanel {
	public rect_input() {
	// Set Layout
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
	
	// Create Swing Components
		
		JLabel h_label = new JLabel("Height");
		JLabel w_label = new JLabel("Width");
		
		JTextField h_field = new JTextField(10);
		JTextField w_field = new JTextField(10);
		
		JButton clearfieldsbutton = new JButton("Clear Fields");
		JButton drawrectbutton = new JButton("Draw Rectangle");
		
	// Create Layout
		//First Column - Labels
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		add(h_label, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(w_label, gc);
		
		// Second Column - Text Fields
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(h_field, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(w_field, gc);
		
		// Final Row - Buttons
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.weighty = 10;
		gc.gridx = 0;
		gc.gridy = 2;
		add(clearfieldsbutton, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		add(drawrectbutton, gc);
		
	// Event Handling
		// Clear Fields
		clearfieldsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clearfields) {
		      h_field.setText(null);
		      w_field.setText(null);
			}});
		
		drawrectbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent drawrectangle) {
				try {
					int h = Integer.parseInt(h_field.getText());
					int w = Integer.parseInt(w_field.getText());
					if ((DrawShape.CheckInput(h) == false) || DrawShape.CheckInput(w) == false) {
						DrawShape.infoBox("Please enter a number between 20cm and 80cm", "Error");
					}else {
						DrawShape.DrawRectangle(h, w);
					}
				}
				catch (NumberFormatException exception) // Catch invalid format of input.
				{
					DrawShape.infoBox("Enter an Integer", "Error");
					
				}

			}


		
		});
		
		
		
		
		
		
	}

}
