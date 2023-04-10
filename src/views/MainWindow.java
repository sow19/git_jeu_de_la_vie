package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Component;

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
		this.grid = new GridGraphique(this.game);
		contentPane.add(menu, BorderLayout.NORTH);
		contentPane.add(createPage(),BorderLayout.CENTER);
		//events

		this.eventNavigation();
		this.zoneConfiguration.listRules.addActionListener(this::choiceRule);
		this.eventConfig();
		this.menu.patterns.addActionListener(this::choicePattern);

		this.setSize(screenWith,screenheight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.game.addListening(this);
	}
	private JPanel createPage(){
		JPanel page = new JPanel(new BorderLayout(50, 30));
		page.setPreferredSize(new Dimension(300,200));
		this.zoneRendu.rendu.add(this.grid,BorderLayout.CENTER);
		page.add(this.zoneConfiguration,BorderLayout.WEST);
		page.add(this.zoneRendu.getRenduPanel(),BorderLayout.CENTER);
		//creation de panel vide pour rejoudre le probleme des margin
		JPanel south = new JPanel();
		south.setPreferredSize(new Dimension(1,0));
		page.add(south,BorderLayout.SOUTH);
		return page;
	}

	public void choiceRule(ActionEvent e) {
		if(this.zoneConfiguration.listRules.getSelectedIndex()==1){
			this.zoneConfiguration.txt.setPreferredSize(new Dimension(100,30));
			this.zoneConfiguration.panelRule.setVisible(false);
			this.zoneConfiguration.rulesZone.setText("");
			this.zoneConfiguration.txt.addActionListener( event -> {
				this.zoneConfiguration.rulesZone.setText(this.zoneConfiguration.txt.getText());
				this.zoneConfiguration.panelRule.remove(this.zoneConfiguration.txt);
				this.zoneConfiguration.panelRule.setVisible(false);
				this.zoneConfiguration.panelRule.setVisible(true);
			});
			this.game.changeRule(new Rule(this.zoneConfiguration.txt.getText()));
			this.zoneConfiguration.panelRule.add(this.zoneConfiguration.txt);
		}
		else{
			this.zoneConfiguration.panelRule.remove(this.zoneConfiguration.txt);
			this.zoneConfiguration.panelRule.setVisible(false);
			this.zoneConfiguration.rulesZone.setText("B3/S23");
			this.game.changeRule(new Rule(constants.Rules.GAMEOFLIFE));
		}
		this.zoneConfiguration.panelRule.revalidate();
		this.zoneConfiguration.panelRule.repaint();
		this.zoneConfiguration.panelRule.setVisible(false);
		this.zoneConfiguration.panelRule.setVisible(true);
	}

	public void eventNavigation() {

		//events pour le bouton play
				this.zoneRendu.play.addActionListener(event -> {
					if(this.zoneRendu.play.isSelected()){
						this.zoneRendu.play.setText("stop");
						this.game.runGenThread();
					}else{
						this.game.stopGenThread();
						this.zoneRendu.play.setText("play");
					}
				});
		//events pour le bouton next
				this.zoneRendu.next.addActionListener(event -> {
					if(this.zoneRendu.classic.isSelected()){
						this.game.nextGenerationClassic();
					}else {
						this.game.nextGenerationHashlife();
					}
				});
		//events pour le bouton back
				this.zoneRendu.back.addActionListener(event -> {

					if(this.game.previousGeneration()){
						this.game.previousGeneration();
					}
				});
		//events pour le bouton avance rapide
				this.zoneRendu.rapide.addActionListener(event -> {
					this.game.increaseSpeed();
				});
		//events pour le bouton recule rapide
				this.zoneRendu.recule.addActionListener(event -> {
					this.game.decreaseSpeed();
				});
		//events pour le bouton debuter
				this.zoneRendu.start.addActionListener(event -> {
					this.game.resetGrid();
				});
		//events pour les boutons zoom in and zoom out
				this.zoneRendu.zoomIn.addActionListener(event -> {
					this.grid.setScale(this.grid.getScale()+0.1f);
				});
				this.zoneRendu.zoomOut.addActionListener(event -> {
					this.grid.setScale(this.grid.getScale()-0.1f);
				});

	}

	public void choicePattern(ActionEvent e) {
		String pattern ="views/"+ (String)this.menu.patterns.getSelectedItem();
			this.game.usePattern(pattern);
	}

	public void eventConfig() {
		this.zoneConfiguration.iterationZone.setText((String.valueOf(this.game.getIteration())));
		this.zoneConfiguration.populationZone.setText((String.valueOf(this.game.getNbLiveCell())));
	}
	@Override
	public void modeleMIsAJour(Object source, Object notification) {
		this.grid.repaint();
	}

}
