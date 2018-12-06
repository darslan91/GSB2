package action.validation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JLabel;

import modele.validation.ModeleValider;
import panel.Vue;
import panel.recherche.Panel_Validation_Recherche;

public class ActionValiderLaFiche implements ActionListener{

	//Attributs
		//JLabel
	private JLabel lblMessage;
		//Vue
	private Vue vue;
		//Parametre � changer
	private String justificatif;
	private float montant;
	private String mois;
	private String id;
			
	//Constructeur
	public ActionValiderLaFiche(Vue vue ,JLabel lblMsg, String justificatif, float montant, String mois, String id){
		this.lblMessage = lblMsg;
		this.vue = vue;
		this.justificatif = justificatif;
		this.montant = montant;
		this.mois = mois;
		this.id = id;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		ModeleValider.validerLaFiche(this.justificatif, this.montant, this.mois, this.id);
		
		//Action btn valider
		this.lblMessage.setText("Valid�");
		this.lblMessage.setForeground(Color.WHITE);
		this.lblMessage.setBackground(Color.BLUE);
		this.lblMessage.setOpaque(true);
		
		//Enlever le panel
		this.vue.remove(this.vue.getContentPane());
		
		//Ajouter de la recherche
		this.vue.setContentPane(new Panel_Validation_Recherche(this.vue, this.lblMessage));
		
		//revalidation
		this.vue.revalidate();
	}

}
