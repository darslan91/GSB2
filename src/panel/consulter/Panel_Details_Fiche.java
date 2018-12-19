package panel.consulter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;





import javax.swing.*;

import modele.consulter.ModeleConsulter;
import classes.FicheFrais;
import classes.LigneFraisForfait;
import classes.LigneFraisHorsForfait;

public class Panel_Details_Fiche extends JPanel{
	
	/* ATTRIBUTS PRIVEES */
		//Labels
	private JLabel lblMessages;
	private JLabel lblEtat;
	private JLabel lblMontantValider;
	private JLabel lblElementsForfaitises;
	private JLabel lblElementsNonForfait;
		//Gestion du tableau
	private JScrollPane scroll;
	private JScrollPane scroll2;
	private JTable table;
	private JTable table2;
	private String[]title;
	private Object [][] data;
	private String[]title2;
	private Object [][] data2;
	private int i;
	private ArrayList<FicheFrais> lesFichesFraisRembourser;
	
		//ELEMENTS TESTS
	private JLabel lblId;
	
	
	
	
	/* CONSTRUCTEURS */
	public Panel_Details_Fiche(String id, Object mois, float montant, ArrayList<LigneFraisForfait> listeFF, ArrayList<LigneFraisHorsForfait> listeFHF , String etat){
		
		this.setBackground(Color.ORANGE);
		/* GRID LAYOUT */
		//this.setLayout(new GridLayout(0, 1));
		Font font = new Font("Calibri", Font.BOLD,20);
		Font fontTitre = new Font("Calibri", Font.BOLD, 40);
		
		/* GRID BAG LAYOUT */ 
		this.setLayout(new GridBagLayout());
		
		
		/* INSTANCIATION DES LABELS, DES TABLEAUX ET AUTRE ELEMENTS */
		this.lblMessages = new JLabel("FICHE FRAIS", JLabel.CENTER);
		this.lblMessages.setFont(fontTitre);
			//Id
		this.lblId = new JLabel ("L'id du visiteur est : " + id);
			//Etat fiche
		this.lblEtat = new JLabel("Etat de la fiche : "+ etat +", depuis le : " + mois);
			//Montant Valider
		this.lblMontantValider = new JLabel("Montant Validé : " + montant + " €");
			//Elements forfatiser
		this.lblElementsForfaitises = new JLabel("Eléments Forfaitiser");
		this.lblElementsForfaitises.setFont(font);
			//Elements hors forfait
		this.lblElementsNonForfait = new JLabel("Eléments Hors Forfaits");
		this.lblElementsNonForfait.setFont(font);
		
			//Tabeleau avec les titres des colonnes 
		String []title = {"Forfait Etape"," Coût Catégorie Véhicule", "Kilomètre", "Nuits", "Repas"};
		data = new Object[1][5];
		
		
			//Parcours de la ligne
		data[0][0] = listeFF.get(1).getQuantite();
		data[0][1] = listeFF.get(0).getQuantite();
		data[0][2] = listeFF.get(2).getQuantite();
		data[0][3] = listeFF.get(3).getQuantite();
		data[0][4] = listeFF.get(4).getQuantite();
		
	
		this.table = new JTable(data, title);

		this.scroll = new JScrollPane(this.table);
		this.scroll.setPreferredSize(new Dimension(700,39));
		
		
			//tableau hors forfaits 
		String []title2 = {"Date"," Libellé ", " Montant ", " Justificatif "};
			data2 = new Object[listeFHF.size()][4];
			this.i = 0;	
		for(LigneFraisHorsForfait uneLigneFHF : listeFHF){
				data2[this.i][0] = uneLigneFHF.getDate();
				data2[this.i][1] = uneLigneFHF.getLibelle();
				data2[this.i][2] = uneLigneFHF.getMontant();
				data2[this.i][3] = uneLigneFHF.getLien();
				this.i = this.i+1;
			}
		
		
		
		this.table2 = new JTable(data2, title2);
		this.scroll2 = new JScrollPane(this.table2);
		this.scroll2.setPreferredSize(new Dimension(700,70));
		/* AJOUT AU PANEL LES ELEMENTS */
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(3,3,3,3);
		this.add(this.lblMessages, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(3,3,3,3);
		this.add(this.lblId, c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(this.lblEtat, c);
		
		c.gridx = 0;
		c.gridy = 3;
		this.add(this.lblMontantValider, c);
		
		c.gridx = 0;
		c.gridy = 4;
		this.add(this.lblElementsForfaitises, c);
		
		c.gridx = 0;
		c.gridy = 5;
		this.add(this.scroll, c);
		
		c.gridx = 0;
		c.gridy = 6;
		this.add(this.lblElementsNonForfait, c);
		
		c.gridx = 0;
		c.gridy = 7;
		this.add(this.scroll2, c);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//GridBagLayout
		/*this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();*/
		//c.fill = GridBagConstraints.BOTH;
				
		//Récupération de la liste par mon modele (ModeleConsulter)
		//this.lesFichesFraisRembourser = ModeleConsulter.getLesFichesFraisRembourser();
		
		
		
		//this.i  = 0;
		//for(LigneFraisForfait uneFiche : listeFF){
		//this.i = this.i+1;
			
		
		//Pour améliorer le tableau. 
		//this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//this.table.setPreferredScrollableViewportSize(new Dimension(12000,160));
		
		
		
		System.out.println(listeFF.get(0).getQuantite());
		
			//Eléments forfaitisés
		//this.lblElementsForfaitises = new JLabel ("Eléments forfaitisés : ");
			//Eléments non-forfaitisés
		//this.lblElementsNonForfait = new JLabel ("Eléments non-forfaitisés : ");
		
		
		/*AJOUT DES PANELS */
		/*c.gridx = 0;
		c.gridy = 0;*/
		
		
		/*c.gridx = 0;
		c.gridy = 1;*/
		
		
		/*c.gridx = 0;
		c.gridy = 2;*/
		
		
		/*c.gridx = 0;
		c.gridy = 3;*/
		//c.gridwidth = 3;
		//c.gridheight = 5;
		
		
		/*String []title = {"Forfait Etapes", "Nombre kilomètre", "Nuité hôtel", "Repas restaurant", "Total"};
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
