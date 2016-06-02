package game;

import java.util.ArrayList;

public class Food extends Item {

	private String name = "";
	private double nutrition = 0;
	private boolean hasBeenEaten = false;
	private boolean isLiquid = false;
	
	public Food(String name, double nutrition) {
		this.name = name;
		this.nutrition = nutrition;
		hasBeenEaten = false;
	}
	
	public Food(String name, double nutrition, boolean isLiquid) {
		this.name = name;
		this.nutrition = nutrition;
		this.isLiquid = isLiquid;
		hasBeenEaten = false;
	}
	
	public void eat() {
		// this needed something - lost train of thought tho
		hasBeenEaten = true;
		//
	}
	
	public boolean checkIfEaten() {
		return hasBeenEaten;
	}
	
	public boolean isLiquid() {
		return isLiquid;
	}
	
	public String getName() {
		return name;
	}
	public double getNutritionValue() {
		return nutrition;
	}
	
	public String toString() {
		ArrayList<String> ss = new ArrayList<String>();
		ss.add("Name: "+name);
		ss.add("Liquid? "+(isLiquid ? "Yes": "No"));
		ss.add("Nutrition Value: "+nutrition);
		ss.add("Has it been eaten? "+(hasBeenEaten ? "Yes" : "No"));
		return ss.toString();
	}

}
