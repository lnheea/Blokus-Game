/**
 * CS 2005
 * Iteration 2
 * Group 7
 * Members:
 * Jeremy Eustace - 20194855
 */
package cs2005_final;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Timer;

//import java.util.concurrent.TimeUnit;

/*
 *  the main window of the gui
 *  notice that it extends JFrame - so we can add our own components
 *  notice that it implements ActionListener - so we can handle user input
 *  
 *   
 *  
 *   1656 lines of code, November 3rd
 *  
 */
public class WindowStart extends JFrame implements ActionListener, MouseListener, KeyListener
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
	private boolean			pieceIsDown; // On when the piece had been laid on the board
	private Piece			selectedPiece; // the piece that the user is trying to place
	private Piece[][] 		piecesLeft; // 2d array containing each usable piece
	private Block[][]		theBoard; // 2d array for each board square
	private Player[] 		thePlayers; // array for each player
	private int[] 			playerColors; // array containing 4 RGBs, one for each player
	private GridSquare2[][] gridSquares; // 2d array of blocks for the board
	private boolean 		gameOver;
	/*
	 *******************************************GLOBAL VARIABLES FOR THE UI
	 * */	
	private JPanel 			topPanel, centerPanel, bottomPanel;
	//combo boxes for main menu---------------
	private JComboBox 		gamePlayerAmount1, gamePlayerAmount2, gamePlayerAmount3, gameDifficulty, gameScoreType, gamePlayerColor, gameColorSettings;
	private JButton 		startGameButton, resumeLastGameButton;
	private JLabel 			menuErrors;
	//for in game-------------------------------
	private JLabel			selectPieceText, topLabel, turnText;
	private JComboBox 		pieceMenu;
	private JButton 		rotateCW, rotateCCW, Up, Down, Left, Right, placePiece, saveYes, saveNo, quit, hint, cancelQuit, giveUpButton;
	private JRadioButton 		p1, p2, p3, p4, p5, p6, p7;
	private JRadioButton 		p8, p9, p10, p11, p12, p13, p14;
	private JRadioButton 		p15, p16, p17, p18, p19, p20, p21;
	//-----------------------------------------
	
	private ImageIcon			icon1, icon2, icon3, icon4, icon5, icon6, icon7;
	private ImageIcon			icon8, icon9, icon10, icon11, icon12, icon13, icon14;
	private ImageIcon			icon15, icon16, icon17, icon18, icon19, icon20, icon21;
	
	//Icon icon = FileSystemView.getFileSystemView().getSystemIcon( File );
	
	
	
	/*
	 *******************************************THINGS THAT MAY BE DELETED
	 * */	
	private JLabel 			nextLabel;// a text label to appear in the top panel
	private JLabel 			infoLabel;
    private JButton 		topButton;// a 'reset' button to appear in the top panel
    private JButton 		firstButton;
    private JButton			secondButton;
    private JButton 		thirdButton;
    private JButton 		replayButton;
    
    private int 			x,y;
    private int 			gridXSize;
    private int 			gridYSize;// the size of the grid
    private int 			squaresLeft;
    //==============================================================
       
    /*
     ****************************************************************************************************
     *********************SELECTING PIECES IN THE BOTTOM********************************************
     ****************************************************************************************************
     * */
    
 // this selects the next available piece automatically, after a piece has been placed
    public void setNextPiece()
    {
    	if (p1.isEnabled())
    		p1.setSelected(true);
    	else if (p2.isEnabled())
    		p2.setSelected(true);
    	else if (p3.isEnabled())
    		p3.setSelected(true);
    	else if (p4.isEnabled())
    		p4.setSelected(true);
    	else if (p5.isEnabled())
    		p5.setSelected(true);
    	else if (p6.isEnabled())
    		p6.setSelected(true);
    	else if (p7.isEnabled())
    		p7.setSelected(true);
    	else if (p8.isEnabled())
    		p8.setSelected(true);
    	else if (p9.isEnabled())
    		p9.setSelected(true);
    	else if (p10.isEnabled())
    		p10.setSelected(true);
    	else if (p11.isEnabled())
    		p11.setSelected(true);
    	else if (p12.isEnabled())
    		p12.setSelected(true);
    	else if (p13.isEnabled())
    		p13.setSelected(true);
    	else if (p14.isEnabled())
    		p14.setSelected(true);
    	else if (p15.isEnabled())
    		p15.setSelected(true);
    	else if (p16.isEnabled())
    		p16.setSelected(true);
    	else if (p17.isEnabled())
    		p17.setSelected(true);
    	else if (p18.isEnabled())
    		p18.setSelected(true);
    	else if (p19.isEnabled())
    		p19.setSelected(true);
    	else if (p20.isEnabled())
    		p20.setSelected(true);
    	else if (p21.isEnabled())
    		p21.setSelected(true);
    }
    
    // this disables a piece button after it has been used
    public void disableButtonByName(String name)
    {
    	// 123 remove this after I have the other thing working
    	
    	switch(name)
    	{
    	case "1- Single": p1.setEnabled(false); break;
    	case "2- Double": p2.setEnabled(false); break;
    	case "3- Line": p3.setEnabled(false); break;
    	case "3- L Shape": p4.setEnabled(false); break;
    	case "4- Z Shape": p5.setEnabled(false); break;
    	case "4- Box": p6.setEnabled(false); break;
    	case "4- T": p7.setEnabled(false); break;
    	case "4- L Shape": p8.setEnabled(false); break;
    	case "4- Line": p9.setEnabled(false); break;
    	case "5- Thumb": p10.setEnabled(false); break;
    	case "5- C Shape": p11.setEnabled(false); break;
    	case "5- T Shape": p12.setEnabled(false); break;
    	case "5- Z Shape": p13.setEnabled(false); break;
    	case "5- S Shape": p14.setEnabled(false); break;
    	case "5- Bent T": p15.setEnabled(false); break;
    	case "5- L Shape": p16.setEnabled(false); break;
    	case "5- Staircase": p17.setEnabled(false); break;
    	case "5- Cross": p18.setEnabled(false); break;
    	case "5- Line": p19.setEnabled(false); break;
    	case "5- Right Angle": p20.setEnabled(false); break;
    	case "5- Line type 2": p21.setEnabled(false); break;
    	default: break;
    	}
    	
    	this.setNextPiece();
    	/*
    	"1- Single"
    	"2- Double"
    	"3- Line"
        "3- L Shape"
        "4- Z Shape"
        "4- Box"
        "4- T"
        "4- L Shape"
        "4- Line"
    	"5- Thumb"
    	"5- C Shape"
    	"5- T Shape"
    	"5- Z Shape"
    	"5- S Shape"
    	"5- Bent T"
    	"5- L Shape"
    	"5- Staircase"
    	"5- Cross"
    	"5- Line"
    	"5- Right Angle"
    	"5- Line type 2"
    	*/
    }
    
    // this gets the string for the button that is currently clicked
    public String getSelectedButton()
    {
    	if (p1.isSelected())
    		return "1- Single"; // put actual names of the blocks that I used here
    	if (p2.isSelected())
    		return "2- Double";
    	if (p3.isSelected())
    		return "3- Line";
        if (p4.isSelected())
    		return "3- L Shape";
        if (p5.isSelected())
    		return "4- Z Shape";
        if (p6.isSelected())
    		return "4- Box";
        if (p7.isSelected())
    		return "4- T";
        if (p8.isSelected())
    		return "4- L Shape";
        if (p9.isSelected())
    		return "4- Line";
    	if (p10.isSelected())
    		return "5- Thumb";
    	if (p11.isSelected())
    		return "5- C Shape";
    	if (p12.isSelected())
    		return "5- T Shape";
    	if (p13.isSelected())
    		return "5- Z Shape";
    	if (p14.isSelected())
    		return "5- S Shape";
    	if (p15.isSelected())
    		return "5- Bent T";
    	if (p16.isSelected())
    		return "5- L Shape";
    	if (p17.isSelected())
    		return "5- Staircase";
    	if (p18.isSelected())
    		return "5- Cross";
    	if (p19.isSelected())
    		return "5- Line";
    	if (p20.isSelected())
    		return "5- Right Angle";
    	if (p21.isSelected())
    		return "5- Line type 2";
    	return "Error: Somehow there was not a return value";
    }
    
    // This is just for loading a png image
    protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} 
		else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

    // this loads all icons that will be needed
    public void getAllPieceImages()
    {
    	icon1 = createImageIcon("icons/1.png", "1");
    	icon2 = createImageIcon("icons/2.png", "2"); 
    	icon3 = createImageIcon("icons/3.png", "3");
    	icon4 = createImageIcon("icons/4.png", "4");
    	icon5 = createImageIcon("icons/5.png", "5");
    	icon6 = createImageIcon("icons/6.png", "6");
    	icon7 = createImageIcon("icons/7.png", "7");
    	icon8 = createImageIcon("icons/8.png", "8"); 
    	icon9 = createImageIcon("icons/9.png", "9");
    	icon10 = createImageIcon("icons/10.png", "10");
    	icon11 = createImageIcon("icons/11.png", "11"); 
    	icon12 = createImageIcon("icons/12.png", "12"); 
    	icon13 = createImageIcon("icons/13.png", "13"); 
    	icon14 = createImageIcon("icons/14.png", "14");
    	icon15 = createImageIcon("icons/15.png", "15");
    	icon16 = createImageIcon("icons/16.png", "16");
    	icon17 = createImageIcon("icons/17.png", "17");
    	icon18 = createImageIcon("icons/18.png", "18");
    	icon19 = createImageIcon("icons/19.png", "19");
    	icon20 = createImageIcon("icons/20.png", "20");
    	icon21 = createImageIcon("icons/21.png", "21");
    }
    
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
		//System.out.println("Creating the players pieces =======================================");
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
		//System.out.println("players color is " + currentGame.playerColor);
		int[] test = this.getColorRGB("Green");
		for (int x : test)
		{
			//System.out.println("Value is " + x);
		}
	}
	
	// Creating the players, and assigning colors to them
	public void createThePlayers(String playerInfo1, String playerInfo2, String playerInfo3)
	{
		//System.out.println("Creating the players  ========================================================");
		if (currentGame.colorOption) {
			//System.out.println("setting alternate colors");
			String [] allColors = new String[] {"Teal", "Purple", "Yellow", "Red" };
			thePlayers = new Player[currentGame.size];
			int count = 0;
			for (int i=0; i < currentGame.size; i++)
			{
				if (i==0) {
					thePlayers[i] = new Player(i, true, false, this.currentGame.playerColor, "Human");
					allColors = removeElement(allColors, this.currentGame.playerColor);
				}
				else if (i==1)
					thePlayers[i] = new Player(i, true, false, allColors[count], playerInfo1);
				else if (i==2)
					thePlayers[i] = new Player(i, true, false, allColors[count], playerInfo2);
				else if (i==3)
					thePlayers[i] = new Player(i, true, false, allColors[count], playerInfo3);
				if (i != 0 )
					count ++;
				/*
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
				*/

			}
		}
		else
		{
			//System.out.println("setting main colors");
			String [] allColors = new String[] {"Green", "Yellow", "Blue", "Red" };
			thePlayers = new Player[currentGame.size];
			int count = 0;
			for (int i=0; i < currentGame.size; i++)
			{
				if (i==0) 
				{
					thePlayers[i] = new Player(i, true, false, this.currentGame.playerColor, "Human");
					allColors = removeElement(allColors, this.currentGame.playerColor);
				}
				else if (i==1)
					thePlayers[i] = new Player(i, true, false, allColors[count], playerInfo1);
				else if (i==2)
					thePlayers[i] = new Player(i, true, false, allColors[count], playerInfo2);
				else if (i==3)
					thePlayers[i] = new Player(i, true, false, allColors[count], playerInfo3);
				if (i != 0 )
					count ++;
				/*
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
				 */
			}
		}
	}
	
	/*
     ****************************************************************************************************
     *********************SIMPLE OPERATIONS FOR CONVINIENCE**********************************************
     ****************************************************************************************************
     * */
	
	public void showGameStatus()
	{
		System.out.println();
		System.out.println("__________________________________________________");
		System.out.print("GAME STATUS - Players_Left:" + this.playersLeft()+ ", ");
		System.out.print("Player:" + this.currentTurn+ ", ");
		System.out.print("Turn:" + this.turnCount);
		System.out.println("-------------------------------------------------");
	}
	
	public int playersLeft()
	{
		int count = this.currentGame.size;
		for(Player x: this.thePlayers)
		{
			if (x.isOut)
				count--;
		}
		return count;
	}
	
	// this prints what is on the grid at the moment
	public void debugGridSquares()
    {
		System.out.println("========================================================================================================");
		System.out.println("========================================================================================================");
    	System.out.println("WHAT IS IN THE GRID RIGHT NOW FOR TURN "+this.currentTurn+"===============================================================");
    	System.out.println();
    	
    	for (int col=0; col<20; col++)
    	{
    		for (int row=0; row<20; row++)
    		{
    			System.out.print("index[" + row + "," + col + "]" + "(" + this.gridSquares[row][col].xcoord + "," + this.gridSquares[row][col].ycoord + ")=" + this.gridSquares[row][col].color + ", ");
    		}
    		System.out.println();
    	}
    	this.debugSelectedPiece();
    }
	
	public void debugSelectedPiece()
	{
		if (this.selectedPiece != null) {
			System.out.println("========================================================================================================");
	    	System.out.println("THIS IS THE SELECTED PIECE RIGHT NOW====================================================================");
	    	System.out.println("name: " + this.selectedPiece.description);
	    	System.out.print(", color: " + this.selectedPiece.color);
	    	System.out.print(", pivot: (" + this.selectedPiece.xPivot + "," + this.selectedPiece.yPivot + ")");
	    	System.out.println();
	    	//System.out.print( this.selectedPiece  + "," +  + ", " +  + ", ");
	    	System.out.print("All points: ");
	    	for (int i=0; i< this.selectedPiece.xValues.length; i++)
	    	{
	    		System.out.print( "(" + (this.selectedPiece.xValues[i] + this.selectedPiece.xPivot)  + "," + (this.selectedPiece.yValues[i] + this.selectedPiece.yPivot) + "), ");
	    	}
	    	System.out.println();
    	}
		System.out.println("========================================================================================================");
		System.out.println("========================================================================================================");
	}
	
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
		pieceIsDown = false; // On when the piece had been laid on the board
		selectedPiece = null; // the piece that the user is trying to place
		piecesLeft = null; // 2d array containing each usable piece
		theBoard = null; // 2d array for each board square
		thePlayers = null; // array for each player
		playerColors = null; // array containing 4 RGBs, one for each player
		gridSquares = null; // 2d array of blocks for the board
		
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
    	//System.out.println("Print error: " + errorMessage);
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
	
	public int getPieceIndexByName(String name)
	{
		switch(name)
    	{
    	case "1- Single": return 0;
    	case "2- Double": return 1;
    	case "3- Line": return 2;
    	case "3- L Shape": return 3;
    	case "4- Z Shape": return 4;
    	case "4- Box": return 5;
    	case "4- T": return 6;
    	case "4- L Shape": return 7;
    	case "4- Line": return 8;
    	case "5- Thumb": return 9;
    	case "5- C Shape": return 10;
    	case "5- T Shape": return 11;
    	case "5- Z Shape": return 12;
    	case "5- S Shape": return 13;
    	case "5- Bent T": return 14;
    	case "5- L Shape": return 15;
    	case "5- Staircase": return 16;
    	case "5- Cross": return 17;
    	case "5- Line": return 18;
    	case "5- Right Angle": return 19;
    	case "5- Line type 2": return 20;
    	default: return -1;
    	}
	}
	
	// This removes a piece from a players inventory
	public void removePieceByName(int player, String name)
	{
		this.piecesLeft[player][getPieceIndexByName(name)].isPlaced = true;
		
		for (int i=0; i < 21; i++)
			if (piecesLeft[player][i].description.equals(name))
				piecesLeft[player][i].placeDown();
		if (this.currentTurn == 0)
			this.startGameFillPieces();
	}
	
	// this class basically fixes it so its not all frozen after a change
    public void HouseKeeping()
    {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable( false);
        setVisible( true);
    }
	   
    // Rotates the current piece, but only in its variable, not on the screen.
    public void rotateSelectedPiece()
    {
    	//1. change all the current x,y positions back to their original block color on the board
    	this.clearCurrentPieceOnBoard();
    	//2. change the x,y positions inside the current piece
    	int changeX = 0;
    	int changeY = 0;
    	int total = this.selectedPiece.xValues.length;	
		for (int i=0; i<total; i++)
		{
			String theseValues = Arrays.toString(new int[] {this.selectedPiece.xValues[i], this.selectedPiece.yValues[i]});
			switch(theseValues)
			{
				//For squares horizontally/vertically 1 block from the pivot
				case"[1, 0]":this.rotatePieceSetValues(0, 1, i); break;
				case"[0, 1]":this.rotatePieceSetValues(-1, 0, i); break;
				case"[-1, 0]":this.rotatePieceSetValues(0, -1, i); break;
				case"[0, -1]":this.rotatePieceSetValues(1, 0, i); break;
				//For squares 1 up/down, and 1 left/right from the pivot
				case"[1, 1]":this.rotatePieceSetValues(-1, 1, i); break;
				case"[-1, 1]":this.rotatePieceSetValues(-1, -1, i); break;
				case"[-1, -1]":this.rotatePieceSetValues(1, -1, i); break;
				case"[1, -1]":this.rotatePieceSetValues(1, 1, i); break;
				//For squares horizontally/veritcally 2 blocks from the pivot
				case"[2, 0]":this.rotatePieceSetValues(0, 2, i); break;
				case"[0, 2]":this.rotatePieceSetValues(-2, 0, i); break;
				case"[-2, 0]":this.rotatePieceSetValues(0, -2, i); break;
				case"[0, -2]":this.rotatePieceSetValues(2, 0, i); break;
				case"[0, 0]": break;
				default: System.out.println("Something was not found " + theseValues); break;
			}
		}
    	
    }
    
    // Returns the usable pieces, ordered from largest to smallest
    
    public ArrayList<Piece> remainingPieces()
    {
    	ArrayList<Piece> returnValues = new ArrayList<Piece>();
    	/*
    	for (Piece x: this.piecesLeft[this.currentTurn])
    	{
    		if (!x.isPlaced) {
    			returnValues.add(x);
    		}
    	}
    	*/
    	for (int i=this.piecesLeft[this.currentTurn].length - 1; i > -1; i--)
    	{
    		if (!this.piecesLeft[this.currentTurn][i].isPlaced) {
    			returnValues.add(this.piecesLeft[this.currentTurn][i]);
    		}
    	}
    	return returnValues;
    }
    
    
    /*
     ****************************************************************************************************
     *********************1. INITIALIZING THE MAIN GAME WINDOW AT START**********************************
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
			
		this.getAllPieceImages(); // run this so the images all exist
		
		ShowMainMenu();
		
		// Adding key listener
		this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
	}
    
    /*
     ****************************************************************************************************
     *********************EXITING AND SAVING*************************************************************
     ****************************************************************************************************
     * */
    
    
    public void writeAllVariablesToTextFile() throws FileNotFoundException
    {
    	String gameData = "Save-File \n";
    	
    	//saving the same settings
    	gameData = gameData + "Settings|";
		gameData = gameData + this.currentGame.size + "|" + this.currentGame.difficulty + "|" + this.currentGame.advancedScoring + "|"; 
		gameData = gameData + this.currentGame.playerColor + "|" + this.currentGame.colorOption + "|" + this.currentGame.currentTurn + "\n";
		
		//Saving each player
		for (Player x: this.thePlayers)
    	{
			gameData = gameData + "Player|";
			gameData = gameData + x.playerNumber + "|"  + x.isPlayer + "|"  + x.isOut + "|"  + x.pieceColor + "|"  + x.AI +  "\n";
    	}
    	
    	//Saving each piece
    	for (Piece[] x: this.piecesLeft)
    	{
    		for (Piece y: x)
    		{
    			gameData = gameData + "Piece|";
    			gameData = gameData + y.description + "|"  + y.color + "|"  + y.isPlaced + "\n";
    		}
    	}
    	
    	//Saving the board
    	for (GridSquare2[] x: this.gridSquares)
    	{
    		for (GridSquare2 y: x)
    		{
    			gameData = gameData + "Block|";
    			gameData = gameData + y.xcoord + "|"  + y.ycoord + "|"  + y.unfilled + "|"  + y.color + "|" + y.origR + "|";
    			gameData = gameData + y.origG + "|"  + y.origB + "|"  + y.tempR + "|"  + y.tempG + "|"  + y.tempB + "|"  + y.tempColor + "\n";
    		}
    	}
    	
    	// Writing the data to the save file
    	try (PrintStream out = new PrintStream(new FileOutputStream("saveFile.txt"))) {
    	    out.print(gameData);
    	    out.flush();
    	    out.close();
    	}
    	
    }
    
    public void saveTheLastGameAndExit() throws FileNotFoundException
    {
    	this.writeAllVariablesToTextFile();
    	this.ShowMainMenu();
    }
    
    public void exitWithoutSaving()
    {
    	this.ShowMainMenu();
    }
    
    public void cancelTheExit()
    {
    	topPanel.removeAll();
    	
        topPanel.setLayout( new GridLayout( 2, 6));

        turnText = new JLabel("                Turn:");
        topLabel = new JLabel("Player");
        
        System.out.println("trying to show in game menu 3");
        giveUpButton = new JButton("Give up");
        giveUpButton.addActionListener( this);
        System.out.println("trying to show in game menu 4");
        
        //topPanel.add ( infoLabel);
        
        System.out.println("trying to show in game menu 5");
        String[] pieceSelect = {"<Select>"};
        pieceMenu = new JComboBox(pieceSelect);
        pieceMenu.addActionListener( this);
        
        
        selectPieceText = new JLabel("Select a piece");
        placePiece = new JButton("Place");
        placePiece.addActionListener(this);
        
        Left = new JButton("Left");
        Left.addActionListener( this);
        Right = new JButton("Right");
        Right.addActionListener( this);
        Up = new JButton("Up");
        Up.addActionListener( this);
        Down = new JButton("Down");
        Down.addActionListener( this);       
        rotateCW = new JButton("CW");
        rotateCW.addActionListener( this);
        rotateCCW = new JButton("CCW");
        rotateCCW.addActionListener( this);
        
        
        
        
        saveYes = new JButton("Yes");
        saveYes.addActionListener( this); 
        saveNo = new JButton("No");
        saveNo.addActionListener( this); 
        cancelQuit = new JButton("Cancel");
        cancelQuit.addActionListener( this); 
        
        topPanel.add ( turnText);
        topPanel.add ( topLabel);
        topPanel.add( placePiece);
        topPanel.add ( hint);
        topPanel.add ( giveUpButton); // this is actually give up
        topPanel.add ( quit);
        //topPanel.add( selectPieceText);
        //topPanel.add( pieceMenu);
        
        topPanel.add(Left);
        topPanel.add(Right);
        topPanel.add(Up);
        topPanel.add(Down);
        topPanel.add(rotateCCW);
        topPanel.add(rotateCW);

        this.colorBlindMode = false;
        
        HouseKeeping();
    }
    
    public void exitTheGame()// shows a menu asking if the user wants to save
    {
    	topPanel.removeAll();
    	
        topPanel.setLayout( new GridLayout( 2, 6));

        turnText = new JLabel("                Turn:");
        topLabel = new JLabel("Player");
        
        System.out.println("trying to show in game menu 3");
        giveUpButton = new JButton("Give up");
        giveUpButton.addActionListener( this);
        System.out.println("trying to show in game menu 4");
        
        //topPanel.add ( infoLabel);
        
        System.out.println("trying to show in game menu 5");
        String[] pieceSelect = {"<Select>"};
        pieceMenu = new JComboBox(pieceSelect);
        pieceMenu.addActionListener( this);
        
        
        selectPieceText = new JLabel("Select a piece");
        placePiece = new JButton("Place");
        placePiece.addActionListener(this);
        
        Left = new JButton("Left");
        Left.addActionListener( this);
        Right = new JButton("Right");
        Right.addActionListener( this);
        Up = new JButton("Up");
        Up.addActionListener( this);
        Down = new JButton("Down");
        Down.addActionListener( this);       
        rotateCW = new JButton("CW");
        rotateCW.addActionListener( this);
        rotateCCW = new JButton("CCW");
        rotateCCW.addActionListener( this);
        
        
        
        
        saveYes = new JButton("Yes");
        saveYes.addActionListener( this); 
        saveNo = new JButton("No");
        saveNo.addActionListener( this); 
        cancelQuit = new JButton("Cancel");
        cancelQuit.addActionListener( this); 
        
        //top row
        topPanel.add ( turnText);
        topPanel.add ( topLabel);
        topPanel.add ( new JLabel("Save game?"));
        topPanel.add ( saveYes);
        topPanel.add ( saveNo);
        topPanel.add ( cancelQuit);
        
        //bottom row
        topPanel.add(Left);
        topPanel.add(Right);
        topPanel.add(Up);
        topPanel.add(Down);
        topPanel.add(rotateCCW);
        topPanel.add(rotateCW);

        this.colorBlindMode = false;
        
        HouseKeeping();
    }
    
    // This loads the last game from a text file
    public void loadLastGame() throws FileNotFoundException
    {
    	// pass the path to the file as a parameter 
        File file = new File("saveFile.txt"); 
        
        Scanner input = new Scanner(System.in);

        input = new Scanner(file);

        String[] settings = new String[5] ;
        ArrayList<String[]> players = new ArrayList<String[]>();
        ArrayList<String[]> pieces = new ArrayList<String[]>();
        ArrayList<String[]> blocks = new ArrayList<String[]>();
        
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] data = line.split("\\|");
            switch(data[0])
            {
            	case "Settings": settings = new String[] {data[1], data[2], data[3], data[4], data[5], data[6]}; break;
            	//case "Player": players.add(new Player(Integer.parseInt(data[1]), Boolean.parseBoolean(data[2]), data[3], data[4])); break;
            	case "Player":players.add(new String[]{data[1], data[2], data[3], data[4], data[5]}); break;
            	case "Piece": pieces.add(new String[]{data[1], data[2], data[3]}); break;
            	case "Block": blocks.add(new String[]{data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11] }); break;
            	default: db("Heading not found: " + data[0]); break;
            }
            System.out.println(line);
        }
        db("players " + players.size());
        db("pieces " + pieces.size());
        db("blocks " + blocks.size());
        input.close();
        
        
        this.startGameFromLoadedFiles(settings, players, pieces, blocks);
        
    }
    
    //unfinished loading class, I will leave it here in case I try to do it later
    public void startGameFromLoadedFiles(String[] settings, ArrayList<String[]> players, ArrayList<String[]> pieces, ArrayList<String[]> blocks)
    {
    	this.currentGame = new GameSettings(settings[0], settings[1], settings[2], settings[3], settings[4], Integer.parseInt(settings[5]));
    	this.startGameShowUI(); // 2. show the board and the top panel
    	
		// assign a color to each player
    	
		//this.createThePlayers(playerSettingsTemp[0], playerSettingsTemp[1], playerSettingsTemp[2]);
    	
		// fill the array of players pieces
		
		
    	//this.debugStartGameMessage();
    	
    	this.thePlayers = new Player[this.currentGame.size];
    	
    	// setting the player settings
    	for (int i=0; i<this.currentGame.size; i++)
    	{
    		int playerNumber = Integer.parseInt(players.get(i)[0]);
    		boolean isPlayer = Boolean.parseBoolean(players.get(i)[1]);
    		boolean isOut = Boolean.parseBoolean(players.get(i)[2]);
    		String pieceColor = players.get(i)[3];
    		String AI;
    		if (Boolean.parseBoolean(players.get(i)[4]))
    			AI = "AI";
    		else
    			AI = "Human";
    			
    		//boolean AI = Boolean.parseBoolean(players.get(i)[1]);
    		
    		this.thePlayers[i] = new Player(playerNumber, isPlayer, isOut, pieceColor, AI ); 
    	}
    	
    	this.createPlayersPieces();
    	
    	for (int i=0; i<this.currentGame.size; i++)
    	{
    		
    		
    		//setting each of this players pieces
    		for (int j=0; j<21;j++)
    		{
    			this.piecesLeft[i][j].description = pieces.get(i)[0];
    			this.piecesLeft[i][j].color = pieces.get(i)[1];
    			this.piecesLeft[i][j].isPlaced = Boolean.parseBoolean(players.get(i)[2]);
    		}
    		
    	}
    	
    	
    	
    	//setting each of the blocks
    	for (String[] x: blocks)
    	{
    		int xcoord = Integer.parseInt(x[0]);
    		int ycoord = Integer.parseInt(x[1]);
    		boolean unfilled = Boolean.parseBoolean(x[2]);
    		String color = x[3];
    		int origR = Integer.parseInt(x[4]);
    		int origG = Integer.parseInt(x[5]);
    		int origB = Integer.parseInt(x[6]);
    		int tempR = Integer.parseInt(x[7]);
    		int tempG = Integer.parseInt(x[8]);
    		int tempB = Integer.parseInt(x[9]);
    		String tempColor = x[10];
    		
    		/*
    		this.gridSquares[xcoord][ycoord].xcoord = xcoord;
    		this.gridSquares[xcoord][ycoord].ycoord = ycoord;
    		this.gridSquares[xcoord][ycoord].unfilled = unfilled;
    		this.gridSquares[xcoord][ycoord].color = color;
    		this.gridSquares[xcoord][ycoord].origR = origR;
    		this.gridSquares[xcoord][ycoord].origG = origG;
    		this.gridSquares[xcoord][ycoord].origB = origB;
    		this.gridSquares[xcoord][ycoord].tempR = tempR;
    		this.gridSquares[xcoord][ycoord].tempG = tempG;
    		this.gridSquares[xcoord][ycoord].tempB = tempB;
    		this.gridSquares[xcoord][ycoord].tempColor = tempColor;
    		*/

    	}
    	
    	
    	
    	/*
    	public int xcoord, ycoord;			// not used in this demo, but might be helpful in future...
        public boolean unfilled;
        public String color; // The color of the piece in this spot
        public int origR;
        public int origG;
        public int origB;
        public int tempR;
        public int tempG;
        public int tempB;
        public String tempColor;
        public boolean colorBlindMode;
        public JLabel colorMarker;
        public boolean originalColorSet;
    	 * 
    	 gameData = gameData + "Player|";
		 gameData = gameData + x.playerNumber + "|"  + x.isPlayer + "|"  + x.isOut + "|"  + x.pieceColor + "\n";
    	 * 
    	 * 
    	 * 
    	gameData = gameData + "Block|";
    	gameData = gameData + y.xcoord + "|"  + y.ycoord + "|"  + y.unfilled + "|"  + y.color + "|" + y.origR + "|";
    	gameData = gameData + y.origG + "|"  + y.origB + "|"  + y.tempR + "|"  + y.tempG + "|"  + y.tempB + "|"  + y.tempColor + "\n";
    	
    	*/
    	
    	
    	this.gameOver = false;
    	//System.out.println("Starting the game ==============================================");
    	this.startGameShowButtons(); // 1. this shows the top and bottom buttons to use while playing the game
    	
    	//this.startGameFillPieces(); // 3. fill all the game pieces
    	//this.startGameChooseFirstPlayer(); // 4. choose the first player
        
    	
    	
    	this.nextTurnBegin(); // This will start the loop of turns, that runs until the end of the game
    	
        //Things to add:
        //choose a player to go first, this will always set it to player 0 for now, but still include the function
        
        //Fill the boxes and other stuff with methods called here
        HouseKeeping();
        this.requestFocusInWindow(); // to make key input work
        
        
    }
    
    /*
     ****************************************************************************************************
     *********************ENDING THE GAME ***************************************************************
     ****************************************************************************************************
     * */
    
    // checks if all players are out
    public boolean areAllPlayersOut()
    {
    	int count = 0;
    	for (Player x: this.thePlayers)
    	{
    		if (x.isOut)
    			count++;
    	}
    	if (count == this.currentGame.size)
    		return true;
    	return false;
    }
    
    // starts the end of the game
    public void endTheGame()
    {
    	System.out.println("The game has ended");
    	System.out.println("ALL PLAYERS ARE OUT");
    	this.gameOver = true;
    	int winner = this.findWinner();
    	this.endGameUI(winner);
    }
    
    public int findWinner()
    {
    	db("Finding the winner of the game------------------------------------------------");
    	int[] playerPiecesLeft = new int[this.currentGame.size];
    	for (int i=0; i < this.currentGame.size; i++)
    		playerPiecesLeft[i] = 21;
    	
    	int lowest = 0;
    	int lastLowestValue = 22;
    	for (int i=0; i<this.currentGame.size;i++) // for each player
    	{
    		for (int j=0; j<21; j++) // for each piece
    		{
    			if (this.piecesLeft[i][j].isPlaced)
    			{
    				playerPiecesLeft[i]--;
    			}
    		}
    		int player = i + 1;
    		db("Player " + player + "("+ this.thePlayers[i].pieceColor + ") has " + playerPiecesLeft[i] + " pieces remaining.");
    		
    		if (lastLowestValue > playerPiecesLeft[i])
    		{
    			lowest = i;
    			lastLowestValue = playerPiecesLeft[i];
    		}
    	}

    	db("The winner is " + this.thePlayers[lowest].pieceColor + " player, the " + this.thePlayers[lowest].pieceColor);	
    	return lowest;

    			
    		
    }
    
    public void endGameUI(int winner)
    {

    	this.topPanel.removeAll();
    	
        //topPanel.setLayout( new GridLayout( 2, 6));
    	topPanel.setLayout( new FlowLayout());
    	
        turnText = new JLabel("                The game has ended, the winner is: ");
        topLabel = new JLabel(this.thePlayers[winner].pieceColor);
        saveNo = new JButton("Main menu");
        saveNo.addActionListener( this); 
        /*
        System.out.println("trying to show in game menu 3");
        giveUpButton = new JButton("Give up");
        giveUpButton.addActionListener( this);
        System.out.println("trying to show in game menu 4");
        
        //topPanel.add ( infoLabel);
        
        System.out.println("trying to show in game menu 5");
        String[] pieceSelect = {"<Select>"};
        pieceMenu = new JComboBox(pieceSelect);
        pieceMenu.addActionListener( this);
        
        
        selectPieceText = new JLabel("Select a piece");
        placePiece = new JButton("Place");
        placePiece.addActionListener(this);
        
        Left = new JButton("Left");
        Left.addActionListener( this);
        Right = new JButton("Right");
        Right.addActionListener( this);
        Up = new JButton("Up");
        Up.addActionListener( this);
        Down = new JButton("Down");
        Down.addActionListener( this);       
        rotateCW = new JButton("CW");
        rotateCW.addActionListener( this);
        rotateCCW = new JButton("CCW");
        rotateCCW.addActionListener( this);
        
        
        
        
        saveYes = new JButton("Yes");
        saveYes.addActionListener( this); 
        
        cancelQuit = new JButton("Cancel");
        cancelQuit.addActionListener( this); 
        */
        //top row
        topPanel.add ( turnText);
        topPanel.add ( topLabel);
        topPanel.add ( saveNo);
        /*
        topPanel.add ( new JLabel("Save game?"));
        topPanel.add ( saveYes);
        
        topPanel.add ( cancelQuit);
        
        //bottom row
        topPanel.add(Left);
        topPanel.add(Right);
        topPanel.add(Up);
        topPanel.add(Down);
        topPanel.add(rotateCCW);
        topPanel.add(rotateCW);

        this.colorBlindMode = false;
        */
        HouseKeeping();
    }
    
    /*
     ****************************************************************************************************
     *********************2. INITIALIZING THE MAIN MENU**************************************************
     ****************************************************************************************************
     * */
    
    // this shows the main menu to the user
    public void ShowMainMenu()
    {
    	this.wipeVariables();
    	this.setSize(800,400);
    	//this.setSize(800,800);
    	// Clearing the 3 panels that make up the window
    	topPanel.removeAll();
        bottomPanel.removeAll();
        centerPanel.removeAll(); 
        // Adding the top and bottom bars items
        
        Font f = new Font("TimesRoman",Font.BOLD,30);
        JLabel title = new JLabel("Blokus Main Menu");
        title.setFont(f);
        topPanel.add( title);
        
        //-------------------------BOTTOM PANEL
        startGameButton = new JButton("Start a New Game");
        startGameButton.addActionListener( this);
        resumeLastGameButton = new JButton("Resume the Saved Game");
        resumeLastGameButton.addActionListener( this);	
        bottomPanel.add( startGameButton);
        bottomPanel.add( resumeLastGameButton);
        
        //-------------------------CENTER PANEL
        centerPanel.setLayout( new GridLayout( 5 , 2));
        //Player 1-----------------------------------------
        centerPanel.add( new JLabel("                  Player 2 options"));
        String[] playerInfo = {"AI", "Human" };
        gamePlayerAmount1 = new JComboBox(playerInfo);
        centerPanel.add( gamePlayerAmount1);
        //Player 2-----------------------------------------
        centerPanel.add( new JLabel("                  Player 3 options"));
        playerInfo = new String[] {"<None>", "AI", "Human" };
        gamePlayerAmount2 = new JComboBox(playerInfo);
        centerPanel.add( gamePlayerAmount2);
        //Player 3-----------------------------------------
        centerPanel.add( new JLabel("                  Player 4 options"));
        playerInfo = new String[] {"<None>", "AI", "Human" };
        gamePlayerAmount3 = new JComboBox(playerInfo);
        centerPanel.add( gamePlayerAmount3);
        //Select the difficulty----------------------------------------
        centerPanel.add( new JLabel("                  AI Difficulty"));
        String[] difficulties = {"Easy", "Medium", "Hard" };
        gameDifficulty = new JComboBox(difficulties);
        centerPanel.add( gameDifficulty);   
        //Select the score type----------------------------------------
        centerPanel.add( new JLabel("                  Score Type"));
        String[] scores = {"Basic", "Advanced" };
        gameScoreType = new JComboBox(scores);
        centerPanel.add( gameScoreType);  
        //Select the piece color---------------------------------------
        centerPanel.add( new JLabel("                  Player 1 color"));
        String[] colors = {"<Select>", "Red", "Blue", "Green", "Yellow" };
        gamePlayerColor = new JComboBox(colors);
        centerPanel.add( gamePlayerColor);
        //-------------------------------------------------------------      
        //Colorblind settings
        //note: this should actually change the piece selections
        centerPanel.add( new JLabel("                  Colorblind Setting"));
        colors = new String[] {"Off", "On"};
        gameColorSettings = new JComboBox(colors);
        gameColorSettings.addActionListener( this);
        centerPanel.add( gameColorSettings);
        centerPanel.add( new JLabel(" "));
        menuErrors = new JLabel(" ");
        centerPanel.add( menuErrors);
        //
        //
        HouseKeeping();
    }
    
    /*
     ****************************************************************************************************
     *********************3. INITIALIZING THE START OF THE GAME******************************************
     ****************************************************************************************************
     * */
    
    // This starts the game
    public void startGame()
    {            
    	this.gameOver = false;
    	//System.out.println("Starting the game ==============================================");
    	this.startGameShowButtons(); // 1. this shows the top and bottom buttons to use while playing the game
    	this.startGameShowUI(); // 2. show the board and the top panel
    	this.startGameFillPieces(); // 3. fill all the game pieces
    	this.startGameChooseFirstPlayer(); // 4. choose the first player
        
    	this.nextTurnBegin(); // This will start the loop of turns, that runs until the end of the game
    	
        //Things to add:
        //choose a player to go first, this will always set it to player 0 for now, but still include the function
        
        //Fill the boxes and other stuff with methods called here
        HouseKeeping();
        this.requestFocusInWindow(); // to make key input work
    }
    
    // 1. this shows the top and bottom buttons to use while playing the game
    public void startGameShowButtons()
    {
    	topPanel.removeAll();
        centerPanel.removeAll();
        
        topPanel.setLayout( new GridLayout( 2, 6));
        
    	//System.out.println("trying to show in game menu 1");

        System.out.println("trying to show in game menu 2");

        turnText = new JLabel("                Turn:");
        topLabel = new JLabel("Player");
        
        System.out.println("trying to show in game menu 3");
        
        
        giveUpButton = new JButton("Give up");
        giveUpButton.addActionListener( this);
        
        quit = new JButton("Quit");
        quit.addActionListener( this);
        
        hint = new JButton("Hint");
        hint.addActionListener( this);
        
        System.out.println("trying to show in game menu 4");
        
        //topPanel.add ( infoLabel);
        
        System.out.println("trying to show in game menu 5");
        String[] pieceSelect = {"<Select>"};
        pieceMenu = new JComboBox(pieceSelect);
        pieceMenu.addActionListener( this);
        
        
        selectPieceText = new JLabel("Select a piece");
        placePiece = new JButton("Place");
        placePiece.addActionListener(this);
        
        
        
        
        
        Left = new JButton("Left");
        Left.addActionListener( this);
        Right = new JButton("Right");
        Right.addActionListener( this);
        Up = new JButton("Up");
        Up.addActionListener( this);
        Down = new JButton("Down");
        Down.addActionListener( this);       
        rotateCW = new JButton("CW");
        rotateCW.addActionListener( this);
        rotateCCW = new JButton("CCW");
        rotateCCW.addActionListener( this);
        
        
        topPanel.add ( turnText);
        topPanel.add ( topLabel);
        topPanel.add( placePiece);
        topPanel.add ( hint);
        topPanel.add ( giveUpButton); // this is actually give up
        topPanel.add ( quit);
        //topPanel.add( selectPieceText);
        //topPanel.add( pieceMenu);
        
        topPanel.add(Left);
        topPanel.add(Right);
        topPanel.add(Up);
        topPanel.add(Down);
        topPanel.add(rotateCCW);
        topPanel.add(rotateCW);
        
        // this just shows the pieces of the current player
        refreshPiecesToCurrentPlayer();
        
        this.colorBlindMode = false;
        
        HouseKeeping();
        
    }
    
    // Shows the pieces of the current player
    public void debugPlayerPieces(int player)
    {
    	db("--------Piece for player " + player);
    	

		for (int j=0; j<21; j++)
		{
			dbb("Piece:" + this.piecesLeft[player][j].description + 
					" isPlaced:" + this.piecesLeft[player][j].isPlaced + 
					" Color:" + this.piecesLeft[player][j].color + 
					" X:" + player + " Y:" + j);
		}

    		
    	/*
    	for (Piece[] x: this.piecesLeft)
    	{
    		for (Piece y: x)
    		{
    			dbb("Piece:" + y.description + " isPlaced:" + y.isPlaced + " Color:" + y.color + " X:" + y.x);
    		}
    	}*/
    	
    	db("---------------------");
    	
    }
    
    public void refreshPiecesToCurrentPlayer()
    {
    	//debugPlayerPieces(this.currentTurn);
    	
    	bottomPanel.removeAll();
        bottomPanel.setLayout( new GridLayout( 3, 7));
        
        ButtonGroup group = new ButtonGroup();
        
        int count = 0;
        
        //FOR DEBUGGING
        if (this.currentTurn ==0)
        	db("Testing if piece #1 is placed " + this.piecesLeft[0][1].isPlaced);
        	//db("Testing if piece #1 is placed " + this.piecesLeft[this.currentTurn][count].isPlaced);
        
        p1 = new JRadioButton("<", icon1, false);
        p1.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][0].isPlaced)
        	{p1.setEnabled(false);}
        p2 = new JRadioButton("<", icon2, false);
        p2.addActionListener( this);  
        db("Player is " + this.currentTurn + " and count is now " + count );
        if (this.piecesLeft[this.currentTurn][1].isPlaced)
    		{
        		db("Testing removing piece 2");
	        	p2.setEnabled(false); 
    		}
        p3 = new JRadioButton("<", icon3, false);
        p3.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][2].isPlaced)
    		{p3.setEnabled(false);}
        p4 = new JRadioButton("<", icon4, false);
        p4.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][3].isPlaced)
    		{p4.setEnabled(false);}
        p5 = new JRadioButton("<", icon5, false);
        p5.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][4].isPlaced)
    		{p5.setEnabled(false);}
        p6 = new JRadioButton("<", icon6, false);
        p6.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][5].isPlaced)
    		{p6.setEnabled(false);}
        p7 = new JRadioButton("<", icon7, false);
        p7.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][6].isPlaced)
    		{p7.setEnabled(false);}
        p8 = new JRadioButton("<", icon8, false);
        p8.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][7].isPlaced)
    		{p8.setEnabled(false);}
    	p9 = new JRadioButton("<", icon9, false);
        p9.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][8].isPlaced)
    		{p9.setEnabled(false);}
    	p10 = new JRadioButton("<", icon10, false);
        p10.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][9].isPlaced)
    		{p10.setEnabled(false);}
    	p11 = new JRadioButton("<", icon11, false);
        p11.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][10].isPlaced)
    		{p11.setEnabled(false);}
    	p12 = new JRadioButton("<", icon12, false);
        p12.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][11].isPlaced)
    		{p12.setEnabled(false);}
    	p13 = new JRadioButton("<", icon13, false);
        p13.addActionListener( this);  
        if (this.piecesLeft[this.currentTurn][12].isPlaced)
    		{p13.setEnabled(false);}
    	p14 = new JRadioButton("<", icon14, false);
        p14.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][13].isPlaced)
    		{p14.setEnabled(false);}
    	p15 = new JRadioButton("<", icon15, false);
        p15.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][14].isPlaced)
    		{p15.setEnabled(false);}
    	p16 = new JRadioButton("<", icon16, false);
        p16.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][15].isPlaced)
    		{p16.setEnabled(false);}
    	p17 = new JRadioButton("<", icon17, false);
        p17.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][16].isPlaced)
    		{p17.setEnabled(false);}
    	p18 = new JRadioButton("<", icon18, false);
        p18.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][17].isPlaced)
    		{p18.setEnabled(false);}
    	p19 = new JRadioButton("<", icon19, false);
        p19.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][18].isPlaced)
    		{p19.setEnabled(false);}
    	p20 = new JRadioButton("<", icon20, false);
        p20.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][19].isPlaced)
    		{p20.setEnabled(false);}
    	p21 = new JRadioButton("<", icon21, false);
        p21.addActionListener( this); 
        if (this.piecesLeft[this.currentTurn][20].isPlaced)
    		{p21.setEnabled(false);}

        group.add(p1);
        group.add(p2); 
        group.add(p3);
        group.add(p4);
        group.add(p5); 
        group.add(p6);
        group.add(p7);
        group.add(p8);
        group.add(p9);
    	group.add(p10);
    	group.add(p11);
    	group.add(p12);
    	group.add(p13);
    	group.add(p14);
    	group.add(p15);
    	group.add(p16);
    	group.add(p17);
    	group.add(p18);
    	group.add(p19);
    	group.add(p20);
    	group.add(p21);
        
        bottomPanel.add(p1);
        bottomPanel.add(p2); 
        bottomPanel.add(p3);
        bottomPanel.add(p4);
        bottomPanel.add(p5); 
        bottomPanel.add(p6);
        bottomPanel.add(p7);
        bottomPanel.add(p8);
    	bottomPanel.add(p9);
    	bottomPanel.add(p10);
    	bottomPanel.add(p11);
    	bottomPanel.add(p12);
    	bottomPanel.add(p13);
    	bottomPanel.add(p14);
    	bottomPanel.add(p15);
    	bottomPanel.add(p16);
    	bottomPanel.add(p17);
    	bottomPanel.add(p18);
    	bottomPanel.add(p19);
    	bottomPanel.add(p20);
    	bottomPanel.add(p21);
    	
    	this.setNextPiece(); // sets the selected piece to the next available
    }
    
    // 2. shows the GUI for the board and the top panel
    public void startGameShowUI()
    {
    	int rows = 20;
        int cols = 20;   
        // create the buttons and add them to the grid
        y = rows;
        x = cols;        
        this.squaresLeft = x * y;     
        System.out.println("x is " + x + ", and rows is " + rows);
        int sizeX = rows * 40; // The size of each block, on the x-axis
        int sizeY = cols * 40; // The size of each block, on the y-axis
        System.out.println("x is " + x + ", sizeX is " + sizeX + ", and rows is " + rows);      
        centerPanel.setLayout( new GridLayout( x, y));
           
        //this.setSize(sizeX,sizeY);
        this.setSize(500, 600);
        centerPanel.setSize(60,60);  
        System.out.println("x value is " + x + " and y value is " + y);
        gridSquares = new GridSquare2 [20][20];
        gridXSize = x;
        gridYSize = y;
        //gridSquares = new GridSquare2 [rows][cols];
       
        for ( int row = 0; row < 20; row ++)
        {
            for ( int column = 0; column < 20; column ++)
            {
                if (this.currentGame.colorOption)
                	gridSquares [row][column] = new GridSquare2( row,column, true);
                else
                	gridSquares [row][column] = new GridSquare2( row,column, false);
                gridSquares [row][column].setSize( 20, 20);
                gridSquares [row][column].setEmpty();

                gridSquares [row][column].addMouseListener( this);		// AGAIN, don't forget this line!

                
                
                //centerPanel.add( gridSquares [row][column]);

                Dimension size = gridSquares [row][column].getSize();
                double sizeX1 = size.width;
                double sizeY1 = size.height;
                //System.out.println("position " + column + "," + row +  ", next square created, sizex:" + sizeX1 + ", sizey:" + sizeY1);
            }
        }
        
        for ( int column = 0; column < 20; column ++)
        {
            for ( int row = 0; row < 20; row ++)
            {
            	//centerPanel.add( gridSquares [column][row]);
            	centerPanel.add( gridSquares [row][column]);
            }
        }
        /*
        for ( int column = 0; column < x; column ++)
        {
            for ( int row = 0; row < y; row ++)
            {
                //notEatenYet [column][row] = true
                gridSquares [column][row] = new GridSquare2( row,column);
                gridSquares [column][row].setSize( 20, 20);
                gridSquares [column][row].setEmpty();

                gridSquares [column][row].addMouseListener( this);		// AGAIN, don't forget this line!

                centerPanel.add( gridSquares [column][row]);

                Dimension size = gridSquares [column][row].getSize();
                double sizeX1 = size.width;
                double sizeY1 = size.height;
                //System.out.println("position " + column + "," + row +  ", next square created, sizex:" + sizeX1 + ", sizey:" + sizeY1);
            }
        }
        */
    }
        
    // 3. gives the user their 21 pieces in a drop-down menu
    public void startGameFillPieces() // this is also called whenever you want to refresh the list
    {
		pieceMenu.removeAllItems();
		for (int i=0; i<21;i++)
		{
			if (!piecesLeft[0][i].isPlaced()) // this is here so that the code will also be usable after every piece is placed
				pieceMenu.addItem(piecesLeft[0][i].description);
		}
		this.pieceIsDown = false;
    }

    // 4. choose the first player                                           FIX THIS ONCE AI CAN TAKE TURNS
    public void startGameChooseFirstPlayer()
    {
    	// add code here to select a player randomly to go first, once the AI is capable of taking turns
    	//this.currentTurn = 0;
    	this.turnCount = 0;
    	

    	Random rand = new Random();
    	int n = rand.nextInt(this.currentGame.size);
    	this.currentTurn = n;

    	this.setCurrentPlayerText();
    }
    
    // Sets the text at the top 	to show who is taking a turn
    public void setCurrentPlayerText()
    {
    	int player = this.currentTurn + 1;
    	if (this.thePlayers[this.currentTurn].AI)
    		topLabel.setText("Player " + player + " (AI)");
    	else
    		topLabel.setText("Player " + player);
    }
    
    // Sets the current player taking a turn, to the next in line to take a turn
    public void setNextPlayerTurn()
    {
    	if (this.gameOver)
    		return;
    	if (this.playersLeft() < 1)
    		this.endTheGame();
    	boolean playerNotFound = true;
    	int count = 0;
    	while(playerNotFound) {
	    	this.currentTurn += 1;
	    	if (this.currentTurn == this.currentGame.size)
	    	{
	    		this.currentTurn = 0;
	    	}
	    	int playerNumber = this.currentTurn + 1;
	    	//System.out.println("{{TESTING index=" + this.currentTurn + " playerCount="+ this.currentGame.size+ " players=" + this.currentGame.size +" }}");
	    	if (!this.thePlayers[this.currentTurn].isOut)
	    		playerNotFound = false;
	    	count++;
	    	if (count > (this.currentGame.size * 2))
	    	{
	    		this.endTheGame();
	    	}
    	}
    	this.turnCount += 1;
    	
    }
    
    /*
     ****************************************************************************************************
     *********************LAYING, MOVING AND ROTATING PIECES*********************************************
     ****************************************************************************************************
     * */
    
	// This sets the 'selected piece' variable to whatever is selected in the drop-down menu.
    public void setNewCurrentPiece(int x, int y)
    {
    	String pieceName = this.getSelectedButton();
    	if (this.selectedPiece == null)
    	{
    		this.selectedPiece = getPiece(pieceName);
        	this.selectedPiece.xPivot = x;
        	this.selectedPiece.yPivot = y;
    	}
    	else
    	{
    		this.clearCurrentPieceOnBoard();
    		this.selectedPiece = getPiece(pieceName);
        	this.selectedPiece.xPivot = x;
        	this.selectedPiece.yPivot = y;
    	}
    	
    	//System.out.println("selected piece is " + this.selectedPiece.description + " colored " + this.selectedPiece.color);
    	
    }
    
    // Clears the unplaced current piece off the board, this is just used between each time its moved.
    public void clearCurrentPieceOnBoard()
    {
    	int[] xValues = this.selectedPiece.xValues;
    	int[] yValues = this.selectedPiece.yValues;
    	int length = xValues.length;
    	for (int i=0; i<length;i++)
    	{
    		int thisX = this.selectedPiece.xPivot + xValues[i];
    		int thisY = this.selectedPiece.yPivot  + yValues[i];  		
    		if (!(thisX > 19 || thisY > 19 || thisX < 0 || thisY < 0)) // if its inside the board
    			this.gridSquares[thisX][thisY].setOriginalColor();
    	}
    }
    
    // This lays the selected piece down on the board initially, before it is moved/rotated in place
    public void holdMarkerAboveBoard(int x, int y)
    {
    	//System.out.println("holding marker above board at " + x + ", " + y + ", for: ");
    	
    	this.pieceIsDown = true;
    	selectPieceText.setText("Select a piece");
    	//just looks at what piece is selected, the players color, and uses the input x and y to place the piece over the board
    	//it can just go over anything, since its not placed down yet.
    	int[] xValues = this.selectedPiece.xValues;
    	int[] yValues = this.selectedPiece.yValues;
    	int blocks = xValues.length;
    	String color = this.thePlayers[this.currentTurn].pieceColor; //this.currentGame.playerColor;
    	int[] rgb = getColorRGB(color);
    	
    	for (int i=0; i<blocks;i++)
    	{
    		int thisX = x + xValues[i];
    		int thisY = y + yValues[i];
    		
    		//System.out.print("(" + thisX + "," +thisY + "), ");
    		
    		if (!(thisX > 19 || thisY > 19 || thisX < 0 || thisY < 0)) // if its inside the board
    			this.gridSquares[thisX][thisY].setTempColorWithRGB(rgb, color);
    	}
    	
    	//this.debugSelectedPiece();
    }
    
    // Moves the current piece one space in an of the four directions
    public void movePiece(String direction)
    {
    	int thisX  = this.selectedPiece.xPivot;
    	int thisY = this.selectedPiece.yPivot;
    	//System.out.println("moving piece to the " + direction + " at x" + thisX + " and y" + thisY);
    	if (thisX > 19 && direction.equals("Right"))
    		return;
    	if (thisY > 19 && direction.equals("Down"))
    		return;
    	if (thisX < 0 && direction.equals("Left"))
    		return;
    	if (thisY < 0 && direction.equals("Up"))
    		return;
    	//System.out.println("moving the piece");
    	//1. change all the current x,y positions back to their original block color on the board
    	this.clearCurrentPieceOnBoard();
    	//2. change the x,y positions inside the current piece
    	int changeX = 0;
    	int changeY = 0;
    	switch(direction)
    	{
    		case "Up": changeY = -1; break;
    		case "Down": changeY = 1; break;
    		case "Left": changeX = -1; break;
    		case "Right": changeX = 1; break;
    	}
    	for (int x: this.selectedPiece.xValues)
    	{
    		x += changeX;
    	}
    	for (int y: this.selectedPiece.yValues)
    	{
    		y += changeY;
    	}
    	this.selectedPiece.xPivot += changeX;
    	this.selectedPiece.yPivot += changeY;
    	//3. run the hold mark above board for the new x,y coords
    	
    	if (!this.markerIsOutsideBoard().equals("No"))
    	{
    		movePiece(this.markerIsOutsideBoard());
    	}
    	else {
    		holdMarkerAboveBoard(this.selectedPiece.xPivot, this.selectedPiece.yPivot);
    	}
    	
    	
    	
    }
    
    public String markerIsOutsideBoard()
    {
    	for (int i=0; i<this.selectedPiece.xValues.length;i++)
    	{
    		int x = this.selectedPiece.xValues[i] + this.selectedPiece.xPivot;
    		int y = this.selectedPiece.yValues[i] + this.selectedPiece.yPivot;
    		if (x > 19)
    			return "Left";
    		if (y > 19)
    			return "Up";
    		if (x < 0)
    			return "Right";
    		if (y < 0)
    			return "Down";
    	}
    	
    	
    	return "No";
    }
    
    // Sets the x y coordinates of a single block of a piece
    public void rotatePieceSetValues(int x, int y, int i)
    {
    	this.selectedPiece.xValues[i]=x;
    	this.selectedPiece.yValues[i]=y;
    }
    
    // Rotates the current piece that the player wants to place
    public void rotatePiece(boolean clockwise)
    {
    	//System.out.print("rotating the piece");
    	//1. change all the current x,y positions back to their original block color on the board
    	this.clearCurrentPieceOnBoard();
    	//2. change the x,y positions inside the current piece
    	int changeX = 0;
    	int changeY = 0;
    	int total = this.selectedPiece.xValues.length;	
    	if (clockwise)
    	{
    		for (int i=0; i<total; i++)
    		{
    			String theseValues = Arrays.toString(new int[] {this.selectedPiece.xValues[i], this.selectedPiece.yValues[i]});
    			switch(theseValues)
    			{
    				//For squares horizontally/vertically 1 block from the pivot
    				case"[1, 0]":this.rotatePieceSetValues(0, 1, i); break;
    				case"[0, 1]":this.rotatePieceSetValues(-1, 0, i); break;
    				case"[-1, 0]":this.rotatePieceSetValues(0, -1, i); break;
    				case"[0, -1]":this.rotatePieceSetValues(1, 0, i); break;
    				//For squares 1 up/down, and 1 left/right from the pivot
    				case"[1, 1]":this.rotatePieceSetValues(-1, 1, i); break;
    				case"[-1, 1]":this.rotatePieceSetValues(-1, -1, i); break;
    				case"[-1, -1]":this.rotatePieceSetValues(1, -1, i); break;
    				case"[1, -1]":this.rotatePieceSetValues(1, 1, i); break;
    				//For squares horizontally/veritcally 2 blocks from the pivot
    				case"[2, 0]":this.rotatePieceSetValues(0, 2, i); break;
    				case"[0, 2]":this.rotatePieceSetValues(-2, 0, i); break;
    				case"[-2, 0]":this.rotatePieceSetValues(0, -2, i); break;
    				case"[0, -2]":this.rotatePieceSetValues(2, 0, i); break;
    				default: System.out.println("Something was not found"); break;
    			}
    		}
    	}
    	else // if counter-clockwise
    	{
    		for (int i=0; i<total; i++)
    		{
    			String theseValues = Arrays.toString(new int[] {this.selectedPiece.xValues[i], this.selectedPiece.yValues[i]});
    			switch(theseValues)
    			{
    				//For squares horizontally/vertically 1 block from the pivot
    				case"[1, 0]":this.rotatePieceSetValues(0, -1, i); break;
    				case"[0, 1]":this.rotatePieceSetValues(1, 0, i); break;
    				case"[-1, 0]":this.rotatePieceSetValues(0, 1, i); break;
    				case"[0, -1]":this.rotatePieceSetValues(-1, 0, i); break;
    				//For squares 1 up/down, and 1 left/right from the pivot
    				case"[1, 1]":this.rotatePieceSetValues(1, -1, i); break;
    				case"[-1, 1]":this.rotatePieceSetValues(1, 1, i); break;
    				case"[-1, -1]":this.rotatePieceSetValues(-1, 1, i); break;
    				case"[1, -1]":this.rotatePieceSetValues(-1, -1, i); break;
    				//For squares horizontally/veritcally 2 blocks from the pivot
    				case"[2, 0]":this.rotatePieceSetValues(0, -2, i); break;
    				case"[0, 2]":this.rotatePieceSetValues(2, 0, i); break;
    				case"[-2, 0]":this.rotatePieceSetValues(0, 2, i); break;
    				case"[0, -2]":this.rotatePieceSetValues(-2, 0, i); break;
    				default: System.out.println("Something was not found"); break;
    			}
    		}
    	} 	
    	
    	holdMarkerAboveBoard(this.selectedPiece.xPivot, this.selectedPiece.yPivot);
    	
    	if (!this.markerIsOutsideBoard().equals("No"))
    	{
    		movePiece(this.markerIsOutsideBoard());
    	}
    	
    	//3. run the hold mark above board for the new x,y coords
    	//holdMarkerAboveBoard(this.selectedPiece.xPivot, this.selectedPiece.yPivot);	
    }
         

    public Piece getPiece(String name)
    {
    	Piece thePiece;
    	for (Piece x:this.piecesLeft[this.currentTurn])
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
     *********************TAKING TURNS*******************************************************************
     ****************************************************************************************************
     * */
    
    public void waitForTime(int milliseconds)
    {
    	
    	
    	//Timer timer = new Timer();
    	//timer.schedule(task, 1000);
    	
    	long t0, t1;
        t0 = System.currentTimeMillis();

        do {
            t1 = System.currentTimeMillis();            
        } while ((t1 - t0) < milliseconds);
        
        
    	/*
    	try {

            System.out.println("Start..." + new Date());
            // delay 5 seconds
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println("End..." + new Date());

            // delay 0.5 second
            //TimeUnit.MICROSECONDS.sleep(500);

			// delay 1 minute
            //TimeUnit.MINUTES.sleep(1);
			
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
        */
    }
    
    public void delayThenAiTurn()
    {
    	Timer timer = new java.util.Timer();
    	timer.schedule( 
    	        new java.util.TimerTask() {
    	            @Override
    	            public void run() {
    	            	aiTurn();
    	                // this is run to prevent problems from happening in the future
    	            	timer.cancel();
    	            }
    	        }, 
    	        1000
    	        //2000 // this is the milliseconds it delays
    	);
    }
    
    // start the next players turn
    public void nextTurnBegin()
    {
    	if (this.gameOver)
    		return;
    	if (this.playersLeft() < 1)
    	{
    		this.endTheGame();
    		return;
    	}
    	db("THE TURN FOR PLAYER " + this.currentTurn + " BEGINS --------------------------");
    	
    	this.currentGame.currentTurn = this.currentTurn; // set this for saving and loading
    	
    	this.showGameStatus();
    	this.refreshPiecesToCurrentPlayer();
    	
    	if (!this.thePlayers[this.currentTurn].AI) {
    		
    		playerTurn(); // if its player 0, player takes a turn
    		this.setMarkerToBoard(9, 9);
    	}
    	else {
    		aiTurnInitUI();
    		delayThenAiTurn();

    	}
    }
    
    // this is good for the player giving up
    public void removeTempPieces()
    {
    	for (GridSquare2[] x: this.gridSquares)
    	{
    		for (GridSquare2 y: x)
    		{
    			if (y.unfilled)
    			{
    				y.setOriginalColor();
    			}
    		}
    	}
    }
    
    // Gets the current player to give up and be out of the game
    public void thisPlayerGiveUp()
    {
    	//get rid of all temporary pieces on the board
    	this.removeTempPieces();
    	this.thePlayers[this.currentTurn].isOut = true;
    	
    	if (this.playersLeft() < 1)
    	{
    		this.endTheGame();
    	}
    	else {
	    	this.setNextPlayerTurn();
	    	this.nextTurnBegin();
    	}
    }
    
    /*
     ****************************************************************************************************
     *********************PLAYER TURNS*******************************************************************
     ****************************************************************************************************
     * */
    
    //NOT DONE
    // Changes the selected piece to this hint, and uses the holdMarkerOverBoard to place it
    public boolean checkForPlayerTurn()
    {
    	//System.out.print("TURN(player:" + this.currentTurn + ", ");
    	
    	//----------------------------------------------------------------------------
    	// 0. Select a random piece, I might actually put this inside the loop after getting the corners to check every piece.
    	//----------------------------------------------------------------------------
    	//this.selectRandomPiece(); // i can't even figure out why I would do this here
    	
    	//----------------------------------------------------------------------------
    	// 1. Get all positions of squares of this players color on board
    	//----------------------------------------------------------------------------
    	ArrayList<Integer> xValues = new ArrayList<Integer>();
    	ArrayList<Integer> yValues = new ArrayList<Integer>();
    	for (int r=0; r<20;r++){
    		for (int c=0; c<20; c++){
    			if (this.gridSquares[r][c].color.equals(this.thePlayers[this.currentTurn].pieceColor))
    				{ xValues.add(r); yValues.add(c); }
    		}
    	}
    	//System.out.print("Test 1 passed ( " );
    	for (int i=0; i<xValues.size(); i++)
    	{
        	//System.out.print("(" + xValues.get(i) + ", " + yValues.get(i) + "), ");
    	}
    	//System.out.println(" )");
    	
    	//----------------------------------------------------------------------------
    	// 2. Get all empty corner pieces of these found squares ^
    	//----------------------------------------------------------------------------
    	ArrayList<Integer> xCorners = new ArrayList<Integer>();
    	ArrayList<Integer> yCorners = new ArrayList<Integer>();
    	boolean found = false;
    	for (int r=0; r<xValues.size();r++){
    		//System.out.println("============CHECKING THE SQUARE " + xValues.get(r) + ", " + yValues.get(r));
			int x1 = xValues.get(r) + 1;
			int y1 = yValues.get(r) + 1;
			int x2 = xValues.get(r) + 1;
			int y2 = yValues.get(r) - 1;
			int x3 = xValues.get(r) - 1;
			int y3 = yValues.get(r) + 1;
			int x4 = xValues.get(r) - 1;
			int y4 = yValues.get(r) - 1;
			if (x1 < 0 || x1 > 19 || y1 < 0 || y1 > 19) {}
			else if (this.gridSquares[x1][y1].color.equals("empty") && this.sideCheck(x1, y1)) {
				xCorners.add(x1);
				yCorners.add(y1);
			}
			if (x2 < 0 || x2 > 19 || y2 < 0 || y2 > 19) {}
			else if (this.gridSquares[x2][y2].color.equals("empty") && this.sideCheck(x2, y2)) {
				xCorners.add(x2);
				yCorners.add(y2);
			}
			if (x3 < 0 || x3 > 19 || y3 < 0 || y3 > 19) {}
			else if (this.gridSquares[x3][y3].color.equals("empty") && this.sideCheck(x3, y3)) {
				xCorners.add(x3);
				yCorners.add(y3);
			}
			if (x4 < 0 || x4 > 19 || y4 < 0 || y4 > 19) {}
			else if (this.gridSquares[x4][y4].color.equals("empty") && this.sideCheck(x4, y4)) {
				xCorners.add(x4);
				yCorners.add(y4);
			}

    	}
    	//System.out.println("Test 2 passed: all empty corners are: ");
    	for (int i=0; i<xCorners.size(); i++)
    	{
        	//System.out.print("(" + xCorners.get(i) + ", " + yCorners.get(i) + "), ");
    	}
    	//System.out.println(" ");
    	
    	
    	//----------------------------------------------------------------------------
    	// 3 . For every piece left, Try laying the piece in every possible position, 
    	//			that will fill these empty corners until one is found
    	//				if none can be found, this player is set to 'out'.
    	//----------------------------------------------------------------------------
    	
    	
    	// Get the array of remaining pieces
    	ArrayList<Piece> piecesLeft = this.remainingPieces();
    	
    	//int debugCount = 0;
    	
    	// For each piece left
    	for (Piece x: piecesLeft) {
    		//System.out.println("--------------------------------------------------1. CHECKING PIECE " + x.description);
    		
    		// for each corner coordinate, try to place the piece in every possible location that might contain it
    		found = false;
        	for (int r=0; r<xCorners.size();r++){
        		found = this.tryToPlaceAllSides(x, xCorners.get(r), yCorners.get(r));
        		if (found)
        			break;
        	}
    		if (found)
    			break;
    		//if (debugCount > 1)
    		//	break;
    		//debugCount++;
    		
    	}
    	if (found)
    		return true;
    		//this.holdMarkerAboveBoard(this.selectedPiece.xPivot, this.selectedPiece.yPivot);
    		//this.finalizeAIPiece();
    	else {
    		//System.out.println("No possible pieces can be placed. this player is out now "  + this.currentTurn);
    		this.thePlayers[this.currentTurn].isOut = true;
    		return false;
    	}
    }
        
    // 1. Player turn begins, and the loop ends, waiting for more play input to start again
    public void playerTurn() 
    {
    	//System.out.println("PLAYER TURN START, WAIT FOR USER INPUT----------------------------------------");
    	int player = this.currentTurn + 1;
    	//topLabel.setText("Player " + player);
    	this.setCurrentPlayerText();
    	selectPieceText.setText("Select a piece");
    	
    	//Now the loop ends, until the player tries to lay a piece again
    }
    
    // 2. Make sure the player's turn is correct, this is run when the player hits the 'Lay Piece' button
    // 1. Player tries to place the piece
    public void playerTurnPlace()     
    {
    	db("NOW TO FIX THE PLAYER TURN PLACE");
    	
    	this.setSelectedForUser();

    	System.out.println("Clicked the 'Place Piece' Button");

    	//1. check if the piece has been placed by the user
    	if (!this.pieceIsDown)
    		return;
    	
    	
    	System.out.println("Trying to place the piece");
    	
    	//2. Check placement of piece
    	if (!this.playerTurnCheckPlacement())
    		return;
    		
    	//System.out.println("Trying to finalize the piece now");
    	
    	// no need of this. just make a method to make the temporary color permanent if the placement is okay.
    	this.finalizeBlocksInPiece();//3. clear the temporary piece off the board
    	
    	// remove the placed piece from the list
    	this.removePieceFromList();
    	
    	// set the turn to the next player
    	this.setNextPlayerTurn();
    	
    	// begin the next turn
    	this.nextTurnBegin();
    }
    
    public void setSelectedForUser()
    {
    	String pieceName = this.getSelectedButton();
    	for (Piece x: this.piecesLeft[this.currentTurn])
    	{
    		if (x.description.equals(pieceName))
    		{
    			this.selectedPiece = x;
    		}
    	}
    }
    
    // Extra for 2. This is for the player's first turns
    public boolean firstTurnCheckPlacement()
    {
    	//System.out.println("piece is:" + this.selectedPiece.description);
    	//System.out.println("pivot is x:" + this.selectedPiece.xPivot + " y:" + this.selectedPiece.yPivot);
    	
    	
    	int length = this.selectedPiece.xValues.length;
    	boolean found = false;
	   	for (int i=0; i<length; i++)
	   	{
	   		int thisX = this.selectedPiece.xPivot + this.selectedPiece.xValues[i];
	   		int thisY = this.selectedPiece.yPivot + this.selectedPiece.yValues[i];
	   		
	   		//System.out.println("this point is X:" + thisX + " " + "Y:" + thisY);

	   		if ((thisX == 0 && thisY == 0) || (thisX == 19 && thisY == 19))
	   			found = true;
	   		else if ((thisX == 0 && thisY == 19) || (thisX == 19 && thisY == 0))
	   			found = true;
	   	}
	   	if (!found)
   		{
	   		if (this.currentTurn == 0)
	   			this.playerTurnError("The first piece must be placed at a corner");
   			return false;
   		}
	   	//System.out.println("first turn check passed: The piece was put in the corner successfully");
    	
    	return true;
    }
    
    // Extra for 2. Makes sure the players turn is valid, also works for the AI turn
    public boolean playerTurnCheckPlacement()
    {
    	/*
	   	 Notes:
	   	 //1. make sure each block is inside the board
	   	 //2. make sure each block is inside an empty block
	   	 //3. make sure no blocks are side to side with blocks of the same color
	   	 //4. check if at least one block has a block of the same color touching by the corner
	   	 //5. clear the temporary piece off the board
	   	 */
	   	
	   	//0. Setting variables
	   	int length = this.selectedPiece.xValues.length;
	   	boolean returnValue = true;
	   	
	   	
	   	//1. make sure each block is inside the board
	   	for (int i=0; i<length; i++)
	   	{
	   		int thisX = this.selectedPiece.xPivot + this.selectedPiece.xValues[i];
	   		int thisY = this.selectedPiece.yPivot + this.selectedPiece.yValues[i];
	   		if ((thisX > 19 || thisY > 19 || thisX < 0 || thisY < 0))
	   		{
	   			if (this.currentTurn == 0)
	   				this.playerTurnError("Place piece inside the board" );
	   			return false;
	   		}
	   	}
	   	System.out.println("part 1 passed: block is inside the board for pivot " + this.selectedPiece.xPivot + "," + this.selectedPiece.yPivot);
	   	
	  	 	//2. make sure each block is inside an empty block
	   	for (int i=0; i<length; i++)
	   	{
	   		int thisX = this.selectedPiece.xPivot + this.selectedPiece.xValues[i];
	   		int thisY = this.selectedPiece.yPivot + this.selectedPiece.yValues[i];
	   		if (!this.gridSquares[thisX][thisY].color.equals("empty"))
	   		{
	   			if (this.currentTurn == 0)
	   				this.playerTurnError("Place piece in empty blocks x:" + thisX + ", y:" + thisY + ", color:" + this.gridSquares[thisX][thisY].color);
	   			return false;
	   		}
	
	   	}
	   	System.out.println("part 2 passed: blocks are inside empty blocks");
	   	
	   	//2. alternate, if its the first turn, check if its in the corner
	   	if (this.turnCount < this.currentGame.size)
    		return this.firstTurnCheckPlacement();
	   	
	   	//---------------------------------------------------------------------
	   	//----------------------------REGULAR-ROUTE----------------------------
	   	//---------------------------------------------------------------------
	   	boolean allEmpty = true;
	   	int thisX = 0;
	   	int thisY = 0;
	   	
	   	//3. make sure no blocks are side to side with blocks of the same color
	   	for (int i=0; i<length; i++)
	   	{
	   		thisX = this.selectedPiece.xPivot + this.selectedPiece.xValues[i];
	   		thisY = this.selectedPiece.yPivot + this.selectedPiece.yValues[i];
	   		String color = this.thePlayers[this.currentTurn].pieceColor;
	   		allEmpty = true;
	   		int x1;
	   		int y1;
	   		
	   		if (!((thisX + 1) > 19))
	   			if (this.gridSquares[thisX + 1][thisY].color.equals(this.thePlayers[this.currentTurn].pieceColor)) {
	   				allEmpty = false; break;}
	   		if (!((thisY + 1) > 19))
	   			if (this.gridSquares[thisX][thisY + 1].color.equals(this.thePlayers[this.currentTurn].pieceColor)) {
	   				allEmpty = false; break;}
	   		if (!((thisX - 1) < 0))
	   			if (this.gridSquares[thisX - 1][thisY].color.equals(this.thePlayers[this.currentTurn].pieceColor)) {
	   				allEmpty = false; break;}
	   		if (!((thisY - 1) < 0))
	   			if (this.gridSquares[thisX][thisY - 1].color.equals(this.thePlayers[this.currentTurn].pieceColor)) {
	   				allEmpty = false; break;}

	   	}
	   	if (!allEmpty)
   		{
   			if (this.currentTurn == 0)
   				this.playerTurnError("Blocks of the same color cannot be side-by-side x:" + thisX + ", y:" + thisY + ", color:" + this.gridSquares[thisX][thisY].color);
   			return false;
   		}
	   	System.out.println("part 3 passed: blocks are not side-by-side with blocks of the same color");
	   	
	   	
	   	
	   	//System.out.println();
	   	//System.out.println("STARTING BROKEN PART OF THE PIECE CHECKER FOR " + this.selectedPiece.description + ", IT WILL CHECK " + length + " TIMES-----------------------------------------");

	   	
	   	//4. check if at least one block has a block of the same color touching by the corner
	   	boolean found = false;
	   	
	   	for (int i=0; i<length; i++)
	   	{
	   		thisX = this.selectedPiece.xPivot + this.selectedPiece.xValues[i];
	   		thisY = this.selectedPiece.yPivot + this.selectedPiece.yValues[i];
	   		String color = this.thePlayers[this.currentTurn].pieceColor;
	   		found = false;
	   		
	   		if (!((thisX + 1) > 19) && !((thisY + 1) > 19))
	   			if (this.gridSquares[thisX + 1][thisY + 1].color.equals(this.thePlayers[this.currentTurn].pieceColor)) {
	   				found = true; break;}
	   		if (!((thisX + 1) > 19) && !((thisY - 1) < 0))
	   			if (this.gridSquares[thisX + 1][thisY - 1].color.equals(this.thePlayers[this.currentTurn].pieceColor)) {
	   				found = true; break;}
	   		if (!((thisX - 1) < 0) && !((thisY + 1) > 19))
	   			if (this.gridSquares[thisX - 1][thisY + 1].color.equals(this.thePlayers[this.currentTurn].pieceColor)) {
	   				found = true; break;}
	   		if (!((thisX - 1) < 0) && !((thisY - 1) < 0))
	   			if (this.gridSquares[thisX - 1][thisY - 1].color.equals(this.thePlayers[this.currentTurn].pieceColor)) {
	   				found = true; break;}

	   	}
	   	
	   	if (!found)
   		{
	   		//this.debugGridSquares();
	   		if (this.currentTurn == 0)
   				this.playerTurnError("At least one block must be touching one of the same color at the corner only x:" + thisX + ", y:" + thisY + ", color:" + this.gridSquares[thisX][thisY].color);
   			return false;
   		}
	   	//System.out.println("part 4 passed: There is at least one block of the same color, touching at the corner");
	   	
	   	//included = this.includedInSelected(xValues[i] + x, yValues[i] + y, thePiece);
	   	
	   	//this.debugGridSquares();
	   	//all tests have passed
	   	return true;
	   	
    }
     
    // 3. Once the turn was checked for correctness, confirm the temporary colors in the blocks on the board, which represent the piece about to laid down
    // Clears the unplaced current piece off the board, this is just used between each time its moved.
    public void finalizeBlocksInPiece()
    {
    	int[] xValues = this.selectedPiece.xValues;
    	int[] yValues = this.selectedPiece.yValues;
    	int length = xValues.length;
    	for (int i=0; i<length;i++)
    	{
    		int thisX = this.selectedPiece.xPivot + xValues[i];
    		int thisY = this.selectedPiece.yPivot  + yValues[i];  		
    		if (!(thisX > 19 || thisY > 19 || thisX < 0 || thisY < 0)) // if its inside the board
    			this.gridSquares[thisX][thisY].finalizeTempColor();
    	}
    }
    
    // 4. The piece is removed from the drop-down menu
    public void removePieceFromList()
    {
    	//System.out.println("trying to remove a piece from the list");
    	String thisPiece = this.getSelectedButton();
    	this.removePieceByName(this.currentTurn, thisPiece);
    	this.disableButtonByName(thisPiece);
    }
    
    // Should display a text error to the player
    public void playerTurnError(String errorMessage)
    {
    	//System.out.println(errorMessage);
    }
    
    /*
     ****************************************************************************************************
     *********************AI TURNS***********************************************************************
     ****************************************************************************************************
     * */
    
    public GridSquare2 getSquare(int x, int y)
    {
    	return this.gridSquares[y][x];
    }
    
    public void aiTurnInitUI()
    {
    	int player = this.currentTurn + 1;
    	//topLabel.setText("Player " + player);
    	this.setCurrentPlayerText();
    	selectPieceText.setText("AI taking turn right now");
    }
    
    // AI turn starts here
    public void aiTurn() 
    {	
    	//System.out.println("AI TURN START>----------------------------------------");
    	//int player = this.currentTurn + 1;
    	//topLabel.setText("Player " + player);
    	//selectPieceText.setText("AI taking turn right now");
    	
    	//if its the first turn, just lay the piece in the first corner found
	   	if (this.turnCount < this.currentGame.size)
	   		this.aiTakeFirstTurn();
	   	else
	   		this.aiTakeTurn();
    	
    	if (this.gameOver)
    		return;
	   	this.setNextPlayerTurn(); // set the next players turn
    	this.nextTurnBegin();
    	
    }
    
    // Lay a piece in the corner
    public void aiTakeFirstTurn()
    {
    	//System.out.print("TURN(player:" + this.currentTurn + ", ");
    	
    	this.selectRandomPiece();
    	
    	// Selecting a corner to start in
    	int corner = 0;
    	int xPivot = 0;
    	int yPivot = 0;
    	
    	
    	//System.out.print("c0:" + this.gridSquares[0][0].unfilled + ", ");
    	//System.out.print("c1:" + this.gridSquares[19][0].unfilled + ", ");
    	//System.out.print("c2:" + this.gridSquares[0][19].unfilled + ", ");
    	//System.out.println("c3:" + this.gridSquares[19][19].unfilled + ", ");
    	
    	
    	//System.out.println("the square in 0, 0 is unfilled:" + this.gridSquares[0][0].unfilled + " with color " + this.gridSquares[0][0].color);
    	//System.out.println("the square in 19, 0 is unfilled:" + this.gridSquares[19][0].unfilled + " with color " + this.gridSquares[19][0].color);
    	//System.out.println("the square in 0,19 is unfilled:" + this.gridSquares[0][19].unfilled + " with color " + this.gridSquares[0][19].color);
    	//System.out.println("the square in 19,19 is unfilled:" + this.gridSquares[19][19].unfilled + " with color " + this.gridSquares[19][19].color);
    	
    	// Top Left Corner
    	if (this.gridSquares[0][0].unfilled) { xPivot = 0; yPivot = 0; corner = 0; System.out.println("ran="+ corner +", ");}
    	// Top right corner
    	else if (this.gridSquares[19][0].unfilled) { xPivot = 19; yPivot = 0; corner = 1; System.out.println("ran="+ corner +", ");}
    	// Bottom left corner
    	else if (this.gridSquares[0][19].unfilled) { xPivot = 0; yPivot = 19; corner = 2; System.out.println("ran="+ corner +", ");}
    	// Bottom right corner
    	else if (this.gridSquares[19][19].unfilled) { xPivot = 19; yPivot = 19; corner = 3; System.out.println("ran="+ corner +", ");}
    	
    	
    	
    	
    	//System.out.println("TRYING TO PLACE BLOCK IN CORNER:" + corner + ", ");
    		
    	//------------------Finding the list of possible pivot coordinates for this piece
    	int[] xValues = new int[]{};
    	int[] yValues = new int[]{};
    	
    	/*
    	// Top left corner
    	if (corner == 0)
    		{ xValues = new int[]{ 0, 1, 2, 0 ,1 }; yValues = new int[]{ 2, 1, 0, 1, 0 }; }
    	// Top right corner
    	else if (corner == 1)
    		{ xValues = new int[]{ -2, -1, 0, -1, 0 }; yValues = new int[]{ 0, 1, 2, 0, 1 }; }
    	// Bottom left corner
    	else if (corner == 2)
    		{ xValues = new int[]{ 0, 1, 2, 0, 1 }; yValues = new int[]{ 2, 1, 0, 1, 0 }; }
    	// Bottom right corner
    	else if (corner == 3)
    		{ xValues = new int[]{ 0, -1, -2, 0, -1 }; yValues = new int[]{ -2, -1, 0, -1, 0 }; }
    	*/
    	
    	// Top left corner
    	if (corner == 0)
    		{ yValues = new int[]{ 0, 1, 2, 0 ,1 }; xValues = new int[]{ 2, 1, 0, 1, 0 }; }
    	// Bottom left corner
    	else if (corner == 1)
    		{ yValues = new int[]{ 0, 1, 2, 0, 1 }; xValues = new int[]{ -2, -1, 0, 1, 0 }; }
    	// Top right corner
    	else if (corner == 2)
    		{ yValues = new int[]{ -2, -1, 0, -1, 0 }; xValues = new int[]{ 0, 1, 2, 0, 1 }; }
    	// Bottom right corner
    	else if (corner == 3)
    		{ yValues = new int[]{ 0, -1, -2, 0, -1 }; xValues = new int[]{ -2, -1, 0, -1, 0 }; }
    	
    	for (int i=0; i<5;i++)
    	{
    		xValues[i] += xPivot;
    		yValues[i] += yPivot;
    	}
    	
    	
    	
    	
    	//--------------Checking each coordinate in 4 rotations
    	boolean isFound = false;
    	for (int i=0; i<5; i++) //for each coordinate pair
    	{
    		isFound = checkAllRotations(xValues[i], yValues[i], this.selectedPiece);
    		//System.out.println("Check " + i + " for  (" + xValues[i] +", "+ yValues[i] + ") ------------------------------1 " + isFound);
    		if (isFound)
    			break;
    	}
    	if (!isFound)
    		System.out.println("This error should never run, since its the first turn. x:" + this.selectedPiece.xPivot + " y:" +  this.selectedPiece.yPivot);
    	else 
    		finalizeAIPiece();
    	
    	//System.out.println(" )");
    }
    
    //For debugging, print what is in the given piece
    public void printPieceData(Piece thePiece)
    {
    	//System.out.println();
    	//System.out.print("name:" + thePiece.description);
    }
    
    // Feed in a piece and pivots, it will see if any possible rotation can be placed at that point
    public boolean checkAllRotations(int pivotX, int pivotY, Piece thisPiece)
    {
    	//System.out.println();
    	System.out.print("CHECKING ROTATIONS FOR " + thisPiece.description + " at " + pivotX + "," + pivotY);
    	this.selectedPiece = thisPiece;
    	this.selectedPiece.xPivot = pivotX;
		this.selectedPiece.yPivot = pivotY;
    	boolean isFound = false;
		
		for (int j=0; j<4;j++) // for each possible rotation of the piece
		{
			if (this.playerTurnCheckPlacement()) //123
			//if (this.firstTurnCheckPlacement())
        	{
				//System.out.println("-----------------------2");
				//System.out.println("Found a correct placement");
				isFound = true;
        		break;
        	}
			//rotating at the end will be fine
			if (!isFound)
				this.rotateSelectedPiece();
			//System.out.println("---------------------------3");
		}
		if (isFound)
			return true;
		else
			return false;
    }
    
    //Checks given point for side-by-side blocks of the same color as the current player
    public boolean sideCheck(int thisX, int thisY)
    {
    	//3. make sure no blocks are side to side with blocks of the same color
   		String color = this.thePlayers[this.currentTurn].pieceColor;
   		if (!((thisX + 1) > 19))
   			if (this.gridSquares[thisX + 1][thisY].color.equals(color))
   				return false;
   		if (!((thisY + 1) > 19))
   			if (this.gridSquares[thisX][thisY + 1].color.equals(color))
   				return false;
   		if (!((thisX - 1) < 0))
   			if (this.gridSquares[thisX - 1][thisY].color.equals(color))
   				return false;
   		if (!((thisY - 1) < 0))
   			if (this.gridSquares[thisX][thisY - 1].color.equals(color))
   				return false;
   		return true;

    }
    
    // Lay the largest piece possible
    public void aiTakeTurn()
    {
    	db("AI turn start, and the size of players left is " + this.playersLeft() + " also is the game over? " + this.gameOver);
    	if (this.gameOver)
    		return;
    	//System.out.print("TURN(player:" + this.currentTurn + ", ");
    	
    	//----------------------------------------------------------------------------
    	// 0. Select a random piece, I might actually put this inside the loop after getting the corners to check every piece.
    	//----------------------------------------------------------------------------
    	//this.selectRandomPiece(); // i can't even figure out why I would do this here
    	
    	//----------------------------------------------------------------------------
    	// 1. Get all positions of squares of this players color on board
    	//----------------------------------------------------------------------------
    	ArrayList<Integer> xValues = new ArrayList<Integer>();
    	ArrayList<Integer> yValues = new ArrayList<Integer>();
    	for (int r=0; r<20;r++){
    		for (int c=0; c<20; c++){
    			if (this.gridSquares[r][c].color.equals(this.thePlayers[this.currentTurn].pieceColor))
    				{ xValues.add(r); yValues.add(c); }
    		}
    	}
    	//System.out.print("Test 1 passed ( " );
    	for (int i=0; i<xValues.size(); i++)
    	{
        	//System.out.print("(" + xValues.get(i) + ", " + yValues.get(i) + "), ");
    	}
    	//System.out.println(" )");
    	
    	//----------------------------------------------------------------------------
    	// 2. Get all empty corner pieces of these found squares ^
    	//----------------------------------------------------------------------------
    	ArrayList<Integer> xCorners = new ArrayList<Integer>();
    	ArrayList<Integer> yCorners = new ArrayList<Integer>();
    	boolean found = false;
    	for (int r=0; r<xValues.size();r++){
    		//System.out.println("============CHECKING THE SQUARE " + xValues.get(r) + ", " + yValues.get(r));
			int x1 = xValues.get(r) + 1;
			int y1 = yValues.get(r) + 1;
			int x2 = xValues.get(r) + 1;
			int y2 = yValues.get(r) - 1;
			int x3 = xValues.get(r) - 1;
			int y3 = yValues.get(r) + 1;
			int x4 = xValues.get(r) - 1;
			int y4 = yValues.get(r) - 1;
			if (x1 < 0 || x1 > 19 || y1 < 0 || y1 > 19) {}
			else if (this.gridSquares[x1][y1].color.equals("empty") && this.sideCheck(x1, y1)) {
				xCorners.add(x1);
				yCorners.add(y1);
			}
			if (x2 < 0 || x2 > 19 || y2 < 0 || y2 > 19) {}
			else if (this.gridSquares[x2][y2].color.equals("empty") && this.sideCheck(x2, y2)) {
				xCorners.add(x2);
				yCorners.add(y2);
			}
			if (x3 < 0 || x3 > 19 || y3 < 0 || y3 > 19) {}
			else if (this.gridSquares[x3][y3].color.equals("empty") && this.sideCheck(x3, y3)) {
				xCorners.add(x3);
				yCorners.add(y3);
			}
			if (x4 < 0 || x4 > 19 || y4 < 0 || y4 > 19) {}
			else if (this.gridSquares[x4][y4].color.equals("empty") && this.sideCheck(x4, y4)) {
				xCorners.add(x4);
				yCorners.add(y4);
			}

    	}
    	//System.out.println("Test 2 passed: all empty corners are: ");
    	for (int i=0; i<xCorners.size(); i++)
    	{
        	//System.out.print("(" + xCorners.get(i) + ", " + yCorners.get(i) + "), ");
    	}
    	//System.out.println(" ");
    	
    	
    	//----------------------------------------------------------------------------
    	// 3 . For every piece left, Try laying the piece in every possible position, 
    	//			that will fill these empty corners until one is found
    	//				if none can be found, this player is set to 'out'.
    	//----------------------------------------------------------------------------
    	
    	
    	// Get the array of remaining pieces
    	ArrayList<Piece> piecesLeft = this.remainingPieces();
    	
    	//int debugCount = 0;
    	
    	// For each piece left
    	for (Piece x: piecesLeft) {
    		//System.out.println("--------------------------------------------------1. CHECKING PIECE " + x.description);
    		
    		// for each corner coordinate, try to place the piece in every possible location that might contain it
    		found = false;
        	for (int r=0; r<xCorners.size();r++){
        		found = this.tryToPlaceAllSides(x, xCorners.get(r), yCorners.get(r));
        		if (found)
        			break;
        	}
    		if (found)
    			break;
    		//if (debugCount > 1)
    		//	break;
    		//debugCount++;
    		
    	}
    	if (found)
    		this.finalizeAIPiece();
    	else {
    		//System.out.println("No possible pieces can be placed. this player is out now "  + this.currentTurn);
    		db("SETTING PLAYER " + this.currentTurn + " TO OUT");
    		this.thePlayers[this.currentTurn].isOut = true;
    		if (this.playersLeft() < 1)
    			this.endTheGame();
    		else {
	    		this.setNextPlayerTurn();
	    		this.nextTurnBegin();
    		}
    	}
    	
    	
    	
    	
    	
    	/*
    	 PLAN
    	 	1. Get all positions of squares of this player's color on board
    	 	2. Get all empty corner pieces of these found squares ^
    	 	3 . For every piece left
    	  		a. Try laying the piece in every possible position, that will fill these empty corners until one is found
    	  			if none can be found, this player is set to 'out'.
    	  			
    	  			
    	  			the thing im forgetting is. I need to also check if the corner piece is included in this selection
    	  				not only if its possible to place.

    	 */
    }
    
    // Checks if the given point is inside the given piece
    public boolean includedInSelected(int x, int y, Piece thePiece)
    {
    	int [] xValues = thePiece.xValues;
    	int [] yValues = thePiece.yValues;
    	for (int i=0; i< xValues.length;i++)
    	{
    		if (x == xValues[i] && y == yValues[i])
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    // tries to place a piece on all sides of a given coordinate, but it must also include the coordinate
    public boolean tryToPlaceAllSides(Piece thePiece, int x, int y)
    {
    	//x and y are the corner coodinates. and given is also a piece.
    	
    	//System.out.println("TRYING TO PLACE THE PIECE IN EVERY POSSIBLE WAY TO CONTAIN THE COORDINATE->  " + x + "," + y);
    	
    	// These are all the possible pivots that might include corner piece (x and y)
    	int[] xValues = new int[] {0, -1, 0, 1, -2, -1, 0, 1, 2, -1, 0, 1, 0};
    	int[] yValues = new int[] {-2, -1, -1, -1, 0, 0, 0, 0, 0, 1, 1, 1, 2};
    	
    	boolean found = false;
    	boolean included = false;
    	
    	// for each possible pivot, check the 4 rotations, and 
    	for (int i=0; i<xValues.length;i++)
    	{
    		
    		
    		
    		
    		
    		
    		
    		//------------------------------------------------------------------------------------------------------------------------------
    		// this is the problem right now, that included in selected needs the piece that is being rotated.
    		
    		
    		
    		
    		
    		
    		
    		// included in selected should probably be called inside the function playerTurnCheckPlacement
    		//asd
    		found = this.checkAllRotations(xValues[i] + x, yValues[i] + y, thePiece);
    		
    		//included = this.includedInSelected(xValues[i] + x, yValues[i] + y, thePiece);
    		included = true;
    		if (found && included) // this.selectedPiece will contain the correct values of where it should be placed
    			break;
    	}
    	if (found) {
    		//System.out.println("Found a piece ---> " + this.selectedPiece.xPivot + "," + this.selectedPiece.yPivot );
    		return true;
    		}
    	else
    		return false;
    			
    }
    
    // sets the Ais selection so it has been placed
    public void finalizeAIPiece()
    {
    	//System.out.println("trying to finalize piece");
    	int[] xValues = this.selectedPiece.xValues;
    	int[] yValues = this.selectedPiece.yValues;
    	int length = xValues.length;
    	for (int i=0; i<length;i++)
    	{
    		int thisX = this.selectedPiece.xPivot + xValues[i];
    		int thisY = this.selectedPiece.yPivot  + yValues[i];  		
    		String theColor = this.thePlayers[this.currentTurn].pieceColor;
    		this.gridSquares[thisX][thisY].setColorForAI(this.getColorRGB(theColor), theColor);
    	}
    	this.removePieceByName(this.currentTurn, this.selectedPiece.description);
    }
    
    // selects the largest random piece possible for the current player, used on the first turn
    public void selectRandomPiece()
    {
    	this.selectRandomPieceHard();

    	// this wouldn't work for some reason
    	/*
    	if (this.currentGame.difficulty == 2)
    		this.selectRandomPieceHard();
    	else if (this.currentGame.difficulty == 1)
    		this.selectRandomPieceMedium();
    	else
    		this.selectRandomPieceEasy();
    	 */
    	
    	//System.out.print( "name:" + this.selectedPiece.description + " )" );
    	//System.out.println(" ");
    }
    
    public void selectRandomPieceEasy()
    {
    	db("SELECT RANDOM PIECE EASY-----------------------------------------------------------------");
    	
    	boolean tierFound = false; //this is true once we know if it will be a 5 piece block, 4, 3, 2 or 1
    	int tier = 0;
    	boolean pieceFound = false; // this is true once the piece has been found
    	ArrayList<Integer> usableItems = new ArrayList<Integer>();
    	
    	for (int i=0; i < this.piecesLeft[this.currentTurn].length; i++)
    	{
    		System.out.println( "#" + i + ":" + this.piecesLeft[this.currentTurn][i].description + ", " );
    		if (!this.piecesLeft[this.currentTurn][i].isPlaced)
    		{
    			if (!tierFound)
    			{
    				tierFound = true;
        			tier = this.piecesLeft[this.currentTurn][i].tier;
        			System.out.print( "tier:" + tier + ", " );
    			}
    			if (this.piecesLeft[this.currentTurn][i].tier > tier)
    				break;
    			if (this.piecesLeft[this.currentTurn][i].tier == tier)
    			{
    				if (!this.piecesLeft[this.currentTurn][i].description.equals("5- Cross") || this.turnCount > this.currentGame.size)
    					usableItems.add(i);
    			}
    			
    		}
    	}

    	for (int x: usableItems)
    	{
    		db("Usable item " + x);
    	}
    	
    	// Now select a random value from the usableItems list
    	int size = usableItems.size();
    	Random randomGenerator = new Random();
    	int randomInt = randomGenerator.nextInt(size);
    	this.selectedPiece = this.piecesLeft[this.currentTurn][usableItems.get(randomInt)];
    	
    	db("Found a piece " + this.selectedPiece.description + " -----------------------------------------------------------------");
    }
    
    public void selectRandomPieceMedium()
    {
    	
    }
    
    public void selectRandomPieceHard()
    {
    	boolean tierFound = false; //this is true once we know if it will be a 5 piece block, 4, 3, 2 or 1
    	int tier = 0;
    	boolean pieceFound = false; // this is true once the piece has been found
    	ArrayList<Integer> usableItems = new ArrayList<Integer>();
    	
    	//Start from the last piece, and find the largest tier of pieces that are not placed
    	for (int i=this.piecesLeft[this.currentTurn].length - 1; i>-1;i--)
    	{
    		//System.out.println( "#" + i + ":" + this.piecesLeft[this.currentTurn][i].description + ", " );
    		if (!this.piecesLeft[this.currentTurn][i].isPlaced)
    		{
    			if (!tierFound)
    			{
    				tierFound = true;
        			tier = this.piecesLeft[this.currentTurn][i].tier;
        			//System.out.print( "tier:" + tier + ", " );
    			}
    			if (this.piecesLeft[this.currentTurn][i].tier < tier)
    				break;
    			if (this.piecesLeft[this.currentTurn][i].tier == tier)
    			{
    				if (!this.piecesLeft[this.currentTurn][i].description.equals("5- Cross") || this.turnCount > this.currentGame.size)
    					usableItems.add(i);
    			}
    			
    		}
    	}
    	
    	// Now select a random value from the usableItems list
    	int size = usableItems.size();
    	Random randomGenerator = new Random();
    	int randomInt = randomGenerator.nextInt(size);
    	this.selectedPiece = this.piecesLeft[this.currentTurn][usableItems.get(randomInt)];
    }
    
    /*
     ****************************************************************************************************
     *********************DEALING WITH USER INPUT********************************************************
     ****************************************************************************************************
     * */ 
    // Show a Debug Message in the console

    public void keyPressed(KeyEvent e) {
    	if (this.currentTurn != 0)
    		return;
    	int keyCode = e.getKeyCode();
    	if (keyCode == KeyEvent.VK_A)
    		this.movePiece("Left");
	    else if (keyCode == KeyEvent.VK_D)
	    	this.movePiece("Right");
	    else if (keyCode == KeyEvent.VK_W)
	    	this.movePiece("Up");
	    else if (keyCode == KeyEvent.VK_S)
	    	this.movePiece("Down");
	    else if (keyCode == KeyEvent.VK_Q)
	    	this.rotatePiece(false);
	    else if (keyCode == KeyEvent.VK_E)
	    	this.rotatePiece(true);
    }
    public void db(String message)
    {
    	System.out.println(message);
    }
    
    // Show a Debug Message in the console without making a space
    public void dbb(String message)
    {
    	System.out.println(message);
    }
    
    // prints an array of strings
    public void printArray(String[] array)
    {
    	db(" ");
    	for (String x: array)
    	{
    		dbb(x + ", ");
    	}
    }
    
    // gets the number of players based on main menu input
    public int getPlayerCountAtStart(String[] info)
    {
    	printArray(info);
    	int count = 1;
    	if (info[0] == "Human" || info[0] == "AI")
    		count ++;
    	if (info[1] == "Human" || info[1] == "AI")
    		count ++;
    	if (info[2] == "Human" || info[2] == "AI")
    		count ++;
    	db("Player count is " + count);
    	return count;
    }
    
    public void debugStartGameMessage()
    {
    	db("StartGame() is being run----------------->>>>");
    	db("Players: " + this.currentGame.size);
    	for (int i=0; i<this.thePlayers.length; i++)
    		db("Player" + i + ": is AI?" + this.thePlayers[i].AI);
    }
    
    // This is run every time a button is clicked
    public void actionPerformed (ActionEvent aevt)
	{
    	// get the object that was selected in the gui
		Object selected = aevt.getSource();
		if (selected.equals( startGameButton)){
			String[] playerSettingsTemp = new String[] { gamePlayerAmount1.getSelectedItem().toString(),gamePlayerAmount2.getSelectedItem().toString(), 
					gamePlayerAmount3.getSelectedItem().toString()};
			int count = getPlayerCountAtStart(playerSettingsTemp);
			String[] game = {
					// 123 fix here
					Integer.toString(count), 
					gameDifficulty.getSelectedItem().toString(), 
					gameScoreType.getSelectedItem().toString(), 
					gamePlayerColor.getSelectedItem().toString(), 
					gameColorSettings.getSelectedItem().toString()
					};
            GameSettings thisGame = new GameSettings(game[0], game[1], game[2], game[3], game[4], 0);
			if (this.checkGameSettings(thisGame))
			{
				//System.out.println("Settings are correct, starting the game");
				this.currentGame = thisGame;
				// assign a color to each player
				this.createThePlayers(playerSettingsTemp[0], playerSettingsTemp[1], playerSettingsTemp[2]);
				// fill the array of players pieces
				this.createPlayersPieces();
				
				this.debugStartGameMessage();
				
				this.startGame();
			}
			else 
			{
				//System.out.println("The settings are incorrect");
			}
		}
		else if (selected.equals( resumeLastGameButton))
		{
			//couldn't get this working on time
			
			/*
			try {
				this.loadLastGame();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
		else if(selected.equals( quit)) 
        {
            exitTheGame();
			//ShowMainMenu();
        }
		else if (selected.equals( gameColorSettings))
		{
			if (gameColorSettings.getSelectedItem().toString() == "Off")
			{
				//System.out.println("action was made for color settings");
				String[] colors = {"<Select>", "Red", "Blue", "Green", "Yellow" };
				gamePlayerColor.removeAllItems();
				for (String x: colors)
				{
					gamePlayerColor.addItem(x);
				}
			}
			else if (gameColorSettings.getSelectedItem().toString() == "On")
			{
				//System.out.println("action was made for color settings");
				String[] colors = {"<Select>", "Teal", "Purple", "Yellow", "Red" };
				gamePlayerColor.removeAllItems();
				for (String x: colors)
				{
					gamePlayerColor.addItem(x);
				}
			}
		}
		else if (selected.equals( pieceMenu))
	    {
			db("check this out later, may need a fix");
			if (!this.thePlayers[this.currentTurn].AI)
				return; // 123 this might need to be fixed
			db("Drawing piece to the center of the board");
			//System.out.println("change focus window");
	    	//if (this.currentTurn == 0)
			if (!this.thePlayers[this.currentTurn].AI)
	    		this.selectPieceText.setText("Click to place");
	    	this.setFocusable(true);
	    	this.requestFocusInWindow();
	    }
		else if (selected.equals( saveYes))
			try {
				this.saveTheLastGameAndExit();
			} catch (FileNotFoundException e) {
				//
			}
		else if (selected.equals( saveNo))
	    	this.exitWithoutSaving();
		else if (selected.equals( cancelQuit))
	    	this.cancelTheExit();
		
		else if (selected.equals(giveUpButton)) {
			db("Clicked the give up button");
			db("is current player AI?" + this.thePlayers[this.currentTurn].AI);
			if (!this.thePlayers[this.currentTurn].AI)
				this.thisPlayerGiveUp();
		}
		
		//===========BLOCK MOVEMENT CONTROLS
		else if (this.thePlayers[this.currentTurn].AI) 
			return;
		else if (selected.equals( Up))
	    	this.movePiece("Up");
	    else if (selected.equals( Down))
	    	this.movePiece("Down");
	    else if (selected.equals( Right))
	    	this.movePiece("Right");
	    else if (selected.equals( Left))
	    	this.movePiece("Left");
	    else if (selected.equals( rotateCW))
	    	this.rotatePiece(true);
	    else if (selected.equals( rotateCCW))
	    	this.rotatePiece(false);
	    else if (selected.equals( placePiece)) {
	    	db("Clicking place is working");
	    	this.playerTurnPlace();
	    }
	    else if (selected.equals(p1))
    		{db("clicked piece 1"); p1.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p2))
    		{p2.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p3))
    		{p3.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p4))
    		{p4.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p5))
    		{p5.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p6))
    		{p6.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p7))
    		{p7.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p8))
    		{p8.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p9))
    		{p9.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p10))
    		{p10.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p11))
    		{p11.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p12))
    		{p12.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p13))
    		{p13.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p14))
    		{p14.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p15))
    		{p15.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p16))
    		{p16.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p17))
    		{p17.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p18))
    		{p18.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p19))
    		{p19.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p20))
    		{p20.setSelected(true); this.setMarkerToBoard(9, 9);}
    	else if (selected.equals(p21))
    		{p21.setSelected(true); this.setMarkerToBoard(9, 9);}
	    
		HouseKeeping();		
	}
    
    public void setMarkerToBoard(int thisX, int thisY)
    {
		setNewCurrentPiece(thisX, thisY); // Save the current piece selected
		holdMarkerAboveBoard(thisX, thisY);
		HouseKeeping();
		repaint();	
    }
    
    // This runs every time the mouse is clicked, but not on a button
 	public void mouseClicked(MouseEvent mevt)
 	{      
 			//if (this.currentTurn != 0)
 				//return;
 		
 			// if the current player is AI, then do not let the mouse input do anything
 			if (this.thePlayers[this.currentTurn].AI)
 				return;
 		
 			Object selected = mevt.getSource();
 			if ( selected instanceof GridSquare2)
 			{
				 //((GridSquare2) selected).setColorWithRGB(getColorRGB(this.currentGame.playerColor), currentGame.playerColor, selectedPiece.color);
				 int thisX = ((GridSquare2) selected).xcoord;
				 int thisY = ((GridSquare2) selected).ycoord;
				 String color = ((GridSquare2) selected).color;
				 System.out.println("the player " + this.currentTurn + " clicked at x:" + thisX + ", y:" + thisY + ", with the color " + this.gridSquares[thisX][thisY].color);
				 //System.out.println("TEST " + this.gridSquares[thisX][thisY].xcoord + ", " + this.gridSquares[thisX][thisY].ycoord);
				 
				 setMarkerToBoard(thisX, thisY);
				 //setNewCurrentPiece(thisX, thisY); // Save the current piece selected
				 //holdMarkerAboveBoard(thisX, thisY);
				 //HouseKeeping();
				 //repaint();	
 			}	
 			
 	}
    
	// not used but must be present to fulfill MouseListener contract
	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	
	// not used but must be present to fulfill KeyListener contract
	public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}



