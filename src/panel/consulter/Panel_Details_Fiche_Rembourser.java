package panel.consulter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import modele.consulter.ModeleConsulter;
import classes.FicheFrais;
import classes.LigneFraisForfait;
import classes.LigneFraisHorsForfait;

public class Panel_Details_Fiche_Rembourser extends JPanel{
	
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
	public Panel_Details_Fiche_Rembourser(String id, Object mois, float montant, ArrayList<LigneFraisForfait> listeFF, ArrayList<LigneFraisHorsForfait> listeFHF){
		
		this.setBackground(Color.ORANGE);
		/* GRID LAYOUT */
		this.setLayout(new GridLayout(0, 1));
		
		
		/* INSTANCIATION DES LABELS, DES TABLEAUX ET AUTRE ELEMENTS */
			//Id
		this.lblId = new JLabel ("L'id du visiteur est : " + id);
			//Etat fiche
		this.lblEtat = new JLabel("Etat de la fiche : Remboursée, depuis le : " + mois);
			//Montant Valider
		this.lblMontantValider = new JLabel("Montant Validé : " + montant + " €");
			//Elements forfatiser
		this.lblElementsForfaitises = new JLabel("Eléments Forfaitiser");
			//Elements hors forfait
		this.lblElementsNonForfait = new JLabel("Eléments Hors Forfaits");
	
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
		this.scroll.setPreferredSize(new Dimension(500,150));
		
		
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
		
		/* AJOUT AU PANEL LES ELEMENTS */
		this.add(this.lblId);
		this.add(this.lblEtat);
		this.add(this.lblMontantValider);
		this.add(this.lblElementsForfaitises);
		this.add(this.scroll);
		this.add(this.lblElementsNonForfait);
		this.add(this.scroll2);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
