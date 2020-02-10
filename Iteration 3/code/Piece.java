public class Piece {
	String description;
	int[] xValues; // the x position of each block of this piece
	int[] yValues; // the y position of each block of this piece
	String color; // the color of this piece
	
	boolean isPlaced; // says if the piece has been placed yet
	int xPivot;
	int yPivot;
	int orientation; // 0, 1, 2, 3 for 0, 90, 180, 270, this is probably not needed
	int tier; // this is the number of blocks in the piece
	
    public Piece(int[] xValues, int[] yValues, String color, String description) 
    { 
    	this.description = description;
    	this.xValues = xValues;
    	this.yValues = yValues;
    	this.color = color;
    	this.isPlaced = false;
    	this.tier = xValues.length; // the number of blocks in the piece
    } 

    public void setPivot(int x, int y)
    {
    	this.xPivot = x;
    	this.yPivot = y;
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
