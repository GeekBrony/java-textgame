package game;

public class Strings {
	
	private static String[] storyBegin = {
		"/--------------------------\\",
		"|        G  A  M  E        |",
		"|        (the game)        |",
		"\\--------------------------/",
		"",
		"ayy fam welcome to the game this is the best ever ill tell u that",
		"think of this game as a standard text-based sandbox game, there's no story involved.",
		"but before you continue, lemme tell you something:",
		"apparently if you type \"help\", you'll see the list of commands, haha",
		"you'll be able to do different things with these commands",
	};
	
	private static String[] suicide =
	{
		"You took the coward\'s way out and drank bleach. You died.",
		"You took a rope and wrapped it around the ceiling.\nImmediately after you think your last thoughts, you kick the chair out of the way.\nYou died.",
		"You find the nearest gun and shoot yourself. You died.",
		"You had to pick between a rifle and a knife. You chose a knife because it's more painful. \n You died."
	};
	
	public static String getString(String action) {
		String s = "";
		
		if (action.equals("start")) {
			s = toString(storyBegin);
		} else if(action.equals("suicide")){
		
			s = suicide[Tools.randomize(suicide.length)];
		}
		
		return s;
	}
	
	private static String toString(String[] st){
		String str = "";
		for(int i = 0; i < st.length; i++) {
			if(i == st.length - 1) str += st[i];
			else str+=st[i]+"\n";
		}
		return str;
	}

}
