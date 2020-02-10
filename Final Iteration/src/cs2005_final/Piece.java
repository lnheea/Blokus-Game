package cs2005_final;

public class Piece {
	public String description;
	public int[] xValues; // the x position of each block of this piece
	public int[] yValues; // the y position of each block of this piece
	public String color; // the color of this piece
	
	public boolean isPlaced; // says if the piece has been placed yet
	public int xPivot;
	public int yPivot;
	public int orientation; // 0, 1, 2, 3 for 0, 90, 180, 270, this is probably not needed
	public int tier; // this is the number of blocks in the piece
	
    public Piece(int[] xValues, int[] yValues, String color, String description) 
    { 
    	this.description = description;
    	this.xValues = xValues;
    	this.yValues = yValues;
    	this.color = color;
    	this.isPlaced = false;
    	this.tier = xValues.length; // the number of blocks in the piece
    } 
  
    public int[] getXValues() 
    { 
        return xValues; 
    }
    
    public int[] getYValues() 
    { 
        return yValues; 
    } 
    
    public String getColor() 
    { 
        return color; 
    } 
  
    public boolean isPlaced() 
    { 
        return isPlaced; 
    } 
    
    public void placeDown()
    {
    	isPlaced = true;
    }
  
}
