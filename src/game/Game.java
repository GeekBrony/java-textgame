package game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public ArrayList<String> commandHistory = new ArrayList<String>();
	public static boolean run = false;
	private Scanner scan;
	public static boolean MLGMode = false;
	
	/*	TODO:
	 *  - Make the command handler guess what you want depending on the input
	 *  	Example: "tell a joke" should go to joke =IF= it contains the word joke and stuff.
	 */
	
	private final String[] cmdsAvailable = {
			"clear",
			"exit",
			"eat",
			"drink",
			"git gud",
			"go",
			"health",
			"history",
			"help",
			"joke",
			"mlg",
			"suicide",
			"walk",
	};
	private final String[] cmdDescriptions = {
			"Clear the screen",
			"Exit the game.",
			"Eat food.",
			"Drink liquid.",
			"If you are feeling lucky...",
			"Go somewhere",
			"Display HP",
			"Display the previous commands entered.",
			"Show the available commands and the game info.",
			"Tell a joke",
			"MLG mode toggle ;)",
			"Permanent solution to a temporary problem. Not a viable option.",
			"Walk somewhere."
	};
	
	public Game(boolean textOnlyMode) {
		Tools.textOnly = textOnlyMode;
		// If text only mode is enabled, then use
		// the console to log the game status.
		if (!Actions.inventoryExists()) {
			Actions.createInventory();
		}
		
		Tools.logln("Game initialized - waiting for runInstance();", 1);
	}
	
	public void runInstance() {
		Tools.toAreaSpaced(Strings.getString("start"));
		Tools.toAreaSpaced("");
		Food pear = new Food("pear", 5);
		Actions.addToInventory(pear);
		Food alcohol = new Food("expired applejuice", -5, true);
		Actions.addToInventory(alcohol);
		Food apple = new Food("apple", 3);
		Actions.addToInventory(apple);
		
		Tools.toAreaSpaced("");
		Tools.toAreaSpaced("What do you want to do?");
		
		run = true;
		while(run) {
			String in = getCommandInput();
			handleInput(in);
		}
	}
	
	public void handleInput(String in) {
		commandHistory.add(in);
		String[] spl = in.split(" ");
		boolean hasCommand = false;
		for(int i = 0; i < cmdsAvailable.length; i++) {
			String combined = spl[0];
			if(spl.length == 2) {
				combined += " "+spl[1];
			} else if(spl.length == 3) {
				combined += " "+spl[1] + " "+spl[2];
			} else if(spl.length == 4) {
				combined += " "+spl[1] + " "+spl[2] + ""+spl[3];
			}
			if(cmdsAvailable[i].equalsIgnoreCase(combined) || cmdsAvailable[i].equalsIgnoreCase(spl[0])) {
				hasCommand = true;
				// do things
			}
		}
		if(!hasCommand) {
			if(in.equalsIgnoreCase("")) {
				Tools.toAreaSpaced("You must enter a command.");
			} else {
				Tools.toAreaSpaced(in + ": command not found in database");
			}
		} else {
			if(spl[0].equalsIgnoreCase("history")) {
				for(int index = 0; index < commandHistory.size(); index++) {
					Tools.toAreaSpaced(commandHistory.get(index));
				}
			} else if(spl[0].equalsIgnoreCase("help")) {
				for(int index = 0; index < cmdsAvailable.length; index++) {
					Tools.toAreaSpaced(cmdsAvailable[index] + " -> "
							+ (cmdDescriptions[index].equals("") ? "[no description available]" :
								cmdDescriptions[index]));
				}
			} else if(spl[0].equalsIgnoreCase("health")) {
				Actions.displayHealth();
			} else if(spl[0].equalsIgnoreCase("eat") || spl[0].equalsIgnoreCase("drink") ) {
				String foodOrDrink = spl[0].equalsIgnoreCase("eat") ? "food" : "drink";
				String sd = spl[0].equalsIgnoreCase("eat") ? "food" : "drinks";
				if(spl.length == 1) {
					String s = "";
					int num = 0;
					for(int i = 0; i < Actions.inv.inventory().length; i++) {
						if(Actions.inv.inventory()[i] != null &&
						Actions.inv.inventory()[i] instanceof Food) {
							Food it = (Food) Actions.inv.getItemAtOffset(i);
							if(spl[0].equalsIgnoreCase("eat")) {
								if(!it.isLiquid()) s += "\t" + it.getID() + " : " + it.getName() + "\n";
							} else if(spl[0].equalsIgnoreCase("drink")) {
								if(it.isLiquid()) s += "\t" + it.getID() + " : " + it.getName() + "\n";
							}
							num++;
						}
					}
					if(num > 0) {
						Tools.toAreaSpaced("You have these "+foodOrDrink+" items in your inventory: ");
						Tools.toAreaSpaced(s);
					} else {
						Tools.toAreaSpaced("You have no "+foodOrDrink+sd+" in your inventory.");
					}
				} else if(spl.length == 2 || (spl.length == 3 || in.replaceFirst(" ", "").contains(" "))) {
					String useThisString = spl[1];
					if(spl.length == 3){
						useThisString += " " + spl[2];
					}
					int offset = Actions.inv.getItemOffsetWithItemName(useThisString);
					if(offset != -1) {
						Actions.eat((Food) Actions.inv.getItemAtOffset(offset));
					} else {
						Tools.toAreaSpaced("There is no such "+foodOrDrink+" named \""+useThisString+"\" in your inventory.");
					}
				} else {
					Tools.toAreaSpaced("Usage: eat/drink [food/drink name]");
				}
			} else if(spl[0].equalsIgnoreCase("suicide")) {
				Actions.suicide();
			} else if(spl[0].equalsIgnoreCase("clear")) {
				Tools.area.setText("");
			} else if(spl[0].equalsIgnoreCase("joke")) {
				Tools.toAreaSpaced(Strings.getString("joke"));
			} else if(spl[0].equalsIgnoreCase("mlg")) {
				MLGMode = !MLGMode;
				if(MLGMode) {
					Tools.toAreaSpaced(Strings.getString("mlg_activate"));
					Tools.area.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
					Food dew = new Food("Mountain Dew", 4.2069, true); // 420 l33t hax0r it3m dr0p;
					Actions.addToInventory(dew);
				} else {
					Tools.toAreaSpaced(Strings.getString("mlg_deactivate"));
					Tools.area.setFont(new Font("Courier", Font.PLAIN, 14));
				}
			} else if(spl[0].equalsIgnoreCase("git") && spl.length > 1 && spl[1].equalsIgnoreCase("gud")) {
				Tools.toAreaSpaced("You look at your screen in guilt as you never figured out why people say \""+in+"\"");
			} else if((spl[0].equalsIgnoreCase("walk") || spl[0].equalsIgnoreCase("go"))) {
				if(spl.length == 2) {
					if(spl[1].equalsIgnoreCase("up") || spl[1].equalsIgnoreCase("forward") ||
					   spl[1].equalsIgnoreCase("straight") || spl[1].equalsIgnoreCase("left") ||
					   spl[1].equalsIgnoreCase("diagonal") || spl[1].equalsIgnoreCase("right") ||
					   spl[1].equalsIgnoreCase("back") || spl[1].equalsIgnoreCase("down") ||
					   spl[1].equalsIgnoreCase("north") || spl[1].equalsIgnoreCase("south") ||
					   spl[1].equalsIgnoreCase("east") || spl[1].equalsIgnoreCase("west")) {
						Tools.toAreaSpaced("You try to walk \'"+spl[1]+"\', but you can\'t because the programmers somehow"
								+ "\nare far too lazy to program anything related to actually accomplishing things, sorry.");
					} else {
						if(spl[0].equalsIgnoreCase("go")) Tools.toAreaSpaced("Where is \'"+spl[1]+"\'?");
						if(spl[0].equalsIgnoreCase("walk")) Tools.toAreaSpaced("What direction is \'"+spl[1]+"\'?");
					}
				} else {
					Tools.toAreaSpaced("Usage: go/walk [direction]");
				}
			} else if(spl[0].equalsIgnoreCase("exit")) {
				Tools.logln("Exiting...", 1);
				System.exit(0);
			}
		}
	}
	
	private String getCommandInput() {
		String str = "";
		
		// Because of limitations, we can't use a Scanner
		// instance from System.in to get the GUI's input
		// so it will only be in text only mode for now..
		if(Tools.textOnly) {
			scan = new Scanner(System.in);
			str = scan.nextLine();
			commandHistory.add(str);
		}
		
		return str;
	}
	
	public boolean instanceRunning() {
		return run;
	}
	
	public static void endGame() {
		Tools.toAreaSpaced("");
		Tools.toAreaSpaced("/---------------------\\");
		Tools.toAreaSpaced("|  G A M E   O V E R  |");
		Tools.toAreaSpaced("\\---------------------/");
		Tools.toAreaSpaced("");
		run = false;
	}
	
}
