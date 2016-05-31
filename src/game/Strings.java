package game;

public class Strings {
	
	private static String[] suicide =
	{
		"You took the coward\'s way out and drank bleach. You died.",
		"You took a rope and wrapped it around the ceiling.\nImmediately after you think your last thoughts, you kick the chair out of the way.\nYou died.",
		"You find the nearest gun and shoot yourself. You died.",
		"You had to pick between a rifle and a knife. You chose a knife because it's more painful. \n You died."
	};
	
	public static String getString(String action) {
		String s = "";
		
		if(action.equals("suicide")){
			s = suicide[Tools.randomize(suicide.length)];
		}
		
		return s;
	}

}
