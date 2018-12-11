package panel.consulter;

import java.util.ArrayList;

import javax.swing.*;

import modele.consulter.ModeleConsulter;
import classes.FicheFrais;

public class Panel_Details_Fiche_Rembourser extends JFrame{
	
	/* ATTRIBUTS PRIVEES */
		//Labels
	private JLabel lblMessages;
	private JLabel lblEtat;
	private JLabel lblMontantValider;
	private JLabel lblElementsForfaitises;
	private JLabel lblElementsNonForfait;
		//Gestion du tableau
	private JScrollPane scroll;
	private JTable table;
	private String[]title;
	private Object [][] data;
	private int i;
	private ArrayList<FicheFrais> lesFichesFraisRembourser;
	
	
	
	
	/* CONSTRUCTEURS */
	public Panel_Details_Fiche_Rembourser(){
		
		//R�cup�ration de la liste par mon modele (ModeleConsulter)
		this.lesFichesFraisRembourser = ModeleConsulter.getLesFichesFraisRembourser();
		
		/* INSTANCIATION DES LABELS */
			//Etat fiche
		this.lblEtat = new JLabel("Etat de la fiche :" );
			//El�ments forfaitis�s
		this.lblElementsForfaitises = new JLabel ("El�ments forfaitis�s : ");
			//El�ments non-forfaitis�s
		this.lblElementsNonForfait = new JLabel ("El�ments non-forfaitis�s : ");
		
		
		String []title = {"Forfait Etapes", "Nombre kilom�tre", "Nuit� h�tel", "Repas restaurant", "Total"};
		data = new Object[lesFichesFraisRembourser.size()][5];
		for(FicheFrais uneFiche : lesFichesFraisRembourser){
			data[this.i][0] = uneFiche.getNom();
			data[this.i][1] = uneFiche.getPrenom();
			data[this.i][2] = uneFiche.getMois();
			data[this.i][3] = uneFiche.getMontantValide();
			data[this.i][4] = uneFiche.getDateModif();
			data[this.i][5] = uneFiche.getNbJustificatifs();
			this.i = this.i+1;
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	

}
