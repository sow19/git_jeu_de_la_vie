package graphique;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRule extends JPanel {
	public PanelRule() {
//		this.setPreferredSize(new Dimension(10, 20));
		this.setMaximumSize(new Dimension(10, 20));		
		this.setLayout(new GridLayout(2,1));
		JTextField rule=new JTextField("rule a saisir");
		rule.setPreferredSize(new Dimension(10,12));;
		String []s = {"java","PHP"};
		JComboBox combowx= new JComboBox(s);
		this.add(rule);
		this.add(combowx);
//		this.resize(10, 20);		
	}
}
