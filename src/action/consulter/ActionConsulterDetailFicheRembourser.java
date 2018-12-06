package action.consulter;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import panel.Vue;
import modele.consulter.ModeleConsulter;
import classes.FicheFrais;
import controleurs.Menu;

public class ActionConsulterDetailFicheRembourser implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private int ligne;
	private ArrayList<FicheFrais> lesFichesFraisRembourser;

	private JTable table;
	private String[]title;
	private Object [][] data;
	private int i;
	
	
	
	/* CONSTRUCTEURS */
	public ActionConsulterDetailFicheRembourser(Vue uneVue, int ligne){
		this.vue = uneVue;
		this.ligne = ligne;
		//récupération indice tableau (créer un nv tableau pour récupération)
		this.lesFichesFraisRembourser = ModeleConsulter.getLesFichesFraisRembourser();
	}

	
	public void actionPerformed(ActionEvent e) {
		
//		Vue uneVue = new Vue();	
		//System.out.println(this.ligne);
				
		
		
		String []title = {"Nom", "Prénom", "Mois", "Montant validé", "Date modification", "Nombre de Justificatif"};
		data = new Object[lesFichesFraisRembourser.size()][6];
		for(FicheFrais uneFiche : lesFichesFraisRembourser){
			data[this.i][0] = uneFiche.getNom();
			data[this.i][1] = uneFiche.getPrenom();
			data[this.i][2] = uneFiche.getMois();
			data[this.i][3] = uneFiche.getMontantValide();
			data[this.i][4] = uneFiche.getDateModif();
			data[this.i][5] = uneFiche.getNbJustificatifs();
			this.i = this.i+1;
		}
		
		table = new JTable(data, title);
		
		//Nom
		System.out.println(data[this.ligne][0]);
		
		//Prénom
		System.out.println(data[this.ligne][1]);
		
		//Mois
		System.out.println(data[this.ligne][2]);
	}
}
