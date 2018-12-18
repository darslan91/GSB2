package panel;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import classes.LigneFraisForfait;
import classes.LigneFraisHorsForfait;
import panel.consulter.Panel_Details_Fiche_Rembourser;


public class Vue_Detail extends JFrame{
	
	/* ATTRIBUTS PRIVEES */
	private JPanel panelGlobal;
	
	
	/* CONSTRUCTEUR */
	public Vue_Detail(String id, Object mois, float montant, ArrayList<LigneFraisForfait> listeFF, ArrayList<LigneFraisHorsForfait> listeFHF){
		
		/*INFOS FENETRE */
		this.setTitle("Fiche frais");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/* GRIDLAYOUT */
		this.setLayout(new GridLayout(2, 0));
		
		/* DECLARATION ET INSTANCIATION DES ELEMENTS */
		this.panelGlobal = new JPanel();
		this.setContentPane(new Panel_Details_Fiche_Rembourser(id, mois, montant, listeFF, listeFHF));
		//this.setContentPane(new Panel_Tableau_Element_Forfaitiser(listeFF));
		this.getContentPane().add(this.panelGlobal);
		this.setVisible(true);
		
		
	}
	

}
