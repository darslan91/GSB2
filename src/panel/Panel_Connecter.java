package panel;

import javax.swing.*;

import modele.consulter.ModeleConsulter;

import controleurs.Menu;

public class Panel_Connecter extends JPanel{
	
	/* ATTRIBUTS PRIVEES */
		//Label
	private JLabel lblMessage;
	private Vue vue;
	private modele.Modele connexionSession;
	private String login;
	
	public Panel_Connecter(Vue maVue){
		this.vue = maVue;
		//Vérification connexionSession
		
		//Déclaration
			//Label
		this.lblMessage = new JLabel("Bienvenue " /*+ nom*/);
		
	
	}
}
