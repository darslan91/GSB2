package action.validation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import controleurs.Menu;
import panel.Vue;
import panel.recherche.Panel_Remboursement_Recherche;
import panel.recherche.Panel_Validation_Recherche;
import panel.validation.Panel_Remboursement_De_La_Fiche;

public class ActionRemboursementFicheFrais implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private JLabel lblVal;
	
	/* CONSTRUCTEURS */
	public ActionRemboursementFicheFrais(Vue uneVue){
		this.vue = uneVue;
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			//Remove
		this.vue.remove(this.vue.getContentPane());
		
			//Ajout menu
		this.vue.setJMenuBar(new Menu(this.vue));
		
			//Ajout recherche du panel
		this.vue.setContentPane(new Panel_Remboursement_Recherche(this.vue));
		
			//Revalidation
		this.vue.revalidate();
	}
}
