package testing;

import cs2005_final.Block;
import cs2005_final.GameSettings;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameSettingsTest {
	
	@Test
	public void testConstructor() 
    { 
		GameSettings settings = new GameSettings( "3", "Easy", "Simple", "Red", "On", 0);
    	assertTrue("The object is not working correctly", "Red".equals(settings.playerColor));
    }
	
	@Test
	public void testReturnSize() 
    { 
		GameSettings settings = new GameSettings( "3", "Easy", "Simple", "Red", "On", 0);
    	assertTrue("Size not set right", settings.returnSize() == 3);
    }

	@Test
    public void testReturnDifficulty() 
    { 
		GameSettings settings = new GameSettings( "3", "Easy", "Simple", "Red", "On", 0);
    	assertTrue("Difficulty scoring not set right", settings.returnDifficulty() == 0);
    }

	@Test
    public void testReturnAdvancedScoring() 
    { 
		GameSettings settings = new GameSettings( "3", "Easy", "Simple", "Red", "On", 0);
    	assertTrue("Advanced scoring not set right",  "Simple".equals(settings.advancedScoring));
    }

	@Test
    public void testReturnPlayerColor() 
    { 
		GameSettings settings = new GameSettings( "3", "Easy", "Simple", "Red", "On", 0);
		assertTrue("The object is not working correctly", "Red".equals(settings.playerColor));
    }

	@Test
    public void testReturnColorOption() 
    { 
		GameSettings settings = new GameSettings( "3", "Easy", "Simple", "Red", "On", 0);
		assertTrue("The object is not working correctly", settings.colorOption == true);
    }

}
