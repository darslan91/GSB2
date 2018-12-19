package panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;

import classes.LigneFraisForfait;
import classes.LigneFraisHorsForfait;
import panel.consulter.Panel_Details_Fiche;
import panel.consulter.Panel_Details_Fiche_Rembourser;


public class Vue_Detail extends JFrame{
	
	/* ATTRIBUTS PRIVEES */
	private JPanel panelGlobal;
	private String etat;
	
	
	/* CONSTRUCTEUR */
	public Vue_Detail(String id, Object mois, float montant, ArrayList<LigneFraisForfait> listeFF, ArrayList<LigneFraisHorsForfait> listeFHF, String etat){
		
		/*INFOS FENETRE */
		this.setTitle("Fiche frais");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.ORANGE);
			//logo
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		
		this.etat = etat;
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* DECLARATION ET INSTANCIATION DES ELEMENTS */
		this.panelGlobal = new JPanel();
		this.panelGlobal.setBackground(Color.ORANGE);
		this.setContentPane(new Panel_Details_Fiche(id, mois, montant, listeFF, listeFHF, this.etat));
		this.getContentPane().add(this.panelGlobal);
		this.setVisible(true);
		
	}
	
}