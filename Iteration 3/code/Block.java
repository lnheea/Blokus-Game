public class Block {
	String color; // will be set to empty by default
	int x; // the x position of this block on the board
	int y; // the y position of this block on the board

    public Block(int x, int y) 
    { 
    	this.color = "empty";
    	this.x = x;
    	this.y = y;
    } 

    public void fillBlock(String color) 
    { 
        this.color = color; 
    }
    
    public int getY() 
    { 
        return x; 
    } 
    
    public int getX() 
    { 
        return y; 
    } 
  
}
