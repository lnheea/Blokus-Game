package testing;

import cs2005_final.Piece;
import cs2005_final.Player;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	/*

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
    }*/
	
	
	@Test
	public void testConstructor() 
    { 
		Player player = new Player( 1, true, false, "Red", "Human");
    	assertTrue("Player should not be out", player.isOut == false);
    }
	
	@Test
	public void testGetNumber() 
    { 
		Player player = new Player( 1, true, false, "Red", "Human");
    	assertTrue("Player's number should be 1", player.getNumber() == 1);
    }

	@Test
    public void testIsPlayer() 
    { 
		Player player = new Player( 1, true, false, "Red", "Human");
    	assertTrue("Should be a human player", player.isPlayer() == true);
    }

	@Test
    public void testIsOut() 
    { 
		Player player = new Player( 1, true, false, "Red", "Human");
    	assertTrue("Player should not be out", player.isOut() == false);
    }

	@Test
    public void testGetColor() 
    { 
		Player player = new Player( 1, true, false, "Red", "Human");
    	assertTrue("Player should not be outr", player.getColor().equals("Red"));
    }


}
