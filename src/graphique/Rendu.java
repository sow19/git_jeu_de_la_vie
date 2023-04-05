package graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import model.Grid;

public class Rendu extends JPanel {
	private static final long serialVersionUID = 1L;
	public JPanel rendu = new JPanel(new BorderLayout());
	public JPanel nav=new JPanel(new FlowLayout());
	public JButton play,next,prec,stop,acc,dcel,initEtat;
	public JRadioButton classic,hashlif;
	public JButton zoomIn,zoomOut;
	public float zoom=1;

//	private  GridGraphique grid;


	public Rendu(){
		this.setLayout(new BorderLayout());
		//boutton de navigation
		this.play=new JButton("play");
		this.next= new JButton("next");
		this.prec= new JButton("prec");
		this.stop= new JButton("stop");
		this.acc= new JButton("accelerer");
		this.dcel= new JButton("decelere");
		this.initEtat= new JButton("debut");
		//boutton de zoom
		this.zoomIn= new JButton("+");
		this.zoomOut= new JButton("-");
		this.zoomOut.addActionListener((Event)-> this.zoom-=0.02);
		//button radio
		this.classic=new JRadioButton("Classic",true);
		this.hashlif=new JRadioButton("Hashlif");
		//create button group
		ButtonGroup bGroup=new ButtonGroup();
		bGroup.add(this.classic);
		bGroup.add(this.hashlif);
		//panel choix de l'algo
		JPanel algoPanel=new JPanel();
		algoPanel.add(this.classic);
		algoPanel.add(this.hashlif);
		//creation de panel nav
		this.nav.setPreferredSize(new Dimension(100,40));
		nav.add(this.play);nav.add(this.next);nav.add(this.prec);nav.add(this.stop);nav.add(this.acc);nav.add(this.dcel);nav.add(this.initEtat);
		this.nav.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		//creation de panel screen
		JPanel screen = new JPanel();
		screen.add(this.zoomIn);
		screen.add(this.zoomOut);

		//creation de panel west pour le marging
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(20,0));

		this.rendu.setPreferredSize(new Dimension(600,600));
		this.rendu.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.rendu.add(algoPanel,BorderLayout.NORTH);
		this.rendu.add(screen,BorderLayout.SOUTH);
		this.rendu.add(west,BorderLayout.WEST);

		JScrollPane jsc1=new JScrollPane(this.rendu);
		JScrollPane jsc2=new JScrollPane(this.nav);
		
		this.add(jsc2,BorderLayout.NORTH);
		this.add(jsc1,BorderLayout.CENTER);
	}
	JScrollPane getRenduPanel(){
		return new JScrollPane(this);
	}
}
