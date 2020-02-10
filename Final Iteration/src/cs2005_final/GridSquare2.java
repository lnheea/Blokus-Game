package cs2005_final;

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
        public boolean originalColorSet;
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
	
	// Do not do the unit test for this class, since it is not done at the moment
	public void setColorBlindMode()
	{
		this.colorBlindMode = true;
	}
	
	// Do not do the unit test for this class, since it is not done at the moment
	public void setColorBlindLetter()
	{
		System.out.println("the substring is " + this.color.substring(0, 1));
		colorMarker = new JLabel(this.color.substring(0, 1));
		this.add(colorMarker);
	}
	
	// Do not do the unit test for this class, since it is not done at the moment
	public void setColorBlindLetterTemp()
	{
		colorMarker = new JLabel(this.tempColor.substring(0, 1));
		colorMarker.setMinimumSize(new Dimension(100, 100));
		this.add(colorMarker);
	}
	
	public void finalizeTempColor()
	{
		this.originalColorSet = true;
		
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
		this.originalColorSet = true;
		
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
		//System.out.println("set the block " + xcoord + "," + ycoord + " to unfilled:" + this.unfilled);
		if (colorBlindMode)
        	this.setColorBlindLetter();
	}
	
	// this sets a temporary color on the block, for the purpose of moving the piece around
	public void setTempColorWithRGB( int[] rgb, String color)
	{
			this.originalColorSet = false;
		
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
	
	//Sets the square to an empty square on the board
	public void setEmpty()
	{
		this.originalColorSet = true;
		
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
}
