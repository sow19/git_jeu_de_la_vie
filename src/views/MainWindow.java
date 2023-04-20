package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		this.zoneConfiguration.listVoisins.addActionListener(this::choiceNeighborsType);
		this.eventConfig();
		this.menu.patterns.addActionListener(this::choicePattern);

		this.setSize(screenWith,screenheight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	public void choiceNeighborsType(ActionEvent e) {
		if(this.zoneConfiguration.listVoisins.getSelectedIndex()==0){
			this.zoneConfiguration.panelVoisins.remove(this.zoneConfiguration.txt2);
			this.zoneConfiguration.panelVoisins.setVisible(false);
			this.zoneConfiguration.panelVoisins.setVisible(true);

		}
		if(this.zoneConfiguration.listVoisins.getSelectedIndex()==1){
			this.zoneConfiguration.panelVoisins.remove(this.zoneConfiguration.txt2);
			this.zoneConfiguration.panelVoisins.setVisible(false);
			this.zoneConfiguration.panelVoisins.setVisible(true);
		}
		if(this.zoneConfiguration.listVoisins.getSelectedIndex()==2){
			this.zoneConfiguration.panelVoisins.remove(this.zoneConfiguration.txt2);
			this.zoneConfiguration.panelVoisins.setVisible(false);
			this.zoneConfiguration.panelVoisins.setVisible(true);
		}
		if(this.zoneConfiguration.listVoisins.getSelectedIndex()==3){
			this.zoneConfiguration.txt2.setPreferredSize(new Dimension(200,30));
			this.zoneConfiguration.panelVoisins.setVisible(false);
			this.zoneConfiguration.voisinsZone.setText("");
			this.zoneConfiguration.txt2.addActionListener( event -> {
				this.zoneConfiguration.voisinsZone.setText(this.zoneConfiguration.txt2.getText());
				this.zoneConfiguration.panelVoisins.remove(this.zoneConfiguration.txt2);
				this.zoneConfiguration.panelVoisins.setVisible(false);
				this.zoneConfiguration.panelVoisins.setVisible(true);
			});

			this.zoneConfiguration.panelVoisins.add(this.zoneConfiguration.txt2);
		}
		this.zoneConfiguration.panelVoisins.revalidate();
		this.zoneConfiguration.panelVoisins.repaint();
		this.zoneConfiguration.panelVoisins.setVisible(false);
		this.zoneConfiguration.panelVoisins.setVisible(true);
	}

	public void eventNavigation() {

		//events pour le bouton play
				this.zoneRendu.play.addActionListener(event -> {
					if(this.zoneRendu.play.isSelected()){
						this.zoneRendu.play.setText("stop");
						if(this.zoneRendu.classic.isSelected()){
							this.game.useClassicAlgo();
						}else{
							this.game.useHashlifeAlgo();
						}
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
		// events pour le radio classic algo
		this.zoneRendu.classic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (zoneRendu.classic.isSelected()) {
					game.useClassicAlgo();
				}
			}
		});

		// events pour le radio hashlife alog
		this.zoneRendu.hashlif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (zoneRendu.hashlif.isSelected()) {
					game.useHashlifeAlgo();;
				}
			}
		});
	}

	public void choicePattern(ActionEvent e) {
		String pattern ="patterns/"+ (String)this.menu.patterns.getSelectedItem();
		System.out.println(pattern);
			this.game.usePattern(pattern);
	}

	public void eventConfig() {
		this.zoneConfiguration.iterationZone.setText((String.valueOf(this.game.getIteration())));
		this.zoneConfiguration.speedZone.setText((String.valueOf(this.game.getLastGenComputeTimeInNnos())));
//		this.zoneConfiguration.speedZone.revalidate();
		this.zoneConfiguration.speedZone.setVisible(false);
		this.zoneConfiguration.speedZone.setVisible(true);
		this.zoneConfiguration.speedZone.repaint();
		this.zoneConfiguration.generationZone.setText((String.valueOf(10)));
		this.zoneConfiguration.repaint();
	}

	public void updateStats() {
		// udpate speed
		this.zoneConfiguration.speedZone.setText(game.getLastGenComputeTimeInNnos() + " ns");

		// update iteration
		this.zoneConfiguration.iterationZone.setText(game.getIteration() + "");

		// update population
		this.zoneConfiguration.generationZone.setText(game.getGrid().getPopulation() + "");
	}

	@Override
	public void modeleMIsAJour(Object source, Object notification) {
		this.grid.repaint();
		this.updateStats();
	}

}
