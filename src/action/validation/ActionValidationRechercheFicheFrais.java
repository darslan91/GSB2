package action.validation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleurs.Menu;
import panel.Vue;
import panel.recherche.Panel_Remboursement_Recherche;
import panel.recherche.Panel_Validation_Recherche;
import panel.validation.Panel_Validation_De_La_Fiche;

public class ActionValidationRechercheFicheFrais implements ActionListener{
	
	/* ATTRIBUTS PRIVEES */
	private Vue vue;
	private JLabel lblVal;
	private String mois;
	private String nom;
	
	/* CONSTRUCTEURS */
	public ActionValidationRechercheFicheFrais(Vue uneVue, JLabel lblMsg, String unMois, String unNom){
		this.vue = uneVue;
		this.lblVal = lblMsg;
		this.mois = unMois;
		this.nom = unNom;
	}
	public ActionValidationRechercheFicheFrais(Vue uneVue, String unMois, String unNom){
		this.vue = uneVue;
		this.mois = unMois;
		this.nom = unNom;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			
		//Remove
		this.vue.remove(this.vue.getContentPane());
		
		//Ajout
		this.vue.setContentPane(new Panel_Validation_De_La_Fiche(this.vue, this.mois, this.nom));
		
		//revalidation
		this.vue.revalidate();
	}

}