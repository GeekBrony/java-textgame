package game;

public class Stat {
	
	public static final double MIN_HEALTH = 0.0;
	public static final double MAX_HEALTH = 20.0;
	
	private static double health = 10.0;
	
	public static void setHealth(double h) {
		health = h;
		if(health == 0.0) {
			Game.endGame();
		}
	}
	
	public static void addHealth(double h) {
		if((health + h) >= MAX_HEALTH) {
			health = MAX_HEALTH;
		} else {
			health = health + h;
		}
	}
	
	public static void subtractHealth(double h) {
		h = Math.abs(h);
		if((health - h) <= MIN_HEALTH) {
			health = MIN_HEALTH;
			Game.endGame();
		} else {
			health = health - h;
		}
	}
	
	public static double getHealth() {
		return health;
	}

}
