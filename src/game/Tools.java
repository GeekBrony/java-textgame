package game;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Tools {
	
	public static boolean textOnly = false;
	public static JTextArea area;
	public static int counter;
	public static JScrollPane scroll;

	/*
	 * Method to log to console;
	 * int LOG_LEVEL:
	 * 0 - NORMAL (NO ERRORS/WARNINGS)
	 * 1 - INFO (USED TO GIVE EXTRA INFO)
	 * 2 - WARN (USED TO GIVE A WARNING)
	 * 3 - ERR (USED TO GIVE AN ERROR)
	 * 
	 * TODO: Figure out how to add colors to text.
	 */
	public static void log(Object str, int LOG_LEVEL) {
		if(textOnly) {
			String prefix = "";
			switch(LOG_LEVEL) {
				case 0:
					prefix = "";
					break;
				case 1:
					prefix = "[INFO] ";
					break;
				case 2:
					prefix = "[WARN] ";
					break;
				case 3:
					prefix = "[ERR] ";
					break;
			}
			System.out.print(prefix + str);
			
		} else {
			// Nothing should be logged at this time.
		}
	}
	
	public static void logln(Object str, int LOG_LEVEL) {
		log(str+"\n", LOG_LEVEL);
	}
	
	public static void toArea(String s1) {
		String s = "> " + s1 + "\n";
		counter++;
		area.append(s);
		area.setCaretPosition(area.getDocument().getLength());
		//if (counter >= 22) {
			//String test = area.getText().substring(area.getText().indexOf("\n")+1, area.getText().length());
			//area.setText(s);
		//}
	}
	
	public static void toAreaSpaced(String s1) {
		s1 = s1.replace("\n", "\n\t");
		String s = "\t" + s1 + "\n";
		counter++;
		area.append(s);
		area.setCaretPosition(area.getDocument().getLength());
		//if (counter >= 22) {
			//String test = area.getText().substring(area.getText().indexOf("\n")+1, area.getText().length());
			//area.setText(s);
		//}
	}
	
	public static void toAreaOutputOneLine(String s1) {
		s1 = s1.replace("\n", "\n\t");
		String s = "\t" + s1;
		counter++;
		area.append(s);
		area.setCaretPosition(area.getDocument().getLength());
		//if (counter >= 22) {
			//String test = area.getText().substring(area.getText().indexOf("\n")+1, area.getText().length());
			//area.setText(s);
		//}
	}
	
	public static void toFrame(JTextArea txt, String st) {
		
	}
	
	public static int makeInt(Number i) {
		return i.intValue();
	}
	
	public static int randomize(int range) {
		return makeInt(Math.random() * (range));
	}
	
	public static boolean checkProperArticle(String n) {
		String su = n.substring(0, 1);
		return !(su.equalsIgnoreCase("a") || su.equalsIgnoreCase("e") ||
				su.equalsIgnoreCase("i") || su.equalsIgnoreCase("o") ||
				su.equalsIgnoreCase("u"));
	}
	
	public static String articleForString(String n) {
		return Tools.checkProperArticle(n) ? "a" : "an";
	}

	public static boolean containsSwears(String text) {
		String[] s = text.split(" ");
		for(int i = 0; i < s.length; i++) {
			if(s[i].equalsIgnoreCase("fuck") || s[i].equalsIgnoreCase("shit") ||
			s[i].equalsIgnoreCase("ass") || s[i].equalsIgnoreCase("bitch") ||
			s[i].equalsIgnoreCase("fucking") || s[i].equalsIgnoreCase("fucker"))
				return true;
		}
		return false;
	}
}
