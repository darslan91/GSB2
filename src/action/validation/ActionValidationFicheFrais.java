package action.validation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import controleurs.Menu;
import panel.Vue;
import panel.recherche.Panel_Validation_Recherche;

public class ActionValidationFicheFrais implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private JLabel lblMsg;
	
	/* CONSTRUCTEURS */
	public ActionValidationFicheFrais(Vue uneVue){
		this.vue = uneVue;
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
			//Remove
		this.vue.remove(this.vue.getContentPane());
		
			//Ajout menu
		this.vue.setJMenuBar(new Menu(this.vue));
		
			//Ajout recherche du panel
		this.vue.setContentPane(new Panel_Validation_Recherche(this.vue));
			
			//Revalidation
		this.vue.revalidate();
	}
}
