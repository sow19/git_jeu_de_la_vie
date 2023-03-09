package graphique;

import java.awt.Canvas;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GraphiqueGrid extends JPanel {
	public GraphiqueGrid() {
		this.setLayout(new GridLayout(5,5,2,2));
		for(int i=0;i<20;i++) {
			this.add(new JButton("bt"+i));
		}
//		Canvas canvas= new Canvas();
		
	}
}
