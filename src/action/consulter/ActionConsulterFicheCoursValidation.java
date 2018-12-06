package action.consulter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import modele.consulter.ModeleConsulter;
import classes.FicheFrais;
import controleurs.Menu;
import panel.Vue;
import panel.consulter.Panel_Fiche_En_Cours_De_Validation;


public class ActionConsulterFicheCoursValidation implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private ArrayList<FicheFrais> lesFichesFraisCoursValidation;
	
	/* CONSTRUCTEURS */
	public ActionConsulterFicheCoursValidation(Vue uneVue){
		this.vue = uneVue;
	}

	
	public void actionPerformed(ActionEvent e) {
		//Recupere la liste au travers du modele 
		this.lesFichesFraisCoursValidation = ModeleConsulter.getLesFichesFraisCoursValidation();
		
		this.vue.remove(this.vue.getContentPane());
		this.vue.setContentPane(new Panel_Fiche_En_Cours_De_Validation(this.lesFichesFraisCoursValidation));
		this.vue.setJMenuBar(new Menu(this.vue));
		this.vue.revalidate();		
		
	}
}
