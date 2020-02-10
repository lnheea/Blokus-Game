
public class GameSettings {
	int size;
	int difficulty;
	String advancedScoring;
	String playerColor;
	boolean colorOption;

    public GameSettings(String size, String difficulty, String advancedScoring, 
    		String playerColor, String colorOption) 
    { 
    	switch(size)
    	{
    		case "2 Player": this.size = 2; break;
    		case "3 Player": this.size = 3; break;
    		case "4 Player": this.size = 4; break;
    		default: this.size = 0; break; // set it to zero to let it know the player did not choose a size
    	}
    	System.out.println("color option is " + colorOption);
    	if (colorOption.equals("On"))
    		this.colorOption = true;
    	else if (colorOption.equals("Off"))
    		this.colorOption = false; 
    	switch(difficulty) // will control how smart the AI is
    	{	
    		case "Easy": this.difficulty = 0; break;
    		case "Medium": this.difficulty = 1; break;
    		case "Hard": this.difficulty = 2; break;
    		default: this.difficulty = 2; break; 
    	}
    	this.advancedScoring = advancedScoring; // will control how the score is done at the end
    	this.playerColor = playerColor; // will control what color is set to the player at the start, the others are random
    }     
}
