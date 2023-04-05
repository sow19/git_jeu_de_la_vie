package graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class Config extends JPanel{
    private static final long serialVersionUID = 1L;
    public JPanel config;
    // public JComboBox<String> listePattern;
    protected JComboBox<String> listRules;
    protected JLabel iterationZone,populationZone,rulesZone;
    public JButton showGrid;
    public  JPanel panelRule;
    public Config(){
    //init attributs
    this.config=new JPanel();
    this.rulesZone=new JLabel();
    this.rulesZone.setPreferredSize(new Dimension(100,30));
    this.iterationZone=new JLabel();
    this.iterationZone.setPreferredSize(new Dimension(100,30));
    this.iterationZone.setBorder(BorderFactory.createLoweredBevelBorder());
    this.populationZone=new JLabel();
    this.populationZone.setPreferredSize(new Dimension(100,30));
    this.populationZone.setBorder(BorderFactory.createLoweredBevelBorder());
    this.showGrid = new JButton("Show Grid");
        //
    JLabel iterationlabel=new JLabel("iteration:");
    iterationlabel.setPreferredSize(new Dimension(90,20));
    JLabel populationLabel=new JLabel("population:");
    populationLabel.setPreferredSize(new Dimension(90,20));
    //creation des regles
    JLabel rulLabel=new JLabel("rules:");
    rulLabel.setPreferredSize(new Dimension(55,20));
    JToolBar rules=new JToolBar();
    String[] rulesExisted= {"game of life","ohter"};
    this.listRules=new JComboBox<>(rulesExisted);
    this.listRules.setPreferredSize(new Dimension(140,30));
    rules.add(rulLabel);
    rules.add(this.listRules);
    //Action events
        this.listRules.addActionListener(this::ajoutRule);

    //panel buttons
    JPanel panelButtons=new JPanel();
    panelButtons.add(this.showGrid);
    //iteration panel
    JPanel panelIteration=new JPanel();
    panelIteration.add(iterationlabel);
    panelIteration.add(iterationZone);
    //population panel
    JPanel panelPopulation =new JPanel();
    panelPopulation.add(populationLabel);
    panelPopulation.add(populationZone);
    //rule panel
    panelRule= new JPanel();
    panelRule.add(rules);
    panelRule.add(this.rulesZone);
    //panel principal
    this.config.setPreferredSize(new Dimension(400,280));
    this.config.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
    this.config.add(panelIteration);
    this.config.add(panelPopulation);
    this.config.add(panelRule);
    this.config.add(panelButtons);
    JScrollPane jscp=new JScrollPane(this.config);
    this.add(jscp);
}

public void ajoutRule(ActionEvent action){
    if(this.listRules.getSelectedIndex()==0){
        this.rulesZone.setText("B2/S23");
    }
    if(this.listRules.getSelectedIndex()==1){
        JTextField txt=new JTextField();
        txt.setPreferredSize(new Dimension(140,30));
        panelRule.add(txt);
        this.rulesZone.setText(txt.getText());
    }
    this.panelRule.revalidate();
    this.panelRule.repaint();
    this.panelRule.setVisible(false);
    this.panelRule.setVisible(true);
}
    
}