package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 7376825297884956163L;
    Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWith = (tailleEcran.width*2/3)+150;
    int screenheight = (tailleEcran.height*2/3)+100;

	private final Menu menu;
    private final Config zoneConfiguration;
    private final Rendu zoneRendu;

public  MainWindow() {
	super("jeux de la vie");
	JPanel contentPane= (JPanel)this.getContentPane();

	this.menu = new Menu();
	this.zoneRendu = new Rendu();
	this.zoneConfiguration = new Config();

	contentPane.add(menu, BorderLayout.NORTH);
	contentPane.add(createPage(),BorderLayout.CENTER);

	this.setSize(screenWith,screenheight);
	// this.pack();
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setVisible(true);
}
private JPanel createPage(){
	JPanel page = new JPanel(new BorderLayout(50, 30));
	page.setPreferredSize(new Dimension(300,200));
	page.add(this.zoneConfiguration,BorderLayout.WEST);
	page.add(this.zoneRendu.getRenduPanel(),BorderLayout.CENTER);
	//creation de deux panel vide pour rejoudre le probleme des margin
	JPanel west = new JPanel();
	JPanel south = new JPanel();
	west.setPreferredSize(new Dimension(20,0));
	south.setPreferredSize(new Dimension(1,0));
	page.add(west,BorderLayout.EAST);
	page.add(south,BorderLayout.SOUTH);
	return page;
}
}
