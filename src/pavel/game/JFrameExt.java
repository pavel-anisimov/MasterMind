package pavel.game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

@SuppressWarnings("serial")
public class JFrameExt extends JFrame implements ActionListener, MouseListener {
	
	// Set of initial 6 pins
	private JPanel [] jpMainPins = {new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};

	// Set of Playing pin Panels in the playing field
	private CirclePin [][] jpPlayingPins = { 
								{	new CirclePin(), 	new CirclePin(), 	new CirclePin(), 	new CirclePin()	}	, 
								{	new CirclePin(), 	new CirclePin(), 	new CirclePin(), 	new CirclePin()	}	, 
								{	new CirclePin(), 	new CirclePin(), 	new CirclePin(), 	new CirclePin()	}	, 
								{	new CirclePin(), 	new CirclePin(), 	new CirclePin(), 	new CirclePin()	}	, 
								{	new CirclePin(), 	new CirclePin(), 	new CirclePin(), 	new CirclePin()	}	, 
								{	new CirclePin(), 	new CirclePin(), 	new CirclePin(), 	new CirclePin()	}	, 
								{	new CirclePin(), 	new CirclePin(), 	new CirclePin(), 	new CirclePin()	}	, 
								{	new CirclePin(), 	new CirclePin(), 	new CirclePin(), 	new CirclePin()	}	};

	// Set of Results Showing pin Panels
	private ResultPin [][] jpResultPins = 	{
								{	new ResultPin(),	new ResultPin(),	new ResultPin(), 	new ResultPin()	}	,	
								{	new ResultPin(),	new ResultPin(),	new ResultPin(), 	new ResultPin()	}	,	
								{	new ResultPin(),	new ResultPin(),	new ResultPin(), 	new ResultPin()	}	,	
								{	new ResultPin(),	new ResultPin(),	new ResultPin(), 	new ResultPin()	}	,	
								{	new ResultPin(),	new ResultPin(),	new ResultPin(), 	new ResultPin()	}	,	
								{	new ResultPin(),	new ResultPin(),	new ResultPin(), 	new ResultPin()	}	,	
								{	new ResultPin(),	new ResultPin(),	new ResultPin(), 	new ResultPin()	}	,	
								{	new ResultPin(),	new ResultPin(),	new ResultPin(), 	new ResultPin()	} 	};
	// Score Board Panel
	private JPanel [] jpScoreBoard = 
								{	new JPanel(), new JPanel(), new JPanel(), new JPanel(), 
									new JPanel(), new JPanel(), new JPanel(), new JPanel()};

	// secondary panels for general Layout usage
	private JPanel jpActionButton = new JPanel();	// panel for the JButton
	private JPanel jpDemoPin = new JPanel();		// panel for demonstration pin
	private JPanel jpMessage = new JPanel();		// panel for the output message
	private JPanel jpCresult = new JPanel();
	private JPanel jpCfield = new JPanel();
	private JPanel jpWest = new JPanel();
	private JPanel jpEast = new JPanel();
	private JPanel jpCenter = new JPanel();
	private JPanel jpPinPanel = new JPanel();
	private JPanel rightCenter = new JPanel();

    private JTextField jtResult = new JTextField(12);	// text area for the output message
    private JButton jbtStart = new JButton ("Start");	// Main action button

	private GameLogic logic = new GameLogic();		// class that contains the game logic.

    private boolean buttonOn = false;	// indicator for the JButton (on/off)
    
    // Miscellaneous array for containing data for the random generator
    private  int [] pinsForRandom = {999, 999, 999, 999, 999, 999};	

    private DemoPin demoPin = new DemoPin();	// Demonstration pin
    
    // General Circle Pin objects. 
    private CirclePin circlePin [] = {	new CirclePin(),	new CirclePin(),	new CirclePin(), 
    									new CirclePin(),	new CirclePin(),	new CirclePin()	};

    // Object used to contain data regarding all pins.
	private PinData [] pinData = {		new PinData(),		new PinData(), 		new PinData(), 
										new PinData(),		new PinData(), 		new PinData()	};

