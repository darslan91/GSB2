package controleurs;


import java.awt.*;

import javax.swing.*;

import modele.consulter.ModeleConsulter;

import action.ActionDeconnexion;
import action.consulter.*;
import action.validation.*;
import generation.GenererPDF;
import panel.Vue;

public class Menu extends JMenuBar{
	
	//Attributs
		//Label
	private JLabel lblMessage;
		//Menu
	private JMenuBar menu;
		//Sous Menu
	private JMenu validationFicheMn;
	private JMenu consulterFicheMn;
	private JMenu pdfMn;
	private JMenu profilUtilisateurMn;
		//Item Menu
			//Validation fiche
	private JMenuItem validationFicheFrais;
	private JMenuItem remboursementFicheFrais;
			//consulter
	private JMenuItem consulterFicheValide;
	private JMenuItem consulterFicheRembourser;
			//Profil
	private JMenuItem monProfil;
	private JMenuItem deconnexion;
	
			//PDF
	private JMenuItem afficherPDF;
			//Vue
	private panel.Vue vue;
			//login
	private String login;
	
	public Menu(Vue uneVue, String login){
		
		this.login = login;
		this.vue = uneVue;
		//D�claration		
			//Sous Menu
		this.validationFicheMn = new JMenu("Validation des Fiches");
		this.consulterFicheMn = new JMenu("Consultation des Fiches");
		this.pdfMn = new JMenu("Enregistrement");
		this.profilUtilisateurMn = new JMenu("Profil Utilisateur");
			//Item Menu
				//ValidationFicheMn
		this.validationFicheFrais = new JMenuItem("Validation");
		this.validationFicheFrais.addActionListener(new ActionValidationFicheFrais(this.vue));
		this.remboursementFicheFrais = new JMenuItem("Remboursement");
		this.remboursementFicheFrais.addActionListener(new ActionRemboursementFicheFrais(this.vue));
				//consulterFicheMn
		this.consulterFicheValide = new JMenuItem("Fiche Valid�");
		this.consulterFicheValide.addActionListener(new ActionConsulterFicheValide(this.vue));
		this.consulterFicheRembourser = new JMenuItem("Fiche Rembours�");
		this.consulterFicheRembourser.addActionListener(new ActionConsulterFicheRembourser(this.vue));
				//pdfMn
		this.afficherPDF = new JMenuItem("PDF / XML");
		this.afficherPDF.addActionListener(new ActionConsulterPDF(this.vue));
				//Mon profil
		this.monProfil = new JMenuItem("Mon profil");
		this.monProfil.addActionListener(new ActionConsulterProfil(this.vue, this.login));
		this.deconnexion = new JMenuItem("Deconnexion");
		this.deconnexion.addActionListener(new ActionDeconnexion(this.vue));
		
		//Ajout
			//Item validation dans validationFicheMn
		this.validationFicheMn.add(this.validationFicheFrais);
		this.validationFicheMn.add(this.remboursementFicheFrais);
			//Item consultation dans consulterFicheMN
		this.consulterFicheMn.add(this.consulterFicheValide);
		this.consulterFicheMn.add(this.consulterFicheRembourser);
			//Item pdf dans pdfMn
		this.pdfMn.add(this.afficherPDF);
			//Item monprofil
		this.profilUtilisateurMn.add(this.monProfil);
		this.profilUtilisateurMn.add(this.deconnexion);
		
		//Affichage
		this.add(this.validationFicheMn);
		this.add(this.consulterFicheMn);
		this.add(this.pdfMn);
		this.add(this.profilUtilisateurMn);
	}
}

