package game;

public class Inventory {

	public final static int spots = 15;
	private Item[] inv_;

	public Inventory() {
		inv_ = new Item[spots];
	}

	public Item[] inventory() {
		return inv_;
	}
	
	public int getOpenSpots() {
		int open = 0;
		for (int i = 0; i < spots; i++) {
			if (inv_[i] == null) {
				open++;
			}
		}
		return open;
	}
	
	public int addItem(Item ob) {
		int y = spots - getOpenSpots();
		if (getOpenSpots() > 0) {
			inv_[y] = ob;
			return y;
		} else {
			removeItem(inv_[y - 1]);
			inv_[y - 1] = ob;
			return y - 1;
		}
		
	}
	
	public int getItemOffsetWithItemName(String n) {
		int o = -1;
		for(int x = 0; x < inv_.length; x++) {
			if(inv_[x] != null && inv_[x].getName().equalsIgnoreCase(n)) {
				o = x;
			}
		}
		return o;
	}
	
	public void removeItem(Item ob) {
		for(int x = 0; x < inv_.length; x++) {
			if(inv_[x] == ob) {
				inv_[x].decrementID();
				inv_[x] = null;
			}
		}
	}
	
	public Item getItemAtOffset(int offset)
		throws ArrayIndexOutOfBoundsException {
		return inv_[offset];
	}
	
	public int offsetForItem(Item ob) {
		int o = -1;
		for(int x = 0; x < inv_.length; x++) {
			if(inv_[x] == ob) {
				o = x;
			}
		}
		return o;
	}
	
	public void moveItem(Item ob, int offset) {
		// TODO: Complete this.
	}

}
