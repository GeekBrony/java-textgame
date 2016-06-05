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
	
	private static String[] joke = {
		"Can a kangaroo jump higher than a house? Of course, a house doesn’t jump at all.",
		"In the beginning, God created the heaven and the earth... After that, everything else was made in China.",
		"Q: What do politicians and diapers have in common? /n - n/ A: Both should be changed regularly, and both for the same reason.",
		"I’m selling my talking parrot. Why? Because yesterday, the bastard tried to sell me",
		"What would you call a very funny mountain? /n - n/ Hill Arious",
		"Yo mama is so fat she falls from both sides of the bed.",
	};
	
	private static String[] mlgActivate = {
		"smonk wed evrydey",
		"4/20 noscoped git gud",
		"d a n k e s t   o f   m e m e s",
		"The fitnessgram pacer test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The twenty meter pacer test will begin in thirty seconds, line up at the start. The running speed starts slowly and gets faster each minute after you hear this signal.",
		"git reddy 4 da dank",
		"here come dat boi",
		"1v1 me irl in rust u cheeky scr0blord",
		"can i join FaZe clan yet?",
	};
	
	private static String[] mlgDeactivate = {
		"We don't need no MLG mode, son.",
	};
		
	private static String[] no = {
		"git gud",
		"holla holla git doller"
	};
	
	private static String[] handleSwear = {
		"You should probably cut that language out.",
		"It isn\'t a good idea to swear.",
		"Watch your language...",
		"Please don\'t say that..."
	};
	
	private static String[] suicide =
	{
		"You took the coward\'s way out and drank bleach. You died.",
		"You took a rope and wrapped it around the ceiling.\nImmediately after you think your last thoughts, you kick the chair out of the way.\nYou died.",
		"You find the nearest gun and shoot yourself. You died.",
		"You had to pick between a rifle and a knife. You chose a knife because it's more painful. \n You died."
	};
	
	private static String[] dew_flavors =
	{
		"the original flavor",
		"Baja Blast",
		"Pitch Black",
		"Voltage",
		"Code Red",
		"White Out",
		"Live Wire"
	};
	
	public static String getString(String action) {
		String s = "";
		
		if (action.equals("start")) {
			s = toString(storyBegin);
		} else if (action.equals("no")) {
			s = no[Tools.randomize(no.length)];
		} else if(action.equals("joke")){
			s = joke[Tools.randomize(joke.length)];
		} else if(action.equals("mlg_activate")){
			s = mlgActivate[Tools.randomize(mlgActivate.length)];
		} else if(action.equals("mlg_deactivate")){
			s = mlgDeactivate[Tools.randomize(mlgDeactivate.length)];
		} else if(action.equals("swear_respond")){
			s = handleSwear[Tools.randomize(handleSwear.length)];
		} else if(action.equals("suicide")){
			s = suicide[Tools.randomize(suicide.length)];
		} else if(action.equals("dew_flavors")){
			s = dew_flavors[Tools.randomize(dew_flavors.length)];
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