	// next four types of objects are used to fill out the PinData
	private Color pinColors [] = {Color.blue, Color.red, Color.green, Color.yellow, Color.magenta, Color.cyan};
	private String pinColorNames [] = {"Blue", "Red", "Green", "Yellow", "Purple", "Cyan"};
	private boolean [] pinOn = {false, false, false, false, false, false};
	private boolean [] pinRule = {true, true, true, true, true, true};

    public JFrameExt ( ) {
        Container c = this.getContentPane();
        
        c.setBackground(Color.lightGray);
 
        // Initialization of the PinData (will used widelly)
        for (int i = 0; i < 6; i++){
        	pinData[i].setPinColor(pinColors[i]);
        	pinData[i].setPinColorNames(pinColorNames[i]);
        	pinData[i].setPinOn(pinOn[i]);
        	pinData[i].setPinRule(pinRule[i]);
        }
        
        BorderLayout mainPanel = new BorderLayout(); 	// main panel
        BorderLayout centerPanel = new BorderLayout(); 	// center field panel
        BorderLayout rightPanel = new BorderLayout(); 	// will be on the east of the mainPanel 
        // GridLayout scoreGrid = new GridLayout (16, 2);	// will be on the center of the mainPanel
        GridLayout [] scoreGrid = new GridLayout [8];	// will be on the center of the mainPanel
        GridLayout playingField = new GridLayout(8,5);	// will be on the west of the mainPanel
        GridLayout sixPins = new GridLayout(3,2);		// will be on the west of the mainPanel
        GridLayout eastCenter = new GridLayout(2,1);	// will be on the west of the mainPanel
        
        c.setLayout(mainPanel);	// setting the main panel
        
        jpWest.setBounds(0, 0, 100, 500);
        jpWest.setSize(100, 300);
        jpWest.setMinimumSize(new Dimension (100, 300));

        // inserting 2 main panels. 
        c.add(jpEast, BorderLayout.EAST);
        c.add(jpCenter, BorderLayout.CENTER);

        ////////////// START OF THE LEFT PANEL (Playing field and Result field) SETUP ////////////////////////
        jpCenter.setLayout(centerPanel);
        jpCenter.add(jpCresult, BorderLayout.EAST);
        jpCresult.setBackground(Color.gray);
        jpCenter.setBackground(Color.lightGray);
        jpCenter.add(jpCfield, BorderLayout.CENTER);
        jpCfield.setBackground(Color.lightGray);
        jpCfield.setLayout(playingField);	
        
        for (int i = 0; i < 8; i++){	// Playing Field Panel Creation
        	for (int j = 0; j < 4; j++){
    			jpPlayingPins[i][j].setCirclePin(40, 5, 5, Color.gray, true);
        		if ( (i + j) % 2 == 0 ) {
        			jpPlayingPins[i][j].setBackground(Color.lightGray);
        		} else {
        			jpPlayingPins[i][j].setBackground(Color.gray.brighter());        			
        		}
        		jpCfield.add(jpPlayingPins[i][j]);
        	}
        
        	jpCfield.add(jpScoreBoard[i]); 			// creating score board panel
        	scoreGrid[i] = new GridLayout(2, 2);	// it is like a panel in a panel
        	jpScoreBoard[i].setBackground(Color.lightGray);
        	jpScoreBoard[i].setLayout(scoreGrid[i]);
        	for (int j = 0; j < 4; j++){
        		jpResultPins[i][j].setPin(20, 0, 0, 3);
        		if ( (j + 1 ) / 2  % 2 == 0) {	// simple algorithm for setting a background color
        			jpResultPins[i][j].setBackground(Color.gray.brighter());
        		} else {
        			jpResultPins[i][j].setBackground(Color.lightGray);        		
        		} 	
        		jpScoreBoard[i].add(jpResultPins[i][j]);
        	}
 
        }      
        ////////////////////// END OF THE LEFT PANEL SETUP ////////////////////////
        
        
        
        ////////////// Setting a right panel and breacking in twice.
        ////////////// once it was broke by a border layout, and then center was broken
        ////////////// by a Grid Layout
        jpEast.setLayout(rightPanel);
        jpEast.setBackground(Color.lightGray);
        jpMessage.setBackground(Color.lightGray);
        jpActionButton.setBackground(Color.lightGray);

        jpEast.add(jpMessage, BorderLayout.NORTH);
        jpEast.add(jpActionButton, BorderLayout.SOUTH);
        jpEast.add(rightCenter, BorderLayout.CENTER);

        rightCenter.setLayout(eastCenter);
        
        ///////////////// adding a demo pin //////////////
        demoPin.SetDemoPin(100, 0, 50, pinData[0].getPinColor());
        demoPin.setBackground(Color.lightGray);
        
        rightCenter.add(demoPin);		//rightCenter.add(jpDemoPin);
        rightCenter.add(jpPinPanel);
        jpDemoPin.setBackground(Color.gray);        
        
        jpPinPanel.setLayout(sixPins);
        
        //////////////// Here's the main 6 pins for the game //////////////////
        for (int i = 0; i < 6; i++){
        		if ( ( ( i + 1 ) / 2 ) % 2 == 0 ) {
        			jpMainPins[i].setBackground(Color.blue);
        		} else {
        			jpMainPins[i].setBackground(Color.blue.darker());        			
        		}
        		circlePin[i].setCirclePin(50, 0, 0, pinColors[i]);
        		circlePin[i].setBounds(10, 10, 50, 50);
        		circlePin[i].setBackground(Color.lightGray);
        		jpPinPanel.add(circlePin[i]);
        		circlePin[i].addMouseListener(this);
        }
        
        jtResult.setForeground(Color.blue.darker());
        jtResult.setFont(new Font("Verdana", Font.BOLD, 12));
        jtResult.setHorizontalAlignment(JTextField.CENTER);
        jtResult.setText("Let's Play!");
        jpMessage.add(jtResult);
        jbtStart.setBackground(Color.gray.brighter());
        jbtStart.setForeground(Color.blue.darker());
        jbtStart.setFont(new Font("Verdana", Font.BOLD, 12));
        jpActionButton.add(jbtStart);
        jbtStart.addActionListener(this);
    }


