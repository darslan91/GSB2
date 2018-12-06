package action.validation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import panel.Vue;
import panel.recherche.Panel_Validation_Recherche;

public class ActionAnnulerLaFiche implements ActionListener{
	
	//Attributs
		//JLabel
	private JLabel lblMessage;
		//Vue
	private Vue vue;

	//Constructeur
	public ActionAnnulerLaFiche(Vue vue ,JLabel lblMsg){
		this.lblMessage = lblMsg;
		this.vue = vue;
	}

	
	public void actionPerformed(ActionEvent e) {
		
		//Clicker sur le btn Annuler
		this.lblMessage.setText("Annuler");
		this.lblMessage.setForeground(Color.WHITE);
		this.lblMessage.setBackground(Color.RED);
		this.lblMessage.setOpaque(true);

		//Enlever la fenetre
		this.vue.remove(this.vue.getContentPane());

		//Remplacer par la nouvelle
		this.vue.setContentPane(new Panel_Validation_Recherche(this.vue, this.lblMessage));

		//Revalidation
		this.vue.revalidate();
	}
}
