package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public ArrayList<String> commandHistory = new ArrayList<String>();
	public static boolean run = false;
	private Scanner scan;
	
	private final String[] cmdsAvailable = {
			"clear",
			"exit",
			"eat",
			"health",
			"history",
			"help",
			"suicide",
	};
	private final String[] cmdDescriptions = {
			"Clear the screen",
			"Exit the game.",
			"Eat food.",
			"Display HP",
			"Display the previous commands entered.",
			"Show the available commands and the game info.",
			"Permanent solution to a temporary problem."
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
		Food alcohol = new Food("beer", -5, true);
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
			if(cmdsAvailable[i].equalsIgnoreCase(spl[0])) {
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
							+ cmdDescriptions[index]);
				}
			} else if(spl[0].equalsIgnoreCase("health")) {
				Actions.displayHealth();
			} else if(spl[0].equalsIgnoreCase("eat")) {
				if(spl.length == 1) {
					String s = "";
					int num = 0;
					for(int i = 0; i < Actions.inv.inventory().length; i++) {
						if(Actions.inv.inventory()[i] != null &&
						Actions.inv.inventory()[i] instanceof Food) {
							Item it = Actions.inv.getItemAtOffset(i);
							s += "\t" + it.getID() + " : " + it.getName() + "\n";
							num++;
						}
					}
					if(num > 0) {
						Tools.toAreaSpaced("You have these food items in your inventory: ");
						Tools.toAreaSpaced(s);
					} else {
						Tools.toAreaSpaced("You have no food in your inventory.");
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
						Tools.toAreaSpaced("There is no such food named \""+useThisString+"\" in your inventory.");
					}
				} else {
					Tools.toAreaSpaced("Usage: eat [food name]");
				}
			} else if(spl[0].equalsIgnoreCase("suicide")) {
				Actions.suicide();
			} else if(spl[0].equalsIgnoreCase("clear")) {
				Tools.area.setText("");
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
