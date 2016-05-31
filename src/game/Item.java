package game;

import java.util.ArrayList;

public class Item {
	
	private static int idSetter = 0;
	public int item_id = idSetter;
	private String item_name;
	private String item_desc;

	public Item() {
		idSetter++;
		setID(getID());
		setName("");
		setDesc("");
	}
	 
	public Item(String name, String description) {
		idSetter++;
		setID(getID());
		setName(name);
		setDesc(description);
	}
	
	public int getID() {
		return item_id;
	}
	
	public void setID(int id) {
		if(id < Inventory.spots) {
			item_id = id;
		}
	}
	
	public String getName() {
		return item_name;
	}
	
	public void setName(String name) {
		item_name = name;
	}
	
	public String getDesc() {
		return item_desc;
	}
	
	public void setDesc(String desc) {
		item_desc = desc;
	}
	
	public boolean equals(Item i) {
		return (getID() == i.item_id);
	}
	
	public void decrementID() {
		idSetter--;
	}
	
	public String toString() {
		ArrayList<String> ss = new ArrayList<String>();
		ss.add("Name: "+item_name);
		ss.add("Description: "+item_desc);
		ss.add("ID: "+item_id);
		return ss.toString();
	}

}
