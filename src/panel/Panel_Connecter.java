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
	
	public Panel_Connecter(Vue maVue, String login){
		this.vue = maVue;
		this.login = login;
		//Vérification connexionSession
		
		//Déclaration
			//Label
		this.lblMessage = new JLabel("Bienvenue " + ModeleConsulter.getNom(ModeleConsulter.getId(this.login)) + " " + ModeleConsulter.getPrenom(ModeleConsulter.getId(this.login)));
		
		this.add(this.lblMessage);
	}
}
