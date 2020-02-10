

/**
 * CS 2005
 * Iteration 2
 * Group 7
 * Members:
 * Jeremy Eustace - 20194855
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Random;

//import java.util.concurrent.TimeUnit;

/*
 *  the main window of the gui
 *  notice that it extends JFrame - so we can add our own components
 *  notice that it implements ActionListener - so we can handle user input
 */
public class WindowStart extends JFrame implements ActionListener, MouseListener
{
		/*
		 * 				GLOBAL VARIABLES THAT RUN THE GAME STATE
		 * */	
		private boolean 		inGame; // Is the game running in the menu
		/*
		 * 				GLOBAL VARIABLES THAT RUN THE GAME
		 * */	
		private int				turnCount; // Index of the current turn
		private int 			numberOfPlayers; // the number of players in the game
		private boolean 		advancedScoring; // is it basic or advanced scoring
		private boolean			colorBlindMode; // goes from 0 to 3 for the 4 possible settings	
		private boolean 		isPlaying; //says if the player is playing, false if the AI is playing
		private int 			currentTurn; // the playerNumber of who is playing
		private GameSettings 	currentGame; // the object for the game settings
		private Piece			selectedPiece; // the piece that the user is trying to place
		private Piece[][] 		piecesLeft; // 2d array containing each usable piece
		private Block[][]		theBoard; // 2d array for each board square
		private Player[] 		thePlayers; // array for each player
		private int[] 			playerColors; // array containing 4 RGBs, one for each player
		private GridSquare2[][] gridSquares; // 2d array of blocks for the board
		/*
		 *******************************************GLOBAL VARIABLES FOR THE UI
		 * */	
		private JPanel 			topPanel, centerPanel, bottomPanel;
		//combo boxes for main menu---------------
		private JComboBox 		gamePlayerAmount, gameDifficulty, gameScoreType, gamePlayerColor, gameColorSettings;
		private JButton 		startGameButton;
		private JLabel 			menuErrors;
		//for in game-------------------------------
		private JComboBox 		pieceMenu;
		private JButton 		rotateCW, rotateCCW;
		//-----------------------------------------
		
		
		/*
		 *******************************************THINGS THAT MAY BE DELETED
		 * */	
		private JLabel 			topLabel, nextLabel;// a text label to appear in the top panel
		private JLabel 			infoLabel;
        private JButton 		topButton;// a 'reset' button to appear in the top panel
        private JButton 		firstButton;
        private JButton			secondButton;
        private JButton 		thirdButton;
        private JButton 		menuButton;
        private JButton 		replayButton;
        
        private int 			x,y;
        private int 			gridXSize;
        private int 			gridYSize;// the size of the grid
        private int 			squaresLeft;
        //==============================================================
        
	
	
        
    /*
     ****************************************************************************************************
     *********************SETTINGS UP THE COLOR OF THE PIECES********************************************
     ****************************************************************************************************
     * */
    
	// Returns RGB values based on an input color name
	public int[] getColorRGB(String thisColor)
	{
		int[] colors = new int[] {0, 0, 0};
		switch (thisColor) 
		{
			//comparing value of variable against each case
			case "Blue":
				colors = new int[] {24, 95, 173};
				break;
			case "Yellow":
				colors = new int[] {250, 234, 35};
				break;
			case "Red":
				colors = new int[] {238, 56, 35};
				break;
			case "Green":
				colors = new int[] {64, 170, 72};
				break;
			case "Teal":
				colors = new int[] {102, 255, 255};
				break;
			case "Purple":
				colors = new int[] {127, 0, 255};
				break;
			default:
				colors = new int[] {0, 0, 0};
	    }
		return colors;		
	}
	
