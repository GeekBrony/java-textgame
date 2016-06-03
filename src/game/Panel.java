package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Panel implements ActionListener {

	static JTextField text;
	Game g;
	JFrame frame;
	final String t = "Game";

	public Panel(int WIDTH, int HEIGHT) {
		frame = new JFrame(t);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);

		text = new JTextField();
		text.addActionListener(this);
		frame.add(text, BorderLayout.PAGE_END);

		Tools.area = new JTextArea();
		Tools.area.setBackground(Color.BLACK);
		Tools.area.setForeground(Color.GREEN);
		Tools.area.setFont(new Font("Courier", Font.PLAIN, 14));
		Tools.area.setMargin(new Insets(10,10,10,10));
		Tools.area.setEditable(false);
		Tools.scroll = new JScrollPane(Tools.area);
		frame.add(Tools.scroll, BorderLayout.CENTER);
		Tools.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	public void show() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		g = new Game(true);
		g.runInstance();
	}
	
	public void stopInstance() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(g.instanceRunning()) {
			if(!text.getText().equalsIgnoreCase("")) {
				Tools.toArea(text.getText());
			} else {
				Tools.toAreaSpaced("");
			}
			if(!Tools.containsSwears(text.getText())) {
				g.handleInput(text.getText());
			} else {
				Tools.toAreaSpaced(Strings.getString("swear_respond"));
			}
		} else {
			//Tools.logln("Exiting...", 1);
			//System.exit(0);
		}
		
		text.setText("");
		frame.setTitle(t+" | HP: "+Stat.getHealth());
	}

}
