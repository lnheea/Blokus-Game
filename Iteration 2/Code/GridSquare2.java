
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.Border;
/*
 *  a simple extension of JComponent which allows the background colour to be set and switched back and forth with ease
 *  
 *  there are other ways of doing this, but it's a neat way to demonstrate how to create your own gui components
 *  (as well as how to use ternary operators)
 */
public class GridSquare2 extends JPanel
{
        public int xcoord, ycoord;			// not used in this demo, but might be helpful in future...
        public boolean filled;
        public String color; // The color of the piece in this spot
        public String pieceName; // The name of the piece in this spot
	// constructor takes the x and y coordinates of this square
	public GridSquare2( int xcoord, int ycoord)
	{
		super();
		this.setSize(50,50);
		this.xcoord = xcoord;
		this.ycoord = ycoord;
        this.filled = false;
        this.color = "empty";
        this.pieceName = "empty";
	}
	
	
	// if the decider is even, it chooses black, otherwise white (for 'column+row' will allow a chequerboard effect)
	public void setColorWithRGB( int[] rgb, String color, String pieceName)
	{
            Color colour = new Color(rgb[0], rgb[1], rgb[2]);
            this.setBackground( colour);
           
            Border blackline = BorderFactory.createLineBorder(Color.black);
            this.setBorder(blackline);
	}
	
	// if the decider is even, it chooses black, otherwise white (for 'column+row' will allow a chequerboard effect)
	public void setColor( int decider)
	{

            Color colour = new Color(153, 153, 153);
            this.setBackground( colour);
           
            Border blackline = BorderFactory.createLineBorder(Color.black);
            this.setBorder(blackline);
	}
	
	// if the square is black it becomes white, and vice-versa
	public void switchColor()
	{
		Color colour = Color.black;
		this.setBackground( colour);
	}
        
}
