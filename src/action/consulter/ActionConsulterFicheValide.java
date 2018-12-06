package action.consulter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import modele.consulter.ModeleConsulter;

import classes.FicheFrais;
import controleurs.Menu;
import panel.Vue;
import panel.consulter.Panel_Fiche_Valider;

public class ActionConsulterFicheValide implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private ArrayList<FicheFrais> lesFichesFraisValider;
	
	/* CONSTRUCTEURS */
	public ActionConsulterFicheValide(Vue uneVue){
		this.vue = uneVue;
		//this.lesFichesFraisValider = new ArrayList<FicheFrais>();
	}

	
	public void actionPerformed(ActionEvent e) {
		//Recupere la liste au travers du modele 
		this.lesFichesFraisValider = ModeleConsulter.getLesFichesFraisValider();
		
		this.vue.remove(this.vue.getContentPane());
		this.vue.setContentPane(new Panel_Fiche_Valider(this.lesFichesFraisValider));
		this.vue.setJMenuBar(new Menu(this.vue));
		this.vue.revalidate();		
		
	}
}
