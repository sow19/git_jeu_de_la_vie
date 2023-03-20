package graphique;

import java.awt.Dimension;

import javax.swing.JLabel;

public class InfoLabel extends JLabel {
//	protected String txt;
	public InfoLabel() {
//		super(txt);
		String txt="";
		this.setText(txt);
		this.setPreferredSize(new Dimension(50,100));
	}
}
