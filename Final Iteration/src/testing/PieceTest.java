package testing;

import cs2005_final.GameSettings;
import cs2005_final.Piece;

import static org.junit.Assert.*;

import org.junit.Test;

public class PieceTest {
	
	@Test
	public void testConstructor() 
    { 
		Piece piece = new Piece( new int[] {1,2,3}, new int[] {4,5,6}, "Red", "Test Piece");
    	assertTrue("The object is not working correctly", piece.xValues.length == piece.tier);
    }
	
	@Test
	public void testGetXValues() 
    { 
		Piece piece = new Piece( new int[] {1,2,3}, new int[] {4,5,6}, "Red", "Test Piece");
    	assertTrue("X values not added right", piece.xValues[0] == 1);
    }

	@Test
    public void testGetYValues() 
    { 
		Piece piece = new Piece( new int[] {1,2,3}, new int[] {4,5,6}, "Red", "Test Piece");
    	assertTrue("Y values not added right", piece.yValues[0] == 4);
    }

	@Test
    public void testGetColor() 
    { 
		Piece piece = new Piece( new int[] {1,2,3}, new int[] {4,5,6}, "Red", "Test Piece");
    	assertTrue("Color not added right", piece.color.equals("Red"));
    }

	@Test
    public void testIsPlaced() 
    { 
		Piece piece = new Piece( new int[] {1,2,3}, new int[] {4,5,6}, "Red", "Test Piece");
    	assertTrue("Should not be placed", piece.isPlaced == false);
    }

	@Test
    public void testPlaceDown() 
    { 
		Piece piece = new Piece( new int[] {1,2,3}, new int[] {4,5,6}, "Red", "Test Piece");
		piece.placeDown();
    	assertTrue("Should not be placed", piece.isPlaced == true);
    }

}
