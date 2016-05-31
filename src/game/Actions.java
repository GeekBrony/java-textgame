package game;

public class Actions {
	
	public static Inventory inv;
	
	public static boolean inventoryExists() {
		return (!(inv == null));
	}
	
	public static void createInventory() {
		Tools.logln("Created inventory", 1);
		inv = new Inventory();
	}
	
	public static int addToInventory(Item i) {
		String an = Tools.articleForString(i.getName());
		Tools.toAreaSpaced("You have "+an+" "+i.getName()+" in your inventory.");
		return inv.addItem(i);
	}
	
	public static void removeFromInventory(Item i) {
		Tools.logln("Removed "+i.getName()+" from inventory", 1);
		inv.removeItem(i);
	}

	public static void suicide() {
		Tools.toAreaSpaced(Strings.getString("suicide"));
		Stat.setHealth(0.0);
	}
	
	public static void eat(Food f) {
		double nutrients = f.getNutritionValue();
		if(f.checkIfEaten()) f.eat(); //for now
		if(nutrients > 0.0) {
			Tools.toAreaSpaced("You take the "+f.getName() + " out of your pocket. You ate it and gained "+nutrients+" health.");
			Stat.addHealth(nutrients);
		} else if(nutrients < 0.0) {
			Tools.toAreaSpaced("You take the "+f.getName() + " out of your pocket. You took the risk, ate it and lost "+Math.abs(nutrients)+" health.");
			Stat.subtractHealth(nutrients);
		}
		removeFromInventory(f);
	}

	public static void displayHealth() {
		double h = Stat.getHealth();
		//double remain = Stat.MAX_HEALTH - Stat.getHealth();
		String s = "[";
		for(int i = 0; i < Stat.MAX_HEALTH; i++) {
			if(i < h) {
				s += "■";
			} else {
				s += "□";
			}
		}
		s += "]";
		s += " - HP: "+h;
		Tools.toAreaSpaced(s);
		
	}

}
