package graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Grid;
import app.Game;
import app.Generator;
import model.rule.Rule;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 7376825297884956163L;
    Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWith = (tailleEcran.width*2/3)+150;
    int screenheight = (tailleEcran.height*2/3)+100;

	private final Menu menu;
    private final Config zoneConfiguration;
    private final Rendu zoneRendu;
	private  GridGraphique grid;

	private Game game;
	private Rule rule;


	public  MainWindow(Game game){
		super("jeux de la vie");
		JPanel contentPane= (JPanel)this.getContentPane();

		this.menu = new Menu();
		this.zoneRendu = new Rendu();
		this.zoneConfiguration = new Config();
		this.game = game;
		this.rule = new Rule("");
		contentPane.add(menu, BorderLayout.NORTH);
		contentPane.add(createPage(),BorderLayout.CENTER);

		this.zoneConfiguration.showGrid.addActionListener(this::generateListener);
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
		//creation de panel vide pour rejoudre le probleme des margin
		JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(1,0));
		page.add(south,BorderLayout.SOUTH);
		return page;
	}
	public void generateListener(ActionEvent e) {
		if(this.grid!=null){
			this.grid.repaint();
			this.zoneRendu.rendu.remove(this.grid);
			this.zoneRendu.rendu.setVisible(false);
		}
		if(this.zoneRendu.classic.isSelected()){
			this.grid = new GridGraphique(new Grid(100,100));
			this.grid.repaint();
			this.grid.setVisible(false);
			this.grid.setVisible(true);
			this.zoneRendu.rendu.add(this.grid,BorderLayout.CENTER);
		}
//		if(this.zoneRendu.hashlif.isSelected()){
//
//		}
		this.zoneRendu.rendu.repaint();
		this.zoneRendu.rendu.setVisible(false);
		this.zoneRendu.rendu.setVisible(true);
	}

	public void choiceRule(ActionEvent e) {
		if(this.zoneConfiguration.listRules.getSelectedIndex()==0){
	//		this.rule = new Rule("B2/S23");
			this.zoneConfiguration.rulesZone.setText("B2/S23");
			this.rule.setRule(Rules.GAMEOFLIFE);
		}
		if(this.zoneConfiguration.listRules.getSelectedIndex()==1){
			String other=this.zoneConfiguration.rulesZone.getText();
			this.rule.setRule(other);
		}
	}

	public void eventNavigation(ActionEvent e) {

	}

}
