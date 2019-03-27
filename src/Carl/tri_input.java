package Carl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class tri_input extends JPanel {
	public tri_input() {
		//Set layout
		setLayout(new GridBagLayout());
		GridBagConstraints tri_gc = new GridBagConstraints();
		
		// create swing components
		JLabel side_a = new JLabel("A");
		JLabel side_b = new JLabel("B");
		JLabel side_c = new JLabel("C");
		
		JTextField a_field = new JTextField(10);
		JTextField b_field = new JTextField(10);
		JTextField c_field = new JTextField(10);
		
		JButton clearfieldsbutton = new JButton("Clear Fields");
		JButton drawtributton = new JButton("Draw Triangle");
	
		
		
	// First Column - Labels#
		tri_gc.anchor = GridBagConstraints.LINE_START;
		// A
		tri_gc.weightx = 0.1;
		tri_gc.weighty = 0.5;
		tri_gc.gridx = 0;
		tri_gc.gridy = 0;
		add(side_a, tri_gc);
		
		// B
		tri_gc.gridx = 0;
		tri_gc.gridy = 1;
		add(side_b, tri_gc);
		
		// C
		tri_gc.gridx = 0;
		tri_gc.gridy = 2;
		add(side_c, tri_gc);
		
	// Second Column - InputFields
		tri_gc.anchor = GridBagConstraints.LINE_START;
		// A
		tri_gc.gridx = 1;
		tri_gc.gridy = 0;
		add(a_field, tri_gc);
		
		//B
		tri_gc.gridx = 1;
		tri_gc.gridy = 1;
		add(b_field, tri_gc);
		
		//C
		tri_gc.gridx = 1;
		tri_gc.gridy = 2;
		add(c_field, tri_gc);
	
	// Final Row
		tri_gc.anchor = GridBagConstraints.FIRST_LINE_START;
		
		tri_gc.weighty = 10;
		// CLEAR BUTTON
		tri_gc.gridx = 0;
		tri_gc.gridy = 3;
		add(clearfieldsbutton, tri_gc);
		
		// DRAW BUTTON
		tri_gc.gridx = 1;
		tri_gc.gridy = 3;
		add(drawtributton, tri_gc);
		
		
	// Event Handling - Listeners
		
		clearfieldsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent clearfields) {
		      a_field.setText(null);
		      b_field.setText(null);
		      c_field.setText(null);
			}});

		drawtributton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent drawtriangle) {
			try {
				int a = Integer.parseInt(a_field.getText());
				int b = Integer.parseInt(b_field.getText());
				int c = Integer.parseInt(c_field.getText());
						
				//insert draw triangle module
				if ((DrawShape.CheckInput(a) == false) || DrawShape.CheckInput(b) == false || DrawShape.CheckInput(c)== false) {
					DrawShape.infoBox("Please enter a number between 20cm and 80cm", "Error");
				}else {
					
					DrawShape.DrawTriangle(a, b, c);
				}
				
			}
			catch (NumberFormatException exception) {
				DrawShape.infoBox("Enter an Integer", "Error");
			}


			}
		});

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
