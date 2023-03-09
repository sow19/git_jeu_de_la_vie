package graphique;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.JPanel;

public class Menu extends JMenuBar {
	public Menu() {
//		JMenuBar menuBar= new JMenuBar();
		JMenu mFile=new JMenu("file");
		JMenu aide=new JMenu("Aide");
		JMenuItem newFile=new JMenuItem("New File");
		mFile.add(newFile);
		this.add(mFile);
		this.add(aide);
	}
}
