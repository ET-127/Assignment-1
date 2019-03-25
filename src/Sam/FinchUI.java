package Sam;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;



public class FinchUI {

	private JFrame frame;
	public JPanel cards;
	public CardLayout cl;
	public static JTextArea console;
	public int limitChosen;
	
	//Launch the application.
	public static void mainSam() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinchUI window = new FinchUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.
	public FinchUI() {
		initialize();
	}

	
	//Initialize the contents of the frame.
	private void initialize() {
		//Setting the frame name, size and close action.
		frame = new JFrame("Light follower 2.0");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); //Not allowed to resize.
		
		//Setting the layout
		cl = new CardLayout();
		cards = new JPanel();
		cards.setLayout(cl);
		
		//Creating the screens for the different sections:
		//Start screen:
		JPanel startScreen = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("Welcome to the Light Follower finch program.");
        topPanel.add(label);
        
		//Buttons for the starting screen.
		JButton btnStart = new JButton("Start");
        JButton btnOptions = new JButton("Options");
        btnPanel.add(btnStart);
        btnPanel.add(btnOptions);
        JLabel finchimg = new JLabel("");
        finchimg.setHorizontalAlignment(SwingConstants.CENTER);
        finchimg.setIcon(new ImageIcon(FinchUI.class.getResource("/Sam/finch.png")));
        startScreen.add(finchimg, BorderLayout.CENTER);
        
        
        //Add the panels to the MAIN panel (the start screen) in the top and bottom positions.
        startScreen.add(topPanel, BorderLayout.NORTH);
        startScreen.add(btnPanel, BorderLayout.SOUTH);
        
        //While running (after pressing start)
        JPanel runningScreen = new JPanel();
        runningScreen.setLayout(new BorderLayout());
        console = new JTextArea();
        console.setEditable(false); //Doesn't let the user edit it.
        console.append("Starting program...\n\n");
        console.setBackground(Color.white);
        console.setFont(new Font("Arial", Font.BOLD, 12));
        console.setForeground(Color.black);
        runningScreen.add(console);
        
        //Options screen 
        JPanel optionsScreen = new JPanel();
        JPanel optionsTop = new JPanel();
        JPanel optionsBot = new JPanel(new BorderLayout());
        
        //Radio buttons:
        JRadioButton withLight = new JRadioButton("With background light."); //Create the radio buttons
        withLight.setActionCommand("with");
        withLight.setSelected(true);
        JRadioButton noLight = new JRadioButton("Without background light.");
        noLight.setActionCommand("without");
        ButtonGroup lightOptions = new ButtonGroup();
        optionsTop.add(withLight);
        optionsTop.add(noLight);
        lightOptions.add(withLight);
        lightOptions.add(noLight);
        optionsScreen.add(optionsTop, BorderLayout.NORTH);
        JButton btnGetOption = new JButton("Confirm Selection");
        optionsScreen.add(btnGetOption);
        
      //Confirm option selection button:
        btnGetOption.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String option = lightOptions.getSelection().getActionCommand();
        		if (option == "with")
        		{
        			limitChosen = 25;
        		}
        		
        		else
        		{
        			limitChosen = 10;
        		}
        		cl.show(cards, "1");
        	}
        });
        optionsScreen.add(optionsBot, BorderLayout.SOUTH); 
        
        //Adding the cards (assigning each a value)
        cards.add(startScreen, "1");
        cards.add(runningScreen, "1.2");
        cards.add(optionsScreen, "2");
        
        
        //ACTIONS. These are the respective actions for the different buttons in the Start and options page.
        
        //Start button:
        btnStart.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cl.show(cards, "1.2");
        		Finch myFinch = new Finch();
        		//Check in case no options were set. Then set it to default.
        		if (limitChosen == 0)
        		{
        			limitChosen = 25;
        		}
        		Lightfollower app = new Lightfollower(myFinch, limitChosen);
        		
        		/* Had to create another thread while running this because the UI has its own thread,
        		 * and the while loop in the other class, blocked the messages from being shown while running.
        		 */
        		new Thread(){
        			@Override
        			public void run(){
        				app.Start(); //Calls the start method and initiates the program.
        			}
        		}.start();
        	}
        });
        
        //Options button:
        btnOptions.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cl.show(cards, "2");
        	}
        });
        
        
        //Displaying everything on the screen:
        cl.show(cards, "1");
        frame.getContentPane().add(cards);
        
	}
}
