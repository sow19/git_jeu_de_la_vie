package graphique;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class Config extends JPanel{
    private static final long serialVersionUID = 1L;
    public JPanel config;
    // public JComboBox<String> listePattern;
    protected JComboBox<String> listeChois;
    protected JLabel iterationZone,populationZone;
public Config(){
    //init attributs
    this.config=new JPanel();
    this.iterationZone=new JLabel();
    this.iterationZone.setPreferredSize(new Dimension(100,30));
    this.iterationZone.setBorder(BorderFactory.createLoweredBevelBorder());
    this.populationZone=new JLabel();
    this.populationZone.setPreferredSize(new Dimension(100,30));
    this.populationZone.setBorder(BorderFactory.createLoweredBevelBorder());
    //
    JLabel iterationlabel=new JLabel("iteration:");
    iterationlabel.setPreferredSize(new Dimension(90,20));
    JLabel populationLabel=new JLabel("population:");
    populationLabel.setPreferredSize(new Dimension(90,20));
    //creation des regles
    JLabel rulLabel=new JLabel("rules:");
    rulLabel.setPreferredSize(new Dimension(55,20));
    JToolBar rules=new JToolBar();
    String[] rulesExisted= {"game of life","automate Cellulaire","ohter"};
    this.listeChois=new JComboBox<>(rulesExisted);
    this.listeChois.setPreferredSize(new Dimension(140,30));
    rules.add(rulLabel);
    rules.add(this.listeChois);
    //
    //iteration panel
    JPanel panelIteration=new JPanel();
    panelIteration.add(iterationlabel);
    panelIteration.add(iterationZone);
    //population panel
    JPanel panelPopulation =new JPanel();
    panelPopulation.add(populationLabel);
    panelPopulation.add(populationZone);
    //rule panel
    JPanel panelRule= new JPanel();
    panelRule.add(rules);
    //panel principal
    this.config.setPreferredSize(new Dimension(400,280));
    this.config.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
    this.config.add(panelIteration);
    this.config.add(panelPopulation);
    this.config.add(panelRule);
    JScrollPane jscp=new JScrollPane(this.config);
    this.add(jscp);
}
    
}