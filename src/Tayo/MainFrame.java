package Tayo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private GridBagLayout layout;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public static JFormattedTextField outputBox = new JFormattedTextField("Hello There :)");
	
	static ArrayList<String> cmds = new ArrayList<String>(5);
	
	public static int maxLines = 6;
	
	private int[][] numConstraints = new int[][] {
		{0,0,1,1},
		{1,0,1,1},
	};
	
	public MainFrame(String title) {
		
		super(title);

		layout =  new GridBagLayout();
		
		layout.columnWidths = new int[] {500,800};
		layout.rowHeights = new int[] {400};
		
		setLayout(layout);
		
		JFormattedTextField inputBox = new JFormattedTextField();
		Font font1 = new Font("Arial", Font.PLAIN, 35);
		Font font2 = new Font("Arial", Font.PLAIN, 25);
		
		inputBox.setColumns(10);
		inputBox.setFont(font1);
		outputBox.setFont(font2);
		outputBox.setColumns(10);
		outputBox.setEditable(false);
	
		
		for(int i = 0; i < 2;i++) {
			
			gbc.gridx = numConstraints[i][0];
			gbc.gridy = numConstraints[i][1];
			gbc.gridheight = numConstraints[i][2];
			gbc.gridwidth = numConstraints[i][3];
			gbc.insets = new Insets(5,5,5,5);
			
			gbc.fill = GridBagConstraints.BOTH; 
			
			if(i == 0) {
				
				add(inputBox,gbc);
				
			} else if(i == 1) {
				
				add(outputBox,gbc);
				
			}
			
		}

		
		inputBox.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				try {
					OnEnterCommand(inputBox);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		});
		
	}
	
	public static void UpdateOuput(Object o) {
		
		cmds.add(o.toString());
		
		if(cmds.size() > maxLines) {
			
			cmds.remove(0);
			
		}
		
		String outputText;
		
		outputText = "";
		
		for(int i = 0; i < cmds.size();i++) {
			
			outputText += '\n' + cmds.get(i);
			
		}
		
		outputBox.setText(outputText);
		
	}
	
	void OnEnterCommand(JFormattedTextField inputBox) throws IOException {
		
		String[] validChars = {"f","b","l","r","t","w","p","q","o","F","B","L","R","T","W","P","Q","O","v","V"};
		boolean containsValidFChar = false;
		
		String[] parts;

		Navigate.input = inputBox.getText();
		
		//Check the input starts with a valid character
		for(int i = 0;i < validChars.length;i++) {

			if(Navigate.input.startsWith(validChars[i])) {
				
				containsValidFChar = true;
				
				break;
				
			}
			
		}
		
		//Check if the input is valid
		if(containsValidFChar) {
		
			parts = Navigate.input.split(" ");
			
			if(parts[0].length() == 1 && parts.length == 1) {
				
				Command.RunCommand(parts[0].toCharArray()[0],parts,Navigate.run);
				
			} else if(parts[0].length() == 1 && parts.length < 4) {
				
				try {
					
					if(Integer.parseInt(parts[1]) > 6000 ) {
					
						Navigate.print("Invalid Input");
						inputBox.setText("");
						return;
						
					}
					
				} catch(NumberFormatException n) {
					
					Navigate.print("Invalid Input");
					inputBox.setText("");
					return;
					
				}
				
				if(parts.length == 3) {
				
					try {
						
						if(Integer.parseInt(parts[2]) > 200) {
							
							Navigate.print("Invalid Input");
							inputBox.setText("");
							return;
							
						}
						
					} catch(NumberFormatException n) {
						
						Navigate.print("Invalid Input");
						inputBox.setText("");
						return;
						
					}
		
				}
				try {
					
					Command.RunCommand(parts[0].toCharArray()[0],parts,Navigate.run);
					
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					
					Navigate.print("Invalid Input");
					inputBox.setText("");
					return;
					
				}
				
			} else {
				
				Navigate.print("Invalid Input");
				inputBox.setText("");
				return;
				
			}
			
			inputBox.setText("");
			
		} else {
			
			Navigate.print("Invalid Input");
			inputBox.setText("");
			return;
			
		}
		
	}
	

}
