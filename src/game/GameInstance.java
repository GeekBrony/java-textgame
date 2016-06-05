package game;

public class GameInstance {

	public final static boolean TEXT_ONLY = true;
	public final static int W = 848;
	public final static int H = 480;
	static Panel p;
	
	public static void main(String[] args) {
		
		p = new Panel(W, H);
		p.show();

	}
	
	public static void restartPanel() {
		p = null;
		p = new Panel(W, H);
		p.show();
	}

}
