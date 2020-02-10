import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Font;
/*
 *  a simple extension of JComponent which allows the background colour to be set and switched back and forth with ease
 *  
 *  there are other ways of doing this, but it's a neat way to demonstrate how to create your own gui components
 *  (as well as how to use ternary operators)
 */
public class GridSquare2 extends JPanel
{
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
	// constructor takes the x and y coordinates of this square
	public GridSquare2( int xcoord, int ycoord, boolean colorBlindMode)
	{
		super();
		this.setSize(50,50);
		this.xcoord = xcoord;
		this.ycoord = ycoord;
        this.unfilled = true;
        this.color = "empty";
        this.tempColor = "null";
        this.colorBlindMode = colorBlindMode;
        //if (this.colorBlindMode)
        //	this.setColorBlindLetter();
        
        //colorMarker = new JLabel("T");
		//this.add(colorMarker);
	}
	
	public void setColorBlindMode()
	{
		this.colorBlindMode = true;
	}
	
	private void setColorBlindLetter()
	{
		System.out.println("the substring is " + this.color.substring(0, 1));
		colorMarker = new JLabel(this.color.substring(0, 1));
		this.add(colorMarker);
	}
	
	private void setColorBlindLetterTemp()
	{
		colorMarker = new JLabel(this.tempColor.substring(0, 1));
		colorMarker.setMinimumSize(new Dimension(100, 100));
		this.add(colorMarker);
	}
	
	public void finalizeTempColor()
	{
		origR = tempR;
        origG = tempG;
        origB = tempB;
        this.setOriginalColor();
        this.unfilled = false;
        this.color = this.tempColor;
        if (colorBlindMode)
        	this.setColorBlindLetter();
	}
	
	public void setOriginalColor()
	{
		Color colour = new Color(origR, origG, origB);
        this.setBackground( colour);
       
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        if (colorBlindMode)
        	this.setColorBlindLetter();
	}
	
	public void setColorForAI(int[] rgb, String color)
	{
		this.setTempColorWithRGB( rgb, color);
		this.finalizeTempColor();
		this.unfilled = false;
		this.color = color;
		System.out.println("set the block " + xcoord + "," + ycoord + " to unfilled:" + this.unfilled);
		if (colorBlindMode)
        	this.setColorBlindLetter();
	}
	
	// this is called when moving a block around, before placing it
	public void setTempColorWithRGB( int[] rgb, String color)
	{
			tempR = rgb[0];
			tempG = rgb[1];
			tempB = rgb[2];    
			this.tempColor = color;
			Color colour = new Color(rgb[0], rgb[1], rgb[2]);
            this.setBackground( colour);
           
            Border blackline = BorderFactory.createLineBorder(Color.black);
            this.setBorder(blackline);
            if (colorBlindMode)
            	this.setColorBlindLetterTemp();
	}
	
	// this is called when placing a block at the end of a turn
	public void setColorWithRGB( int[] rgb, String color, String colorName)
	{
			this.color = colorName;
			origR = rgb[0];
	        origG = rgb[1];
	        origB = rgb[2];
			Color colour = new Color(rgb[0], rgb[1], rgb[2]);
            this.setBackground( colour);
           
            Border blackline = BorderFactory.createLineBorder(Color.black);
            this.setBorder(blackline);
            if (colorBlindMode)
            	this.setColorBlindLetter();
	}
	
	//Sets the square to an empty square on the board
	public void setEmpty()
	{
		origR = 153;
        origG = 153;
        origB = 153;
        Color colour = new Color(153, 153, 153);
        this.setBackground( colour);
       
        Border blackline = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackline);
        this.color = "empty";
        if (colorBlindMode)
        	this.setColorBlindLetter();
	}
	
	// if the decider is even, it chooses black, otherwise white (for 'column+row' will allow a chequerboard effect)
	public void setColor( int decider)
	{
			
	}
	
	// if the square is black it becomes white, and vice-versa
	public void switchColor()
	{
		Color colour = Color.black;
		this.setBackground( colour);
	}
        
}
