package testing;

import cs2005_final.GridSquare2;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import org.junit.Test;

public class GridSquare2Test {

	//@Test
	//public void test() {
	//	fail("Not yet implemented");
	//}

	@Test
	public void testConstructor() 
    { 
    	GridSquare2 square = new GridSquare2( 1, 2, false);
    	assertTrue("The square does not exist", "empty".equals(square.color));
    } 
	
	@Test
	public void testSetColorBlindMode()
	{
		GridSquare2 square = new GridSquare2( 1, 2, false);
		square.setColorBlindMode();
    	assertTrue("The square does not exist", square.colorBlindMode);
	}
	
	//															NOT IMPLEMENTED AT THE MOMENT
	//@Test
	//public void testSetColorBlindLetter()
	//{
	//
	//}
	
	//															NOT IMPLEMENTED AT THE MOMENT
	//@Test
	//public void testSetColorBlindLetterTemp()
	//{
	//
	//}

	@Test
	public void testFinalizeTempColor()
	{
		GridSquare2 square = new GridSquare2( 1, 2, false);
		square.setTempColorWithRGB(new int[] {150, 100, 50}, "TestColor");
		square.finalizeTempColor();
    	assertTrue("The square was not finalized with its current temporary color", square.color.equals("TestColor"));
	}

	@Test
	public void testSetOriginalColor()
	{
		GridSquare2 square = new GridSquare2( 1, 2, false);
		square.setTempColorWithRGB(new int[] {150, 100, 50}, "TestColor");
		square.setOriginalColor();
    	assertTrue("The original color was not set", square.originalColorSet);
	}

	@Test
	public void testSetColorForAI()
	{
		GridSquare2 square = new GridSquare2( 1, 2, false);
		square.setColorForAI(new int[] {150, 100, 50}, "TestColor");
    	assertTrue("The color was not set right for the AI", square.color.equals("TestColor"));
	}

	@Test
	public void testSetTempColorWithRGB()
	{
		GridSquare2 square = new GridSquare2( 1, 2, false);
		square.setTempColorWithRGB(new int[] {150, 100, 50}, "TempColor");
    	assertTrue("The temporary color was not set", square.tempColor.equals("TempColor"));
	}
	
	@Test
	public void testSetEmpty()
	{
		GridSquare2 square = new GridSquare2( 1, 2, false);
		square.setEmpty();
    	assertTrue("The temporary color was not set", square.color.equals("empty"));
	}
}
