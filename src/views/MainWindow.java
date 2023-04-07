package views;

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
import util.ListeningModel;
import constants.Rules;

public class MainWindow extends JFrame implements ListeningModel {
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
		this.game.addListening(this);

		this.rule = new Rule("B2/S23");
//		this.grid = new GridGraphique(this.game.getGrid());
		contentPane.add(menu, BorderLayout.NORTH);
		contentPane.add(createPage(),BorderLayout.CENTER);
		//events

		this.zoneConfiguration.showGrid.addActionListener(this::generateListener);
//		this.eventNavigation();

		this.zoneRendu.play.addActionListener(event -> {
//			while (this.zoneRendu.play.isSelected()) {
			if(this.zoneRendu.play.isSelected()){
				this.zoneRendu.play.setText("stop");

				this.game.play();
				
			}else{
				this.zoneRendu.play.setText("play");
			}
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//				}
//				Thread.sleep(10);
//			}
			this.grid.setVisible(false);
			this.grid.setVisible(true);
			this.grid.repaint();
			this.zoneRendu.rendu.repaint();
			this.zoneRendu.rendu.setVisible(false);
			this.zoneRendu.rendu.setVisible(true);
		});


		this.zoneConfiguration.listRules.addActionListener(this::choiceRule);

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
			this.grid = new GridGraphique(game.getGrid());

			this.grid.repaint();
			this.grid.setVisible(false);
			this.grid.setVisible(true);
			this.zoneRendu.rendu.add(this.grid,BorderLayout.CENTER);
		}
		this.zoneRendu.rendu.repaint();
		this.zoneRendu.rendu.setVisible(false);
		this.zoneRendu.rendu.setVisible(true);
	}

	public void choiceRule(ActionEvent e) {
		if(this.zoneConfiguration.listRules.getSelectedIndex()==0){
			this.zoneConfiguration.panelRule.remove(this.zoneConfiguration.txt);
			this.zoneConfiguration.panelRule.setVisible(false);
			this.zoneConfiguration.rulesZone.setText("B2/S23");
		}
		if(this.zoneConfiguration.listRules.getSelectedIndex()==1){
			this.zoneConfiguration.txt.setPreferredSize(new Dimension(100,30));
//        panelRule.remove(txt);
			this.zoneConfiguration.panelRule.setVisible(false);
			this.zoneConfiguration.rulesZone.setText("");
			this.zoneConfiguration.txt.addActionListener( event -> {
				this.zoneConfiguration.rulesZone.setText(this.zoneConfiguration.txt.getText());
				this.zoneConfiguration.panelRule.remove(this.zoneConfiguration.txt);
				this.zoneConfiguration.panelRule.setVisible(false);
				this.zoneConfiguration.panelRule.setVisible(true);
			});
			this.zoneConfiguration.panelRule.add(this.zoneConfiguration.txt);
		}
		this.zoneConfiguration.panelRule.revalidate();
		this.zoneConfiguration.panelRule.repaint();
		this.zoneConfiguration.panelRule.setVisible(false);
		this.zoneConfiguration.panelRule.setVisible(true);
	}

//	public void eventNavigation() {
//			if(this.zoneRendu.play.isSelected()){
//				this.zoneRendu.play.addActionListener(event -> {
//					while (this.zoneRendu.play.isSelected()) {
//						this.game.setGrid(this.game.getGenerator().nextGeneration(this.game.getGrid()));
//						this.grid.setVisible(false);
//						this.grid.setVisible(true);
//						this.grid.repaint();
//					}
//					this.zoneRendu.rendu.repaint();
//					this.zoneRendu.rendu.setVisible(false);
//					this.zoneRendu.rendu.setVisible(true);
//				});
//			}
//	}

	public void playGraphique() {
		this.game.play();
	}

	@Override
	public void modeleMIsAJour(Object source, Object notification) {
		//System.out.println("new one");
		this.grid.setGridModel(this.game.getGrid());
	}

}
