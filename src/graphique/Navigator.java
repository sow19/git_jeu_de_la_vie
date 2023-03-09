package graphique;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Navigator extends JToolBar {
	JButton play,next,prec,stop,acc,dcel,initEtat;
	
	JButton zoomIn,zoomOut,adapt,fullScreen;
	
	public Navigator(){
//		this.setLayout(new FlowLayout());
		//boutton de navigation
		this.play=new JButton(new ImageIcon("play.jpg"));
		this.play.setPreferredSize(new Dimension(150,100));
		this.next= new JButton("next");
		this.prec= new JButton("prec");
		this.stop= new JButton("stop");
		this.acc= new JButton("accelerer");
		this.dcel= new JButton("decelere");
		this.initEtat= new JButton("debut");
		//boutton de pour gerer la grille
		this.zoomIn= new JButton("Zoom avant");
		this.zoomOut= new JButton("Zooom arriere");
		this.adapt= new JButton("adapter");
		this.fullScreen= new JButton("next");
		//creation de panel
		JPanel nav=new JPanel(); //panel pour les bouton navigation
		JPanel screen = new JPanel();
		//ajout des boutons dans nav
		nav.setLayout(new FlowLayout());
		nav.setPreferredSize(new Dimension(40,30));
//		play.setPreferredSize(new Dimension(10,30));
		nav.add(this.play);
		nav.add(this.next);
		nav.add(this.prec);
		nav.add(this.stop);
		nav.add(this.acc);
		nav.add(this.dcel);
		nav.add(this.initEtat);
		//ajout dans screen
		screen.setLayout(new FlowLayout());
		screen.setPreferredSize(new Dimension(40,30));
		screen.add(this.zoomIn);
		screen.add(this.zoomOut);
		screen.add(this.adapt);
		screen.add(this.fullScreen);
		this.setSize(100, 200);
		this.add(nav);
		this.add(screen);
	}
}
