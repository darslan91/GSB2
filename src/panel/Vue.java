package panel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.*;

import controleurs.Menu;
import modele.Modele;

public class Vue extends JFrame{
	
	/* ATTRIBUTS */
	private JPanel panelGlobal;
	
	/* CONSTRUCTEUR */
	public Vue(){
		
		/*INFOS FENETRE */
		this.setTitle("GSB - Applis Frais");
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//logo
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		
		/* DECLARATION */
			//Panel
		this.panelGlobal = new JPanel();
		if(Modele.resteConnecter()) {
			this.setContentPane(new panel_ConnexionSave(this, Modele.getNomS(), Modele.getMdpS()));
		}
		else {
			this.setContentPane(new panel_Connexion(this));
		}
		
			//Affichage
		this.getContentPane().add(this.panelGlobal);
		this.panelGlobal.setBackground(Color.ORANGE);
		this.setVisible(true);
	}

}
