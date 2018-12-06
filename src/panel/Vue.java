package panel;

import java.awt.GridBagLayout;

import javax.swing.*;

import controleurs.Menu;

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
		
		
		/* DECLARATION */
			//Panel
		this.panelGlobal = new JPanel();
		this.setContentPane(new panel_Connexion(this));
			//Affichage
		this.getContentPane().add(this.panelGlobal);
		this.setVisible(true);
	}

}