	// hard coded data for each piece
	public void createPlayersPieces()
	{
		System.out.println("Creating the players pieces =======================================");
		piecesLeft = new Piece[this.currentGame.size][21];
		for (int i=0; i<this.currentGame.size; i++)
		{
			String color = this.thePlayers[i].pieceColor;
			// 1 block pieces
			piecesLeft[i][0] = new Piece(new int[] {0}, new int[] {0}, color, "1- Single");
			// 2 block pieces
			piecesLeft[i][1] = new Piece(new int[] {0, 1}, new int[] {0, 0}, color, "2- Double");
			// 3 block pieces
			piecesLeft[i][2] = new Piece(new int[] {-1, 0, 1}, new int[] {0, 0, 0}, color, "3- Line");
			piecesLeft[i][3] = new Piece(new int[] {-1, 0, 0}, new int[] {0, 0, 1}, color, "3- L Shape");
			// 4 block pieces
			piecesLeft[i][4] = new Piece(new int[] {-1, 0, 0, 1}, new int[] {0, 0, 1, 1}, color, "4- Z Shape");
			piecesLeft[i][5] = new Piece(new int[] {0, 1, 0, 1}, new int[] {0, 0, 1, 1}, color, "4- Box");
			piecesLeft[i][6] = new Piece(new int[] {-1, 0, 1, 0}, new int[] {0, 0, 0, 1}, color, "4- T");
			piecesLeft[i][7] = new Piece(new int[] {0, 1, 2, 0}, new int[] {0, 0, 0, 1}, color, "4- L Shape");
			piecesLeft[i][8] = new Piece(new int[] {-1, 0, 1, 2}, new int[] {0, 0, 0, 0}, color, "4- Line");
			// 5 block pieces
			piecesLeft[i][9] = new Piece(new int[] {-1, 0, 1, -1, 0}, new int[] {0, 0, 0, 1, 1}, color, "5- Thumb");
			piecesLeft[i][10] = new Piece(new int[] {-1, 0, 1, -1, 1}, new int[] {0, 0, 0, 1, 1}, color, "5- C Shape");
			piecesLeft[i][11] = new Piece(new int[] {-1, 0, 1, 0, 0}, new int[] {0, 0, 0, 1, 2}, color, "5- T Shape");
			piecesLeft[i][12] = new Piece(new int[] {-1, 0, 0, 1, 2}, new int[] {-1, -1, 0, 0, 0}, color, "5- Z Shape");
			piecesLeft[i][13] = new Piece(new int[] {-1, -1, 0, 1, 1}, new int[] {-1, 0, 0, 0, 1}, color, "5- S Shape");
			piecesLeft[i][14] = new Piece(new int[] {-1, -1, 0, 1, 0}, new int[] {-1, 0, 0, 0, 1}, color, "5- Bent T");
			piecesLeft[i][15] = new Piece(new int[] {1, -2, -1, 0, 1}, new int[] {-1, 0, 0, 0, 0}, color, "5- L Shape");
			piecesLeft[i][16] = new Piece(new int[] {-1, 0, 0, 1, 1}, new int[] {-1, -1, 0, 0, 1}, color, "5- Staircase");
			piecesLeft[i][17] = new Piece(new int[] {0, -1, 0, 1, 0}, new int[] {-1, 0, 0, 0, 1}, color, "5- Cross");
			piecesLeft[i][18] = new Piece(new int[] {-2, -1, 0, 1, 2}, new int[] {0, 0, 0, 0, 0}, color, "5- Line");
			piecesLeft[i][19] = new Piece(new int[] {0, 1, 2, 0, 0}, new int[] {0, 0, 0, 1, 2}, color, "5- Right Angle");
			piecesLeft[i][20] = new Piece(new int[] {-1, 0, 1, 2, 0}, new int[] {0, 0, 0, 0, 1}, color, "5- Line type 2");
		}
		System.out.println("players color is " + currentGame.playerColor);
		int[] test = this.getColorRGB("Green");
		for (int x : test)
		{
			System.out.println("Value is " + x);
		}
	}
	
