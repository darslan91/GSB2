package action.validation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import panel.Vue;
import panel.recherche.Panel_Remboursement_Recherche;
import panel.recherche.Panel_Validation_Recherche;
import controleurs.Menu;
import modele.validation.ModeleRembourser;

public class ActionRembourserLaFiche implements ActionListener{

	//Attributs
		//JLabel
	private JLabel lblMessage;
		//Vue
	private Vue vue;
		//Parametre à changer
	private String mois;
	private String id;	
	
	//Constructeur
	public ActionRembourserLaFiche(Vue vue ,JLabel lblMsg, String mois, String id){
		this.lblMessage = lblMsg;
		this.vue = vue;
		this.mois = mois;
		this.id = id;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		ModeleRembourser.rembourserLaFiche(this.mois, this.id);
		
		//Action btn valider
		this.lblMessage.setText("Remboursé");
		this.lblMessage.setForeground(Color.WHITE);
		this.lblMessage.setBackground(Color.BLUE);
		this.lblMessage.setOpaque(true);
		
		//Enlever le panel
		this.vue.remove(this.vue.getContentPane());
		
		//Ajouter de la recherche
		this.vue.setContentPane(new Panel_Remboursement_Recherche(this.vue, this.lblMessage));
		
		//revalidation
		this.vue.revalidate();
	}
}
