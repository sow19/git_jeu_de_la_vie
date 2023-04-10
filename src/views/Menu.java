package views;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class Menu extends JToolBar {
	private static final long serialVersionUID = 1L;
	public JButton aide;
	protected JComboBox<String> patterns;
	public Menu() {
		//parie pattern
		JLabel menu=new JLabel("Patterns:");
		String[] choix={"Burst.txt","Coeur.txt","Delta.txt","Lettre_txt","Sawfish.txt"};
		this.patterns=new JComboBox<>(choix);
		// this.patterns.setPreferredSize(new Dimension(140,30));
		//create button aide
		this.aide=new JButton("aide");
		this.aide.setPreferredSize(new Dimension(110,30));
		//add in JToolBar
		this.add(menu);
		this.add(this.patterns);
		this.add(aide);
	}
}
