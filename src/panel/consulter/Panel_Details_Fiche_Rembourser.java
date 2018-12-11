package panel.consulter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.*;

import modele.consulter.ModeleConsulter;
import classes.FicheFrais;

public class Panel_Details_Fiche_Rembourser extends JLabel{
	
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
	
		//ELEMENTS TESTS
	private JLabel lblId;
	
	
	
	
	/* CONSTRUCTEURS */
	public Panel_Details_Fiche_Rembourser(String id, Object mois, float montant){
		
		//GridBagLayout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
				
		//R�cup�ration de la liste par mon modele (ModeleConsulter)
		this.lesFichesFraisRembourser = ModeleConsulter.getLesFichesFraisRembourser();
		
		/* INSTANCIATION DES LABELS */
			//Id
		this.lblId = new JLabel ("L'id du visiteur est : " + id);
			//Etat fiche
		this.lblEtat = new JLabel("Etat de la fiche : Rembours�e, de puis le : " + mois);
			//Montant Valider
		this.lblMontantValider = new JLabel("Montant Valid� : " + montant);
			//El�ments forfaitis�s
		//this.lblElementsForfaitises = new JLabel ("El�ments forfaitis�s : ");
			//El�ments non-forfaitis�s
		//this.lblElementsNonForfait = new JLabel ("El�ments non-forfaitis�s : ");
		
		
		/*AJOUT DES PANELS */
		c.gridx = 0;
		c.gridy = 0;
		this.add(lblId, c);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(lblId, c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(lblEtat, c);
		
		/*String []title = {"Forfait Etapes", "Nombre kilom�tre", "Nuit� h�tel", "Repas restaurant", "Total"};
		data = new Object[lesFichesFraisRembourser.size()][5];
		for(FicheFrais uneFiche : lesFichesFraisRembourser){
			data[this.i][0] = uneFiche.getNom();
			data[this.i][1] = uneFiche.getPrenom();
			data[this.i][2] = uneFiche.getMois();
			data[this.i][3] = uneFiche.getMontantValide();
			data[this.i][4] = uneFiche.getDateModif();
			data[this.i][5] = uneFiche.getNbJustificatifs();
			this.i = this.i+1;
		}*/
		
		
		
		
		
		
	}
	
	
	
	
	
	

}
