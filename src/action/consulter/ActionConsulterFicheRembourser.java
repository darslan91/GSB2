package action.consulter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import modele.consulter.ModeleConsulter;
import classes.FicheFrais;
import controleurs.Menu;
import panel.Vue;
import panel.consulter.Panel_Fiche_Rembourser;

public class ActionConsulterFicheRembourser implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private ArrayList<FicheFrais> lesFichesFraisRembourser;
	
	/* CONSTRUCTEURS */
	public ActionConsulterFicheRembourser(Vue uneVue){
		this.vue = uneVue;
	}

	
	public void actionPerformed(ActionEvent e) {
		//Recupere la liste au travers du modele 
		this.lesFichesFraisRembourser = ModeleConsulter.getLesFichesFraisRembourser();
		
		this.vue.remove(this.vue.getContentPane());
		this.vue.setContentPane(new Panel_Fiche_Rembourser(this.vue, this.lesFichesFraisRembourser));
		this.vue.setJMenuBar(new Menu(this.vue));
		this.vue.revalidate();		
		
	}
}
