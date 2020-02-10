public class Player {
	int playerNumber; // this number, is the row in the pieces array, which contains this players pieces. Also used to see who is currently playing
	boolean isPlayer; // tells us if this is an AI or the human
	boolean isOut; // tells us if the player is out of the game or not
	String pieceColor; // tells us the color this player is using
	
    public Player(int playerNumber, boolean isPlayer, String pieceColor) 
    { 
    	this.playerNumber = playerNumber;
    	this.isPlayer = isPlayer;
    	this.isOut = false;
    	this.pieceColor = pieceColor;
    } 

    public int getNumber() 
    { 
        return playerNumber; 
    } 
  
    public boolean isPlayer() 
    { 
        return isPlayer; 
    } 
  
    public boolean isOut() 
    { 
        return isOut; 
    } 
  
    public String getColor() 
    { 
        return pieceColor; 
    } 
  
    /*
    
    @Override
    public String toString() 
    { 
        return("Player: "+ this.getNumber()+ 
               ".\nAm I a real player? " + 
               this.isPlayer()+", what color?" + this.getColor()+ 
               ", is out?"+ this.isOut()); 
    } 
    
    public static void main(String[] args) 
    { 
        Player tuffy = new Dog("tuffy","papillon", 5, "white"); 
        System.out.println(tuffy.toString()); 
    } 
    */
}
