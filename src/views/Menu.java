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
		//partie pattern
		JLabel menu=new JLabel("Patterns:");
		String[] choix={"Burst.txt","Coeur.txt","Delta.txt","Lettre_L.txt","Sawfish.txt"};
		this.patterns=new JComboBox<>(choix);
		//create button aide
		this.aide=new JButton("aide");
		this.aide.setPreferredSize(new Dimension(110,30));
		//add in JToolBar
		this.add(menu);
		this.add(this.patterns);
		this.add(aide);
	}
}
