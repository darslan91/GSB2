package panel.recherche;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import panel.Vue;
import action.validation.ActionRemboursementRechercheFicheFrais;
import action.validation.ActionValidationRechercheFicheFrais;
import modele.validation.ModeleRembourser;

public class Panel_Remboursement_Recherche extends JPanel implements ItemListener{
	
	//Attributs privé
		//Label
	private JLabel lblMessage;
	private JLabel lblListePersonne;
	private JLabel lblListMois;
	private JLabel lblErreur;
	private JLabel lblMsgVal;
	
		//Liste
	private JComboBox lstPersonne;
	private JComboBox lstMois;
	
		//Button
	private JButton btnValider;
	
		//Vue
	private Vue vue;
	
	public Panel_Remboursement_Recherche(Vue vue, JLabel lblMsg) {
		//Vue
		this.vue = vue;
		this.lblMsgVal = lblMsg;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
		//Instanciation
			//label
		this.lblMessage = new JLabel("Recherche Utilisateur");
		this.lblListePersonne = new JLabel("Visiteur");
		this.lblListMois = new JLabel("Mois");
		this.lblErreur = new JLabel(" ");
			
			//List
		//Vérification pour éviter le nul pointer
		if(ModeleRembourser.getNbMoisVA() == 0) {
				//Déclaration lstMois
			this.lstMois = new JComboBox();
			String[] tab = new String[1];
			tab[0] = "Aucune Fiche à l'état VA";
			this.lstMois.addItem(tab[0]);
			
			//Déclaration lstPersonne
			this.lstPersonne = new JComboBox();
			String[] tab2 = new String[1];
			tab2[0] = "Aucune Personne";
			this.lstPersonne.addItem(tab2[0]);
			
			//Button
			this.btnValider = new JButton("Valider");
			String mois = this.lstMois.getSelectedItem().toString();
			String nom = this.lstPersonne.getSelectedItem().toString();
			this.btnValider.addActionListener(new ActionValidationRechercheFicheFrais(this.vue, mois, nom));
		}
		else {
			//Déclaration lstMois
			this.lstMois = new JComboBox(ModeleRembourser.getDateFicheFraisVA());
			this.lstMois.addItemListener(this);
		
			//Déclaration lstPersonne
			this.lstPersonne = new JComboBox(ModeleRembourser.getTabNomVisiteurVA(this.lstMois.getSelectedItem().toString(), ModeleRembourser.getNbVisiteurMoisVA(this.lstMois.getSelectedItem().toString())));
			this.lstPersonne.addItemListener(this);
			
			//Button
			this.btnValider = new JButton("Valider");
			String mois = this.lstMois.getSelectedItem().toString();
			String nom = this.lstPersonne.getSelectedItem().toString();
			this.btnValider.addActionListener(new ActionRemboursementRechercheFicheFrais(this.vue, mois, nom));
		
			gc.gridx = 0;
			gc.gridy = 4;
			gc.gridwidth = 3;
			this.add(this.btnValider, gc);
		}	
		
		//Contrainte
		
			//Ajout
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		this.add(this.lblMessage, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		this.add(this.lblListMois, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 2;
		this.add(this.lstMois, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		this.add(this.lblListePersonne, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridwidth = 2;
		this.add(this.lstPersonne, gc);
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 3;
		this.add(this.lblErreur, gc);
		
		gc.gridx = 0;
		gc.gridy = 5;
		gc.gridwidth = 3;
		this.add(this.lblMsgVal, gc);
	}
	
	public Panel_Remboursement_Recherche(Vue vue) {
		//Vue
		this.vue = vue;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
		//Instanciation
			//label
		this.lblMessage = new JLabel("Recherche Utilisateur");
		this.lblListePersonne = new JLabel("Visiteur");
		this.lblListMois = new JLabel("Mois");
		this.lblErreur = new JLabel(" ");
		lblMsgVal = new JLabel(" ");
		
			//List
		//Vérification pour éviter le nul pointer
		if(ModeleRembourser.getNbMoisVA() == 0) {
				//Déclaration lstMois
			this.lstMois = new JComboBox();
			String[] tab = new String[1];
			tab[0] = "Aucune Fiche à l'état VA";
			this.lstMois.addItem(tab[0]);
			
			//Déclaration lstPersonne
			this.lstPersonne = new JComboBox();
			String[] tab2 = new String[1];
			tab2[0] = "Aucune Personne";
			this.lstPersonne.addItem(tab2[0]);
			
		}
		else {
			//Déclaration lstMois
			this.lstMois = new JComboBox(ModeleRembourser.getDateFicheFraisVA());
			this.lstMois.addItemListener(this);
		
			//Déclaration lstPersonne
			this.lstPersonne = new JComboBox(ModeleRembourser.getTabNomVisiteurVA(this.lstMois.getSelectedItem().toString(), ModeleRembourser.getNbVisiteurMoisVA(this.lstMois.getSelectedItem().toString())));
			this.lstPersonne.addItemListener(this);
			
			
			//Button
			this.btnValider = new JButton("Valider");
			String mois = this.lstMois.getSelectedItem().toString();
			String nom = this.lstPersonne.getSelectedItem().toString();
			this.btnValider.addActionListener(new ActionRemboursementRechercheFicheFrais(this.vue, this.lblMsgVal,  mois, nom));
			
			gc.gridx = 0;
			gc.gridy = 4;
			gc.gridwidth = 3;
			this.add(this.btnValider, gc);
		}
		
		//Contrainte
		
			//Ajout
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		this.add(this.lblMessage, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		this.add(this.lblListMois, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 2;
		this.add(this.lstMois, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 1;
		this.add(this.lblListePersonne, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridwidth = 2;
		this.add(this.lstPersonne, gc);
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 3;
		this.add(this.lblErreur, gc);
		
	}	
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub	
		
		if(e.getSource() == this.lstMois) {
				
				//Enlever le panel
			this.remove(this.btnValider);
				//Enlever l'écoute sinon ca boucle sur l'autre puis erreur
			this.lstPersonne.removeItemListener(this);
				//enlever les item
			this.lstPersonne.removeAllItems();	
			
				//Element pour remplacer les items
					//taille
			int taille = ModeleRembourser.getNbVisiteurMoisVA(this.lstMois.getSelectedItem().toString());
					//mois
			String mois = this.lstMois.getSelectedItem().toString();
					//tableau
			String tab[] = new String[ModeleRembourser.getNbVisiteurMoisVA(this.lstMois.getSelectedItem().toString())];
			tab = ModeleRembourser.getTabNomVisiteurVA(this.lstMois.getSelectedItem().toString(), ModeleRembourser.getNbVisiteurMoisVA(this.lstMois.getSelectedItem().toString()));
				//nouveau item ajout
			for(int i = 0; i < taille; i++) {
				this.lstPersonne.addItem(tab[i]);
			}
			this.lstPersonne.addItemListener(this);
			
				//Contrainte
			GridBagConstraints gc = new GridBagConstraints();
			this.btnValider = new JButton("Valider");
			gc.fill = GridBagConstraints.BOTH;
			gc.gridx = 0;
			gc.gridy = 4;
			gc.gridwidth = 3;
			this.add(this.btnValider, gc);
				//Déclaration pour le bouton
			String nom = this.lstPersonne.getSelectedItem().toString();
			
				//Choix Constructeur
			if(this.lblMsgVal.equals(" ")) {
				this.btnValider.addActionListener(new ActionRemboursementRechercheFicheFrais(this.vue, this.lblMsgVal,  mois, nom));
			}
			else {
				this.btnValider.addActionListener(new ActionRemboursementRechercheFicheFrais(this.vue, mois, nom));
			}
			
			this.revalidate();
		}
		
		if(e.getSource() == this.lstPersonne) {
				
				//Enlever le panel
			this.remove(this.btnValider);
				//enlever les intemlistener
			this.lstMois.removeItemListener(this);
			this.lstPersonne.removeItemListener(this);
			
				//Selection des nouveau element à envoyer au bouton en parametre
			String mois = this.lstMois.getSelectedItem().toString();
			String nom = this.lstPersonne.getSelectedItem().toString();
			
				//Contrainte
			GridBagConstraints gc = new GridBagConstraints();
			this.btnValider = new JButton("Valider");
			gc.fill = GridBagConstraints.BOTH;
			gc.gridx = 0;
			gc.gridy = 4;
			gc.gridwidth = 3;
			this.add(this.btnValider, gc);
			
				//Choix Constructeur
			if(this.lblMsgVal.equals(" ")) {
				this.btnValider.addActionListener(new ActionRemboursementRechercheFicheFrais(this.vue, this.lblMsgVal,  mois, nom));
			}
			else {
				this.btnValider.addActionListener(new ActionRemboursementRechercheFicheFrais(this.vue, mois, nom));
			}
			
				//Ajout des itemListener
			this.lstMois.addItemListener(this);
			this.lstPersonne.addItemListener(this);
			
			this.revalidate();
		}
	}
}