    //////////////  method that listens to the JButton ////////////////
    // method allows to create a new game and as well cancel the game in progress
    public void actionPerformed (ActionEvent ev) {

    	if (ev.getSource() == jbtStart) {
    		
    		if (buttonOn) { 
    			buttonOn = false;
    			jbtStart.setText("Start");
    		} else {
    			buttonOn = true;
    			jbtStart.setText("End");
    			for (int i = 0; i < 4; i++) {
    				jpPlayingPins[logic.getTurn()][i].addMouseListener(this);  			
    			}

				// changing the demo pin color and curient working pin
				// if the last pin that was deselected is still in the buffer
				for (int i = 0; i < 6; i++){
					if (!pinData[logic.getCurientPin()].isPinRule()) {
						int indx = logic.getCurientPin() + 1;
						if (indx == 6)
							indx = 0;
						logic.setCurientPin(indx);
					}
					
					demoPin.changeColor(pinData[logic.getCurientPin()].getPinColor());
					demoPin.validate();
					demoPin.repaint();
					jtResult.setBackground(Color.lightGray.brighter());
					jtResult.setText(pinData[logic.getCurientPin()].getPinColorNames() + " pin playing");

					if (pinData[i].isPinRule()) {
						pinsForRandom[i] = i;
					}
				}
				
				// here's the code for reading pins for the random generation
				// in case if there were less then 6 pins celected
				Arrays.sort(pinsForRandom);				
				
	   			this.setUp();
				logic.doRandom(pinsForRandom);
				for (int i = 0; i < 6; i++)
					pinsForRandom[i] = 999; 				
    		}
   		}                	
    }

    /////////////// mouse listener /////////////// 
    // probably most difficult method
    
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// working with initial 6 pins when the game in progress or not
		for (int i = 0; i < 6; i++) {
			if (e.getSource() == circlePin[i]) {
								
				if (!this.buttonOn) {
					if (pinData[i].isPinRule()) {
						circlePin[i].setNewColor(pinData[i].getPinColor().darker().darker().darker());
						pinData[i].setPinRule(false);					
						jtResult.setText(pinColorNames[i] + " pin OFF");					
					} else {
						circlePin[i].setNewColor(pinData[i].getPinColor());
						pinData[i].setPinRule(true);
						jtResult.setText(pinColorNames[i] + " pin ON");					
					}
					circlePin[i].validate();
					circlePin[i].repaint();
				} else if (this.buttonOn) {				
					if (pinData[i].isPinRule()) {
						logic.setCurientPin(i);
						jtResult.setText(pinColorNames[i] + " pin playing");
						demoPin.changeColor(pinData[i].getPinColor());
						demoPin.validate();
						demoPin.repaint();
					} 
				}
			}
		}
		