	// Creating the players, and assigning colors to them
	public void createThePlayers()
	{
		System.out.println("Creating the players  ========================================================");
		if (currentGame.colorOption) {
			System.out.println("setting alternate colors");
			String [] allColors = new String[] {"Teal", "Purple", "Yellow", "Red" };
			thePlayers = new Player[currentGame.size];
			int count = 0;
			for (int i=0; i < currentGame.size; i++)
			{
				if (i == 0) {
					thePlayers[i] = new Player(i, true, this.currentGame.playerColor);
					allColors = removeElement(allColors, this.currentGame.playerColor);
				}
				else 
				{
					// else add the other players
					thePlayers[i] = new Player(i, true, allColors[count]);	
					count ++;
				}
				System.out.println("add player " + thePlayers[i].playerNumber + " with " + thePlayers[i].pieceColor);
			}
		}
		else
		{
			System.out.println("setting main colors");
			String [] allColors = new String[] {"Green", "Yellow", "Blue", "Red" };
			thePlayers = new Player[currentGame.size];
			int count = 0;
			for (int i=0; i < currentGame.size; i++)
			{
				if (i == 0) {
					thePlayers[i] = new Player(i, true, this.currentGame.playerColor);
					allColors = removeElement(allColors, this.currentGame.playerColor);
				}
				else 
				{
					// else add the other players
					thePlayers[i] = new Player(i, true, allColors[count]);	
					count ++;
				}
				System.out.println("add player " + thePlayers[i].playerNumber + " with " + thePlayers[i].pieceColor);
			}
		}
	}
	
	/*
     ****************************************************************************************************
     *********************SIMPLE OPERATIONS FOR CONVINIENCE**********************************************
     ****************************************************************************************************
     * */
	
	// this is run to make sure nothing strange happens after returning to the main menu and playing again
	public void wipeVariables()
	{
		inGame = false; // Is the game running in the menu
		/*
		 * 				GLOBAL VARIABLES THAT RUN THE GAME
		 * */	
		turnCount = 0; // Index of the current turn
		numberOfPlayers = 0; // the number of players in the game
		advancedScoring = false; // is it basic or advanced scoring
		colorBlindMode = false; // goes from 0 to 3 for the 4 possible settings	
		isPlaying = false; //says if the player is playing, false if the AI is playing
		currentTurn = 0; // the playerNumber of who is playing
		currentGame = null; // the object for the game settings
		selectedPiece = null; // the piece that the user is trying to place
		piecesLeft = null; // 2d array containing each usable piece
		theBoard = null; // 2d array for each board square
		thePlayers = null; // array for each player
		playerColors = null; // array containing 4 RGBs, one for each player
		
	}
	
	// Checks to make sure all the user input was entered, for starting the game
	public boolean checkGameSettings(GameSettings game) 
    {
    	boolean errorsNotFound = true;
    	if (game.size == 0)
    	{
    		showMenuError("Game size was not selected");
    		errorsNotFound = false;
    	}
    	else if (game.advancedScoring.equals("<Select>"))
    	{
    		showMenuError("Score mode was not selected");
    		errorsNotFound = false;
    	}
    	else if (game.playerColor.equals("<Select>"))
    	{
    		showMenuError("Your piece color was not selected");
    		errorsNotFound = false;
    	}
    	return errorsNotFound;
    }
	
	// Shows an error on the main menu
	public void showMenuError(String errorMessage)
    {
    	System.out.println("Print error: " + errorMessage);
    	menuErrors.setText(errorMessage);
    	HouseKeeping();
    }
	
	// To print all the elements in an array
	public void printElements(String[] array)
	{
		for (String value: array)
			System.out.print(value + ", ");			
		System.out.println(" ");		
	}
	
	// To remove a string from a list
	public String [] removeElement(String [] inputString, String elementData)
	{
		int valueToRemove = 99;
		int size = inputString.length;
		for (int i=0; i < size; i++)
		{
			if (inputString[i].equals(elementData))
			{
				valueToRemove = i;
				break;
			}
		}
		if (valueToRemove == 99)	
			return inputString;
		else
		{
			size --;
			String[] newArray = new String[size];
			int count = 0;
			for (int i=0; i < inputString.length; i++)
			{
				if (valueToRemove != i)
				{
					newArray[count] = inputString[i];
					count++;
				}
			}
			return newArray;
		}
	}
	
