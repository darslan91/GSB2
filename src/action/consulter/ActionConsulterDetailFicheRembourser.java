package action.consulter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import panel.Vue;
import panel.Vue_Detail;
import panel.consulter.Panel_Details_Fiche_Rembourser;
import panel.consulter.Panel_Fiche_En_Cours_De_Validation;
import modele.consulter.ModeleConsulter;
import classes.FicheFrais;
import classes.LigneFraisForfait;
import classes.LigneFraisHorsForfait;
import controleurs.Menu;

public class ActionConsulterDetailFicheRembourser implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private Object mois;
	private Object nom;
	private Object prenom;
	private ArrayList<FicheFrais> lesFichesFraisRembourser;
	private ArrayList<LigneFraisForfait> lesFF;
	private ArrayList<LigneFraisHorsForfait> lesFHF;

	/*private JTable table;
	private String[]title;
	private Object [][] data;
	private int i;
	*/
	
	/* CONSTRUCTEURS */
	public ActionConsulterDetailFicheRembourser(Vue uneVue, Object nom, Object prenom, Object mois){
		this.vue = uneVue;
		this.mois = mois;
		this.prenom = prenom;
		this.nom = nom;
		
		this.lesFF = new ArrayList<LigneFraisForfait>();
		this.lesFHF = new ArrayList<LigneFraisHorsForfait>();
		
		//récupération indice tableau (créer un nv tableau pour récupération)
		//this.lesFichesFraisRembourser = ModeleConsulter.getLesFichesFraisRembourser();
	}

	
	public void actionPerformed(ActionEvent e) {
		
//		Vue uneVue = new Vue();	
		//System.out.println(this.ligne);
		//String []title = {"Nom", "Prénom", "Mois", "Montant validé", "Date modification", "Nombre de Justificatif"};
		/*Object nom = null; 
		Object prenom = null; 
		Object mois = null;*/
		//this.i = 0;
	//	data = new Object[lesFichesFraisRembourser.size()][6];
	//	for(FicheFrais uneFiche : lesFichesFraisRembourser){
			
	//		data[this.i][0] = uneFiche.getNom();
			//nom = data[this.i][0];	
	//		data[this.i][1] = uneFiche.getPrenom();
			//prenom = data[this.i][1];
	//		data[this.i][2] = uneFiche.getMois();
			//mois = data[this.i][2];
	//		data[this.i][3] = uneFiche.getMontantValide();
	//		data[this.i][4] = uneFiche.getDateModif();
	//		data[this.i][5] = uneFiche.getNbJustificatifs();
	//		this.i = this.i+1;
			/*String id = ModeleConsulter.getId(data[this.i][0] , data[this.i][1] );
			float montant = ModeleConsulter.getMontantValider(data[this.i][2], id);
			Object mois = data[this.i][2];*/
			

		//}
		
		
		//RECUPERATION DES VALEURS
		/*System.out.println(this.i);
		 
		nom = data[this.i-1][0];
		prenom = data[this.i-1][1];
		mois = data[this.i-1][2];*/
		
		/*table = new JTable(data, title);
		//Nom
		System.out.println(data[this.ligne][0]);
		//Prénom
		System.out.println(data[this.ligne][1]);
		//Mois
		System.out.println(data[this.ligne][2]);
		//Espace
		System.out.println("");*/
		
		//Recuperation de l'id du visiteur grace a son nom et prenom (Object et Object)
//		String id = ModeleConsulter.getId(data[this.i][0] , data[this.i][1] );
		
		//Recuperation simplement du montant valider (Object et String)
//		float montant = ModeleConsulter.getMontantValider(data[this.i][2], id);
		
		//Recuperation du mois à partir du tableau
//		Object mois = data[this.i][2];
		
		/*this.vue.remove(this.vue.getContentPane());
		this.vue.setContentPane(new Vue_Detail(ModeleConsulter.getId(nom, prenom), mois, ModeleConsulter.getMontantValider(mois, ModeleConsulter.getId(nom, prenom))));
		this.vue.setJMenuBar(new Menu(this.vue));
		this.vue.revalidate();	*/
		this.lesFF = ModeleConsulter.getLesFraisForfaits(this.mois, ModeleConsulter.getId(nom, prenom));
		this.lesFHF = ModeleConsulter.getLesFraisHorsForfaits(this.mois, ModeleConsulter.getId(nom, prenom));
		
		new Vue_Detail(ModeleConsulter.getId(nom, prenom), mois, ModeleConsulter.getMontantValider(mois, ModeleConsulter.getId(nom, prenom)), this.lesFF, this.lesFHF, "Remboursée");
		
		
		
	}
}