		// here's the code for putting pins on the game panel
		for (int i = 0; i < 4; i++){
			if (e.getSource() == jpPlayingPins[logic.getTurn()][i]) {
				jpPlayingPins[logic.getTurn()][i].setNewColor(pinData[logic.getCurientPin()].getPinColor());
				jpPlayingPins[logic.getTurn()][i].setEmptyOff();
				jpPlayingPins[logic.getTurn()][i].validate();
				jpPlayingPins[logic.getTurn()][i].repaint();
				logic.setGuess(logic.getCurientPin(), i);
			}
		}
		
		
		// logic of the pin panel.
		// It reads the score and does appropriated action (start a new game)
		if (logic.isLineClosed()){
			logic.checkScores();

			int tempIndex = 0;
			
			for (int i = 0; i < logic.getGuessedThePin(); i++) {
				jpResultPins[logic.getTurn()][tempIndex].setType(0);
				jpResultPins[logic.getTurn()][tempIndex].validate();
				jpResultPins[logic.getTurn()][tempIndex].repaint();
				tempIndex++;
			}
			for (int i = 0; i < logic.getGuessedTheColor(); i++) {
				jpResultPins[logic.getTurn()][tempIndex].setType(1);
				jpResultPins[logic.getTurn()][tempIndex].validate();
				jpResultPins[logic.getTurn()][tempIndex].repaint();
				tempIndex++;
			}
			for (int i = 0; i < (4 - logic.getGuessedThePin() - logic.getGuessedTheColor()); i++) {
				jpResultPins[logic.getTurn()][tempIndex].setType(2);
				jpResultPins[logic.getTurn()][tempIndex].validate();
				jpResultPins[logic.getTurn()][tempIndex].repaint();
				tempIndex++;
			}

			if (logic.getGuessedThePin() == 4){

				jtResult.setText("GREAT JOB! :)");
    			buttonOn = false;
    			jbtStart.setText("Start");
				
			} else if (logic.getTurn() == 7) {

				jtResult.setText("You Lost :(");
    			buttonOn = false;
    			jbtStart.setText("Start");
	   		 
			} else {							
				logic.renewGuess();
				logic.addTurn();
				for (int i = 0; i < 4; i++)
					jpPlayingPins[logic.getTurn()][i].addMouseListener(this);  
			}			
		}
	}

	
	//////// this method resets the data after the game is finished 
	//// updates data, redraws images
	public void setUp(){
        for (int i = 0; i < logic.getTurn() + 1; i++){
        	for (int j = 0; j < 4; j++){
    			jpPlayingPins[i][j].setCirclePin(40, 5, 5, Color.gray, true);
        		if ( (i + j) % 2 == 0 ) {
        			jpPlayingPins[i][j].setBackground(Color.lightGray);
        		} else {
        			jpPlayingPins[i][j].setBackground(Color.gray.brighter());        			
        		}
        		jpPlayingPins[i][j].validate();
        		jpPlayingPins[i][j].repaint();
        	}
        }      

        for (int i = 0; i < logic.getTurn() + 1; i++){
        	for (int j = 0; j < 4; j++){
        		jpResultPins[i][j].setPin(20, 0, 0, 3);
        		if ( (j + 1 ) / 2  % 2 == 0) {	// simple algorithm for setting a background color
        			jpResultPins[i][j].setBackground(Color.gray.brighter());
        		} else {
        			jpResultPins[i][j].setBackground(Color.lightGray);        		
        		}        		
        		jpResultPins[i][j].validate();
        		jpResultPins[i][j].repaint();        		
        	}
        }
        int tempcurient = logic.getCurientPin();
        logic = new GameLogic();
        logic.setCurientPin(tempcurient);
	}
	
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
}
