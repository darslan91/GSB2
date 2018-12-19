package panel.consulter;


import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


import classes.LigneFraisForfait;
import classes.LigneFraisHorsForfait;

public class Panel_Details_Fiche_Validee extends JPanel{
	
	/* ATTTRIBUTS PRIVEES */
		//Labels
	private JLabel lblMessages;
	private JLabel lblEtat;
	private JLabel lblMontantValider;
	private JLabel lblElementsForfaitises;
	private JLabel lblElementsNonForfait;
		//Tableaux
	private JScrollPane scroll;
	private Object [][] data;
	private JTable table;
	private JScrollPane scroll2;
	private Object [][] data2;
	private JTable table2;
	
		//Autre �l�ments
	private int i;
	
	
	/* CONSTRUCTEURS */
	public Panel_Details_Fiche_Validee(String id, Object mois, float montant, ArrayList<LigneFraisForfait> listeFF, ArrayList<LigneFraisHorsForfait> listeFHF){
		//COULEURS
		this.setBackground(Color.ORANGE);
		
		//GRID LAYOUT
		this.setLayout(new GridLayout(0, 1));
		
		//POLICE
		Font font = new Font("Calibri", Font.BOLD,20);
		Font font2 = new Font("Calibri", Font.BOLD,15);
		
		/* INSTANCIATIONS ET DECLARATIONS DES LABELS, TABLEAUX */
			//LABELS
		this.lblMessages = new JLabel(" FICHE VALIDER ");
		this.lblMessages.setFont(font);
		
		this.lblEtat = new JLabel("Etat de la fiche : Valid�e depuis le : " + mois);
		this.lblMontantValider = new JLabel("Montant Valid� : " + montant + " �");
		
		this.lblElementsForfaitises = new JLabel("El�ments Forfaitiser");
		this.lblElementsForfaitises.setFont(font2);
		
		this.lblElementsNonForfait = new JLabel("El�ments Hors-Forfaits");
		this.lblElementsNonForfait.setFont(font2);
		
			//TABLEAU 1
				//Ent�te
		String []title = {"Forfait Etape"," Co�t Cat�gorie V�hicule", "Kilom�tre", "Nuits", "Repas"};
		data = new Object[1][5];
		
				//Parcours un �l�ment de cette liste -> puis la quantite entrer
		data[0][0] = listeFF.get(1).getQuantite();
		data[0][1] = listeFF.get(0).getQuantite();
		data[0][2] = listeFF.get(2).getQuantite();
		data[0][3] = listeFF.get(3).getQuantite();
		data[0][4] = listeFF.get(4).getQuantite();
		
				//Ajout des �l�ments dans le tableaux -> puis le scroll pane
		this.table = new JTable(data, title);
		this.scroll = new JScrollPane(this.table);
		
			//TABLEAU 2
				//Ent�te
		String []title2 = {"Date"," Libell� ", " Montant ", " Justificatif "};
		data2 = new Object[listeFHF.size()][4];
		
			//Parcours un �l�ment de cette liste -> puis la quantite entrer
		this.i = 0;
		for(LigneFraisHorsForfait uneLigneFHF : listeFHF){
			data2[this.i][0] = uneLigneFHF.getDate();
			data2[this.i][1] = uneLigneFHF.getLibelle();
			data2[this.i][2] = uneLigneFHF.getMontant();
			data2[this.i][3] = uneLigneFHF.getLien();
			this.i = this.i+1;
		}
			//Ajout des �l�ments dans le tableaux -> puis le scroll pane
		this.table2 = new JTable(data2, title2);
		this.scroll2 = new JScrollPane(this.table2);
		
		
		/* AJOUTS DES ELEMENTS AU PANEL */
		this.add(this.lblMessages);
		this.add(this.lblEtat);
		this.add(this.lblMontantValider);
		this.add(this.lblElementsForfaitises);
		this.add(this.scroll);
		this.add(this.lblElementsNonForfait);
		this.add(this.scroll2);

		
	}
	

}
