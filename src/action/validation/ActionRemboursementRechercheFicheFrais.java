package action.validation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import panel.Vue;
import panel.validation.Panel_Remboursement_De_La_Fiche;

public class ActionRemboursementRechercheFicheFrais implements ActionListener{
	
	/* ATTRIBUTS PRIVEES */
	private Vue vue;
	private JLabel lblVal;
	private String mois;
	private String nom;
	
	/* CONSTRUCTEURS */
	public ActionRemboursementRechercheFicheFrais(Vue uneVue, JLabel lblMsg, String unMois, String unNom){
		this.vue = uneVue;
		this.lblVal = lblMsg;
		this.mois = unMois;
		this.nom = unNom;
	}
	public ActionRemboursementRechercheFicheFrais(Vue uneVue, String unMois, String unNom){
		this.vue = uneVue;
		this.mois = unMois;
		this.nom = unNom;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//Remove
		this.vue.remove(this.vue.getContentPane());
		
		//Ajout recherche du panel
		this.vue.setContentPane(new Panel_Remboursement_De_La_Fiche(this.vue, this.mois, this.nom));
		
		//revalidation
		this.vue.revalidate();
	}

}
