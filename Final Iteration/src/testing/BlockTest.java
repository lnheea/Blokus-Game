package testing;

import cs2005_final.Block;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlockTest {
	
	@Test
	public void testBlock_exists() 
    { 
    	Block block = new Block( 1, 2);
    	assertTrue("Make sure the color is empty", "empty".equals(block.color));
    	
    } 
	
	@Test
    public void testFillBlock() 
    { 
		Block block = new Block( 1, 2);
		block.fillBlock("TestColor");
    	assertTrue("The block was not correctly filled with the color", block.color.equals("TestColor"));
    }
    
	@Test
    public void testGetY() 
    { 
		Block block = new Block( 1, 2);
		block.fillBlock("TestColor");
    	assertTrue("The x value could not be found", block.x == 1);
    } 
    
	@Test
    public void testGetX() 
    { 
		Block block = new Block( 1, 2);
		block.fillBlock("TestColor");
    	assertTrue("The y value could not be found", block.y == 2);
    } 

}
