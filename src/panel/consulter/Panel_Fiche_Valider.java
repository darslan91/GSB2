package panel.consulter;

import action.consulter.ActionConsulterDetailFicheRembourser;
import action.consulter.ActionConsulterDetailFicheValidee;
import classes.FicheFrais;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.*;

import panel.Vue;



public class Panel_Fiche_Valider extends JPanel implements FocusListener{
	
	/*ATTRIBUTS PRIVES */
		//Labels
	private JLabel lblMessage;
		//Gestion tableaux
	private JScrollPane scroll;
	private JTable table;
	private String[]title;
	private Object [][] data;
	private int i;
		//Boutons
	private JButton btnConsulter;
		
	
		//Autre
	private Vue vue;
	
	/* CONSTRUCTEUR */
	public Panel_Fiche_Valider(Vue vue, ArrayList<FicheFrais> lesFichesFraisValider){
		this.setBackground(Color.ORANGE);
		this.vue = vue;
		//GridBagLayout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;		
		//Police
		Font font = new Font("Calibri", Font.BOLD,20);
				
		//Label
		this.lblMessage = new JLabel("Consultation fiche valider :");
		this.lblMessage.setFont(font);
				
		//En-t�te du tableau 
		String []title = {"Nom", "Pr�nom", "Mois", "Montant valid�", "Date modification", "Nombre de Justificatif"};
				
		//parcours de la collection lesAvions
		data = new Object[lesFichesFraisValider.size()][6]; //le premier entre-crochet pour l'avion et le deuxieme pour le tableau
		this.i= 0; //le petit i qui parcour la liste
		
		
		/* A TERMINER */
		//foreach
		 //for()
		for(FicheFrais uneFiche : lesFichesFraisValider){
			data[this.i][0] = uneFiche.getNom();
			data[this.i][1] = uneFiche.getPrenom();
			data[this.i][2] = uneFiche.getMois();
			data[this.i][3] = uneFiche.getMontantValide();
			data[this.i][4] = uneFiche.getDateModif();
			data[this.i][5] = uneFiche.getNbJustificatifs();
			this.i = this.i+1;
		}
				
		//Instanciation du tableau titre et des cellules dans le JTable
		table = new JTable(data, title);
		this.table.addFocusListener(this);	
		//Pour pouvoir defiler si trop grand nombre d'avions par exemple
		scroll = new JScrollPane(table);
		
		//Instanciation du bouton et ajout de l'action listener au btnConsulter
		this.btnConsulter = new JButton ("Consulter");
		
		//Instanciation des listes lstOrdre et Mois
		
				
		/* AJOUT AU PANEL */
			//Label
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		c.insets = new Insets(6,6,6,6);
		this.add(this.lblMessage, c);
					
			//ScrollPane
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(6,6,6,6);
		this.add(this.scroll, c);
			
			//JButton
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		this.add(this.btnConsulter, c);
		c.fill = GridBagConstraints.EAST;		

			
	}	
	
	
	public void focusGained(FocusEvent arg0) {
		
		//Valeur de la ligne
		int val = this.table.getSelectedRow();
	
		//r�cup�ration des �l�ments � passer dans le constructeur pour pas reg�n�rer le tableau dans le actionConsulterD�tail
		Object nom = data[val][0];
		Object prenom = data[val][1];
		Object mois = data[val][2];		
	
		this.remove(this.btnConsulter);
	
		this.btnConsulter = new JButton("Consulter");
		
		//GridBagLayout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;		
		
		/* AJOUT AU PANEL */
			//Label
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		c.insets = new Insets(6,6,6,6);
		this.add(this.lblMessage, c);
					
			//ScrollPane
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(6,6,6,6);
		this.add(this.scroll, c);
			
			//JButton
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		this.add(this.btnConsulter, c);
		
		c.fill = GridBagConstraints.EAST;		

		this.btnConsulter.addActionListener(new ActionConsulterDetailFicheValidee(this.vue, nom, prenom, mois));
		
		this.revalidate();
	}


	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
	}
	

}


