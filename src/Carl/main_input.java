package Carl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class main_input extends JPanel {
	public main_input() {
	//Create Components
		JLabel optionLabel = new JLabel("Enter Option: ");
		final JTextField optionField = new JTextField(10);
		JButton optionEnter = new JButton("Proceed");
		
	//Event Handling
		optionEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str_option = optionField.getText();
				char option = str_option.charAt(0);
				
				if (option == 'T') {
					JFrame triframe = new TriangleFrame("Draw Shape");
					triframe.setSize(500, 400);
					triframe.setVisible(true);
					triframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
		
				}
				else if (option == 'R') {
					JFrame rectframe = new RectFrame("Draw Shape");
					rectframe.setSize(800, 400);
					rectframe.setVisible(true);
					rectframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else if (option == 'Q') {
					System.exit(0);
				}
				else {
					DrawShape.infoBox("Enter a valid option Q, R, or T", "Error");
				}
			}
		});

	//Create Layout 
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints(); // use to specify size and position of components.
		// First Column
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		
		add(optionLabel, gc); 
		
		// Second Column
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(optionField, gc);
		
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 1;
		add(optionEnter, gc);
	}

}
