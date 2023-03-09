package graphique;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
public  MainWindow() {
	super("jeux de la vie");
	this.setSize(400,300);
	this.setLocationRelativeTo(null);
//	this.setLayout(new BorderLayout());
	//create the menu
	Menu menu=new Menu();
	this.setJMenuBar(menu);
	//create Navigator
	Navigator nav= new Navigator();
	
	this.add(nav);
	this.setVisible(true);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}
}
