package action.consulter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modele.consulter.ModeleConsulter;
import panel.Vue;
import panel.Vue_Detail;
import classes.LigneFraisForfait;
import classes.LigneFraisHorsForfait;

public class ActionConsulterDetailFicheValidee implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private Object mois;
	private Object nom;
	private Object prenom;
	private ArrayList<LigneFraisForfait> lesFF;
	private ArrayList<LigneFraisHorsForfait> lesFHF;
	
	
	/* CONSTRUCTEUR */
	public ActionConsulterDetailFicheValidee(Vue uneVue, Object nom, Object prenom, Object mois){
		this.vue = uneVue;
		this.mois = mois;
		this.prenom = prenom;
		this.nom = nom;
		
		this.lesFF = new ArrayList<LigneFraisForfait>();
		this.lesFHF = new ArrayList<LigneFraisHorsForfait>();
	}
	
	
	/* ACTION PERFORMED */	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		this.lesFF = ModeleConsulter.getLesFraisForfaits(this.mois, ModeleConsulter.getId(nom, prenom));
		this.lesFHF = ModeleConsulter.getLesFraisHorsForfaits(this.mois, ModeleConsulter.getId(nom, prenom));
		
		new Vue_Detail(ModeleConsulter.getId(nom, prenom), mois, ModeleConsulter.getMontantValider(mois, ModeleConsulter.getId(nom, prenom)), this.lesFF, this.lesFHF);

	}

	
}