	// this class basically fixes it so its not all frozen after a change
    public void HouseKeeping()
    {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable( false);
        setVisible( true);
    }
	
    
    /*
     ****************************************************************************************************
     *********************INITIALIZING THE MAIN GAME WINDOW AT START*************************************
     ****************************************************************************************************
     * */
    
    // This is the Start function for the main game
    public WindowStart(int x, int y)
	{
		this.x = x;
		this.y = y;
		//this.setSize(600,600);
		
		// first create the panels
		topPanel = new JPanel();
		topPanel.setLayout( new FlowLayout());
			
		// now add the top and bottom panels to the main frame
		getContentPane().setLayout( new BorderLayout());
		getContentPane().add( topPanel, BorderLayout.NORTH);
         
		centerPanel = new JPanel();
		getContentPane().add( centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout( new GridLayout(5, 5, 0, 100));
		
        bottomPanel = new JPanel();
		getContentPane().add( bottomPanel, BorderLayout.SOUTH);		// needs to be center or will draw too small

		ShowMainMenu();
		
	}
    
    /*
     ****************************************************************************************************
     *********************INITIALIZING THE MAIN MENU*****************************************************
     ****************************************************************************************************
     * */
    
    // this shows the main menu to the user
    public void ShowMainMenu()
    {
    	this.wipeVariables();
    	this.setSize(800,800);
    	// Clearing the 3 panels that make up the window
    	topPanel.removeAll();
        bottomPanel.removeAll();
        centerPanel.removeAll(); 
        // Adding the top and bottom bars items
        topPanel.add( new JLabel("Blokus: Main Menu"));
        startGameButton = new JButton("start game");
        startGameButton.addActionListener( this);	
        bottomPanel.add( startGameButton);
        //Select the game size-----------------------------------------
        centerPanel.add( new JLabel("Select game size"));
        String[] gameSize = {"<Select>", "2 Player", "3 Player", "4 Player" };
        gamePlayerAmount = new JComboBox(gameSize);
        centerPanel.add( gamePlayerAmount);
        centerPanel.add( new JLabel(" "));
        centerPanel.add( new JLabel(" "));
        centerPanel.add( new JLabel(" "));          
        //Select the difficulty----------------------------------------
        centerPanel.add( new JLabel("Select game difficulty"));
        String[] difficulties = {"Easy", "Medium", "Hard" };
        gameDifficulty = new JComboBox(difficulties);
        centerPanel.add( gameDifficulty);
        centerPanel.add( new JLabel(" "));
        centerPanel.add( new JLabel(" "));
        centerPanel.add( new JLabel(" "));      
        //Select the score type----------------------------------------
        centerPanel.add( new JLabel("Select score type"));
        String[] scores = {"Basic", "Advanced" };
        gameScoreType = new JComboBox(scores);
        centerPanel.add( gameScoreType);
        centerPanel.add( new JLabel(" "));
        centerPanel.add( new JLabel(" "));
        centerPanel.add( new JLabel(" "));      
        //Select the piece color---------------------------------------
        centerPanel.add( new JLabel("Select your color"));
        String[] colors = {"<Select>", "Red", "Blue", "Green", "Yellow" };
        gamePlayerColor = new JComboBox(colors);
        centerPanel.add( gamePlayerColor);
        centerPanel.add( new JLabel(" "));
        centerPanel.add( new JLabel(" "));
        centerPanel.add( new JLabel(" "));
        //-------------------------------------------------------------      
        //Colorblind settings
        //note: this should actually change the piece selections
        centerPanel.add( new JLabel("Select colorblind setting"));
        colors = new String[] {"Off", "On"};
        gameColorSettings = new JComboBox(colors);
        gameColorSettings.addActionListener( this);
        centerPanel.add( gameColorSettings);
        centerPanel.add( new JLabel(" "));
        menuErrors = new JLabel(" ");
        centerPanel.add( menuErrors);
        //
        Player test = new Player(1, true, "Red");
        Block block = new Block(1, 2);
        block.fillBlock("Green");
        //
        HouseKeeping();
    }
    
    /*
     ****************************************************************************************************
     *********************INITIALIZING THE START OF THE GAME*********************************************
     ****************************************************************************************************
     * */
	
    // this shows the in-game user interface to the user
    public void ShowInGameMenu()
    {
    	topPanel.removeAll();
        centerPanel.removeAll();
        bottomPanel.removeAll();
    	
    	System.out.println("trying to show in game menu 1");
        topPanel.removeAll();
        System.out.println("trying to show in game menu 2");
        
        topLabel = new JLabel("Select a piece, click around");
        
        /*
        topLabel = new JLabel("Current turn: ");

        if (this.isPlaying)
            infoLabel = new JLabel("Player");
        else
            infoLabel = new JLabel("Opponent");
        */
        
        System.out.println("trying to show in game menu 3");
        menuButton = new JButton("Main Menu");
        menuButton.addActionListener( this);
        System.out.println("trying to show in game menu 4");
        topPanel.add ( topLabel);
        //topPanel.add ( infoLabel);
        topPanel.add ( menuButton);
        System.out.println("trying to show in game menu 5");
        String[] pieceSelect = {"<Select>"};
        pieceMenu = new JComboBox(pieceSelect);
        topPanel.add(new JLabel("Select a piece"));
        topPanel.add( pieceMenu);
        
        rotateCW = new JButton("Rotate CW");
        rotateCW.addActionListener( this);
        rotateCCW = new JButton("Rotate CCW");
        rotateCCW.addActionListener( this);
        topPanel.add(rotateCCW);
        topPanel.add(rotateCW);
        
        this.colorBlindMode = false;
        
        HouseKeeping();
        
    }
    
    // runs all the methods to start the game
    public void startGame()
    {            
    	System.out.println("Starting the game ==============================================");
    	startGameShowUI(); // show the board and the top panel
        startGameFillPieces(); // fill all the game pieces
        
        //Fill the boxes and other stuff with methods called here

        HouseKeeping();
    }
    
    // gives the user their 21 pieces in a drop-down menu
    public void startGameFillPieces()
    {
		System.out.println("action was made for color settings");
		String[] colors = {"<Select>", "Red", "Blue", "Green", "Yellow" };
		pieceMenu.removeAllItems();
		for (int i=0; i<21;i++)
		{
			pieceMenu.addItem(piecesLeft[0][i].description);
		}
    }
    
    // shows the GUI for the board and the top panel
    public void startGameShowUI()
    {
    	int rows = 20;
        int cols = 20;
        this.ShowInGameMenu();
    	bottomPanel.removeAll();         
        // create the buttons and add them to the grid
        y = rows;
        x = cols;        
        this.squaresLeft = x * y;     
        System.out.println("x is " + x + ", and rows is " + rows);
        int sizeX = rows * 40; // The size of each block, on the x-axis
        int sizeY = cols * 40; // The size of each block, on the y-axis
        System.out.println("x is " + x + ", sizeX is " + sizeX + ", and rows is " + rows);      
        centerPanel.setLayout( new GridLayout( x, y));
        centerPanel.setSize(500,500);     
        this.setSize(sizeX,sizeY);
        //bottomPanel.setSize(sizeX,sizeY);    
        System.out.println("x value is " + x + " and y value is " + y);
        gridSquares = new GridSquare2 [x][y];
        gridXSize = x;
        gridYSize = y;
        //gridSquares = new GridSquare2 [rows][cols];
       
        for ( int column = 0; column < x; column ++)
        {
            for ( int row = 0; row < y; row ++)
            {
                //notEatenYet [column][row] = true
                gridSquares [column][row] = new GridSquare2( row,column);
                gridSquares [column][row].setSize( 20, 20);
                gridSquares [column][row].setColor( column + row);

                gridSquares [column][row].addMouseListener( this);		// AGAIN, don't forget this line!

                centerPanel.add( gridSquares [column][row]);

                Dimension size = gridSquares [column][row].getSize();
                double sizeX1 = size.width;
                double sizeY1 = size.height;
                //System.out.println("position " + column + "," + row +  ", next square created, sizex:" + sizeX1 + ", sizey:" + sizeY1);
            }
        }
    }
    
    /*
     ****************************************************************************************************
     *********************LAYING, MOVING AND ROTATING PIECES*********************************************
     ****************************************************************************************************
     * */
    
    // Laying a piece step 1 - Fills the blocks where the user clicks on the board initially
    public void holdMarkerAboveBoard(int x, int y)
    {
    	//just looks at what piece is selected, the players color, and uses the input x and y to place the piece over the board
    	//it can just go over anything, since its not placed down yet.
    	String pieceName = pieceMenu.getSelectedItem().toString();
    	this.selectedPiece = getPiece(pieceName);
    	int[] xValues = this.selectedPiece.xValues;
    	int[] yValues = this.selectedPiece.yValues;
    	int blocks = xValues.length;
    	String color = this.currentGame.playerColor;
    	int[] rgb = getColorRGB(color);
    	
    	for (int i=0; i<blocks;i++)
    	{
    		int thisX = x + xValues[i];
    		int thisY = y + yValues[i];
    		System.out.println("filling block (" + thisX + ", " + thisY + ")");
    		
    		if (!(thisX > 19 || thisY > 19 || thisX < 0 || thisY < 0))
    			this.gridSquares[thisY][thisX].setColorWithRGB(rgb, color, pieceName);
    	}
    }
    
    public Piece getPiece(String name)
    {
    	Piece thePiece;
    	for (Piece x:this.piecesLeft[0])
    	{
    		//System.out.println("comparing " + x.description + " and " + name);
    		if (x.description.equals(name))
    		{
    			//System.out.println("found a match");
    			return x;
    		}
    	}
    	return null;
    }
    
    /*
     ****************************************************************************************************
     *********************DEALING WITH USER INPUT********************************************************
     ****************************************************************************************************
     * */
    
    // This is run every time a button is clicked
    public void actionPerformed (ActionEvent aevt)
	{
    	// get the object that was selected in the gui
		Object selected = aevt.getSource();
		if (selected.equals( startGameButton)){
			String[] game = {
					gamePlayerAmount.getSelectedItem().toString(), 
					gameDifficulty.getSelectedItem().toString(), 
					gameScoreType.getSelectedItem().toString(), 
					gamePlayerColor.getSelectedItem().toString(), 
					gameColorSettings.getSelectedItem().toString()
					};
            GameSettings thisGame = new GameSettings(game[0], game[1], game[2], game[3], game[4]);
			if (this.checkGameSettings(thisGame))
			{
				System.out.println("Settings are correct, starting the game");
				this.currentGame = thisGame;
				// assign a color to each player
				this.createThePlayers();
				// fill the array of players pieces
				this.createPlayersPieces();
				this.startGame();
			}
			else 
			{
				System.out.println("The settings are incorrect");
			}
		}
		else if(selected.equals( menuButton)) 
        {
            ShowMainMenu();
        }
		else if (selected.equals( gameColorSettings))
		{
			if (gameColorSettings.getSelectedItem().toString() == "Off")
			{
				System.out.println("action was made for color settings");
				String[] colors = {"<Select>", "Red", "Blue", "Green", "Yellow" };
				gamePlayerColor.removeAllItems();
				for (String x: colors)
				{
					gamePlayerColor.addItem(x);
				}
			}
			else if (gameColorSettings.getSelectedItem().toString() == "On")
			{
				System.out.println("action was made for color settings");
				String[] colors = {"<Select>", "Teal", "Purple", "Yellow", "Red" };
				gamePlayerColor.removeAllItems();
				for (String x: colors)
				{
					gamePlayerColor.addItem(x);
				}
			}
		}
		HouseKeeping();		
	}
    
    // This runs every time the mouse is clicked, but not on a button
 	public void mouseClicked(MouseEvent mevt)
 	{      
 			Object selected = mevt.getSource();
             if ( selected instanceof GridSquare2)
             {
            	 //((GridSquare2) selected).setColorWithRGB(getColorRGB(this.currentGame.playerColor), currentGame.playerColor, selectedPiece.color);
            	 int thisX = ((GridSquare2) selected).xcoord;
                 int thisY = ((GridSquare2) selected).ycoord;
                 holdMarkerAboveBoard(thisX, thisY);
                 System.out.println("clicked at x:" + thisX + ", y:" + thisY);
                 HouseKeeping();
                 repaint();	
             }	
 			
 	}
    
 	/*
     ****************************************************************************************************
     *********************THE END OF THE CODE************************************************************
     ****************************************************************************************************
     * */
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
    /*
     * ================================================================================
     * ================================================================================
     * =========ALL BELOW THIS HAS NOT BEEN INTEGRATED YET WITH WHAT IS ABOVE==========
     * ================================================================================
     * ================================================================================
     * */
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
    
    public void RefreshGameMenu()
    {
        topPanel.removeAll();
        topLabel = new JLabel("Current turn: ");
        if (this.isPlaying)
            infoLabel = new JLabel("Player");
        else
            infoLabel = new JLabel("Opponent");
        
        menuButton = new JButton("Main Menu");
        menuButton.addActionListener( this);
        topPanel.add ( topLabel);
        topPanel.add ( infoLabel);
        topPanel.add ( menuButton); 
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable( false);
        setVisible( true);
    }
    
    public void restartGame()
    {
        startGame();
    }
    
    public void ShowWinnerMenu()
    {
        topPanel.removeAll();
        bottomPanel.removeAll();  
        
        if (this.isPlaying)
            infoLabel = new JLabel("You are the winner!");
        else
            infoLabel = new JLabel("The opponent has defeated you!");
        
        menuButton = new JButton("Main Menu");
        menuButton.addActionListener( this);
        replayButton = new JButton("Play again?");
        replayButton.addActionListener( this);
        
        topPanel.add ( infoLabel);
        topPanel.add ( replayButton);     
        topPanel.add ( menuButton);
                  
        HouseKeeping();
    }
    
    public void selectPlayer(){
        Random rand=new Random();
        int n = rand.nextInt();
        if (n % 2 == 0)
        {
            isPlaying = true;
            System.out.println("the player starts=======================================");
            ShowInGameMenu();
        }
        else
        {
            isPlaying = false;
            System.out.println("the ai starts===========================================");
            
        }
        
        
    }
    /*    
	public void startGame(int rows, int cols)
        {            
            bottomPanel.removeAll();    

            this.selectPlayer();
            
            // create the buttons and add them to the grid
            y = rows;
            x = cols;
            
            this.squaresLeft = x * y;
            
            System.out.println("x is " + x + ", and rows is " + rows);
            int sizeX = rows * 100;
            int sizeY = cols * 100;
            System.out.println("x is " + x + ", sizeX is " + sizeX + ", and rows is " + rows);
            
            bottomPanel.setLayout( new GridLayout( x, y));
            bottomPanel.setSize(500,500);
            
            this.setSize(sizeX,sizeY);
            //bottomPanel.setSize(sizeX,sizeY);    
            
            System.out.println("x value is " + x + " and y value is " + y);
            gridSquares = new GridSquare2 [x][y];
            gridXSize = x;
            gridYSize = y;
            //gridSquares = new GridSquare2 [rows][cols];
           
            for ( int column = 0; column < x; column ++)
            {
                for ( int row = 0; row < y; row ++)
                {
                    //notEatenYet [column][row] = true
                    gridSquares [column][row] = new GridSquare2( row,column);
                    gridSquares [column][row].setSize( 20, 20);
                    gridSquares [column][row].setColor( column + row);

                    gridSquares [column][row].addMouseListener( this);		// AGAIN, don't forget this line!

                    bottomPanel.add( gridSquares [column][row]);

                    Dimension size = gridSquares [column][row].getSize();
                    double sizeX1 = size.width;
                    double sizeY1 = size.height;
                    //System.out.println("position " + column + "," + row +  ", next square created, sizex:" + sizeX1 + ", sizey:" + sizeY1);
                }
            }

            // housekeeping : behaviour
            setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
            setResizable( false);
            setVisible( true);
            
            if (!this.isPlaying)
                opponentTurnStart();
        }
        */

	
	
        public void gameOver()
        {
            System.out.println(" the winner is based on " + this.isPlaying);
            ShowWinnerMenu();
        }
        
        /*
        
        public void changeSquareCount(int count)
        {
            //this.squaresLeft -= count;
            int c = 0;
            for (int i=0; i<this.gridXSize;i++)
            {
                for (int j=0; j<this.gridYSize; j++)
                {
                    if (gridSquares[i][j].notEatenYet)
                    {
                        c++;
                    }
                }
            }
            this.squaresLeft = c;
        }
        
        
        public void opponentTurnStart()
        {
            
            if (squaresLeft < 2)
            {
                this.isPlaying = true;
                gameOver();
                return;
            }
            else if (squaresLeft == 2)
            {
                this.isPlaying = false;
                gameOver();
                return;
            }
            
            System.out.println("Opponent turn begins ========================================");
            
            this.isPlaying = false;
            ShowInGameMenu();
            //HouseCleaning();
            
            
            long start = System.currentTimeMillis();
            long current;
            long runTime = 0;
            while (runTime < 1000) { // 1000 milliseconds or 1 second
                current = System.currentTimeMillis();
                runTime = current - start;
            }

            //yea the AI should just get a list of the blocks left and pick at random
            boolean squareNotFound = true;
            int thisX = 0;
            int thisY = 0;
            
            int checks = 0;
            while (squareNotFound)
            {
                Random rand = new Random();
                //i just need to guess random numbers in the range.
                
                int vx = this.gridXSize - 1;
                int vy = this.gridYSize - 1;
                thisX = rand.nextInt(vx + 1 - 0);
                thisY = rand.nextInt(vy + 1 - 0);
                
                
                System.out.println(this.gridXSize + ", " + 0 + "and the return value was " + thisX);
                System.out.println(this.gridYSize + ", " + 0 + "and the return value was " + thisY);
                //thisY = rand.nextInt(((this.gridYSize-1) - 0) + 1) + 0;

                //thisX = rand.nextInt(this.gridXSize-1);
                //thisY = rand.nextInt(this.gridYSize-1);
                System.out.println("checking " + thisX + ", " + thisY);
                System.out.println("the range is " + this.gridXSize + ", " + this.gridYSize);
                System.out.println("the actual array size is  " + this.gridSquares[0].length + ", "+ this.gridSquares.length);
                //System.out.println("is it eaten yet1? " + gridSquares [thisY][thisX].notEatenYet);
                System.out.println("is it eaten yet2? " + gridSquares [thisX][thisY].notEatenYet);     
                if (gridSquares [thisX][thisY].notEatenYet)
                {
                    System.out.println("it was true, so while loop should now end." + thisX + ", " + thisY);
                    if (thisX == 0 && thisY ==0)
                    {
                        System.out.println("Both values are 0, pass");
                    }
                    else{
                        System.out.println("square set to false");
                        squareNotFound = false;
                    }
                }
            }
            //THEN PLAYER TAKES TURN HERE

            System.out.println("OPPONENTS TURN IS IN " + thisX + ", " + thisY);
            //((GridSquare2) selected).eatPiece();
            //get current position of soaps x and y. compare to max x and y.
            System.out.println("x " + thisX + ", " +  this.gridXSize);
            System.out.println("x " + thisY + ", " +  this.gridYSize);
            int count = 0;
            for (int i=thisY; i < this.gridXSize; i++)
            {
                for (int j=thisX; j < this.gridYSize; j++)
                {
                    //System.out.println("changing position " + i + "," + j);
                    gridSquares [i][j].eatPiece();
                    count++;
                }
            }
            changeSquareCount(count);
            System.out.println("Squares left:" + this.squaresLeft);

            if (this.squaresLeft < 2)
            {
                gameOver();
            }
            playerTurnStart();
            
        }
        */
        
        public void playerTurnStart()
        {
            this.isPlaying = true;
            ShowInGameMenu();
            System.out.println("Player turn begins ========================================");
        }
        
        
        
      
        
	// not used but must be present to fulfil MouseListener contract
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}



